package org.usfirst.frc.team4561.robot.commands;

import org.usfirst.frc.team4561.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SetShooterSpeed extends Command {

	private final double MAXIMUM_RPM = 7500;

	public SetShooterSpeed() {
		requires(Robot.shooter);
	}

	protected void initialize() {
		if(Robot.isVerbose()) {
			System.out.println("Starting SetShooterSpeed");
		}
	}

	protected void execute() {
		double rpm = Robot.oi.getCorrectedLeftStickThrottle() * MAXIMUM_RPM;
		Robot.shooter.setRPM(rpm);
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Robot.shooter.stop();
		if(Robot.isVerbose()) {
			System.out.println("Stopping SetShooterSpeed");
		}
	}

	protected void interrupted() {
		end();
	}
}
