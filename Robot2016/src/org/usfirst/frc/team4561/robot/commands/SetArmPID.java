package org.usfirst.frc.team4561.robot.commands;

import org.usfirst.frc.team4561.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetArmPID extends Command {

	private boolean on;
	
    public SetArmPID(boolean on) {
    	this.on = on;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.arm.pidMode = on;
    	if(!on) {
    		Robot.arm.armMotorOutput = 0;
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
