// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final class DRIVE_TRAIN {
        public static final class PORT {
            public static final class FRONT {
                public static final int LEFT = 1;
                public static final int RIGHT = 2;
            }

            public static final class BACK {
                public static final int LEFT = 3;
                public static final int RIGHT = 4;
            }
        }

        public static final class SPEED {
            public static final int ALL = 1;

            public static final class FRONT {
                public static final int LEFT = 1;
                public static final int RIGHT = 1;
            }

            public static final class BACK {
                public static final int LEFT = 1;
                public static final int RIGHT = 1;
            }
        }

        public static final class POLARITY {
            public static final int ALL = 1;

            public static final class FRONT {
                public static final int LEFT = 1;
                public static final int RIGHT = 1;
            }

            public static final class BACK {
                public static final int LEFT = 1;
                public static final int RIGHT = 1;
            }
        }

        public static final class MODIFIERS {
            public static final class FRONT {
                public static final int LEFT = Constants.DRIVE_TRAIN.POLARITY.FRONT.LEFT * Constants.DRIVE_TRAIN.POLARITY.ALL * Constants.DRIVE_TRAIN.SPEED.ALL * Constants.DRIVE_TRAIN.SPEED.FRONT.LEFT;
                public static final int RIGHT = Constants.DRIVE_TRAIN.POLARITY.FRONT.RIGHT * Constants.DRIVE_TRAIN.POLARITY.ALL * Constants.DRIVE_TRAIN.SPEED.ALL * Constants.DRIVE_TRAIN.SPEED.FRONT.RIGHT;
            }
    
            public static final class BACK {
                public static final int LEFT = Constants.DRIVE_TRAIN.POLARITY.FRONT.LEFT * Constants.DRIVE_TRAIN.POLARITY.ALL * Constants.DRIVE_TRAIN.SPEED.ALL * Constants.DRIVE_TRAIN.SPEED.FRONT.LEFT;
                public static final int RIGHT = Constants.DRIVE_TRAIN.POLARITY.FRONT.RIGHT * Constants.DRIVE_TRAIN.POLARITY.ALL * Constants.DRIVE_TRAIN.SPEED.ALL * Constants.DRIVE_TRAIN.SPEED.FRONT.RIGHT;
            }
        }
    }
}
}