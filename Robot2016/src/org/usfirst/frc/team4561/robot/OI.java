package org.usfirst.frc.team4561.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4561.robot.commands.EnableArmDoubleTouringMode;
import org.usfirst.frc.team4561.robot.commands.EnableArmTouringMode;
import org.usfirst.frc.team4561.robot.commands.LowerArms;
import org.usfirst.frc.team4561.robot.commands.MoveArmTo;
import org.usfirst.frc.team4561.robot.commands.PIDGoalAlign;
import org.usfirst.frc.team4561.robot.commands.RaiseArms;
import org.usfirst.frc.team4561.robot.commands.ReverseDriveDirection;
import org.usfirst.frc.team4561.robot.commands.RollersIn;
import org.usfirst.frc.team4561.robot.commands.RollersOut;
import org.usfirst.frc.team4561.robot.commands.SetShooterSpeed;
import org.usfirst.frc.team4561.robot.commands.ToggleCamera;
import org.usfirst.frc.team4561.robot.triggers.ArmDownTrigger;
import org.usfirst.frc.team4561.robot.triggers.ArmPreset1Trigger;
import org.usfirst.frc.team4561.robot.triggers.ArmPreset2Trigger;
import org.usfirst.frc.team4561.robot.triggers.ArmPreset3Trigger;
import org.usfirst.frc.team4561.robot.triggers.ArmPreset4Trigger;
import org.usfirst.frc.team4561.robot.triggers.ArmPreset5Trigger;
import org.usfirst.frc.team4561.robot.triggers.ArmUpTrigger;
import org.usfirst.frc.team4561.robot.triggers.DPadDownTrigger;
import org.usfirst.frc.team4561.robot.triggers.DPadLeftTrigger;
import org.usfirst.frc.team4561.robot.triggers.DPadRightTrigger;
import org.usfirst.frc.team4561.robot.triggers.DPadUpTrigger;

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
//	private JoystickButton shooterPIDFlipButton = new JoystickButton(rightStick, 
//												  RobotMap.SHOOTER_PID_FLIP_BUTTON);
	
	// Left Stick Buttons
	private JoystickButton reverseDirectionButton = new JoystickButton(leftStick,
			RobotMap.REVERSE_DIRECTION_BUTTON);
//	private JoystickButton loaderButton = new JoystickButton(leftStick,
//			RobotMap.LOADER_BUTTON);
//	private JoystickButton lowShotButton = new JoystickButton(leftStick,
//			RobotMap.LOWSHOT_BUTTON);
	private JoystickButton rollInButton1 = new JoystickButton(rightStick, RobotMap.ROLL_IN_BUTTON_1);
	private JoystickButton rollInButton2 = new JoystickButton(rightStick, RobotMap.ROLL_IN_BUTTON_2);
	private JoystickButton rollInButton3 = new JoystickButton(leftStick, RobotMap.ROLL_IN_BUTTON_3);
	private JoystickButton rollInButton4 = new JoystickButton(leftStick, RobotMap.ROLL_IN_BUTTON_4);
	private JoystickButton rollOutButton1 = new JoystickButton(rightStick, RobotMap.ROLL_OUT_BUTTON_1);
	private JoystickButton rollOutButton2 = new JoystickButton(rightStick, RobotMap.ROLL_OUT_BUTTON_2);
	private JoystickButton rollOutButton3 = new JoystickButton(leftStick, RobotMap.ROLL_OUT_BUTTON_3);
	private JoystickButton rollOutButton4 = new JoystickButton(leftStick, RobotMap.ROLL_OUT_BUTTON_4);
	
	private JoystickButton cameraToggleButton = new JoystickButton(leftStick, RobotMap.CAMERA_TOGGLE_BUTTON);
	
	// Controller Buttons
