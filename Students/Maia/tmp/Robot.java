package frc.robot;


import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.DriveTrain;

public void robotInit() {
  // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
  // autonomous chooser on the dashboard.
  m_robotContainer = new RobotContainer();
}

public void robotPeriodic() {
  // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
  // commands, running already-scheduled commands, removing finished or interrupted commands,
  // and running subsystem periodic() methods.  This must be called from the robot's periodic
  // block in order for anything in the Command-based framework to work.
  CommandScheduler.getInstance().run();
}

public void disabledInit() {}

public void disabledPeriodic() {}

public void teleopInit() {
  // This makes sure that the autonomous stops running when
  // teleop starts running. If you want the yautonomous to
  // continue until interrupted by another command, remove
  // this line or comment it out.
  if (m_autonomousCommand != null) {
    m_autonomousCommand.cancel();
  }
}

public void teleopPeriodic() {

  m_robotContainer.getDriveTrainCommand().execute();
  m_robotContainer.getArmCommand().execute();

}

public void testInit() {
  // Cancels all running commands at the start of test mode.
  CommandScheduler.getInstance().cancelAll();
}

** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
