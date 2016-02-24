package org.usfirst.frc.team4561.robot.subsystems;

import org.usfirst.frc.team4561.robot.Robot;
import org.usfirst.frc.team4561.robot.RobotMap;

import com.ni.vision.NIVision.GetPerpendicularLineResult;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
	
	private double leftEncoderError = 0;
	private double rightEncoderError = 0;
	
	public DigitalInput topLimitSwitch = new DigitalInput(RobotMap.ARM_TOP_LIMIT_SWITCH);
	public DigitalInput bottomLimitSwitch = new DigitalInput(RobotMap.ARM_BOTTOM_LIMIT_SWITCH);
	
	private final double ENCODER_TICKS = 2048;
	private final double ARM_REDUCTION = 0.2777777;
	private final double DISTANCE_PER_PULSE = (360/ENCODER_TICKS) * ARM_REDUCTION; // Degrees per tick
	
	public double armMotorOutput;
	
	private final double ACCELERATION = 0.001; //TODO
	
	public HashMap<String, Double> presets = new HashMap<String, Double>();
	
	public boolean pidMode = false;
	
	/**
	 * 0 = left<br>
	 * 1 = right
	 */
	int encoderToUse = 0;
	
    // Initialize your subsystem here
    public Arm() {
    	super(0.03, // P
    		  0.03, // I
    		  0.04); // D
    	
    	if(Robot.isVerbose()) {
    		System.out.println("Initializing Arm Subsystem");
    	}
		getPIDController().setOutputRange(-0.9, 0.9);
		
		leftEncoder.setPIDSourceType(PIDSourceType.kDisplacement);
    	
    	LiveWindow.addActuator("Arm", "PIDController", getPIDController());
    	
    	leftMotor.setInverted(true);
    	
    	leftMotor.enableBrakeMode(true);
    	rightMotor.enableBrakeMode(true);
    	
    	getPIDController().setAbsoluteTolerance(1);
    	
    	leftMotor.setVoltageRampRate(10.23);
    	rightMotor.setVoltageRampRate(10.23);
    	
    	leftEncoder.setDistancePerPulse(DISTANCE_PER_PULSE); // Sets the encoder to measure in degrees
    	rightEncoder.setDistancePerPulse(DISTANCE_PER_PULSE); // Sets the encoder to measure in degrees
    	
    	presets.put("Bottom", 155.0);
    	presets.put("Intake", 141.0);
    	presets.put("Middle", 41.0);
    	presets.put("Top", 4.0);
    	
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
    
    public double getCorrectedLeftEncoder() {
    	return leftEncoder.getDistance() + leftEncoderError;
    }
    public double getCorrectedRightEncoder() {
    	return rightEncoder.getDistance() + rightEncoderError;
    }

    protected double returnPIDInput() {
    	if(encoderToUse == 0) {
    		return getCorrectedRightEncoder();
    	}
    	else {
    		return getCorrectedLeftEncoder();
    	}
	}

	protected void usePIDOutput(double output) {
		if(pidMode) {
			if(!bottomLimitSwitch.get() && output > 0) {
				leftMotor.setVoltageRampRate(0);
				rightMotor.setVoltageRampRate(0);
				output = 0;
			}
			else if(!topLimitSwitch.get() && output < 0) {
				leftMotor.setVoltageRampRate(0);
				rightMotor.setVoltageRampRate(0);
				output = 0;
			}
			
			
			
			getPIDController().setPID(SmartDashboard.getNumber("DB/Slider 0"), 
										SmartDashboard.getNumber("DB/Slider 1"),
										SmartDashboard.getNumber("DB/Slider 2"));
			

			
			if(getPIDController().getError() > 20 || getPIDController().getError() < -20) {
				getPIDController().setPID(getPIDController().getP(), 0, getPIDController().getD());
				leftMotor.setVoltageRampRate(10.23);
				rightMotor.setVoltageRampRate(10.23);
			}
			else {
				leftMotor.setVoltageRampRate(0);
				rightMotor.setVoltageRampRate(0);
			}
			
			
			
			leftMotor.set(output);
			rightMotor.set(output);
			
		
			armMotorOutput = -output;
		}
		
		if(!topLimitSwitch.get() || !bottomLimitSwitch.get()) {
			leftMotor.setVoltageRampRate(0);
			rightMotor.setVoltageRampRate(0);
		} else {
			leftMotor.setVoltageRampRate(10.23);
			rightMotor.setVoltageRampRate(10.23);
		}
		
		System.out.println(getCorrectedLeftEncoder());
		
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
			leftEncoderError = 0;
			rightEncoderError = 0;
			System.out.println("top is pressed");
		}
		if(!bottomLimitSwitch.get()) {
//			leftEncoderError = /* real bottom limit switch value  - */ leftEncoder.getDistance();
//			rightEncoderError = /* real bottom limit switch value  - */ rightEncoder.getDistance();
			System.out.println("bottom is pressed");
		}
	}
}