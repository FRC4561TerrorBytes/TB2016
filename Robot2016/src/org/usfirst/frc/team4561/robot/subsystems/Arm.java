package org.usfirst.frc.team4561.robot.subsystems;

import org.usfirst.frc.team4561.robot.Robot;
import org.usfirst.frc.team4561.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
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
											RobotMap.ARM_LEFT_ENCODER_B_SOURCE, false, EncodingType.k1X);
	private Encoder rightEncoder = new Encoder(RobotMap.ARM_RIGHT_ENCODER_A_SOURCE, 
											RobotMap.ARM_RIGHT_ENCODER_B_SOURCE);
	
	private double leftEncoderError = 0;
	private double rightEncoderError = 0;
	
	public DigitalInput topLimitSwitch = new DigitalInput(RobotMap.ARM_TOP_LIMIT_SWITCH);
	public DigitalInput bottomLimitSwitch = new DigitalInput(RobotMap.ARM_BOTTOM_LIMIT_SWITCH);
	
	private final double ENCODER_TICKS = 2048;
	private final double ARM_REDUCTION = 0.2777777;
	private final double DISTANCE_PER_PULSE = -((360/ENCODER_TICKS) * ARM_REDUCTION); // Degrees per tick
	
	public double armMotorOutput;
	
	public HashMap<String, Double> presets = new HashMap<String, Double>();
	
	public boolean pidMode = false;
	
	public int touringModeLevel = 0;
	
	/**
	 * 0 = left<br>
	 * 1 = right
	 */
	int encoderToUse = 0;
	
    // Initialize your subsystem here
    public Arm() {
    	super(0.03, // P
      		  0.0, // I
      		  0.04); // D
    	
    	if(Robot.isVerbose()) {
    		System.out.println("Initializing Arm Subsystem");
    	}
		getPIDController().setOutputRange(-0.3, 0.3);
    	
    	LiveWindow.addActuator("Arm", "PIDController", getPIDController());
    	
    	leftMotor.setInverted(true);
    	
    	leftMotor.enableBrakeMode(true);
    	rightMotor.enableBrakeMode(true);
    	
    	getPIDController().setAbsoluteTolerance(1);
    	
//    	leftMotor.setVoltageRampRate(2.5);
//    	rightMotor.setVoltageRampRate(2.5);
    	
    	leftEncoder.setDistancePerPulse(DISTANCE_PER_PULSE); // Sets the encoder to measure in degrees
    	rightEncoder.setDistancePerPulse(DISTANCE_PER_PULSE); // Sets the encoder to measure in degrees
    	
    	presets.put("Bottom", 125.0);
    	presets.put("Intake", 103.0);
    	presets.put("Cheval", 70.0);
    	presets.put("Middle", 41.0);
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
    
    public double getCorrectedLeftEncoder() {
    	return leftEncoder.getDistance();
    }
    public double getCorrectedRightEncoder() {
    	return leftEncoder.getDistance();
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
				//leftMotor.setVoltageRampRate(0);
				//rightMotor.setVoltageRampRate(0);
				output = 0;
			}
			else if(!topLimitSwitch.get() && output < 0) {
				//leftMotor.setVoltageRampRate(0);
				//rightMotor.setVoltageRampRate(0);
				output = 0;
			}
			
			
			
//			getPIDController().setPID(Robot.oi.getDashboardSlider0(), 
//										Robot.oi.getDashboardSlider1(),
//										Robot.oi.getDashboardSlider2());
			

			
			if(getPIDController().getError() > 20 || getPIDController().getError() < -20) {
				getPIDController().setPID(getPIDController().getP(), 0, getPIDController().getD());
//				leftMotor.setVoltageRampRate(10.23);
//				rightMotor.setVoltageRampRate(10.23);
			}
			else {
				getPIDController().setPID(getPIDController().getP(), 0.0, getPIDController().getD());
//				leftMotor.setVoltageRampRate(0);
//				rightMotor.setVoltageRampRate(0);
			}
			
//			if(getCorrectedLeftEncoder() < -45) {
//				double min = 0;
//				if(getCorrectedLeftEncoder() > -60) {
//					min = (-0.01333333333 * getCorrectedLeftEncoder()) - 1.2;
//				} else {
//					min = -0.4;
//				}
//				
////				if(output > 0 && output > -min) {
////					output = -min;
////				} else if (output < 0 && output < min) {
////					output = min;
////				}
//				
//				if(output < min) {
//					output = min;
//				}
//			}
			
			leftMotor.set(-output);
			rightMotor.set(-output);
			
			System.out.println(-output);
			
			armMotorOutput = -output;
		}
		
		if(!topLimitSwitch.get() || !bottomLimitSwitch.get()) {
//			leftMotor.setVoltageRampRate(0);
//			rightMotor.setVoltageRampRate(0);
		} else {
//			leftMotor.setVoltageRampRate(10.23);
//			rightMotor.setVoltageRampRate(10.23);
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
			leftEncoderError = 0;
			rightEncoderError = 0;
		}
		if(!bottomLimitSwitch.get()) {
//			leftEncoderError = /* real bottom limit switch value  - */ leftEncoder.getDistance();
//			rightEncoderError = /* real bottom limit switch value  - */ rightEncoder.getDistance();
		}
		
		if(Robot.isInDebugMode()) {
			if(encoderToUse == 0) {
				Robot.getDebugTable().putString("Arm/encoderToUse", "left");
			} else if(encoderToUse == 1){
				Robot.getDebugTable().putString("Arm/encoderToUse", "right");
			} else {
				Robot.getDebugTable().putString("Arm/encoderToUse", "not identifiable");
			}
			Robot.getDebugTable().putNumber("Arm/leftMotor/Status", leftMotor.get());
			Robot.getDebugTable().putNumber("Arm/leftMotor/BusVoltage", leftMotor.getBusVoltage());
			Robot.getDebugTable().putNumber("Arm/leftMotor/OutputCurrent", leftMotor.getOutputCurrent());
			Robot.getDebugTable().putNumber("Arm/leftMotor/OutputVoltage", leftMotor.getOutputVoltage());
			Robot.getDebugTable().putNumber("Arm/leftMotor/Temperature", leftMotor.getTemperature());
			Robot.getDebugTable().putNumber("Arm/leftMotor/DeviceID", leftMotor.getDeviceID());
			Robot.getDebugTable().putNumber("Arm/leftMotor/FirmwareVersion", leftMotor.GetFirmwareVersion());
			Robot.getDebugTable().putBoolean("Arm/leftMotor/IsInverted", leftMotor.getInverted());
			Robot.getDebugTable().putBoolean("Arm/leftMotor/IsAlive", leftMotor.isAlive());
			Robot.getDebugTable().putBoolean("Arm/leftMotor/ControlEnabled", leftMotor.isControlEnabled());
			Robot.getDebugTable().putBoolean("Arm/leftMotor/IsEnabled", leftMotor.isEnabled());
			Robot.getDebugTable().putNumber("Arm/rightMotor/Status", rightMotor.get());
			Robot.getDebugTable().putNumber("Arm/rightMotor/BusVoltage", rightMotor.getBusVoltage());
			Robot.getDebugTable().putNumber("Arm/rightMotor/OutputCurrent", rightMotor.getOutputCurrent());
			Robot.getDebugTable().putNumber("Arm/rightMotor/OutputVoltage", rightMotor.getOutputVoltage());
			Robot.getDebugTable().putNumber("Arm/rightMotor/Temperature", rightMotor.getTemperature());
			Robot.getDebugTable().putNumber("Arm/rightMotor/DeviceID", rightMotor.getDeviceID());
			Robot.getDebugTable().putNumber("Arm/rightMotor/FirmwareVersion", rightMotor.GetFirmwareVersion());
			Robot.getDebugTable().putBoolean("Arm/rightMotor/IsInverted", rightMotor.getInverted());
			Robot.getDebugTable().putBoolean("Arm/rightMotor/IsAlive", rightMotor.isAlive());
			Robot.getDebugTable().putBoolean("Arm/rightMotor/ControlEnabled", rightMotor.isControlEnabled());
			Robot.getDebugTable().putBoolean("Arm/rightMotor/IsEnabled", rightMotor.isEnabled());
			Robot.getDebugTable().putBoolean("Arm/leftEncoder/Direction", leftEncoder.getDirection());
			Robot.getDebugTable().putBoolean("Arm/leftEncoder/Stopped", leftEncoder.getStopped());
			Robot.getDebugTable().putNumber("Arm/leftEncoder/CurrentCount", leftEncoder.get());
			Robot.getDebugTable().putNumber("Arm/leftEncoder/Distance", leftEncoder.getDistance());
			Robot.getDebugTable().putNumber("Arm/leftEncoder/Rate", leftEncoder.getRate());
			Robot.getDebugTable().putNumber("Arm/leftEncoder/encodingScale", leftEncoder.getEncodingScale());
			Robot.getDebugTable().putNumber("Arm/leftEncoder/FPGAIndex", leftEncoder.getFPGAIndex());
			Robot.getDebugTable().putNumber("Arm/leftEncoder/RawCount", leftEncoder.getRaw());
			Robot.getDebugTable().putNumber("Arm/leftEncoder/CorrectedCount", getCorrectedLeftEncoder());
			Robot.getDebugTable().putNumber("Arm/leftEncoder/SamplesToAverage", leftEncoder.getSamplesToAverage());
			Robot.getDebugTable().putNumber("Arm/leftEncoder/Error", leftEncoderError);
			Robot.getDebugTable().putBoolean("Arm/rightEncoder/Direction", rightEncoder.getDirection());
			Robot.getDebugTable().putBoolean("Arm/rightEncoder/Stopped", rightEncoder.getStopped());
			Robot.getDebugTable().putNumber("Arm/rightEncoder/CurrentCount", rightEncoder.get());
			Robot.getDebugTable().putNumber("Arm/rightEncoder/Distance", rightEncoder.getDistance());
			Robot.getDebugTable().putNumber("Arm/rightEncoder/Rate", rightEncoder.getRate());
			Robot.getDebugTable().putNumber("Arm/rightEncoder/encodingScale", rightEncoder.getEncodingScale());
			Robot.getDebugTable().putNumber("Arm/rightEncoder/FPGAIndex", rightEncoder.getFPGAIndex());
			Robot.getDebugTable().putNumber("Arm/rightEncoder/RawCount", rightEncoder.getRaw());
			Robot.getDebugTable().putNumber("Arm/rightEncoder/CorrectedCount", getCorrectedRightEncoder());
			Robot.getDebugTable().putNumber("Arm/rightEncoder/SamplesToAverage", rightEncoder.getSamplesToAverage());
			Robot.getDebugTable().putNumber("Arm/rightEncoder/Error", rightEncoderError);
			Robot.getDebugTable().putBoolean("Arm/topLimitSwitch/isNotPressed", topLimitSwitch.get());
			Robot.getDebugTable().putBoolean("Arm/bottomLimitSwitch/isNotPressed", bottomLimitSwitch.get());
			Robot.getDebugTable().putNumber("Arm/armMotorOutput", armMotorOutput);
			Robot.getDebugTable().putBoolean("Arm/pidMode", pidMode);
			Robot.getDebugTable().putNumber("Arm/PIDController/Output", getPIDController().get());
			Robot.getDebugTable().putNumber("Arm/PIDController/AverageError", getPIDController().getAvgError());
			Robot.getDebugTable().putNumber("Arm/PIDController/P", getPIDController().getP());
			Robot.getDebugTable().putNumber("Arm/PIDController/I", getPIDController().getI());
			Robot.getDebugTable().putNumber("Arm/PIDController/D", getPIDController().getD());
			Robot.getDebugTable().putNumber("Arm/PIDController/F", getPIDController().getF());
			Robot.getDebugTable().putNumber("Arm/PIDController/DeltaSetpoint", getPIDController().getDeltaSetpoint());
			Robot.getDebugTable().putNumber("Arm/PIDController/Error", getPIDController().getError());
			Robot.getDebugTable().putNumber("Arm/PIDController/Setpoint", getPIDController().getSetpoint());
			Robot.getDebugTable().putBoolean("Arm/PIDController/IsEnabled", getPIDController().isEnabled());
			Robot.getDebugTable().putBoolean("Arm/PIDController/OnTarget", getPIDController().onTarget());
		}
	}
}