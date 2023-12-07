// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.time.format.ResolverStyle;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveSubsystem extends SubsystemBase {

  static CANSparkMax frontLeftSparkMax = new CANSparkMax(Constants.MOTOR_FRONT_LEFT, MotorType.kBrushless);
  private static CANSparkMax frontRightSparkMax = new CANSparkMax(Constants.MOTOR_FRONT_RIGHT, MotorType.kBrushless);
  private static CANSparkMax backLeftSparkMax = new CANSparkMax(Constants.MOTOR_BACK_LEFT, MotorType.kBrushless);
  private static CANSparkMax backRightSparkMax = new CANSparkMax(Constants.MOTOR_BACK_RIGHT, MotorType.kBrushless);

  private static double xInput;
  private static double yInput;
  private static double rInput;
  private static boolean isMecanum = false;

  private static int[] motorPolarity = {Constants.FRONT_LEFT_POLARITY, Constants.FRONT_RIGHT_POLARITY, Constants.BACK_LEFT_POLARITY, Constants.BACK_RIGHT_POLARITY};


  private static double[] motorCoefficients = {Constants.frontLeftMotorCoeff, Constants.frontRightMotorCoeff, Constants.backLeftMotorCoeff, Constants.backRightMotorCoeff};

  private static MecanumWrapperClass chassisDrive = new MecanumWrapperClass(frontLeftSparkMax, backLeftSparkMax, frontRightSparkMax, backRightSparkMax);
 
  private static boolean brakeBooleanToggle = false;
  
  private static XboxController driverController = new XboxController(Constants.DRIVER_XBOX_CONTROLLER_PORT);

  public static void updateShuffleboard()
  {
    motorCoefficients[0] = SmartDashboard.getNumber("frontLeftMotorCoeff", Constants.frontLeftMotorCoeff);
    motorCoefficients[1] = SmartDashboard.getNumber("frontRightMotorCoeff", Constants.frontRightMotorCoeff);
    motorCoefficients[2] = SmartDashboard.getNumber("backLeftMotorCoeff", Constants.backLeftMotorCoeff);
    motorCoefficients[3] = SmartDashboard.getNumber("backRightMotorCoeff", Constants.backRightMotorCoeff);

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
    chassisDrive.driveCartesian(0, -.2, 0, null, motorCoefficients);
  }
  public static void driveLeft(){
    chassisDrive.driveCartesian(.2, 0, 0, null, motorCoefficients);
  }
  public static void driveRight(){
    chassisDrive.driveCartesian(-.2, 0, 0, null, motorCoefficients);
  }
  public static void driveAngleRight(){
    chassisDrive.driveCartesian(0, 0, .2, null, motorCoefficients);
  }
  public static void driveAngleLeft(){
    chassisDrive.driveCartesian(0, 0, -.2, null, motorCoefficients);
  }

  public static double[] getEncoderArray(){
    return new double[]{
      frontLeftSparkMax.getEncoder().getPosition(),
      frontRightSparkMax.getEncoder().getPosition(), 
      backLeftSparkMax.getEncoder().getPosition(), 
      backRightSparkMax.getEncoder().getPosition()
    };
  }

  public static void resetEncoders(){
    frontLeftSparkMax.getEncoder().setPosition(0);
    frontRightSparkMax.getEncoder().setPosition(0);
    backLeftSparkMax.getEncoder().setPosition(0);
    backRightSparkMax.getEncoder().setPosition(0);
  }

 
  /** Creates a new DriveSubsystem. */
  public DriveSubsystem() {
    SmartDashboard.putNumber("frontLeftMotorCoeff", Constants.frontLeftMotorCoeff);
    SmartDashboard.putNumber("frontRightMotorCoeff", Constants.frontRightMotorCoeff);
    SmartDashboard.putNumber("backLeftMotorCoeff", Constants.backLeftMotorCoeff);
    SmartDashboard.putNumber("backRightMotorCoeff", Constants.backRightMotorCoeff);
    
    backLeftSparkMax.setInverted(true);
    backRightSparkMax.setInverted(true);
    frontLeftSparkMax.setInverted(false);
    frontRightSparkMax.setInverted(false);
    resetEncoders();
  }

  public static void driveMecanum()
  {
    

    xInput = (MathUtil.applyDeadband(driverController.getLeftX(), .2));
    yInput = -(MathUtil.applyDeadband(driverController.getLeftY(), .2));
    rInput = (MathUtil.applyDeadband(driverController.getRightX(), .2));

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
  }

// X-Axis on drive controller right joystick
public static void twist(double inputX){
  double[] baseBehaviour = new double[] {1.0 * inputX, -1.0 * inputX, 1.0 * inputX, -1.0 * inputX};
  double[] motorInputs = applyFilters(motorPolarity, motorCoefficients, baseBehaviour);
  frontLeftSparkMax.set(motorInputs[0]);
  frontRightSparkMax.set(motorInputs[1]);
  backLeftSparkMax.set(motorInputs[2]);
  backRightSparkMax.set(motorInputs[3]);
}

public static double[] applyFilters(int[] polarity, double[] coeff, double[] speeds){
  double[] correctedValues = new double[] {
    coeff[0] * speeds[0] * polarity[0],
    coeff[1] * speeds[1] * polarity[1],
    coeff[2] * speeds[2] * polarity[2], 
    coeff[3] * speeds[3] * polarity[3]
  };
  return correctedValues;
}
  /** Creates a new ExampleSubsystem. */
  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}