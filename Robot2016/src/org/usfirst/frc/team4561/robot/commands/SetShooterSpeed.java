package org.usfirst.frc.team4561.robot.commands;

import org.usfirst.frc.team4561.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SetShooterSpeed extends Command {
	
	double speed;
	
	public SetShooterSpeed(double speed) {
		requires(Robot.shooter);
		this.speed = speed;
	}

	protected void initialize() {
		if(Robot.isVerbose()) {
			System.out.println("Starting SetShooterSpeed");
		}
	}

	protected void execute() {
		Robot.shooter.setPower(speed);
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
