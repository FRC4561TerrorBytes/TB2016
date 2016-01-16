package org.usfirst.frc.team4561.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 * 
 * kaiz make sure that these are correct
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    public static int FRONT_LEFT_MOTOR_CAN = 0; //TODO: make these correct
    public static int FRONT_RIGHT_MOTOR_CAN = 1;
    public static int BACK_LEFT_MOTOR_CAN = 2;
    public static int BACK_RIGHT_MOTOR_CAN = 3;
    public static int DRIVE_JOYSTICK = 0; //TODO: make these correct
    public static int ROTATION_JOYSTICK = 1;
    public static int FRONT_LEFT_MOTOR_BUTTON = 9; //TODO: Kaiz seriously don't ignore this
    public static int FRONT_RIGHT_MOTOR_BUTTON = 8;
    public static int REAR_LEFT_MOTOR_BUTTON = 7;
    public static int REAR_RIGHT_MOTOR_BUTTON = 6;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
}
