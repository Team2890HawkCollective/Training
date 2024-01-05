package frc.robot.subsystems;

import java.time.format.ResolverStyle;

//import javax.lang.model.util.ElementScanner14;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.networktables.DoubleEntry;
import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.networktables.GenericSubscriber;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.motorcontrol.MotorController;
//import edu.wpi.first.wpilibj.motorcontrol.Victor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.networktables.NetworkTableEntry;

public class DriveTrain extends SubsystemBase 
{
    
  private static AHRS gyro = new AHRS();
  
private static CANSparkMax frontLeftSparkMax = new CANSparkMax(Constants.MOTOR_FRONT_LEFT, MotorType.kBrushless);
  private static CANSparkMax frontRightSparkMax = new CANSparkMax(Constants.MOTOR_FRONT_RIGHT, MotorType.kBrushless);
  private static CANSparkMax backLeftSparkMax = new CANSparkMax(Constants.MOTOR_BACK_LEFT, MotorType.kBrushless);
  private static CANSparkMax backRightSparkMax = new CANSparkMax(Constants.MOTOR_BACK_RIGHT, MotorType.kBrushless);


  private static double[] motorCoefficients = {Constants.FRONT_LEFT_MOTOR_COEFF, Constants.FRONT_RIGHT_MOTOR_COEFF, Constants.BACK_LEFT_MOTOR_COEFF, Constants.BACK_RIGHT_MOTOR_COEFF};
  
  
  private static XboxController driverController = new XboxController(Constants.DRIVER_XBOX_CONTROLLER_PORT);

  private static double xInput;
  private static double yInput;
  private static double rInput=0;

  private static double accZ;
  private static double accY;
  private static double accX;
  private static double roll;
  private static double pitch;
  private static double yaw;

  public static double dynamicPosDeadspace = frc.robot.Constants.DEADSPACE_POSITIVE;
  public static double dynamicNegDeadspace = frc.robot.Constants.DEADSPACE_NEGATIVE;
  public static double dynamicSpeed = frc.robot.Constants.SPEED_MOD;
  
  private static boolean brakeBooleanToggle = false;

  private static int[] motorPolarity = {Constants.FRONT_LEFT_POLARITY, Constants.FRONT_RIGHT_POLARITY, Constants.BACK_LEFT_POLARITY, Constants.BACK_RIGHT_POLARITY};

  public static double deadShuffle=Constants.DEADSPACE_POSITIVE;
  public static double speedShuffle=Constants.SPEED_MOD;

  public static ShuffleboardTab tab = Shuffleboard.getTab("Jacoby");

  

public static int counter=0;

