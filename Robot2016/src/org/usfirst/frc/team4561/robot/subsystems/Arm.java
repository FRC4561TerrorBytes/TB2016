package org.usfirst.frc.team4561.robot.subsystems;

import org.usfirst.frc.team4561.robot.Robot;
import org.usfirst.frc.team4561.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import java.util.HashMap;

/**
 *
 */
public class Arm extends PIDSubsystem {

	public CANTalon leftMotor = new CANTalon(RobotMap.LEFT_ARM_MOTOR);
	public CANTalon rightMotor  = new CANTalon(RobotMap.RIGHT_ARM_MOTOR);
	
	private Encoder leftEncoder = new Encoder(RobotMap.ARM_LEFT_ENCODER_A_SOURCE, 
											RobotMap.ARM_LEFT_ENCODER_B_SOURCE);
	private Encoder rightEncoder = new Encoder(RobotMap.ARM_RIGHT_ENCODER_A_SOURCE, 
											RobotMap.ARM_RIGHT_ENCODER_B_SOURCE);
	
	public DigitalInput topLimitSwitch = new DigitalInput(RobotMap.ARM_TOP_LIMIT_SWITCH);
	public DigitalInput bottomLimitSwitch = new DigitalInput(RobotMap.ARM_BOTTOM_LIMIT_SWITCH);
	
	private final double ENCODER_TICKS = 2048; // TODO: Verify
	private final double DISTANCE_PER_PULSE = 360/ENCODER_TICKS; // Degrees per tick
	
	public double armMotorOutput;
	
	public HashMap<String, Double> presets = new HashMap<String, Double>();
	
	public boolean pidMode = false;
	
	/**
	 * 0 = left<br>
	 * 1 = right
	 */
	int encoderToUse = 0;
	
    // Initialize your subsystem here
    public Arm() {
    	super(0.00666 // P: 1/150
    			, 0 // TODO: I
    			, 0); // TODO: D
    	
    	if(Robot.isVerbose()) {
    		System.out.println("Initializing Arm Subsystem");
    	}
		getPIDController().setOutputRange(0.9, 0.9);
		
		leftEncoder.setPIDSourceType(PIDSourceType.kDisplacement);
    	
    	LiveWindow.addActuator("Arm", "PIDController", getPIDController());
    	
    	leftMotor.setInverted(true);
    	
    	leftMotor.enableBrakeMode(true);
    	rightMotor.enableBrakeMode(true);
    	
    	leftEncoder.setDistancePerPulse(DISTANCE_PER_PULSE); // Sets the encoder to measure in degrees
    	rightEncoder.setDistancePerPulse(DISTANCE_PER_PULSE); // Sets the encoder to measure in degrees
    	
    	presets.put("Bottom", 100.0);
    	presets.put("Intake", 75.0);
    	presets.put("Middle", 33.0);
    	presets.put("Top", 0.0);
    	
    	setSetpoint(presets.get("Top"));
    }
    
    public void initDefaultCommand() {
    }
    
    public void setPreset(String presetName, double newPreset) {
    	presets.put(presetName, newPreset);
    }
    
    public void stop() {
    	leftMotor.set(0);
    	rightMotor.set(0);
    	armMotorOutput = 0;
    }

    protected double returnPIDInput() {
    	if(encoderToUse == 0) {
    		return rightEncoder.getDistance();
    	}
    	else {
    		return leftEncoder.getDistance();
    	}
	}

	protected void usePIDOutput(double output) {
		if(pidMode) {
			if(!topLimitSwitch.get() && output > 0) {
				output = 0;
			}
			else if(!bottomLimitSwitch.get() && output < 0) {
				output = 0;
			}
			leftMotor.set(output);
			rightMotor.set(output);
			System.out.println("in pid mode");
		
			armMotorOutput = output;
		}
		
		// Not using PID output
		if(Robot.oi.getDashboardButton0()) {
			encoderToUse = 0;
		}
		else {
			encoderToUse = 1;
		}
		
		if(!topLimitSwitch.get()) {
			leftEncoder.reset();
			rightEncoder.reset();
		}
	}
}