package org.usfirst.frc.team4561.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team4561.robot.commands.ArcadeDrive;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
	private static Joystick driveStick = new Joystick(RobotMap.DRIVE_JOYSTICK);
	private static Joystick rotationStick = new Joystick(RobotMap.ROTATION_JOYSTICK);
	private JoystickButton driveFrontLeft = new JoystickButton(driveStick,
			RobotMap.FRONT_LEFT_MOTOR_BUTTON);
	private JoystickButton driveRearLeft = new JoystickButton(driveStick,
			RobotMap.REAR_LEFT_MOTOR_BUTTON);
	private JoystickButton driveFrontRight = new JoystickButton(driveStick,
			RobotMap.FRONT_RIGHT_MOTOR_BUTTON);
	private JoystickButton driveRearRight = new JoystickButton(driveStick,
			RobotMap.REAR_RIGHT_MOTOR_BUTTON);
	public static int DRIVE_DEAD_ZONE = 0; //TODO: it probably isn't actually zero
    public static int DRIVE_STICK_REDUCTION = 0;
	public static double getDrive() {
		// Dead zone management
		double driveStickY = driveStick.getY();
		if(Math.abs(driveStickY) < DRIVE_DEAD_ZONE) {
			driveStickY = 0;
		}
		if(driveStickY > 0) {
			driveStickY = driveStickY - DRIVE_STICK_REDUCTION;
		}
		else if(driveStickY < 0) {
			driveStickY = driveStickY + DRIVE_STICK_REDUCTION;
		}
		return driveStickY;
		// return xBoxDriveStick.getX();
	}
	public static int getTurn() {
		double driveStickX = rotationStick.getX();
		if(Math.abs(driveStickX) < DRIVE_DEAD_ZONE) {
			driveStickX = 0;
		}
		if(driveStickX > 0) {
			driveStickX = driveStickX - DRIVE_STICK_REDUCTION;
		}
		else if(driveStickX < 0) {
			driveStickX = driveStickX + DRIVE_STICK_REDUCTION;
		}
		return (int) driveStickX;
	}
}

