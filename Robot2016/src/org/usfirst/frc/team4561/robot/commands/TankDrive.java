package org.usfirst.frc.team4561.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team4561.robot.Robot;
import org.usfirst.frc.team4561.robot.RobotMap;

/**
 * Activates tank drive. In tank drive, the left 
 * stick controls the left wheels, and the 
 * right stick controls the right wheels.
 */
public class TankDrive extends Command {

	private static final double TOURING_MODE_MULTIPLIER = RobotMap.TOURING_MODE_MULTIPLIER; // TODO: Put in
	
    public TankDrive() {
        requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(Robot.isVerbose()) {
    		System.out.println("Starting TankDrive");
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.driveTrain.isReversed()) {
    		if(Robot.driveTrain.isTouringMode()) {
    			Robot.driveTrain.driveTank(-Robot.oi.getLeftStickY() * TOURING_MODE_MULTIPLIER, 
    					 				   -Robot.oi.getRightStickY() * TOURING_MODE_MULTIPLIER);
    		}
    		else {
    			Robot.driveTrain.driveTank(-Robot.oi.getLeftStickY(), -Robot.oi.getRightStickY());
    		}
    	}
    	else  {
    		if(Robot.driveTrain.isTouringMode()) {
    			Robot.driveTrain.driveTank(Robot.oi.getLeftStickY() * TOURING_MODE_MULTIPLIER,  
    									   Robot.oi.getRightStickY() * TOURING_MODE_MULTIPLIER);
    		}
    		else {
    			Robot.driveTrain.driveTank(Robot.oi.getLeftStickY(), Robot.oi.getRightStickY());
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
    		System.out.println("Stopping TankDrive");
    	}
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