//	private JoystickButton alignLeftButton = new JoystickButton(controller, RobotMap.ALIGN_LEFT_BUTTON);
//	private JoystickButton alignRightButton = new JoystickButton(controller, RobotMap.ALIGN_RIGHT_BUTTON);
//	private JoystickButton fireButton = new JoystickButton(controller, RobotMap.FIRE_BUTTON);
//	private JoystickButton moveArmDeltaUpButton = new JoystickButton(controller, RobotMap.MOVE_ARM_DELTA_UP);
//	private JoystickButton moveArmDeltaDownButton = new JoystickButton(controller, RobotMap.MOVE_ARM_DELTA_DOWN);
	
	// GamePad Buttons
	private JoystickButton raiseArmsButton = new JoystickButton(controller, RobotMap.RAISE_ARMS_BUTTON);
	private JoystickButton lowerArmsButton = new JoystickButton(controller, RobotMap.LOWER_ARMS_BUTTON);
	private JoystickButton armSingleTouringButton = new JoystickButton(controller, RobotMap.ARM_TOURING_BUTTON_1);
	private JoystickButton armDoubleTouringButton = new JoystickButton(controller, RobotMap.ARM_TOURING_BUTTON_2);
	
	// Secondary Controller Buttons
	private JoystickButton shooterFullButton = new JoystickButton(controller, RobotMap.SHOOTER_FULL_BUTTON);
	private JoystickButton shooterLowButton1 = new JoystickButton(controller, RobotMap.SHOOTER_LOW_BUTTON_1);
	private JoystickButton shooterLowButton2 = new JoystickButton(controller, RobotMap.SHOOTER_LOW_BUTTON_2);
	private JoystickButton shooterOffButton = new JoystickButton(controller, RobotMap.SHOOTER_OFF_BUTTON);
	
	// Controller Buttons
	private JoystickButton alignLeftButton = new JoystickButton(controller, RobotMap.ALIGN_LEFT_BUTTON);
	private JoystickButton alignRightButton = new JoystickButton(controller, RobotMap.ALIGN_RIGHT_BUTTON);
	private JoystickButton armPIDButton = new JoystickButton(controller, RobotMap.ARM_PID_BUTTON);
	private ArmPreset1Trigger armPreset1Trigger = new ArmPreset1Trigger();
	private ArmPreset2Trigger armPreset2Trigger = new ArmPreset2Trigger();
	private ArmPreset3Trigger armPreset3Trigger = new ArmPreset3Trigger();
	private ArmPreset4Trigger armPreset4Trigger = new ArmPreset4Trigger();
	private ArmPreset5Trigger armPreset5Trigger = new ArmPreset5Trigger();
	
	public ArmUpTrigger armUpTrigger = new ArmUpTrigger();
	private ArmDownTrigger armDownTrigger = new ArmDownTrigger();
	
	private DPadDownTrigger dPadDownTrigger = new DPadDownTrigger();
	private DPadUpTrigger dPadUpTrigger = new DPadUpTrigger();
	private DPadRightTrigger dPadRightTrigger = new DPadRightTrigger();
	private DPadLeftTrigger dPadLeftTrigger = new DPadLeftTrigger();
	
	// SmartDashboard triggers
