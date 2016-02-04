package org.usfirst.frc.team4561.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.CANTalon;
import org.usfirst.frc.team4561.robot.RobotMap;
import edu.wpi.first.wpilibj.DigitalInput;

public class Rollers extends Subsystem {
	
	public CANTalon rollerMotor = new CANTalon(RobotMap.ROLLER_MOTOR);
	
	public DigitalInput loadFinishedSwitch = new DigitalInput(RobotMap.ROLLER_LIMIT_SWITCH);
	
	public Rollers() {
		rollerMotor.enableBrakeMode(true);
	}
	
	public void initDefaultCommand() {
	}
	
	public void setRollers(double power) {
		rollerMotor.set(power);
	}
	
	public void stop() {
		rollerMotor.set(0);
	}
}