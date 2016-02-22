package org.usfirst.frc.team4561.robot.commands;

import org.usfirst.frc.team4561.robot.Robot;
import org.usfirst.frc.team4561.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveArmDelta extends Command {

	private static final double INCREMENT = RobotMap.ARM_DELTA_INCREMENT;
	
	boolean up;
	
    public MoveArmDelta(boolean up) {
    	this.up = up;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(Robot.isVerbose()) {
    		System.out.println("Starting MoveArmDelta");
    	}
    	Robot.arm.pidMode = true;
    	if(up) {
    		Robot.arm.setSetpointRelative(INCREMENT);
    	}
    	else {
    		Robot.arm.setSetpointRelative(-INCREMENT);
    	}
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
    	System.out.println("Stopping MoveArmDelta");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
