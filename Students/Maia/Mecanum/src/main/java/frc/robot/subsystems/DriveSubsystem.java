// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;

public class DriveSubsystem extends SubsystemBase {

  private static CANSparkMax frontLeftSparkMax = new CANSparkMax(Constants.MOTOR_FRONT_LEFT, MotorType.kBrushless);
  private static CANSparkMax frontRightSparkMax = new CANSparkMax(Constants.MOTOR_FRONT_RIGHT, MotorType.kBrushless);
  private static CANSparkMax backLeftSparkMax = new CANSparkMax(Constants.MOTOR_BACK_LEFT, MotorType.kBrushless);
  private static CANSparkMax backRightSparkMax = new CANSparkMax(Constants.MOTOR_BACK_RIGHT, MotorType.kBrushless);

  private static double[] motorCoefficients = {Constants.frontLeftMotorCoeff, Constants.frontRightMotorCoeff, Constants.backLeftMotorCoeff, Constants.backRightMotorCoeff};

  private static MecanumWrapperClass chassisDrive = new MecanumWrapperClass(frontLeftSparkMax, backLeftSparkMax, frontRightSparkMax, backRightSparkMax);


  private static XboxController driverController = new XboxController(Constants.DRIVER_XBOX_CONTROLLER_PORT);

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
  public static void driveAngeLeft(){
    chassisDrive.driveCartesian(0, 0, -.2, null, motorCoefficients);
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
  public static void driveAngeLeft(){
    chassisDrive.driveCartesian(0, 0, -.2, null, motorCoefficients);
  }
  /** Creates a new DriveSubsystem. */
  public DriveSubsystem() {
    SmartDashboard.putNumber("frontLeftMotorCoeff", Constants.frontLeftMotorCoeff);
    SmartDashboard.putNumber("frontRightMotorCoeff", Constants.frontRightMotorCoeff);
    SmartDashboard.putNumber("backLeftMotorCoeff", Constants.backLeftMotorCoeff);
    SmartDashboard.putNumber("backRightMotorCoeff", Constants.backRightMotorCoeff);
    gyro.resetDisplacement();
    backLeftSparkMax.setInverted(true);
    backRightSparkMax.setInverted(true);
    frontLeftSparkMax.setInverted(false);
    frontRightSparkMax.setInverted(false);
    resetEncoders();
  }
}