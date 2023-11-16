package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.AutoCommand;
import frc.robot.commands.DriveTrainCommand;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.Command;


public class RobotContainer {


  private final DriveTrain m_driveTrainSubsystem = new DriveTrain();
  private final DriveTrainCommand m_driveTrainCommand = new DriveTrainCommand(m_driveTrainSubsystem);
  private final AutoCommand m_AutoCommand = new AutoCommand(m_driveTrainSubsystem);

  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  private void configureButtonBindings() {}

  public Command getDriveTrainCommand() {
    return m_driveTrainCommand;
}

}