package org.usfirst.frc.team4561.robot.subsystems;

import org.usfirst.frc.team4561.robot.Robot;
import org.usfirst.frc.team4561.robot.RobotMap;
import org.usfirst.frc.team4561.robot.commands.FlyWheels;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

import java.lang.Math;

public class Shooter extends PIDSubsystem {
	
	private CANTalon leftMotor = new CANTalon(RobotMap.LEFT_SHOOTER_MOTOR);
	private CANTalon rightMotor = new CANTalon(RobotMap.RIGHT_SHOOTER_MOTOR);
	
	private static Encoder shooterEncoder = new Encoder(RobotMap.SHOOTER_ENCODER_A_SOURCE,
												 RobotMap.SHOOTER_ENCODER_B_SOURCE);
	
	private static final double PERIOD = 0.05;
	
	private final double WHEEL_RADIUS = 2; // Inches
	private final double WHEEL_CIRCUMFERENCE = 2 * Math.PI * WHEEL_RADIUS; // Inches
	private final double ENCODER_TICKS = 2048; // TODO: Verify
	private final double DISTANCE_PER_PULSE = WHEEL_CIRCUMFERENCE / ENCODER_TICKS; // Inches per tick
	
	private boolean usePID = true;
	
	public Shooter() {
		super(0, 0, 0, 0, PERIOD); // TODO: Tune PDF values, I term is obsolete in velocity mode.
		
		leftMotor.enableBrakeMode(true);
		rightMotor.enableBrakeMode(true);
		
		shooterEncoder.setPIDSourceType(PIDSourceType.kRate);
		shooterEncoder.setDistancePerPulse(DISTANCE_PER_PULSE);
	}

	protected void initDefaultCommand() {
		setDefaultCommand(new FlyWheels());
	}

	public void stop(){
		leftMotor.set(0);
		rightMotor.set(0);
	}

	public void setInchesPerSecond(double inchesPerSecond) {
		setSetpoint(inchesPerSecond);
	}
	
	public void setRPM(double rpm) {
		double inchesPerSecond = (rpm * WHEEL_CIRCUMFERENCE) / 60;
		setSetpoint(inchesPerSecond);
	}
	
	public void setRPS(double rps) {
		double inchesPerSecond = rps * WHEEL_CIRCUMFERENCE;
		setSetpoint(inchesPerSecond);
	}
	
	public double getRPM() {
		double rpm = (shooterEncoder.pidGet() / WHEEL_CIRCUMFERENCE) * 60;
		return rpm;
	}
	
	public double getRPS() {
		double rps = shooterEncoder.pidGet() * WHEEL_CIRCUMFERENCE;
		return rps;
	}
	
	public double getInchesPerSecond() {
		return shooterEncoder.pidGet();
	}
	
	protected double returnPIDInput() {
		return shooterEncoder.pidGet(); //TODO: Test to see if this is the correct method.
	}
	
	protected void usePIDOutput(double output) {
		if(usePID){
			leftMotor.set(output);
			rightMotor.set(output);
		}
		else{
			double correctedThrottle = (Robot.oi.getCorrectedLeftStickThrottle());
			leftMotor.set(correctedThrottle); 
			rightMotor.set(correctedThrottle);
		}
		
	}
}
