package frc.robot.subsystems;

import java.time.format.ResolverStyle;

import javax.lang.model.util.ElementScanner14;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.networktables.GenericEntry;
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
    

private static CANSparkMax frontLeftSparkMax = new CANSparkMax(Constants.MOTOR_FRONT_LEFT, MotorType.kBrushless);
  private static CANSparkMax frontRightSparkMax = new CANSparkMax(Constants.MOTOR_FRONT_RIGHT, MotorType.kBrushless);
  private static CANSparkMax backLeftSparkMax = new CANSparkMax(Constants.MOTOR_BACK_LEFT, MotorType.kBrushless);
  private static CANSparkMax backRightSparkMax = new CANSparkMax(Constants.MOTOR_BACK_RIGHT, MotorType.kBrushless);


  private static double[] motorCoefficients = {Constants.FRONT_LEFT_MOTOR_COEFF, Constants.FRONT_RIGHT_MOTOR_COEFF, Constants.BACK_LEFT_MOTOR_COEFF, Constants.BACK_RIGHT_MOTOR_COEFF};
  
  
  private static XboxController driverController = new XboxController(Constants.DRIVER_XBOX_CONTROLLER_PORT);

  private static double xInput;
  private static double yInput;
  private static double deltaZ;
  private static double rInput;



  public static double dynamicPosDeadspace = frc.robot.Constants.DEADSPACE_POSITIVE;
  public static double dynamicNegDeadspace = frc.robot.Constants.DEADSPACE_NEGATIVE;
  public static double dynamicSpeed = frc.robot.Constants.SPEED_MOD;
  
  private static boolean brakeBooleanToggle = false;

  private static int[] motorPolarity = {Constants.FRONT_LEFT_POLARITY, Constants.FRONT_RIGHT_POLARITY, Constants.BACK_LEFT_POLARITY, Constants.BACK_RIGHT_POLARITY};

  public static double deadShuffle=Constants.DEADSPACE_POSITIVE;
  public static double speedShuffle=Constants.SPEED_MOD;

  public static void updateShuffleboard()
  {

    final ShuffleboardTab tab = Shuffleboard.getTab("Jacoby");

    final GenericEntry frontLeftMotorCoeff = tab.add("frontLeftMotorCoeff", Constants.FRONT_LEFT_MOTOR_COEFF).getEntry();
    final GenericEntry frontRightMotorCoeff = tab.add("frontRightMotorCoeff", Constants.FRONT_LEFT_MOTOR_COEFF).getEntry();
    final GenericEntry backLeftMotorCoeff = tab.add("backLeftMotorCoeff", Constants.BACK_LEFT_MOTOR_COEFF).getEntry();
    final GenericEntry backRightMotorCoeff = tab.add("backRightMotorCoeff", Constants.BACK_RIGHT_MOTOR_COEFF).getEntry();
  
    final GenericEntry frontLeftMotorPolarity = tab.add("frontLeftMotorPolarity", Constants.FRONT_LEFT_POLARITY).getEntry();
    final GenericEntry frontRightMotorPolarity = tab.add("frontRightMotorPolarity", Constants.FRONT_LEFT_POLARITY).getEntry();
    final GenericEntry backLeftMotorPolarity = tab.add("backLeftMotorPolarity", Constants.BACK_LEFT_POLARITY).getEntry();
    final GenericEntry backRightMotorPolarity = tab.add("backRightMotorPolarity", Constants.BACK_RIGHT_POLARITY).getEntry();
  
    final GenericEntry deadband = tab.add("deadband", dynamicPosDeadspace).getEntry();
    final GenericEntry speed = tab.add("speed", dynamicSpeed).getEntry();
            

    motorCoefficients[0] = frontLeftMotorCoeff.getDouble( Constants.FRONT_LEFT_MOTOR_COEFF);
    motorCoefficients[1] = frontRightMotorCoeff.getDouble( Constants.FRONT_LEFT_MOTOR_COEFF);
    motorCoefficients[2] = backLeftMotorCoeff.getDouble(Constants.BACK_LEFT_MOTOR_COEFF);
    motorCoefficients[3] = backRightMotorCoeff.getDouble( Constants.BACK_RIGHT_MOTOR_COEFF);


    if (frontLeftMotorPolarity.getDouble( Constants.FRONT_LEFT_POLARITY)>0)
    {
      motorPolarity[0] = 1;
    }
    else 
    {
      motorPolarity[0] = -1;
    }
//----------------------------------
    if (frontRightMotorPolarity.getDouble(Constants.FRONT_LEFT_POLARITY)>0)
    {
        motorPolarity[1] = 1;
    }
    else
    {
      motorPolarity[1] = -1;
    }
//----------------------------------
    if (backLeftMotorPolarity.getDouble(Constants.BACK_LEFT_POLARITY)>0)
    {
      motorPolarity[2] = 1;
    }
    else
    {
      motorPolarity[2] = -1;
    }
//----------------------------------
    if (backRightMotorPolarity.getDouble( Constants.BACK_RIGHT_POLARITY)>0)
    {
      motorPolarity[3] = 1;
    }
    else
    {
      motorPolarity[3] = -1;
    }

    deadShuffle = deadband.getDouble(dynamicPosDeadspace);
    speedShuffle = speed.getDouble(dynamicSpeed);
    //SmartDashboard.putNumber("Compressor Pressure", phCompressor.getPressure());
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
    frontLeftSparkMax.set(dynamicSpeed*motorPolarity[0]*Constants.POLARITY_SWAP*motorCoefficients[0]);
    frontRightSparkMax.set(dynamicSpeed*motorPolarity[1]*Constants.POLARITY_SWAP*motorCoefficients[1]);
    backLeftSparkMax.set(dynamicSpeed*motorPolarity[2]*motorCoefficients[2]);
    backRightSparkMax.set(dynamicSpeed*motorPolarity[3]*motorCoefficients[3]);
  }
  public static void driveRight(){
    frontLeftSparkMax.set(dynamicSpeed*motorPolarity[0]*motorCoefficients[0]);
    frontRightSparkMax.set(dynamicSpeed*motorPolarity[1]*motorCoefficients[1]);
    backLeftSparkMax.set(dynamicSpeed*motorPolarity[2]*Constants.POLARITY_SWAP*motorCoefficients[2]);
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
  public static void twist()
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
      twist();
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
