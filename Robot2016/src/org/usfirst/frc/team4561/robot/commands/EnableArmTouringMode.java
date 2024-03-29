package org.usfirst.frc.team4561.robot.commands;

import org.usfirst.frc.team4561.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class EnableArmTouringMode extends Command {

    public EnableArmTouringMode() {
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.arm.touringModeLevel = 1;
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
    	Robot.arm.touringModeLevel = 0;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
