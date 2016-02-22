package org.usfirst.frc.team4561.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.SpeedController;
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
}