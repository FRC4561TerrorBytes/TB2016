package org.usfirst.frc.team4561.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// DriveTrain Motor Controllers
    public static int FRONT_LEFT_MOTOR_CAN = 3; //TODO: Verify
    public static int FRONT_RIGHT_MOTOR_CAN = 1;
    public static int REAR_LEFT_MOTOR_CAN = 2;
    public static int REAR_RIGHT_MOTOR_CAN = 0;
    
    // Joystick ports
    //public static int FLY_ONE = 0; //TODO: do these ports correctly
	//public static int FLY_TWO = 1;
    public static int LEFT_JOYSTICK = 0; //TODO: Verify
    public static int RIGHT_JOYSTICK = 1;
    
    // Individual Motor Drive buttons
    public static int FRONT_LEFT_MOTOR_BUTTON = 9; //TODO: Verify
    public static int FRONT_RIGHT_MOTOR_BUTTON = 10;
    public static int REAR_LEFT_MOTOR_BUTTON = 11;
    public static int REAR_RIGHT_MOTOR_BUTTON = 12;
    
    // Loader buttons
    public static int LOADER_BUTTON = 6;
    
    // Camera buttons
    public static int CAMERA_TOGGLE_BUTTON = 1;
    
    // Encoder Ports
    //public static int ENCODER_A_CHANNEL = 0; //TODO: Make active once in use.
    //public static int ENCODER_B_CHANNEL = 1;
}
