
package org.usfirst.frc.team4561.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team4561.robot.Robot;
import org.usfirst.frc.team4561.robot.RobotMap;

/**
 * Activates arcade drive. In arcade drive, the left 
 * stick controls the forward/backward speed, and the 
 * right stick controls turns.
 */
public class ArcadeDrive extends Command {

	private static final double TOURING_MODE_MULTIPLIER = RobotMap.TOURING_MODE_MULTIPLIER;
	
    public ArcadeDrive() {
        requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(Robot.isVerbose()) {
    		System.out.println("Starting Arcade Drive");
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.driveTrain.isReversed()) {
    		if(Robot.driveTrain.isTouringMode()) {
    			Robot.driveTrain.driveArcade(-Robot.oi.getLeftStickY() * TOURING_MODE_MULTIPLIER, 
    										 Robot.oi.getRightStickX() * TOURING_MODE_MULTIPLIER);
    		}
    		else {
    			Robot.driveTrain.driveArcade(-Robot.oi.getLeftStickY(), Robot.oi.getRightStickX());
    		}
    	}
    	else {
    		if(Robot.driveTrain.isTouringMode()) {
    			Robot.driveTrain.driveArcade(Robot.oi.getLeftStickY() * TOURING_MODE_MULTIPLIER, 
    										 Robot.oi.getRightStickX() * TOURING_MODE_MULTIPLIER);
    		}
    		else {
    			Robot.driveTrain.driveArcade(Robot.oi.getLeftStickY(), Robot.oi.getRightStickX());
    		}
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.stop();
    	if(Robot.isVerbose()) {
    		System.out.println("Stopping Arcade Drive");
    	}
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
