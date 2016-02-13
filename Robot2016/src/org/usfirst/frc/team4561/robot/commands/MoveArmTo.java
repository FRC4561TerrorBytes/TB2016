package org.usfirst.frc.team4561.robot.commands;

import org.usfirst.frc.team4561.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Moves the arm to a position.
 */
public class MoveArmTo extends Command {

	double setPoint;
	/**
	 * Sets the setPoint of the arm to a position.
	 * When moving to common positions use Robot.arm.presets.get("WhateverPositionYouWant")
	 * @param setPoint
	 */
    public MoveArmTo(double setPoint) {
    	this.setPoint = setPoint;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.arm.setPoint = setPoint;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
