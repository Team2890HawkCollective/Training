package frc.robot.subsystems;

import edu.wpi.first.hal.HAL;
import edu.wpi.first.hal.FRCNetComm.tInstances;
import edu.wpi.first.hal.FRCNetComm.tResourceType;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import frc.robot.Constants;

public class MecanumWrapperClass extends MecanumDrive
{

    private MotorController m_frontLeftMotor;
    private MotorController m_rearLeftMotor;
    private MotorController m_frontRightMotor;
    private MotorController m_rearRightMotor;
    private boolean m_reported;
    public static double[] coefficients = {Constants.frontLeftMotorCoeff, Constants.frontRightMotorCoeff, Constants.backLeftMotorCoeff, Constants.backRightMotorCoeff};
                            //            Front Left ,  Front Right  ,  Back Left   ,  Back Right


    MecanumWrapperClass(
        MotorController frontLeftMotor,
        MotorController rearLeftMotor,
        MotorController frontRightMotor,
        MotorController rearRightMotor) {
        super(rearRightMotor, rearRightMotor, rearRightMotor, rearRightMotor);
        this.m_frontLeftMotor = frontLeftMotor;
        this.m_frontRightMotor = frontRightMotor;
        this.m_rearLeftMotor = rearLeftMotor;
        this.m_rearRightMotor = rearRightMotor;
        
        
    }

    public void driveCartesian(double xSpeed, double ySpeed, double zRotation, Rotation2d gyroAngle, double[] coefficients) {
        if (!m_reported) {
          HAL.report(
              tResourceType.kResourceType_RobotDrive, tInstances.kRobotDrive2_MecanumCartesian, 4);
          m_reported = true;
        }
    
        xSpeed = MathUtil.applyDeadband(xSpeed, m_deadband);
        ySpeed = MathUtil.applyDeadband(ySpeed, m_deadband);
    
        var speeds = driveCartesianIK(xSpeed, ySpeed, zRotation, gyroAngle);
    
        m_frontLeftMotor.set(coefficients[0] * speeds.frontLeft * m_maxOutput);
        m_frontRightMotor.set(coefficients[1] * speeds.frontRight * m_maxOutput);
        m_rearLeftMotor.set(coefficients[2] * speeds.rearLeft * m_maxOutput);
        m_rearRightMotor.set(coefficients[3] * speeds.rearRight * m_maxOutput);
    
        feed();
      }



}