package org.usfirst.frc.team4561.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4561.robot.commands.ArcadeDrive;
import org.usfirst.frc.team4561.robot.commands.Fire;
import org.usfirst.frc.team4561.robot.commands.IndividualMotorDrive;
import org.usfirst.frc.team4561.robot.commands.LowerArms;
import org.usfirst.frc.team4561.robot.commands.MoveArmDelta;
import org.usfirst.frc.team4561.robot.commands.MoveArmTo;
import org.usfirst.frc.team4561.robot.commands.PIDGoalAlign;
import org.usfirst.frc.team4561.robot.commands.RaiseArms;
import org.usfirst.frc.team4561.robot.commands.ReverseDriveDirection;
import org.usfirst.frc.team4561.robot.commands.LoadBall;
import org.usfirst.frc.team4561.robot.commands.RollersIn;
import org.usfirst.frc.team4561.robot.commands.RollersOut;
import org.usfirst.frc.team4561.robot.commands.ToggleShooterPID;
import org.usfirst.frc.team4561.robot.commands.SwitchToCamera1;
import org.usfirst.frc.team4561.robot.commands.SwitchToCamera2;
import org.usfirst.frc.team4561.robot.commands.ToggleCamera;
import org.usfirst.frc.team4561.robot.triggers.DSButton0Trigger;
import org.usfirst.frc.team4561.robot.triggers.DSButton1Trigger;
import org.usfirst.frc.team4561.robot.triggers.DSButton2Trigger;
import org.usfirst.frc.team4561.robot.commands.ShootLow;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	// Joysticks
	private static Joystick rightStick = new Joystick(RobotMap.RIGHT_JOYSTICK);
	private static Joystick leftStick = new Joystick(RobotMap.LEFT_JOYSTICK);
	private static Joystick controller = new Joystick(RobotMap.CONTROLLER);
	
	// Individual motor drive buttons
//	private JoystickButton driveFrontLeft = new JoystickButton(leftStick,
//			RobotMap.FRONT_LEFT_MOTOR_BUTTON);
//	private JoystickButton driveRearLeft = new JoystickButton(leftStick,
//			RobotMap.REAR_LEFT_MOTOR_BUTTON);
//	private JoystickButton driveFrontRight = new JoystickButton(leftStick,
//			RobotMap.FRONT_RIGHT_MOTOR_BUTTON);
//	private JoystickButton driveRearRight = new JoystickButton(leftStick,
//			RobotMap.REAR_RIGHT_MOTOR_BUTTON);
	
	// Right Stick Buttons
	private JoystickButton touringModeButton = new JoystickButton(rightStick, RobotMap.TOURING_MODE_BUTTON);
	private JoystickButton shooterPIDFlipButton = new JoystickButton(rightStick, 
												  RobotMap.SHOOTER_PID_FLIP_BUTTON);
	
	// Left Stick Buttons
	private JoystickButton reverseDirectionButton = new JoystickButton(leftStick,
			RobotMap.REVERSE_DIRECTION_BUTTON);
	private JoystickButton loaderButton = new JoystickButton(leftStick,
			RobotMap.LOADER_BUTTON);
	private JoystickButton lowShotButton = new JoystickButton(leftStick,
			RobotMap.LOWSHOT_BUTTON);
	private JoystickButton rollInButton = new JoystickButton(rightStick, RobotMap.ROLL_IN_BUTTON);
	private JoystickButton rollOutButton = new JoystickButton(leftStick, RobotMap.ROLL_OUT_BUTTON);
	private JoystickButton cameraToggleButton = new JoystickButton(leftStick, RobotMap.CAMERA_TOGGLE_BUTTON);
	
	// Controller Buttons
