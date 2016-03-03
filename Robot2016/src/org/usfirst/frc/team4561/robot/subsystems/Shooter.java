package org.usfirst.frc.team4561.robot.subsystems;

import org.usfirst.frc.team4561.robot.Robot;
import org.usfirst.frc.team4561.robot.RobotMap;
import org.usfirst.frc.team4561.robot.commands.SetShooterSpeed;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.lang.Math;

public class Shooter extends PIDSubsystem {
	
	private CANTalon leftMotor = new CANTalon(RobotMap.LEFT_SHOOTER_MOTOR);
	private CANTalon rightMotor = new CANTalon(RobotMap.RIGHT_SHOOTER_MOTOR);
	
	private static Encoder shooterEncoder = new Encoder(RobotMap.SHOOTER_ENCODER_A_SOURCE,
												 RobotMap.SHOOTER_ENCODER_B_SOURCE);
	
	
	private final double WHEEL_RADIUS = 2; // Inches
	private final double WHEEL_CIRCUMFERENCE = 2 * Math.PI * WHEEL_RADIUS; // Inches
	
	private double speed = 0;
	
	private final double ENCODER_TICKS = 1024; // TODO: Verify
	private final double DISTANCE_PER_PULSE = 1.0 / ENCODER_TICKS; // Revolutions per tick
	
	private boolean usePID = false;
	
	public Shooter() {
		super("Shooter", 0, 0, 0, 0); // TODO: Tune PDF values, I term is obsolete in velocity mode.
		
		if(Robot.isVerbose()) {
			System.out.println("Initializing Shooter Subsystem");
		}
		
		leftMotor.setInverted(true);
		rightMotor.setInverted(true);
		
		leftMotor.enableBrakeMode(false);
		rightMotor.enableBrakeMode(false);
		
		shooterEncoder.setDistancePerPulse(DISTANCE_PER_PULSE);
		
		getPIDController().setAbsoluteTolerance(5);
	}

	protected void initDefaultCommand() {
	}

	public void stop(){
		leftMotor.set(0);
		rightMotor.set(0);
	}
	
	public void setPower(double power) {
		speed = power;
	}

	public void setInchesPerSecond(double inchesPerSecond) {
		setSetpoint(inchesPerSecond/WHEEL_CIRCUMFERENCE);
	}
	
	public void setRPM(double rpm) {
		setSetpoint(rpm/60.0);
	}
	
	public void setRPS(double rps) {
		setSetpoint(rps);
	}
	
	public double getRPM() {
		return shooterEncoder.getRate() * 60;
	}
	
	public double getRPS() {
		return shooterEncoder.getRate();
	}
	
	public double getInchesPerSecond() {
		return shooterEncoder.getRate() * WHEEL_CIRCUMFERENCE;
	}
	
	protected double returnPIDInput() {
		return shooterEncoder.getRate();
	}
	
