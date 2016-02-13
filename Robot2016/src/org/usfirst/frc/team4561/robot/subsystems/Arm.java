package org.usfirst.frc.team4561.robot.subsystems;

import org.usfirst.frc.team4561.robot.RobotMap;
import org.usfirst.frc.team4561.robot.commands.UseArmPIDOutput;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

import java.util.HashMap;

/**
 *
 */
public class Arm extends Subsystem {

	public CANTalon leftArmMotor = new CANTalon(RobotMap.LEFT_ARM_MOTOR);
	public CANTalon rightArmMotor  = new CANTalon(RobotMap.RIGHT_ARM_MOTOR);
	
	public Encoder armEncoderLeft = new Encoder(RobotMap.ARM_LEFT_ENCODER_A_SOURCE, 
											RobotMap.ARM_LEFT_ENCODER_B_SOURCE);
	public Encoder armEncoderRight = new Encoder(RobotMap.ARM_RIGHT_ENCODER_A_SOURCE, 
											RobotMap.ARM_RIGHT_ENCODER_B_SOURCE);
	
	private final double ENCODER_TICKS = 2048; // TODO: Verify
	private final double DISTANCE_PER_PULSE = 360/ENCODER_TICKS; // Degrees per tick
	
	public HashMap<String, Double> presets = new HashMap<String, Double>();
	
	public double setPoint;
	public double distanceOutput;
	public double leftAlignmentOutput;
	public double rightAlignmentOutput;
	
    // Initialize your subsystem here
    public Arm() {
    	armEncoderLeft.setDistancePerPulse(DISTANCE_PER_PULSE); // Sets the encoder to measure in degrees
    	armEncoderRight.setDistancePerPulse(DISTANCE_PER_PULSE); // Sets the encoder to measure in degrees
    	
    	presets.put("Bottom", 0.0);
    	presets.put("Intake", 25.0);
    	presets.put("Loader", 75.0);
    	presets.put("Top", 100.0);
    	
    	setPoint = presets.get("Top");
    }
    
    public void initDefaultCommand() {
        setDefaultCommand(new UseArmPIDOutput());
    }
    
    public void setPreset(String presetName, double newPreset) {
    	presets.put(presetName, newPreset);
    }
    
    public void setSetpoint(double setPoint) {
    	this.setPoint = setPoint;
    }
    
    public void setSetpointRelative(double deltaSetPoint) {
    	setPoint += deltaSetPoint;
    }
    
    public void stop() {
    	leftArmMotor.set(0);
    	rightArmMotor.set(0);
    }
}