package org.usfirst.frc.team4561.robot.commands;

import org.usfirst.frc.team4561.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class FlyWheels extends Command {

	private final double MAXIMUM_RPM = 7500;
	
	public FlyWheels() {
		requires(Robot.shooter);
	}
	
	protected void initialize() {
	}
	
	protected void execute() {
		double correctedThrottle = (Robot.oi.getLeftStickThrottle() + 1) * 0.5;
		double rpm = correctedThrottle * MAXIMUM_RPM;
		Robot.shooter.setRPM(rpm);
	}

	
	protected boolean isFinished() {
		return false;
	}

	
	protected void end() {
		Robot.shooter.stop();
	}

	
	protected void interrupted() {
		end();
	}

}
