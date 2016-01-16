package org.usfirst.frc.team4561.robot.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

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
	private static String driveType = "tank";
	
	// "talon" for TalonSRs, "victor" for Victors, "cantalon" for CANTalons or CANTalonSRXs
	private static String motorType = "cantalon";
	
	private SpeedController leftFront;
	private SpeedController leftRear;
	private SpeedController rightFront;
	private SpeedController rightRear;
	
	private RobotDrive robotDrive;
	
	public DriveTrain() {
		constructMotorControllers();
	}
	 
    public void initDefaultCommand() {
    	if(driveType == "arcade") {
    		setDefaultCommand(new ArcadeDrive());
    	}
    	else if(driveType == "tank") {
    		setDefaultCommand(new TankDrive());
    	}
    	else {
    		System.out.println("The specified drive type is unidentifiable.");
    		System.out.println("The DriveTrain will not function as intended.");
    		System.out.println("Reference the \"driveType\" variable in the DriveTrain subsystem to fix.");
    	}
    }

    public void constructMotorControllers() {
    	if(motorType == "talon") {
    		leftFront = new Talon(RobotMap.FRONT_LEFT_MOTOR_CAN);
    		leftRear = new Talon(RobotMap.BACK_LEFT_MOTOR_CAN);
    		rightFront = new Talon(RobotMap.FRONT_RIGHT_MOTOR_CAN);
    		rightRear = new Talon(RobotMap.BACK_RIGHT_MOTOR_CAN);
    		
    		robotDrive = new RobotDrive(leftFront, leftRear,
    				rightFront, rightRear);
    	}
    	else if(motorType == "victor") {
    		leftFront = new Victor(RobotMap.FRONT_LEFT_MOTOR_CAN);
    		leftRear = new Victor(RobotMap.BACK_LEFT_MOTOR_CAN);
    		rightFront = new Victor(RobotMap.FRONT_RIGHT_MOTOR_CAN);
    		rightRear = new Victor(RobotMap.BACK_RIGHT_MOTOR_CAN);
    		
    		robotDrive = new RobotDrive(leftFront, leftRear,
    				rightFront, rightRear);
    	}
    	else if(motorType == "cantalon") {
    		leftFront = new CANTalon(RobotMap.FRONT_LEFT_MOTOR_CAN);
    		leftRear = new CANTalon(RobotMap.BACK_LEFT_MOTOR_CAN);
    		rightFront = new CANTalon(RobotMap.FRONT_RIGHT_MOTOR_CAN);
    		rightRear = new CANTalon(RobotMap.BACK_RIGHT_MOTOR_CAN);
    		
    		robotDrive = new RobotDrive(leftFront, leftRear,
    				rightFront, rightRear);
    	}
    	else {
    		System.out.println("The specified motor type is unidentifiable.");
    		System.out.println("The DriveTrain will not function as intended.");
    		System.out.println("Reference the \"motorType\" varible in the DriveTrain subsystem to fix.");
    	}
    }
	
	public void driveArcade(double drive, double rot) {
		robotDrive.arcadeDrive(drive, rot, true);
	}
	
	public void driveTank(double left, double right) {
		robotDrive.tankDrive(left, right, true);
	}
	
	public void stop() {
		leftFront.set(0);
		leftRear.set(0);
		rightFront.set(0);
		rightRear.set(0);
	}
}

