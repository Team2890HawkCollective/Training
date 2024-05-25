// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.stream.IntStream;

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
    public static final int MOTOR_SERVO_DRIVETRAIN=5;

    public static final int DRIVER_XBOX_CONTROLLER_PORT = 0;


    public static final double SPEED_MOD = 0.2;

    public static final int POLARITY_SWAP = -1;
    //FRONT_LEFT_MOTOR_COEFF
    public static final double FRONT_LEFT_MOTOR_COEFF = 1.0;
    public static final double FRONT_RIGHT_MOTOR_COEFF = 1.0;
    public static final double BACK_LEFT_MOTOR_COEFF = 1.0;
    public static final double BACK_RIGHT_MOTOR_COEFF = 1.0;
    
    public static final int FRONT_LEFT_POLARITY = -1;
    public static final int FRONT_RIGHT_POLARITY = 1;
    public static final int BACK_LEFT_POLARITY = 1;
    public static final int BACK_RIGHT_POLARITY = -1;

    public static final double DEADSPACE_POSITIVE = 0.2;
    public static final double DEADSPACE_NEGATIVE = -0.2;
}