//	private JoystickButton alignLeftButton = new JoystickButton(controller, RobotMap.ALIGN_LEFT_BUTTON);
//	private JoystickButton alignRightButton = new JoystickButton(controller, RobotMap.ALIGN_RIGHT_BUTTON);
//	private JoystickButton fireButton = new JoystickButton(controller, RobotMap.FIRE_BUTTON);
	private JoystickButton moveArmDeltaUpButton = new JoystickButton(controller, RobotMap.MOVE_ARM_DELTA_UP);
	private JoystickButton moveArmDeltaDownButton = new JoystickButton(controller, RobotMap.MOVE_ARM_DELTA_DOWN);
	private JoystickButton raiseArmsButton = new JoystickButton(controller, RobotMap.RAISE_ARMS_BUTTON);
	private JoystickButton lowerArmsButton = new JoystickButton(controller, RobotMap.LOWER_ARMS_BUTTON);
	private JoystickButton armPreset1 = new JoystickButton(controller, RobotMap.ARM_PRESET_1);
	private JoystickButton armPreset2 = new JoystickButton(controller, RobotMap.ARM_PRESET_2);
	private JoystickButton armPreset3 = new JoystickButton(controller, RobotMap.ARM_PRESET_3);
	private JoystickButton armPreset4 = new JoystickButton(controller, RobotMap.ARM_PRESET_4);
	
	// SmartDashboard triggers
	private DSButton0Trigger dsButton0 = new DSButton0Trigger();
	private DSButton1Trigger dsButton1 = new DSButton1Trigger();
	private DSButton2Trigger dsButton2 = new DSButton2Trigger();
	
	public OI() {
		
		// DriveTrain button command assignments
		reverseDirectionButton.whenPressed(new ReverseDriveDirection());
//		alignLeftButton.whileHeld(new PIDGoalAlign(true));
//		alignRightButton.whileHeld(new PIDGoalAlign(false));
		
		// Loader button command assignment
		loaderButton.whileHeld(new LoadBall());
		lowShotButton.whileHeld(new ShootLow());
		
		// Shooter button command assignment
//		fireButton.whileHeld(new Fire());
		
		// Arm button command assignment
		moveArmDeltaDownButton.whenPressed(new MoveArmDelta(false));
		moveArmDeltaUpButton.whenPressed(new MoveArmDelta(true));
		raiseArmsButton.whileHeld(new RaiseArms());
		lowerArmsButton.whileHeld(new LowerArms());
		armPreset1.whileHeld(new MoveArmTo(Robot.arm.presets.get("Bottom")));
		armPreset2.whileHeld(new MoveArmTo(Robot.arm.presets.get("Intake")));
		armPreset3.whileHeld(new MoveArmTo(Robot.arm.presets.get("Middle")));
		armPreset4.whileHeld(new MoveArmTo(Robot.arm.presets.get("Top")));
		
		// Roller button command assignment
		rollInButton.whileHeld(new RollersIn());
		rollInButton.whenPressed(new RollersIn());
		rollOutButton.whileHeld(new RollersOut());
		
		// SmartDashboard trigger preparation
		SmartDashboard.putBoolean("DB/Button 0", false);
		SmartDashboard.putBoolean("DB/Button 1", false);
		SmartDashboard.putBoolean("DB/Button 2", false);
		
		// SmartDashboard trigger command assignments
//		dsButton0.whenActive(new SwitchToCamera1());
//		dsButton1.whenActive(new SwitchToCamera2());
//		dsButton2.whenActive(new ToggleCamera());
		
		// Camera button command assignments
		cameraToggleButton.whenPressed(new ToggleCamera());
		
		// Individual Motor Drive command assignments
//		driveFrontLeft.whileHeld(new IndividualMotorDrive(RobotMap.FRONT_LEFT_MOTOR_CAN));
//		driveRearLeft.whileHeld(new IndividualMotorDrive(RobotMap.REAR_LEFT_MOTOR_CAN));
//		driveRearRight.whileHeld(new IndividualMotorDrive(RobotMap.REAR_RIGHT_MOTOR_CAN));
//		driveFrontRight.whileHeld(new IndividualMotorDrive(RobotMap.FRONT_RIGHT_MOTOR_CAN));
		
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
	
	public double getLeftStickThrottle() {
		return leftStick.getThrottle();
	}
	public double getCorrectedLeftStickThrottle() {
		return (-getLeftStickThrottle() + 1) * 0.5;
	}
	
	public boolean isTouringMode() {
		return touringModeButton.get();
	}
	
	public boolean getDashboardButton0() {
		return SmartDashboard.getBoolean("DB/Button 0", false);
	}
	public boolean getDashboardButton1() {
		return SmartDashboard.getBoolean("DB/Button 1", false);
	}
	public boolean getDashboardButton2() {
		return SmartDashboard.getBoolean("DB/Button 2", false);
	}
	public boolean getDashboardButton3() {
		return SmartDashboard.getBoolean("DB/Button 3", false);
	}
	
}