	protected void usePIDOutput(double output) {
		if(usePID) {
			leftMotor.set(output);
			rightMotor.set(output);
		}
		else {
			double correctedThrottle = (Robot.oi.getCorrectedLeftStickThrottle());
			leftMotor.set(speed);
			rightMotor.set(speed);
			
		}
		
		if(Robot.isInDebugMode()) {
			Robot.getDebugTable().putNumber("Shooter/leftMotor/Status", leftMotor.get());
			Robot.getDebugTable().putNumber("Shooter/leftMotor/BusVoltage", leftMotor.getBusVoltage());
			Robot.getDebugTable().putNumber("Shooter/leftMotor/OutputCurrent", leftMotor.getOutputCurrent());
			Robot.getDebugTable().putNumber("Shooter/leftMotor/OutputVoltage", leftMotor.getOutputVoltage());
			Robot.getDebugTable().putNumber("Shooter/leftMotor/Temperature", leftMotor.getTemperature());
			Robot.getDebugTable().putNumber("Shooter/leftMotor/DeviceID", leftMotor.getDeviceID());
			Robot.getDebugTable().putNumber("Shooter/leftMotor/FirmwareVersion", leftMotor.GetFirmwareVersion());
			Robot.getDebugTable().putBoolean("Shooter/leftMotor/IsInverted", leftMotor.getInverted());
			Robot.getDebugTable().putBoolean("Shooter/leftMotor/IsAlive", leftMotor.isAlive());
			Robot.getDebugTable().putBoolean("Shooter/leftMotor/ControlEnabled", leftMotor.isControlEnabled());
			Robot.getDebugTable().putBoolean("Shooter/leftMotor/IsEnabled", leftMotor.isEnabled());
		
			Robot.getDebugTable().putNumber("Shooter/rightMotor/Status", rightMotor.get());
			Robot.getDebugTable().putNumber("Shooter/rightMotor/BusVoltage", rightMotor.getBusVoltage());
			Robot.getDebugTable().putNumber("Shooter/rightMotor/OutputCurrent", rightMotor.getOutputCurrent());
			Robot.getDebugTable().putNumber("Shooter/rightMotor/OutputVoltage", rightMotor.getOutputVoltage());
			Robot.getDebugTable().putNumber("Shooter/rightMotor/Temperature", rightMotor.getTemperature());
			Robot.getDebugTable().putNumber("Shooter/rightMotor/DeviceID", rightMotor.getDeviceID());
			Robot.getDebugTable().putNumber("Shooter/rightMotor/FirmwareVersion", rightMotor.GetFirmwareVersion());
			Robot.getDebugTable().putBoolean("Shooter/rightMotor/IsInverted", rightMotor.getInverted());
			Robot.getDebugTable().putBoolean("Shooter/rightMotor/IsAlive", rightMotor.isAlive());
			Robot.getDebugTable().putBoolean("Shooter/rightMotor/ControlEnabled", rightMotor.isControlEnabled());
			Robot.getDebugTable().putBoolean("Shooter/rightMotor/IsEnabled", rightMotor.isEnabled());
			
			Robot.getDebugTable().putBoolean("Shooter/shooterEncoder/Direction", shooterEncoder.getDirection());
			Robot.getDebugTable().putBoolean("Shooter/shooterEncoder/Stopped", shooterEncoder.getStopped());
			Robot.getDebugTable().putNumber("Shooter/shooterEncoder/CurrentCount", shooterEncoder.get());
			Robot.getDebugTable().putNumber("Shooter/shooterEncoder/Distance", shooterEncoder.getDistance());
			Robot.getDebugTable().putNumber("Shooter/shooterEncoder/Rate", shooterEncoder.getRate());
			Robot.getDebugTable().putNumber("Shooter/shooterEncoder/encodingScale", shooterEncoder.getEncodingScale());
			Robot.getDebugTable().putNumber("Shooter/shooterEncoder/FPGAIndex", shooterEncoder.getFPGAIndex());
			Robot.getDebugTable().putNumber("Shooter/shooterEncoder/RawCount", shooterEncoder.getRaw());
			Robot.getDebugTable().putNumber("Shooter/shooterEncoder/SamplesToAverage", shooterEncoder.getSamplesToAverage());
			
			Robot.getDebugTable().putBoolean("Shooter/usePID", usePID);
			
			Robot.getDebugTable().putNumber("Shooter/PIDController/Output", getPIDController().get());
			Robot.getDebugTable().putNumber("Shooter/PIDController/AverageError", getPIDController().getAvgError());
			Robot.getDebugTable().putNumber("Shooter/PIDController/P", getPIDController().getP());
			Robot.getDebugTable().putNumber("Shooter/PIDController/I", getPIDController().getI());
			Robot.getDebugTable().putNumber("Shooter/PIDController/D", getPIDController().getD());
			Robot.getDebugTable().putNumber("Shooter/PIDController/F", getPIDController().getF());
			Robot.getDebugTable().putNumber("Shooter/PIDController/DeltaSetpoint", getPIDController().getDeltaSetpoint());
			Robot.getDebugTable().putNumber("Shooter/PIDController/Error", getPIDController().getError());
			Robot.getDebugTable().putNumber("Shooter/PIDController/Setpoint", getPIDController().getSetpoint());
			Robot.getDebugTable().putBoolean("Shooter/PIDController/IsEnabled", getPIDController().isEnabled());
			Robot.getDebugTable().putBoolean("Shooter/PIDController/OnTarget", getPIDController().onTarget());
		}
		
	}
	public void togglePID(){
		usePID = !usePID;
	}
}
