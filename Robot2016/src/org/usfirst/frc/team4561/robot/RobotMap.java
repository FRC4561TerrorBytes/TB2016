package org.usfirst.frc.team4561.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// Motors (PWM)
	// DriveTrain
    public static final int FRONT_LEFT_MOTOR_CAN = 7;
    public static final int FRONT_RIGHT_MOTOR_CAN = 5;
    public static final int REAR_LEFT_MOTOR_CAN = 6;
    public static final int REAR_RIGHT_MOTOR_CAN = 2;
    // Shooter
    public static final int LEFT_SHOOTER_MOTOR = 3;
    public static final int RIGHT_SHOOTER_MOTOR = 4;
    // Arm
    public static final int LEFT_ARM_MOTOR = 8;
    public static final int RIGHT_ARM_MOTOR = 9;
    // Roller
    public static final int ROLLER_MOTOR = 1;
    
    // Joystick ports (USB)
    public static final int LEFT_JOYSTICK = 0;
    public static final int RIGHT_JOYSTICK = 1;
    public static final int CONTROLLER = 2;
    
    // Camera buttons (Left Stick)
    public static final int CAMERA_TOGGLE_BUTTON = 8; // TODO: Optimize
    
    // Shooter buttons (Left Stick)
    public static final int SHOOTER_PID_FLIP_BUTTON = 2; // TODO: Verify
    
    // Shooter buttons (Controller)
    public static final int FIRE_BUTTON = 10; // TODO: Verify
    
    // Roller buttons (Left Stick)
    public static final int ROLL_OUT_BUTTON = 1; // TODO: Optimize
    
    // Roller button (Right Stick)
    public static final int ROLL_IN_BUTTON = 1; // TODO: Optimize
    
    // Arm buttons (Left Stick)
    public static final int LOADER_BUTTON = 6; // TODO: Verify
    public static final int LOWSHOT_BUTTON = 3; // TODO: Verify
    
    // Arm buttons (Controller)
    public static final int LOWER_ARMS_BUTTON = 10; // TODO: Verify
    public static final int RAISE_ARMS_BUTTON = 9; // TODO: Verify
    public static final int MOVE_ARM_DELTA_UP = 6; // TODO: Verify
    public static final int MOVE_ARM_DELTA_DOWN = 5; // TODO: Verify
    public static final int ARM_PRESET_1 = 1;
    public static final int ARM_PRESET_2 = 2;
    public static final int ARM_PRESET_3 = 3;
    public static final int ARM_PRESET_4 = 4;
    public static final int ARM_PRESET_5 = 5;
    
    
    public static final int ARM_PRESET_1_LOWER = -1;
    public static final int ARM_PRESET_1_UPPER = 1;
    public static final int ARM_PRESET_2_LOWER = 2;
    public static final int ARM_PRESET_2_UPPER = 2;
    public static final int ARM_PRESET_3_LOWER = 3;
    public static final int ARM_PRESET_3_UPPER = 2;
    public static final int ARM_PRESET_4_LOWER = 4;
    public static final int ARM_PRESET_4_UPPER = 2;
    public static final int ARM_PRESET_5_LOWER = 5;
    public static final int ARM_PRESET_5_UPPER = 1;
    
    // DriveTrain buttons (Left Stick)
    public static final int REVERSE_DIRECTION_BUTTON = 7; // TODO: Optimize
    
    // DriveTrain buttons (Right Stick)
    public static final int TOURING_MODE_BUTTON = 2; // TODO: Optimize
    
    // DriveTrain buttons (Controller)
    public static final int ALIGN_RIGHT_BUTTON = 1; // TODO: Verify
    public static final int ALIGN_LEFT_BUTTON = 2; // TODO: Verify
    
    // Individual Motor Drive buttons (Left Stick)
//    public static final int FRONT_LEFT_MOTOR_BUTTON = 9;
//    public static final int FRONT_RIGHT_MOTOR_BUTTON = 10;
//    public static final int REAR_LEFT_MOTOR_BUTTON = 11;
//    public static final int REAR_RIGHT_MOTOR_BUTTON = 12;
    
    // Encoders (DIO)
    public static final int SHOOTER_ENCODER_A_SOURCE = 0; // TODO: Verify
    public static final int SHOOTER_ENCODER_B_SOURCE = 1; // TODO: Verify
    public static final int ARM_LEFT_ENCODER_A_SOURCE = 2; // TODO: Verify
    public static final int ARM_LEFT_ENCODER_B_SOURCE = 3; // TODO: Verify
    public static final int ARM_RIGHT_ENCODER_A_SOURCE = 4; // TODO: Verify
    public static final int ARM_RIGHT_ENCODER_B_SOURCE = 5; //TODO: Verify
    
    // Limit Switches (DIO)
    public static final int ROLLER_LIMIT_SWITCH = 6; // TODO: Verify
    public static final int ARM_TOP_LIMIT_SWITCH = 7; // TODO: Verify
    public static final int ARM_BOTTOM_LIMIT_SWITCH = 8; // TODO: Verify
    
    // Camera Specifications
    public static final double CAMERA_HORIZONTAL_FOV = 59; // Degrees
    public static final double CAMERA_VERTICAL_FOV = 33.1; // Degrees
    public static final int CAMERA_RESOLUTION_X = 640; // Pixels
    public static final int CAMERA_RESOLUTION_Y = 480; // Pixels
    
    // DriveTrain constants
    public static final double TOURING_MODE_MULTIPLIER = 0.5;
    public static final double DRIVETRAIN_ALIGNMENT_SPEED = 0.2;
    
    // Arm constants
    public static final double ARM_DELTA_INCREMENT = 2; // Degrees
}
