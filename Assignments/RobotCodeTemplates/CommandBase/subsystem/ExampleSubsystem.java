// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.wpilibj.templates.commandbased.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ExampleSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */

  private static XboxController driverController = new XboxController(Constants.DRIVER_XBOX_CONTROLLER_PORT);

  private static AHRS gyro = new AHRS();
  private static double xInput;

  private static double yInput;
  private static double deltaZ;

  private static double rInput;
  private static boolean isMecanum = false;

  private static DoubleSolenoid butterFlySolenoid = null;
  private static Compressor phCompressor = null;

  private static boolean brakeBooleanToggle = false;

  private static int[] motorPolarity = {Constants.FRONT_LEFT_POLARITY, Constants.FRONT_RIGHT_POLARITY, Constants.BACK_LEFT_POLARITY, Constants.BACK_RIGHT_POLARITY};

  public ExampleSubsystem() 
  {
    //its a drive train woag 

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

  public static void driveMecanum()
  {
    

    xInput = (MathUtil.applyDeadband(driverController.getLeftX(), .20));
    yInput = -(MathUtil.applyDeadband(driverController.getLeftY(), .20)); //20% deadzone required for ticket (I think)
    rInput = (MathUtil.applyDeadband(driverController.getRightX(), .20));

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

  public static void twist(double inputX){
    double[] baseBehaviour = new double[] {1.0 * inputX, -1.0 * inputX, 1.0 * inputX, -1.0 * inputX};
    double[] motorInputs = applyFilters(motorPolarity, motorCoefficients, baseBehaviour);
    frontLeftSparkMax.set(motorInputs[0]);
    frontRightSparkMax.set(motorInputs[1]);
    backLeftSparkMax.set(motorInputs[2]);
    backRightSparkMax.set(motorInputs[3]);
  }



  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  
  
  
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}