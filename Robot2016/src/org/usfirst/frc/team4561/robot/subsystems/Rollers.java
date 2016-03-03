package org.usfirst.frc.team4561.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.CANTalon;

import org.usfirst.frc.team4561.robot.Robot;
import org.usfirst.frc.team4561.robot.RobotMap;
import org.usfirst.frc.team4561.robot.commands.RollersCompensate;

import edu.wpi.first.wpilibj.DigitalInput;

public class Rollers extends Subsystem {
	
	private CANTalon rollerMotor = new CANTalon(RobotMap.ROLLER_MOTOR);
	
	public DigitalInput loadFinishedSwitch = new DigitalInput(RobotMap.ROLLER_LIMIT_SWITCH);
	
	public Rollers() {
		if(Robot.isVerbose()) {
			System.out.println("Initializing Rollers Subsystem");
		}
		rollerMotor.enableBrakeMode(true);
		rollerMotor.setInverted(true);
	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new RollersCompensate());
	}
	
	public void setRollers(double power) {
		rollerMotor.set(power);
	}
	
	public void stop() {
		rollerMotor.set(0);
	}
	
	public void putDebugInfo() {
		Robot.getDebugTable().putNumber("Rollers/rollerMotor/Status", rollerMotor.get());
		Robot.getDebugTable().putNumber("Rollers/rollerMotor/BusVoltage", rollerMotor.getBusVoltage());
		Robot.getDebugTable().putNumber("Rollers/rollerMotor/OutputCurrent", rollerMotor.getOutputCurrent());
		Robot.getDebugTable().putNumber("Rollers/rollerMotor/OutputVoltage", rollerMotor.getOutputVoltage());
		Robot.getDebugTable().putNumber("Rollers/rollerMotor/Temperature", rollerMotor.getTemperature());
		Robot.getDebugTable().putNumber("Rollers/rollerMotor/DeviceID", rollerMotor.getDeviceID());
		Robot.getDebugTable().putNumber("Rollers/rollerMotor/FirmwareVersion", rollerMotor.GetFirmwareVersion());
		Robot.getDebugTable().putBoolean("Rollers/rollerMotor/IsInverted", rollerMotor.getInverted());
		Robot.getDebugTable().putBoolean("Rollers/rollerMotor/IsAlive", rollerMotor.isAlive());
		Robot.getDebugTable().putBoolean("Rollers/rollerMotor/ControlEnabled", rollerMotor.isControlEnabled());
		Robot.getDebugTable().putBoolean("Rollers/rollerMotor/IsEnabled", rollerMotor.isEnabled());
		
		Robot.getDebugTable().putBoolean("Rollers/loadFinishedSwitch/IsNotPressed", loadFinishedSwitch.get());
	}
}