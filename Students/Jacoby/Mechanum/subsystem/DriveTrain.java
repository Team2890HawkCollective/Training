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
  
  private static boolean brakeBooleanToggle = false;

  private static int[] motorPolarity = {Constants.FRONT_LEFT_POLARITY, Constants.FRONT_RIGHT_POLARITY, Constants.BACK_LEFT_POLARITY, Constants.BACK_RIGHT_POLARITY};

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
   (driverController.getLeftX() * -1 * Constants.SPEED_MOD, 
   driverController.getLeftY() * Constants.SPEED_MOD, 
   driverController.getRightX() * -1 * Constants.SPEED_MOD,
   new Rotation2d(), 
   new double[] {0.0,0.0,0.0,0.0});
 }

 public static void driveForward(){
    frontLeftSparkMax.set(-0.2);
    frontRightSparkMax.set(0.2);
    backLeftSparkMax.set(.2);
    backRightSparkMax.set(-0.2);
  }
  public static void driveBackward(){
    frontLeftSparkMax.set(0.2);
    frontRightSparkMax.set(-0.2);
    backLeftSparkMax.set(-0.2);
    backRightSparkMax.set(0.2);
  }
  public static void driveLeft(){
    frontLeftSparkMax.set(0.2);
    frontRightSparkMax.set(-0.2);
    backLeftSparkMax.set(.2);
    backRightSparkMax.set(-0.2);
  }
  public static void driveRight(){
    frontLeftSparkMax.set(-0.2);
    frontRightSparkMax.set(0.2);
    backLeftSparkMax.set(-0.2);
    backRightSparkMax.set(0.2);
  }
  public static void driveAngleRight(){
    frontLeftSparkMax.set(-0.2);
    frontRightSparkMax.set(0);
    backLeftSparkMax.set(.2);
    backRightSparkMax.set(0);
  }
  public static void driveAngleLeft(){
    frontLeftSparkMax.set(0);
    frontRightSparkMax.set(0.2);
    backLeftSparkMax.set(0);
    backRightSparkMax.set(-0.2);
  }
  public static void driveAngleRightBack(){
    frontLeftSparkMax.set(0.2);
    frontRightSparkMax.set(0);
    backLeftSparkMax.set(-0.2);
    backRightSparkMax.set(0);
  }
  public static void driveAngleLeftBack(){
    frontLeftSparkMax.set(0);
    frontRightSparkMax.set(-0.2);
    backLeftSparkMax.set(0);
    backRightSparkMax.set(0.2);
  }



  public static void driveMecanum()
  {
    

    xInput = (MathUtil.applyDeadband(driverController.getLeftX(), .2));
    yInput = -(MathUtil.applyDeadband(driverController.getLeftY(), .2));
    rInput = (MathUtil.applyDeadband(driverController.getRightX(), .2));


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
