// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Autonomous;
import frc.robot.subsystems.DriveTrain;

/** An example command that uses an example subsystem. */
public class AutonomousCommand extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final DriveTrain m_subsystem;
    Timer timer;
    int counter=0;
    /**
     * Creates a new ExampleCommand.
     *
     * @param subsystem The subsystem used by this command.
     */
  
  
  
    public AutonomousCommand(DriveTrain subsystem) {
      m_subsystem = subsystem;
      // Use addRequirements() here to declare subsystem dependencies.
      addRequirements(subsystem);
      timer = new Timer();
    }
  
    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
      timer.start();
    }
  
    @Override
    public void execute(){
        if (!isFinished())
        {
            DriveTrain.driveForward();
        }
        else if (counter==0)
        {
            while (DriveTrain.getYaw()<90)
            {
                DriveTrain.twist(1);
            }
        }
    }
  
 
    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
      DriveTrain.stopMotors();
    }
  
    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
      return timer.get() > 5;
      // return false;
    }
  }