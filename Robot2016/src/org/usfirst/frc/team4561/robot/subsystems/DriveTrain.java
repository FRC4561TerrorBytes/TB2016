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
	private static String DRIVE_TYPE = "tank";
	
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
	
	public void putDebugInfo() {
		Robot.getDebugTable().putNumber("DriveTrain/leftFront/Status", ((CANTalon)leftFront).get());
		Robot.getDebugTable().putNumber("DriveTrain/leftFront/BusVoltage", ((CANTalon)leftFront).getBusVoltage());
		Robot.getDebugTable().putNumber("DriveTrain/leftFront/OutputCurrent", ((CANTalon)leftFront).getOutputCurrent());
		Robot.getDebugTable().putNumber("DriveTrain/leftFront/OutputVoltage", ((CANTalon)leftFront).getOutputVoltage());
		Robot.getDebugTable().putNumber("DriveTrain/leftFront/Temperature", ((CANTalon)leftFront).getTemperature());
		Robot.getDebugTable().putNumber("DriveTrain/leftFront/DeviceID", ((CANTalon)leftFront).getDeviceID());
		Robot.getDebugTable().putNumber("DriveTrain/leftFront/FirmwareVersion", ((CANTalon)leftFront).GetFirmwareVersion());
		Robot.getDebugTable().putBoolean("DriveTrain/leftFront/IsInverted", ((CANTalon)leftFront).getInverted());
		Robot.getDebugTable().putBoolean("DriveTrain/leftFront/IsAlive", ((CANTalon)leftFront).isAlive());
		Robot.getDebugTable().putBoolean("DriveTrain/leftFront/ControlEnabled", ((CANTalon)leftFront).isControlEnabled());
		Robot.getDebugTable().putBoolean("DriveTrain/leftFront/IsEnabled", ((CANTalon)leftFront).isEnabled());
		
		Robot.getDebugTable().putNumber("DriveTrain/leftRear/Status", ((CANTalon)leftRear).get());
		Robot.getDebugTable().putNumber("DriveTrain/leftRear/BusVoltage", ((CANTalon)leftRear).getBusVoltage());
		Robot.getDebugTable().putNumber("DriveTrain/leftRear/OutputCurrent", ((CANTalon)leftRear).getOutputCurrent());
		Robot.getDebugTable().putNumber("DriveTrain/leftRear/OutputVoltage", ((CANTalon)leftRear).getOutputVoltage());
		Robot.getDebugTable().putNumber("DriveTrain/leftRear/Temperature", ((CANTalon)leftRear).getTemperature());
		Robot.getDebugTable().putNumber("DriveTrain/leftRear/DeviceID", ((CANTalon)leftRear).getDeviceID());
		Robot.getDebugTable().putNumber("DriveTrain/leftRear/FirmwareVersion", ((CANTalon)leftRear).GetFirmwareVersion());
		Robot.getDebugTable().putBoolean("DriveTrain/leftRear/IsInverted", ((CANTalon)leftRear).getInverted());
		Robot.getDebugTable().putBoolean("DriveTrain/leftRear/IsAlive", ((CANTalon)leftRear).isAlive());
		Robot.getDebugTable().putBoolean("DriveTrain/leftRear/ControlEnabled", ((CANTalon)leftRear).isControlEnabled());
		Robot.getDebugTable().putBoolean("DriveTrain/leftRear/IsEnabled", ((CANTalon)leftRear).isEnabled());
		
		Robot.getDebugTable().putNumber("DriveTrain/rightFront/Status", ((CANTalon)rightFront).get());
		Robot.getDebugTable().putNumber("DriveTrain/rightFront/BusVoltage", ((CANTalon)rightFront).getBusVoltage());
		Robot.getDebugTable().putNumber("DriveTrain/rightFront/OutputCurrent", ((CANTalon)rightFront).getOutputCurrent());
		Robot.getDebugTable().putNumber("DriveTrain/rightFront/OutputVoltage", ((CANTalon)rightFront).getOutputVoltage());
		Robot.getDebugTable().putNumber("DriveTrain/rightFront/Temperature", ((CANTalon)rightFront).getTemperature());
		Robot.getDebugTable().putNumber("DriveTrain/rightFront/DeviceID", ((CANTalon)rightFront).getDeviceID());
		Robot.getDebugTable().putNumber("DriveTrain/rightFront/FirmwareVersion", ((CANTalon)rightFront).GetFirmwareVersion());
		Robot.getDebugTable().putBoolean("DriveTrain/rightFront/IsInverted", ((CANTalon)rightFront).getInverted());
		Robot.getDebugTable().putBoolean("DriveTrain/rightFront/IsAlive", ((CANTalon)rightFront).isAlive());
		Robot.getDebugTable().putBoolean("DriveTrain/rightFront/ControlEnabled", ((CANTalon)rightFront).isControlEnabled());
		Robot.getDebugTable().putBoolean("DriveTrain/rightFront/IsEnabled", ((CANTalon)rightFront).isEnabled());
	
		Robot.getDebugTable().putNumber("DriveTrain/rightRear/Status", ((CANTalon)rightRear).get());
		Robot.getDebugTable().putNumber("DriveTrain/rightRear/BusVoltage", ((CANTalon)rightRear).getBusVoltage());
		Robot.getDebugTable().putNumber("DriveTrain/rightRear/OutputCurrent", ((CANTalon)rightRear).getOutputCurrent());
		Robot.getDebugTable().putNumber("DriveTrain/rightRear/OutputVoltage", ((CANTalon)rightRear).getOutputVoltage());
		Robot.getDebugTable().putNumber("DriveTrain/rightRear/Temperature", ((CANTalon)rightRear).getTemperature());
		Robot.getDebugTable().putNumber("DriveTrain/rightRear/DeviceID", ((CANTalon)rightRear).getDeviceID());
		Robot.getDebugTable().putNumber("DriveTrain/rightRear/FirmwareVersion", ((CANTalon)rightRear).GetFirmwareVersion());
		Robot.getDebugTable().putBoolean("DriveTrain/rightRear/IsInverted", ((CANTalon)rightRear).getInverted());
		Robot.getDebugTable().putBoolean("DriveTrain/rightRear/IsAlive", ((CANTalon)rightRear).isAlive());
		Robot.getDebugTable().putBoolean("DriveTrain/rightRear/ControlEnabled", ((CANTalon)rightRear).isControlEnabled());
		Robot.getDebugTable().putBoolean("DriveTrain/rightRear/IsEnabled", ((CANTalon)rightRear).isEnabled());
		
		Robot.getDebugTable().putBoolean("DriveTrain/robotDrive/IsAlive", robotDrive.isAlive());
		Robot.getDebugTable().putBoolean("DriveTrain/robotDrive/IsSafetyEnabled", robotDrive.isSafetyEnabled());
		
		Robot.getDebugTable().putString("DriveTrain/motorType", MOTOR_TYPE);
		Robot.getDebugTable().putString("DriveTrain/motorType", DRIVE_TYPE);
		Robot.getDebugTable().putBoolean("DriveTrain/squaredInputs", squaredInputs);
		Robot.getDebugTable().putBoolean("DriveTrain/reversed", reversed);
	}
}

