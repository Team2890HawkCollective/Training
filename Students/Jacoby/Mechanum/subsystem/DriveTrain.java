package Jacoby.Mechanum.subsystems;

import java.time.format.ResolverStyle;

import javax.lang.model.util.ElementScanner14;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.motorcontrol.MotorController;
//import edu.wpi.first.wpilibj.motorcontrol.Victor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import Jacoby.Mechanum.Constants;

public class DriveTrain extends SubsystemBase 
{
    

    private static CANSparkMax frontLeftSparkMax = new CANSparkMax(Constants.MOTOR_FRONT_LEFT, MotorType.kBrushless);
  private static CANSparkMax frontRightSparkMax = new CANSparkMax(Constants.MOTOR_FRONT_RIGHT, MotorType.kBrushless);
  private static CANSparkMax backLeftSparkMax = new CANSparkMax(Constants.MOTOR_BACK_LEFT, MotorType.kBrushless);
  private static CANSparkMax backRightSparkMax = new CANSparkMax(Constants.MOTOR_BACK_RIGHT, MotorType.kBrushless);


  private static double[] motorCoefficients = {Constants.frontLeftMotorCoeff, Constants.frontRightMotorCoeff, Constants.backLeftMotorCoeff, Constants.backRightMotorCoeff};
  
  private static MecanumWrapperClass chassisDrive = new MecanumWrapperClass(frontLeftSparkMax, backLeftSparkMax, frontRightSparkMax, backRightSparkMax);

  private static XboxController driverController = new XboxController(Constants.DRIVER_XBOX_CONTROLLER_PORT);

  private static AHRS gyro = new AHRS();
  private static double xInput;
  private static double yInput;
  private static double deltaZ;
  private static double rInput;

  private static ShuffleboardTab tab= Shuffleboard.getTab("Main");

  private NetworkTableEntry frontLeftMotorCoeff =
      tab.add("frontLeftMotorCoeff", Constants.FRONT_LEFT_MOTOR_COEFF)
          .getEntry();
  private NetworkTableEntry frontRightMotorCoeff =
      tab.add("frontRightMotorCoeff", Constants.FRONT_LEFT_MOTOR_COEFF)
          .getEntry();
  private NetworkTableEntry backLeftMotorCoeff =
       tab.add("backLeftMotorCoeff", Constants.BACK_LEFT_MOTOR_COEFF)
          .getEntry();

          private NetworkTableEntry backRightMotorCoeff =
          tab.add("backRightMotorCoeff", Constants.BACK_RIGHT_MOTOR_COEFF)
              .getEntry();
      private NetworkTableEntry deadband =
          tab.add("deadband", dynamicPosDeadspace)
              .getEntry();
        private NetworkTableEntry speed =
           tab.add("speed", dynamicSpeed)
              .getEntry();
          

  public static double dynamicPosDeadspace = edu.wpi.first.wpilibj.templates.commandbased.Constants.DEADSPACE_POSITIVE;
  public static double dynamicNegDeadspace = edu.wpi.first.wpilibj.templates.commandbased.Constants.DEADSPACE_NEGATIVE;
  public static double dynamicSpeed = edu.wpi.first.wpilibj.templates.commandbased.Constants.SPEED_MOD;
  
  private static boolean brakeBooleanToggle = false;

  private static int[] motorPolarity = {Constants.FRONT_LEFT_POLARITY, Constants.FRONT_RIGHT_POLARITY, Constants.BACK_LEFT_POLARITY, Constants.BACK_RIGHT_POLARITY};

  public static double deadShuffle=Constants.DEADSPACE_POSITIVE;
  public static double speedShuffle=Constants.SPEED_MOD;

  public static void updateShuffleboard()
  {
    motorCoefficients[0] = SmartDashboard.getNumber("frontLeftMotorCoeff", Constants.FRONT_LEFT_MOTOR_COEFF);
    motorCoefficients[1] = SmartDashboard.getNumber("frontRightMotorCoeff", Constants.FRONT_LEFT_MOTOR_COEFF);
    motorCoefficients[2] = SmartDashboard.getNumber("backLeftMotorCoeff", Constants.BACK_LEFT_MOTOR_COEFF);
    motorCoefficients[3] = SmartDashboard.getNumber("backRightMotorCoeff", Constants.BACK_RIGHT_MOTOR_COEFF);

    motorPolarity[0] = SmartDashboard.getNumber("frontLeftMotorPolarity", Constants.FRONT_LEFT_POLARITY);
    motorPolarity[1] = SmartDashboard.getNumber("frontRightMotorPolarity", Constants.FRONT_LEFT_POLARITY);
    motorPolarity[2] = SmartDashboard.getNumber("backLeftMotorPolarity", Constants.BACK_LEFT_POLARITY);
    motorPolarity[3] = SmartDashboard.getNumber("backRightMotorPolarity", Constants.BACK_RIGHT_POLARITY);

    deadShuffle = SmartDashboard.getNumber("deadband", dynamicPosDeadspace);
    speedShuffle = SmartDashboard.getNumber("speed", dynamicSpeed);
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
 public static double getDeltaZ()
 {
   deltaZ = gyro.getDisplacementZ();

   return deltaZ;
 }

 public static void stopMotors()
 {
   chassisDrive.driveCartesian
   (driverController.getLeftX() * Constants.POLARITY_SWAP * dynamicSpeed, 
   driverController.getLeftY() * dynamicSpeed, 
   driverController.getRightX() * Constants.POLARITY_SWAP * dynamicSpeed,
   new Rotation2d(), 
   new double[] {0.0,0.0,0.0,0.0});
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
    backLeftSparkMax.set(dynamicSpeed*motorPolarity[2]*motorCoefficients[2]);
    backRightSparkMax.set(0);
  }
  public static void driveAngleLeft(){
    frontLeftSparkMax.set(0);
    frontRightSparkMax.set(dynamicSpeed*motorPolarity[1]*motorCoefficients[1]);
    backLeftSparkMax.set(0);
    backRightSparkMax.set(dynamicSpeed*motorPolarity[3]*motorCoefficients[3]);
  }
  public static void driveAngleRightBack(){
    frontLeftSparkMax.set(dynamicSpeed*motorPolarity[0]*Constants.POLARITY_SWAP*motorCoefficients[0]);
    frontRightSparkMax.set(0);
    backLeftSparkMax.set(dynamicSpeed*motorPolarity[2]*Constants.POLARITY_SWAP*motorCoefficients[2]);
    backRightSparkMax.set(0);
  }
  public static void driveAngleLeftBack(){
    frontLeftSparkMax.set(0);
    frontRightSparkMax.set(dynamicSpeed*motorPolarity[1]*Constants.POLARITY_SWAP*motorCoefficients[1]);
    backLeftSparkMax.set(0);
    backRightSparkMax.set(dynamicSpeed*motorPolarity[3]*Constants.POLARITY_SWAP*motorCoefficients[3]);
  }



  public static void driveMecanum()
  {
    dynamicPosDeadspace=deadShuffle;
    dynamicSpeed=speedShuffle;

    xInput = (MathUtil.applyDeadband(driverController.getLeftX(), dynamicPosDeadspace));
    yInput = -(MathUtil.applyDeadband(driverController.getLeftY(), dynamicPosDeadspace));
    rInput = (MathUtil.applyDeadband(driverController.getRightX(), dynamicPosDeadspace));


    if(rInput=0)
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
          driveAngleRghtBack();
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
      }
    }
    else 
    {
      twist(rInput);
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
