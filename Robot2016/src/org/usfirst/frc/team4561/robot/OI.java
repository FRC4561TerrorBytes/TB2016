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
	
	private static Joystick rightStick = new Joystick(RobotMap.RIGHT_JOYSTICK);
	private static Joystick leftStick = new Joystick(RobotMap.LEFT_JOYSTICK);
	
	private JoystickButton driveFrontLeft = new JoystickButton(rightStick,
			RobotMap.FRONT_LEFT_MOTOR_BUTTON);
	private JoystickButton driveRearLeft = new JoystickButton(rightStick,
			RobotMap.REAR_LEFT_MOTOR_BUTTON);
	private JoystickButton driveFrontRight = new JoystickButton(rightStick,
			RobotMap.FRONT_RIGHT_MOTOR_BUTTON);
	private JoystickButton driveRearRight = new JoystickButton(rightStick,
			RobotMap.REAR_RIGHT_MOTOR_BUTTON);
	
	public static double RIGHT_STICK_DEAD_ZONE = 0.1; //TODO: Change based on tests
    public static double RIGHT_STICK_REDUCTION = 0;
    
    public static double LEFT_STICK_DEAD_ZONE = 0.1; //TODO: Change based on tests
    public static double LEFT_STICK_REDUCTION = 0;
    
	public double getRightStickY() {
		double rightStickY = rightStick.getY();
		// Dead zone management
		if(Math.abs(rightStickY) < RIGHT_STICK_DEAD_ZONE) {
			rightStickY = 0;
		}
		// Reductions
		if(rightStickY > 0) {
			rightStickY = rightStickY - RIGHT_STICK_REDUCTION;
			if(rightStickY < 0) {
				rightStickY = 0;
			}
		}
		else if(rightStickY < 0) {
			rightStickY = rightStickY + RIGHT_STICK_REDUCTION;
			if(rightStickY > 0) {
				rightStickY = 0;
			}
		}
		return rightStickY;
	}
	
	public double getRightStickX() {
		double rightStickX = rightStick.getX();
		// Dead zone management
		if(Math.abs(rightStickX) < RIGHT_STICK_DEAD_ZONE) {
			rightStickX = 0;
		}
		// Reductions
		if(rightStickX > 0) {
			rightStickX = rightStickX - RIGHT_STICK_REDUCTION;
			if(rightStickX < 0) {
				rightStickX = 0;
			}
		}
		else if(rightStickX < 0) {
			rightStickX = rightStickX + RIGHT_STICK_REDUCTION;
			if(rightStickX > 0) {
				rightStickX = 0;
			}
		}
		return rightStickX;
	}
	
	public double getLeftStickY() {
		double leftStickY = leftStick.getY();
		// Dead zone management
		if(Math.abs(leftStickY) < LEFT_STICK_DEAD_ZONE) {
			leftStickY = 0;
		}
		// Reductions
		if(leftStickY > 0) {
			leftStickY = leftStickY - RIGHT_STICK_REDUCTION;
			if(leftStickY < 0) {
				leftStickY = 0;
			}
		}
		else if(leftStickY < 0) {
			leftStickY = leftStickY + RIGHT_STICK_REDUCTION;
			if(leftStickY > 0) {
				leftStickY = 0;
			}
		}
		return leftStickY;
	}
	
	public double getLeftStickX() {
		double leftStickX = leftStick.getX();
		// Dead zone management
		if(Math.abs(leftStickX) < LEFT_STICK_DEAD_ZONE) {
			leftStickX = 0;
		}
		// Reductions
		if(leftStickX > 0) {
			leftStickX = leftStickX - RIGHT_STICK_REDUCTION;
			if(leftStickX < 0) {
				leftStickX = 0;
			}
		}
		else if(leftStickX < 0) {
			leftStickX = leftStickX + RIGHT_STICK_REDUCTION;
			if(leftStickX > 0) {
				leftStickX = 0;
			}
		}
		return leftStickX;
	}
}

