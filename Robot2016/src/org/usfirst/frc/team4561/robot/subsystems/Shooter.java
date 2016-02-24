package org.usfirst.frc.team4561.robot.subsystems;

import org.usfirst.frc.team4561.robot.Robot;
import org.usfirst.frc.team4561.robot.RobotMap;
import org.usfirst.frc.team4561.robot.commands.SetShooterSpeed;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

import java.lang.Math;

public class Shooter extends PIDSubsystem {
	
	private CANTalon leftMotor = new CANTalon(RobotMap.LEFT_SHOOTER_MOTOR);
	private CANTalon rightMotor = new CANTalon(RobotMap.RIGHT_SHOOTER_MOTOR);
	
	private static Encoder shooterEncoder = new Encoder(RobotMap.SHOOTER_ENCODER_A_SOURCE,
												 RobotMap.SHOOTER_ENCODER_B_SOURCE);
	
	private final double WHEEL_RADIUS = 2; // Inches
	private final double WHEEL_CIRCUMFERENCE = 2 * Math.PI * WHEEL_RADIUS; // Inches
	
	
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
	}

	protected void initDefaultCommand() {
		setDefaultCommand(new SetShooterSpeed());
	}

	public void stop(){
		leftMotor.set(0);
		rightMotor.set(0);
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
		double rpm = shooterEncoder.getRate();
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
			leftMotor.set(correctedThrottle);
			rightMotor.set(correctedThrottle);
			//System.out.println(correctedThrottle);
		}
		
		
	}
	public void togglePID(){
		usePID = !usePID;
	}
}
