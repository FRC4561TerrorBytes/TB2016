package org.usfirst.frc.team4561.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team4561.robot.Robot;

/**
 *
 */
public class LowerArms extends Command {

    public LowerArms() {
    	requires(Robot.arm);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.arm.leftArmMotor.set(-0.9); //TODO: Find speed values
    	Robot.arm.rightArmMotor.set(-0.9); //TODO: Find speed values
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.arm.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
