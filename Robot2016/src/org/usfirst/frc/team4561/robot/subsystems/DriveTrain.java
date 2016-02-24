package org.usfirst.frc.team4561.robot.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team4561.robot.Robot;
import org.usfirst.frc.team4561.robot.RobotMap;
import org.usfirst.frc.team4561.robot.commands.ArcadeDrive;
import org.usfirst.frc.team4561.robot.commands.TankDrive;

/**
 *
 */
public class DriveTrain extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	// "tank" for TankDrive, "arcade" for ArcadeDrive
	private static String DRIVE_TYPE = "arcade";
	
	// "talon" for TalonSRs, "victor" for Victors, "cantalon" for CANTalons or CANTalonSRXs
	private static String MOTOR_TYPE = "cantalon";
	
	// Squares the input values, such that 0.5 power becomes 0.25, etc.
	private static boolean squaredInputs = false;
	
	private SpeedController leftFront;
	private SpeedController leftRear;
	private SpeedController rightFront;
	private SpeedController rightRear;
	
	private RobotDrive robotDrive;
	
	private boolean reversed = false;
	
	public DriveTrain() {
		constructMotorControllers();
		if(Robot.isVerbose()) {
			System.out.println("Initializing DriveTrain Subsystem");
		}
	}
	 
    public void initDefaultCommand() {
    	if(DRIVE_TYPE == "arcade") {
    		setDefaultCommand(new ArcadeDrive());
    	}
    	else if(DRIVE_TYPE == "tank") {
    		setDefaultCommand(new TankDrive());
    	}
    	else {
    		System.out.println("The specified drive type is unidentifiable.");
	    		System.out.println("The DriveTrain will not function as intended.");
	    		System.out.println("Reference the \"DRIVE_TYPE\" variable in the DriveTrain subsystem to fix.");
	    	}
	    }

    public void constructMotorControllers() {
    	if(MOTOR_TYPE == "talon") {
    		leftFront = new Talon(RobotMap.FRONT_LEFT_MOTOR_CAN);
    		leftRear = new Talon(RobotMap.REAR_LEFT_MOTOR_CAN);
    		rightFront = new Talon(RobotMap.FRONT_RIGHT_MOTOR_CAN);
    		rightRear = new Talon(RobotMap.REAR_RIGHT_MOTOR_CAN);
    		
    		robotDrive = new RobotDrive(leftFront, leftRear,
    				rightFront, rightRear);
    	}
    	else if(MOTOR_TYPE == "victor") {
    		leftFront = new Talon(RobotMap.FRONT_LEFT_MOTOR_CAN);
    		leftRear = new Victor(RobotMap.REAR_LEFT_MOTOR_CAN);
    		rightFront = new Talon(RobotMap.FRONT_RIGHT_MOTOR_CAN);
    		rightRear = new Victor(RobotMap.REAR_RIGHT_MOTOR_CAN);
    		
    		leftFront.setInverted(true);
    		leftRear.setInverted(true);
    		rightFront.setInverted(true);
    		rightRear.setInverted(true);
    		
    		
    		robotDrive = new RobotDrive(leftFront, leftRear,
    				rightFront, rightRear);
    	}
    	else if(MOTOR_TYPE == "cantalon") {
    		leftFront = new CANTalon(RobotMap.FRONT_LEFT_MOTOR_CAN);
    		leftRear = new CANTalon(RobotMap.REAR_LEFT_MOTOR_CAN);
    		rightFront = new CANTalon(RobotMap.FRONT_RIGHT_MOTOR_CAN);
    		rightRear = new CANTalon(RobotMap.REAR_RIGHT_MOTOR_CAN);
    		
    		((CANTalon)leftFront).enableBrakeMode(true);
    		((CANTalon)leftRear).enableBrakeMode(true);
    		((CANTalon)rightFront).enableBrakeMode(true);
    		((CANTalon)rightRear).enableBrakeMode(true);
    		
    		((CANTalon)leftFront).setInverted(true);
    		((CANTalon)leftRear).setInverted(true);
    		((CANTalon)rightFront).setInverted(true);
    		((CANTalon)rightRear).setInverted(true);
    		
    		robotDrive = new RobotDrive(leftFront, leftRear,
    				rightFront, rightRear);
    	}
    	else {
    		System.out.println("The specified motor type is unidentifiable.");
    		System.out.println("The DriveTrain will not function as intended.");
    		System.out.println("Reference the \"MOTOR_TYPE\" varible in the DriveTrain subsystem to fix.");
    	}
    }
	
	public void driveArcade(double drive, double rot) {
		robotDrive.arcadeDrive(drive, rot, squaredInputs);
	}
	
	public void driveTank(double left, double right) {
		robotDrive.tankDrive(left, right, squaredInputs);
	}
	
	public void driveSingleMotor(int motorID) {
		if(motorID == RobotMap.FRONT_LEFT_MOTOR_CAN) {
			leftFront.set(0.2);
		}
		else if(motorID == RobotMap.REAR_LEFT_MOTOR_CAN) {
			leftRear.set(0.2);
		}
		else if(motorID == RobotMap.FRONT_RIGHT_MOTOR_CAN) {
			rightFront.set(0.2);
		}
		else if(motorID == RobotMap.REAR_RIGHT_MOTOR_CAN) {
			rightRear.set(0.2);
		}
	}
	
	public void reverseDriveDirection() {
		reversed = !reversed;
	}
	
	public boolean isReversed() {
		return reversed;
	}
	
	public boolean isTouringMode() {
		return Robot.oi.isTouringMode();
	}
	
	public void stop() {
		leftFront.set(0);
		leftRear.set(0);
		rightFront.set(0);
		rightRear.set(0);
	}
}

