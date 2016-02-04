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
    public static final int FRONT_LEFT_MOTOR_CAN = 3; // TODO: Verify
    public static final int FRONT_RIGHT_MOTOR_CAN = 1; // TODO: Verify
    public static final int REAR_LEFT_MOTOR_CAN = 2; // TODO: Verify
    public static final int REAR_RIGHT_MOTOR_CAN = 0; // TODO: Verify
    // Shooter
    public static int LEFT_SHOOTER_MOTOR = 4; // TODO: Verify
    public static final int RIGHT_SHOOTER_MOTOR = 5; // TODO: Verify
    // Arm
    public static final int LEFT_ARM_MOTOR = 6; // TODO: Verify
    public static final int RIGHT_ARM_MOTOR = 7; // TODO: Verify
    // Roller
    public static final int ROLLER_MOTOR = 8; //TODO: Verify
    
    // Joystick ports (USB)
    public static final int LEFT_JOYSTICK = 0;
    public static final int RIGHT_JOYSTICK = 1;
    
    // Camera buttons (Left Stick)
    public static final int CAMERA_TOGGLE_BUTTON = 1;
    
    //Shooter buttons (Left Stick)
    public static final int SHOOTER_PID_FLIP_BUTTON = 2; //TODO: CHOOSE BETTER BUTTON
    
    // Arm buttons (Left Stick)
    public static final int LOADER_BUTTON = 6;
    
    // DriveTrain buttons (Left Stick)
    public static final int REVERSE_DIRECTION_BUTTON = 7;
    
    // Individual Motor Drive buttons (Left Stick)
    public static final int FRONT_LEFT_MOTOR_BUTTON = 9;
    public static final int FRONT_RIGHT_MOTOR_BUTTON = 10;
    public static final int REAR_LEFT_MOTOR_BUTTON = 11;
    public static final int REAR_RIGHT_MOTOR_BUTTON = 12;
    
    // Encoders (DIO)
    public static final int SHOOTER_ENCODER_A_SOURCE = 0; // TODO: Verify
    public static final int SHOOTER_ENCODER_B_SOURCE = 1; // TODO: Verify
    public static final int ARM_LEFT_ENCODER_A_SOURCE = 2; // TODO: Verify
    public static final int ARM_LEFT_ENCODER_B_SOURCE = 3; // TODO: Verify
    public static final int ARM_RIGHT_ENCODER_A_SOURCE = 4; // TODO: Verify
    public static final int ARM_RIGHT_ENCODER_B_SOURCE = 5; //TODO: Verify
    
    // Limit Switches (DIO)
    public static final int ROLLER_LIMIT_SWITCH = 6; // TODO: Verify
}
