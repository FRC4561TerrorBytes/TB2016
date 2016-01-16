package org.usfirst.frc.team4561.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    public static int FRONT_LEFT_MOTOR_CAN = 0; //TODO: Verify
    public static int FRONT_RIGHT_MOTOR_CAN = 1;
    public static int BACK_LEFT_MOTOR_CAN = 2;
    public static int BACK_RIGHT_MOTOR_CAN = 3;
    
    public static int LEFT_JOYSTICK = 0; //TODO: Verify
    public static int RIGHT_JOYSTICK = 1;
    
    public static int FRONT_LEFT_MOTOR_BUTTON = 9; //TODO: Verify
    public static int FRONT_RIGHT_MOTOR_BUTTON = 8;
    public static int REAR_LEFT_MOTOR_BUTTON = 7;
    public static int REAR_RIGHT_MOTOR_BUTTON = 6;
    public static int LOADER_BUTTON = 10;
}
