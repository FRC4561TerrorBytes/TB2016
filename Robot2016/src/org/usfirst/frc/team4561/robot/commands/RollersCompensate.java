package org.usfirst.frc.team4561.robot.commands;

import org.usfirst.frc.team4561.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RollersCompensate extends Command {

    public RollersCompensate() {
        requires(Robot.rollers);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(Robot.isVerbose()) {
    		System.out.println("Starting RollersCompensate");
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.rollers.setRollers(Robot.arm.armMotorOutput * 0.359);
    	System.out.println("rollers at: "  + Double.toString(Robot.arm.armMotorOutput * 0.359));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.rollers.stop();
    	if(Robot.isVerbose()) {
    		System.out.println("Stopping RollersCompensate");
    	}
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
