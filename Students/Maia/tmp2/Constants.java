// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.wpilibj.templates.commandbased;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants 
{

    public static final int MOTOR_FRONT_LEFT = 1;
    public static final int MOTOR_FRONT_RIGHT = 2;
    public static final int MOTOR_BACK_LEFT = 3;
    public static final int MOTOR_BACK_RIGHT = 4;

    public static final int DRIVER_XBOX_CONTROLLER_PORT = 0;
    public static final int ASSIST_XBOX_CONTROLLER_PORT = 1;

    public static final double SPEED_MOD = 1;

    public static final int POLARITY_SWAP = -1;

    public static final int ARM_MOTOR = 20;
    public static final int SHOULDER_MOTOR = 19; // CHECK VALUES
    public static final double ARM_SPEED = .1;
    public static final double ARM_REBOUND_SPEED = 0.1;

    /**
     * The coefficients for the mecanum drive motor controllers.
     */
    public static final double frontLeftMotorCoeff = 1.0;
    public static final double frontRightMotorCoeff = 1.0;
    public static final double backLeftMotorCoeff = 1.0;
    public static final double backRightMotorCoeff = 1.0;

    public static final double PID_P = 0.03;
    public static final double PID_I = 0.0000255;
    public static final double PID_D = 0.24;
    public static final double PID_I_ZONE = 3;
    public static final double PID_FF = 0;

    public static final int BUTTERFLY_SOLENOID_DEPLOY = 12;
    public static final int BUTTERFLY_SOLENOID_RETRACT = 13;

    public static final int REV_PNEUMATIC_MODULE = 11;
    
    public static final double RAMP_TARGET_HEIGHT = .5;
    public static final double LINE_TARGET_DISTANCE = 0; //TODO: Find the distance to the line target IN ENCODER VALUE
	
    
    public static final int FRONT_LEFT_POLARITY = -1;
    public static final int FRONT_RIGHT_POLARITY = 1;
    public static final int BACK_LEFT_POLARITY = 1;
    public static final int BACK_RIGHT_POLARITY = -1;
    //first commit change
}