//	private DSButton0Trigger dsButton0 = new DSButton0Trigger();
//	private DSButton1Trigger dsButton1 = new DSButton1Trigger();
//	private DSButton2Trigger dsButton2 = new DSButton2Trigger();
	
	public OI() {
		System.out.println("hi");
		// DriveTrain button command assignments
		reverseDirectionButton.whenPressed(new ReverseDriveDirection());
		
		// Arm button command assignment

		raiseArmsButton.whileHeld(new RaiseArms());
		lowerArmsButton.whileHeld(new LowerArms());
		armDoubleTouringButton.whileHeld(new EnableArmDoubleTouringMode());
		armSingleTouringButton.whileHeld(new EnableArmTouringMode());
		
		// Roller button command assignment
		rollInButton1.whileHeld(new RollersIn());
		rollInButton2.whileHeld(new RollersIn());
		rollInButton3.whileHeld(new RollersIn());
		rollInButton4.whileHeld(new RollersIn());
		rollOutButton1.whileHeld(new RollersOut());
		rollOutButton2.whileHeld(new RollersOut());
		rollOutButton3.whileHeld(new RollersOut());
		rollOutButton4.whileHeld(new RollersOut());
		
		// SmartDashboard trigger preparation
		SmartDashboard.putBoolean("DB/Button 0", false);
		SmartDashboard.putBoolean("DB/Button 1", false);
		SmartDashboard.putBoolean("DB/Button 2", false);
		
		
		// Camera button command assignments
		cameraToggleButton.whenPressed(new ToggleCamera());
		
		// Secondary Controller
		if(Robot.useSecondaryController()) {
			armPreset1Trigger.whenActive(new MoveArmTo(Robot.arm.presets.get("Top")));
			armPreset2Trigger.whenActive(new MoveArmTo(Robot.arm.presets.get("Middle")));
			armPreset3Trigger.whenActive(new MoveArmTo(Robot.arm.presets.get("Cheval")));
			armPreset4Trigger.whenActive(new MoveArmTo(Robot.arm.presets.get("Intake")));
			armPreset5Trigger.whenActive(new MoveArmTo(Robot.arm.presets.get("Bottom")));
			
			armUpTrigger.whileActive(new RaiseArms());
			armDownTrigger.whileActive(new LowerArms());
			//armUpTrigger.whenInactive(new MoveArmTo(Robot.arm.getCorrectedLeftEncoder()));
			//armDownTrigger.whenInactive(new MoveArmTo(Robot.arm.getCorrectedLeftEncoder()));
			
			shooterFullButton.whenPressed(new SetShooterSpeed(1.0));
			shooterLowButton1.whenPressed(new SetShooterSpeed(0.4));
			shooterLowButton2.whenPressed(new SetShooterSpeed(0.4));
			shooterOffButton.whenPressed(new SetShooterSpeed(0.0));
			
			dPadLeftTrigger.whenActive(new PIDGoalAlign(true));
			dPadRightTrigger.whenActive(new PIDGoalAlign(false));
		}
		else {
			alignLeftButton.whenPressed(new PIDGoalAlign(true));
			alignRightButton.whenPressed(new PIDGoalAlign(false));
		}
		
		// Unused
		// Shooter button command assignment
//		shooterPIDFlipButton.whenActive(new ToggleShooterPID());
		// Individual Motor Drive command assignments
//		driveFrontLeft.whileHeld(new IndividualMotorDrive(RobotMap.FRONT_LEFT_MOTOR_CAN));
//		driveRearLeft.whileHeld(new IndividualMotorDrive(RobotMap.REAR_LEFT_MOTOR_CAN));
//		driveRearRight.whileHeld(new IndividualMotorDrive(RobotMap.REAR_RIGHT_MOTOR_CAN));
//		driveFrontRight.whileHeld(new IndividualMotorDrive(RobotMap.FRONT_RIGHT_MOTOR_CAN));
		// SmartDashboard trigger command assignments
//		dsButton0.whenActive(new SwitchToCamera1());
//		dsButton1.whenActive(new SwitchToCamera2());
//		dsButton2.whenActive(new ToggleCamera());
		
//		moveArmDeltaDownButton.whenPressed(new MoveArmDelta(false));
//		moveArmDeltaUpButton.whenPressed(new MoveArmDelta(true));
		// Shooter button command assignment
//		fireButton.whileHeld(new Fire());
//		alignLeftButton.whileHeld(new PIDGoalAlign(true));
//		alignRightButton.whileHeld(new PIDGoalAlign(false));
		// Loader button command assignment
//		loaderButton.whileHeld(new LoadBall());
//		lowShotButton.whileHeld(new ShootLow());
	}
	
	// Joystick config
	// Right stick config
	public static double RIGHT_STICK_DEAD_ZONE = 0.25;
    public static double RIGHT_STICK_REDUCTION = 0.25;
    
    // Left stick config
    public static double LEFT_STICK_DEAD_ZONE = 0.25;
    public static double LEFT_STICK_REDUCTION = 0.25;
    
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
			rightStickY = (rightStickY - RIGHT_STICK_REDUCTION) * (4.0/3.0);
			if(rightStickY < 0) {
				rightStickY = 0;
			}
		}
		else if(rightStickY < 0) {
			rightStickY = (rightStickY + RIGHT_STICK_REDUCTION) * (4.0/3.0);
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
			rightStickX = (rightStickX - RIGHT_STICK_REDUCTION) * (4.0/3.0);
			if(rightStickX < 0) {
				rightStickX = 0;
			}
		}
		else if(rightStickX < 0) {
			rightStickX = (rightStickX + RIGHT_STICK_REDUCTION) * (4.0/3.0);
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
			leftStickY = (leftStickY - LEFT_STICK_REDUCTION) * (4.0/3.0);
			if(leftStickY < 0) {
				leftStickY = 0;
			}
		}
		else if(leftStickY < 0) {
			leftStickY = (leftStickY + LEFT_STICK_REDUCTION) * (4.0/3.0);
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
			leftStickX = (leftStickX - LEFT_STICK_REDUCTION) * (4.0/3.0);
			if(leftStickX < 0) {
				leftStickX = 0;
			}
		}
		else if(leftStickX < 0) {
			leftStickX = (leftStickX + LEFT_STICK_REDUCTION) * (4.0/3.0);
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
	
	public double getRightStickThrottle() {
		return rightStick.getThrottle();
	}
	public double getCorrectedRightStickThrottle() {
		return (-getRightStickThrottle() + 1) * 0.5;
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
	public double getDashboardSlider0() {
		return SmartDashboard.getNumber("DB/Slider 0");
	}
	public double getDashboardSlider1() {
		return SmartDashboard.getNumber("DB/Slider 1");
	}
	public double getDashboardSlider2() {
		return SmartDashboard.getNumber("DB/Slider 2");
	}
	public double getDashboardSlider3() {
		return SmartDashboard.getNumber("DB/Slider 3");
	}
	public int getControllerDpad() {
		return controller.getPOV();
	}
	public double getArmPIDAxis() {
		return controller.getZ(); // TODO: Find axis
	}
	public double getArmNonPIDAxis() {
		return controller.getX();
	}
	public boolean getDPadDownPressed() {
		return dPadDownTrigger.get();
	}
	public boolean getDPadUpPressed() {
		return dPadUpTrigger.get();
	}
	public boolean getArmPIDButton() {
		return armPIDButton.get();
	}
}

