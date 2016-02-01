package org.usfirst.frc.team4561.robot.subsystems;

import org.usfirst.frc.team4561.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class Arm extends PIDSubsystem {

	public SpeedController leftArmMotor = new CANTalon(RobotMap.LEFT_ARM_MOTOR);
	public SpeedController rightArmMotor  = new CANTalon(RobotMap.RIGHT_ARM_MOTOR);
	
	public Encoder armEncoder = new Encoder(RobotMap.ARM_ENCODER_A_SOURCE, RobotMap.ARM_ENCODER_B_SOURCE);
	
	private final double ENCODER_TICKS = 2048; // TODO: Verify
	private final double DISTANCE_PER_PULSE = 360/ENCODER_TICKS;
	
    // Initialize your subsystem here
    public Arm() {
    	super(0, 0, 0);
    	armEncoder.setDistancePerPulse(DISTANCE_PER_PULSE); // Sets the encoder to measure in degrees
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void stop() {
    	leftArmMotor.set(0);
    	rightArmMotor.set(0);
    }
    
    protected double returnPIDInput() {
    	return armEncoder.pidGet();
    }
    
    protected void usePIDOutput(double output) {
        leftArmMotor.set(output);
        rightArmMotor.set(output);
    }
}
