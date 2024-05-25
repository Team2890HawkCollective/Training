package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class DriveCommand extends CommandBase {
  
  private DriveSubsystem m_subsystem;

  public DriveCommand(DriveSubsystem subsystem) {
    m_subsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }


   public void initialize() {}
 
   @Override
   public void execute() 
     {
        m_subsystem.updateShuffleboard();
        
     }
 
 
   // Called once the command ends or is interrupted.
   @Override
   public void end(boolean interrupted) {}
 
   // Returns true when the command should end.
   @Override
   public boolean isFinished() {
     return false;
   }
}
