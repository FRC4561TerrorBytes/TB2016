package org.usfirst.frc.team4561.robot.subsystems;

import org.usfirst.frc.team4561.robot.OI;
import org.usfirst.frc.team4561.robot.Robot;
import org.usfirst.frc.team4561.robot.RobotMap;

import edu.wpi.first.wpilibj.CANSpeedController.ControlMode;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import java.lang.Math;

public class Shooter extends PIDSubsystem {
	static CANTalon shooterMotorLeft = new CANTalon(4); //RobotMap.LEFT_SHOOTER_MOTOR); //TODO: DO ROBOT MAP KAIZ
	static CANTalon shooterMotorRight = new CANTalon(5); //RobotMap.RIGHT_SHOOTER_MOTOR); //TODO: DO ROBOT MAP KAIZ
	
	private final int THROTTLE_MULTIPLIER = 1;
	//TODO: WHEEL CALCULATIONS
	
	//private Talon fly1 = new Talon(RobotMap.FLY_ONE);
	//private Talon fly2 = new Talon(RobotMap.FLY_TWO);
	
	
	private static Encoder encoder = new Encoder(0,1);//RobotMap.ENCODER_A_CHANNEL, RobotMap.ENCODER_B_CHANNEL);	
									//TODO: DO ROBOT MAP KAIZ
	public Shooter() {
		super(0,0,0,0);
		
		encoder.setPIDSourceType(PIDSourceType.kRate);
		
//		shooterMotorLeft.changeControlMode(TalonControlMode.Speed);
//		shooterMotorRight.changeControlMode(TalonControlMode.Speed);

		
	}

	@Override
	protected void initDefaultCommand() {
		// TODO: Consider what to put here, most likely nothing. 
		
	}
//	protected void set(double setPoint){
//		shooterMotorLeft.set(setPoint);
//		shooterMotorRight.set(setPoint);
//	}
	protected void stop(){
		//shooterMotorLeft.set(0);
		//shooterMotorRight.set(0);
		
		//fly1.set(0);
		//fly2.set(0);
	}

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected void usePIDOutput(double output) {
		shooterMotorLeft.set(output);
		shooterMotorRight.set(output);
		// TODO Auto-generated method stub
		
	}
	public void setVelocityFromThrottle(double throttleValue){
		
		double throttleSetPointValue = (Math.abs(throttleValue) * THROTTLE_MULTIPLIER); 
		Robot.shooter.setSetpoint(throttleSetPointValue);
		//set(throttleSetPointValue);
		
	}
	
}