  public static void updateShuffleboard()
  {
    if (counter==0){
    SmartDashboard.putNumber("frontLeftMotorCoeff", Constants.FRONT_LEFT_MOTOR_COEFF);
    SmartDashboard.putNumber("frontRightMotorCoeff", Constants.FRONT_LEFT_MOTOR_COEFF);
    SmartDashboard.putNumber("backLeftMotorCoeff", Constants.BACK_LEFT_MOTOR_COEFF);
    SmartDashboard.putNumber("backRightMotorCoeff", Constants.BACK_RIGHT_MOTOR_COEFF);
  
    SmartDashboard.putNumber("frontLeftMotorPolarity", motorPolarity[0]);
    SmartDashboard.putNumber("frontRightMotorPolarity", motorPolarity[1]);
    SmartDashboard.putNumber("backLeftMotorPolarity", motorPolarity[2]);
    SmartDashboard.putNumber("backRightMotorPolarity", motorPolarity[3]);
  
    SmartDashboard.putNumber("deadband", dynamicPosDeadspace);
    SmartDashboard.putNumber("speed", dynamicSpeed);

    gyro.resetDisplacement();
    SmartDashboard.putNumber("accZ", accZ);
    SmartDashboard.putNumber("accX", accX);
    SmartDashboard.putNumber("accY", accY);
    SmartDashboard.putNumber("roll", roll);
    SmartDashboard.putNumber("yaw", yaw);
    SmartDashboard.putNumber("pitch", pitch);
    counter++;
    }
          
    SmartDashboard.putNumber("frontRightMotorPolarityUpdate", motorPolarity[1]);
 
    motorCoefficients[0] = SmartDashboard.getNumber("frontLeftMotorCoeff", Constants.FRONT_LEFT_MOTOR_COEFF);
    motorCoefficients[1] = SmartDashboard.getNumber("frontRightMotorCoeff", Constants.FRONT_LEFT_MOTOR_COEFF);
    motorCoefficients[2] = SmartDashboard.getNumber("backLeftMotorCoeff", Constants.BACK_LEFT_MOTOR_COEFF);
    motorCoefficients[3] = SmartDashboard.getNumber("backRightMotorCoeff", Constants.BACK_RIGHT_MOTOR_COEFF);


    if (SmartDashboard.getNumber("frontLeftMotorPolarity", Constants.FRONT_LEFT_POLARITY)>0)
    {
      motorPolarity[0] = 1;
    }
    else 
    {
      motorPolarity[0] = -1;
    }
//----------------------------------
    if (SmartDashboard.getNumber("frontRightMotorPolarity", Constants.FRONT_RIGHT_POLARITY)>0)
    {
        motorPolarity[1] = 1;
    }
    else
    {
      motorPolarity[1] = -1;
    }
//----------------------------------
    if (SmartDashboard.getNumber("backLeftMotorPolarity", Constants.BACK_LEFT_POLARITY)>0)
    {
      motorPolarity[2] = 1;
    }
    else
    {
      motorPolarity[2] = -1;
    }
//----------------------------------
    if (SmartDashboard.getNumber("backRightMotorPolarity", Constants.BACK_RIGHT_POLARITY)>0)
    {
      motorPolarity[3] = 1;
    }
    else
    {
      motorPolarity[3] = -1;
    }

    deadShuffle = SmartDashboard.getNumber("deadband", dynamicPosDeadspace);
    speedShuffle = SmartDashboard.getNumber("space", dynamicSpeed);

    getAccX();
    getAccY();
    getAccZ();
    getPitch();
    getRoll();
    getYaw();
  }

  public static double getAccZ()
  {
    accZ = gyro.getRawAccelZ();

    return accZ;
  }
  public static double getAccY()
  {
    accY = gyro.getRawAccelY();

    return accY;
  }
  public static double getAccX()
  {
    accY = gyro.getRawAccelX();

    return accX;
  }
  public static double getPitch()
  {
    pitch = gyro.getPitch();

    return pitch;
  }
  public static double getRoll()
  {
    roll = gyro.getRoll();

    return roll;
  }
  public static double getYaw()
  {
    yaw = gyro.getYaw();

    return yaw;
  }

  public static void brakeModeToggle()
  {
    if(driverController.getBButtonReleased()){
      brakeBooleanToggle = !brakeBooleanToggle;
    }
    if(brakeBooleanToggle){
      frontLeftSparkMax.setIdleMode(CANSparkMax.IdleMode.kBrake);
      backLeftSparkMax.setIdleMode(CANSparkMax.IdleMode.kBrake);
      frontRightSparkMax.setIdleMode(CANSparkMax.IdleMode.kBrake);
      backRightSparkMax.setIdleMode(CANSparkMax.IdleMode.kBrake);
    }
    else{
      frontLeftSparkMax.setIdleMode(CANSparkMax.IdleMode.kCoast);
      backLeftSparkMax.setIdleMode(CANSparkMax.IdleMode.kCoast);
      frontRightSparkMax.setIdleMode(CANSparkMax.IdleMode.kCoast);
      backRightSparkMax.setIdleMode(CANSparkMax.IdleMode.kCoast);
    }
 }

 public static void stopMotors()
 {
    frontLeftSparkMax.set(0);
    frontRightSparkMax.set(0);
    backLeftSparkMax.set(0);
    backRightSparkMax.set(0);
 }

