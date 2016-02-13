package org.usfirst.frc.team4561.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4561.robot.commands.ArcadeDrive;
import org.usfirst.frc.team4561.robot.commands.IndividualMotorDrive;
import org.usfirst.frc.team4561.robot.commands.ReverseDriveDirection;
import org.usfirst.frc.team4561.robot.commands.LoadBallCommandGroup;
import org.usfirst.frc.team4561.robot.commands.ToggleShooterPID;
import org.usfirst.frc.team4561.robot.commands.SwitchToCamera1;
import org.usfirst.frc.team4561.robot.commands.SwitchToCamera2;
import org.usfirst.frc.team4561.robot.commands.ToggleCamera;
import org.usfirst.frc.team4561.robot.triggers.DSButton0Trigger;
import org.usfirst.frc.team4561.robot.triggers.DSButton1Trigger;
import org.usfirst.frc.team4561.robot.triggers.DSButton2Trigger;
import org.usfirst.frc.team4561.robot.commands.ShootLowCommandGroup;

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
	
	// Joysticks
	private static Joystick rightStick = new Joystick(RobotMap.RIGHT_JOYSTICK);
	private static Joystick leftStick = new Joystick(RobotMap.LEFT_JOYSTICK);
	
	// Individual motor drive buttons
	private JoystickButton driveFrontLeft = new JoystickButton(leftStick,
			RobotMap.FRONT_LEFT_MOTOR_BUTTON);
	private JoystickButton driveRearLeft = new JoystickButton(leftStick,
			RobotMap.REAR_LEFT_MOTOR_BUTTON);
	private JoystickButton driveFrontRight = new JoystickButton(leftStick,
			RobotMap.FRONT_RIGHT_MOTOR_BUTTON);
	private JoystickButton driveRearRight = new JoystickButton(leftStick,
			RobotMap.REAR_RIGHT_MOTOR_BUTTON);
	
	// Loader buttons
	private JoystickButton loaderButton = new JoystickButton(leftStick,
			RobotMap.LOADER_BUTTON);
	private JoystickButton lowShotButton = new JoystickButton(leftStick,
			RobotMap.LOWSHOT_BUTTON);
	private JoystickButton reverseDirectionButton = new JoystickButton(leftStick,
			RobotMap.REVERSE_DIRECTION_BUTTON);

	// Camera buttons
	private JoystickButton cameraToggleButton = new JoystickButton(leftStick, RobotMap.CAMERA_TOGGLE_BUTTON);
	
	// SmartDashboard triggers
	private DSButton0Trigger dsButton0 = new DSButton0Trigger();
	private DSButton1Trigger dsButton1 = new DSButton1Trigger();
	private DSButton2Trigger dsButton2 = new DSButton2Trigger();
	
	//Shooter Buttons and Controls
	private JoystickButton shooterPIDFlipButton = new JoystickButton(rightStick, RobotMap.SHOOTER_PID_FLIP_BUTTON);
	
	public OI(){
		// Loader button command assignment
		loaderButton.whileHeld(new LoadBallCommandGroup());
		lowShotButton.whileHeld(new ShootLowCommandGroup()); 
		
		// SmartDashboard trigger preparation
		SmartDashboard.putBoolean("DB/Button 0", false);
		SmartDashboard.putBoolean("DB/Button 1", false);
		SmartDashboard.putBoolean("DB/Button 2", false);
		
		// SmartDashboard trigger command assignments
		dsButton0.whenActive(new SwitchToCamera1());
		dsButton1.whenActive(new SwitchToCamera2());
		dsButton2.whenActive(new ToggleCamera());
		
		// Camera button command assignments
		cameraToggleButton.whenPressed(new ToggleCamera());
		
		reverseDirectionButton.whenPressed(new ReverseDriveDirection());
		
		// Individual Motor Drive command assignments
		driveFrontLeft.whileHeld(new IndividualMotorDrive(RobotMap.FRONT_LEFT_MOTOR_CAN));
		driveRearLeft.whileHeld(new IndividualMotorDrive(RobotMap.REAR_LEFT_MOTOR_CAN));
		driveRearRight.whileHeld(new IndividualMotorDrive(RobotMap.REAR_RIGHT_MOTOR_CAN));
		driveFrontRight.whileHeld(new IndividualMotorDrive(RobotMap.FRONT_RIGHT_MOTOR_CAN));
		
		// Shooter button comand assignment
		shooterPIDFlipButton.whenActive(new ToggleShooterPID());
	}
	
	// Joystick config
	// Right stick config
	public static double RIGHT_STICK_DEAD_ZONE = 0.15; //TODO: Change based on tests
    public static double RIGHT_STICK_REDUCTION = 0;
    
    // Left stick config
    public static double LEFT_STICK_DEAD_ZONE = 0.15; //TODO: Change based on tests
    public static double LEFT_STICK_REDUCTION = 0;
    
    /**
     * Get the value given by the right stick's Y-axis. Applies dead zone and reduction.
     * @return Right Joystick's Y-axis
     */
	public double getRightStickY() {
		double rightStickY = rightStick.getY();
		// Dead zone management
		if(Math.abs(rightStick.getMagnitude()) < RIGHT_STICK_DEAD_ZONE) {
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
	
	/**
	 * Get the value given by the right stick's X-axis. Applies dead zone and reduction.
     * @return Right Joystick's X-axis
	 */
	public double getRightStickX() {
		double rightStickX = rightStick.getX();
		// Dead zone management
		if(Math.abs(rightStick.getMagnitude()) < RIGHT_STICK_DEAD_ZONE) {
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
	
	/**
	 * Get the value given by the left stick's Y-axis. Applies dead zone and reduction.
     * @return Left Joystick's Y-axis
	 */
	public double getLeftStickY() {
		double leftStickY = leftStick.getY();
		// Dead zone management
		if(Math.abs(leftStick.getMagnitude()) < LEFT_STICK_DEAD_ZONE) {
			leftStickY = 0;
		}
		// Reductions
		if(leftStickY > 0) {
			leftStickY = leftStickY - LEFT_STICK_REDUCTION;
			if(leftStickY < 0) {
				leftStickY = 0;
			}
		}
		else if(leftStickY < 0) {
			leftStickY = leftStickY + LEFT_STICK_REDUCTION;
			if(leftStickY > 0) {
				leftStickY = 0;
			}
		}
		return leftStickY;
	}
	
	/**
	 * Get the value given by the left stick's X-axis. Applies dead zone and reduction.
     * @return Left Joystick's X-axis
	 */
	public double getLeftStickX() {
		double leftStickX = leftStick.getX();
		// Dead zone management
		if(Math.abs(leftStick.getMagnitude()) < LEFT_STICK_DEAD_ZONE) {
			leftStickX = 0;
		}
		// Reductions
		if(leftStickX > 0) {
			leftStickX = leftStickX - LEFT_STICK_REDUCTION;
			if(leftStickX < 0) {
				leftStickX = 0;
			}
		}
		else if(leftStickX < 0) {
			leftStickX = leftStickX + LEFT_STICK_REDUCTION;
			if(leftStickX > 0) {
				leftStickX = 0;
			}
		}
		return leftStickX;
	}
	
	public double getLeftStickThrottle(){
		return leftStick.getThrottle();
	}
	public double getCorrectedLeftStickThrottle() {
		return (getLeftStickThrottle() + 1) * 0.5;
	}
}