 public static void driveForward(){
    frontLeftSparkMax.set(dynamicSpeed*motorPolarity[0]*motorCoefficients[0]);
    frontRightSparkMax.set(dynamicSpeed*motorPolarity[1]*motorCoefficients[1]);
    backLeftSparkMax.set(dynamicSpeed*motorPolarity[2]*motorCoefficients[2]);
    backRightSparkMax.set(dynamicSpeed*motorPolarity[3]*motorCoefficients[3]);
  }
  public static void driveBackward(){
    frontLeftSparkMax.set(dynamicSpeed*motorPolarity[0]*Constants.POLARITY_SWAP*motorCoefficients[0]);
    frontRightSparkMax.set(dynamicSpeed*motorPolarity[1]*Constants.POLARITY_SWAP*motorCoefficients[1]);
    backLeftSparkMax.set(dynamicSpeed*motorPolarity[2]*Constants.POLARITY_SWAP*motorCoefficients[2]);
    backRightSparkMax.set(dynamicSpeed*motorPolarity[3]*Constants.POLARITY_SWAP*motorCoefficients[3]);
  }
  public static void driveLeft(){
    frontLeftSparkMax.set(dynamicSpeed*motorPolarity[0]*motorCoefficients[0]);
    frontRightSparkMax.set(dynamicSpeed*motorPolarity[1]*Constants.POLARITY_SWAP*motorCoefficients[1]);
    backLeftSparkMax.set(dynamicSpeed*motorPolarity[2]*Constants.POLARITY_SWAP*motorCoefficients[2]);
    backRightSparkMax.set(dynamicSpeed*motorPolarity[3]*motorCoefficients[3]);
  }
  public static void driveRight(){
    frontLeftSparkMax.set(dynamicSpeed*motorPolarity[0]*Constants.POLARITY_SWAP*motorCoefficients[0]);
    frontRightSparkMax.set(dynamicSpeed*motorPolarity[1]*motorCoefficients[1]);
    backLeftSparkMax.set(dynamicSpeed*motorPolarity[2]*motorCoefficients[2]);
    backRightSparkMax.set(dynamicSpeed*motorPolarity[3]*Constants.POLARITY_SWAP*motorCoefficients[3]);
  }
  public static void driveAngleRight(){
    frontLeftSparkMax.set(dynamicSpeed*motorPolarity[0]*motorCoefficients[0]);
    frontRightSparkMax.set(0);
    backLeftSparkMax.set(0);
    backRightSparkMax.set(dynamicSpeed*motorPolarity[3]*motorCoefficients[3]);
  }
  public static void driveAngleLeft(){
    frontLeftSparkMax.set(0);
    frontRightSparkMax.set(dynamicSpeed*motorPolarity[1]*motorCoefficients[1]);
    backLeftSparkMax.set(dynamicSpeed*motorPolarity[2]*motorCoefficients[2]);
    backRightSparkMax.set(0);
  }
  public static void driveAngleRightBack(){
    frontLeftSparkMax.set(0);
    frontRightSparkMax.set(dynamicSpeed*motorPolarity[0]*Constants.POLARITY_SWAP*motorCoefficients[0]);
    backLeftSparkMax.set(dynamicSpeed*motorPolarity[2]*Constants.POLARITY_SWAP*motorCoefficients[2]);
    backRightSparkMax.set(0);
  }
  public static void driveAngleLeftBack(){
    frontLeftSparkMax.set(dynamicSpeed*motorPolarity[0]*Constants.POLARITY_SWAP*motorCoefficients[0]);
    frontRightSparkMax.set(0);
    backLeftSparkMax.set(0);
    backRightSparkMax.set(dynamicSpeed*motorPolarity[3]*Constants.POLARITY_SWAP*motorCoefficients[3]);
  }
  public static void twist(int twistput)
  {
    if (rInput>0)
    {
      frontLeftSparkMax.set(dynamicSpeed*motorPolarity[0]*motorCoefficients[0]);
      frontRightSparkMax.set(dynamicSpeed*motorPolarity[1]*motorCoefficients[1]*Constants.POLARITY_SWAP);
      backLeftSparkMax.set(dynamicSpeed*motorPolarity[2]*motorCoefficients[2]);
      backRightSparkMax.set(dynamicSpeed*motorPolarity[3]*motorCoefficients[3]*Constants.POLARITY_SWAP);
    }
    else if (rInput<0)
    {
      frontLeftSparkMax.set(dynamicSpeed*motorPolarity[0]*motorCoefficients[0]*Constants.POLARITY_SWAP);
      frontRightSparkMax.set(dynamicSpeed*motorPolarity[1]*motorCoefficients[1]);
      backLeftSparkMax.set(dynamicSpeed*motorPolarity[2]*motorCoefficients[2]*Constants.POLARITY_SWAP);
      backRightSparkMax.set(dynamicSpeed*motorPolarity[3]*motorCoefficients[3]);
    }
    else if (twistput>0)
    {
      frontLeftSparkMax.set(dynamicSpeed*motorPolarity[0]*motorCoefficients[0]);
      frontRightSparkMax.set(dynamicSpeed*motorPolarity[1]*motorCoefficients[1]*Constants.POLARITY_SWAP);
      backLeftSparkMax.set(dynamicSpeed*motorPolarity[2]*motorCoefficients[2]);
      backRightSparkMax.set(dynamicSpeed*motorPolarity[3]*motorCoefficients[3]*Constants.POLARITY_SWAP);
    }
    else if (twistput<0)
    {
      frontLeftSparkMax.set(dynamicSpeed*motorPolarity[0]*motorCoefficients[0]*Constants.POLARITY_SWAP);
      frontRightSparkMax.set(dynamicSpeed*motorPolarity[1]*motorCoefficients[1]);
      backLeftSparkMax.set(dynamicSpeed*motorPolarity[2]*motorCoefficients[2]*Constants.POLARITY_SWAP);
      backRightSparkMax.set(dynamicSpeed*motorPolarity[3]*motorCoefficients[3]);
    }
  }


  public static void driveMecanum()
  {
    dynamicPosDeadspace=deadShuffle;
    dynamicSpeed=speedShuffle;

    xInput = (MathUtil.applyDeadband(driverController.getLeftX(), dynamicPosDeadspace));
    yInput = -(MathUtil.applyDeadband(driverController.getLeftY(), dynamicPosDeadspace));
    rInput = (MathUtil.applyDeadband(driverController.getRightX(), dynamicPosDeadspace));


    if(rInput==0)
    {
      if (xInput>0)
      {
        if(yInput>0)
        {
          driveAngleLeft();
        }
        else if (yInput<0)
        {
          driveAngleLeftBack();
        }
        else 
        {
          driveLeft();
        }
      }
      else if (xInput<-0)
      {
        if(yInput>0)
        {
          driveAngleRight();
        }
        else if (yInput<0)
        {
          driveAngleRightBack();
        }
        else 
        {
          driveRight();
        }
      }
      else 
      {
        if(yInput>0)
        {
          driveForward();
        }
        else if (yInput<0)
        {
          driveBackward();
        }
        else
        {
          stopMotors();
        }
      }
    }
    else 
    {
      twist(0);
    }
    
    
    
    
    
    
    
    
    
    /* 

    OLD CODE FROM LAST YEAR

    //2/8/2023 USE THE new Rotation2d() THING TO PASS A BLANK GYRO VALUE IF NOT USING GYRO !!!!!!!!!!!!!!

    if(rInput != 0){
      twist(rInput);
    }
    else{
      chassisDrive.driveCartesian
      (driverController.getLeftX() * -1 * Constants.SPEED_MOD, 
      driverController.getLeftY() * Constants.SPEED_MOD, 
      0,
      new Rotation2d(), 
      motorCoefficients);
    }
    */
  }

}
