package org.usfirst.frc.team4561.robot.commands;

import org.usfirst.frc.team4561.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RaiseArms extends Command {

    public RaiseArms() {
    	requires(Robot.arm);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(Robot.isVerbose()) {
    		System.out.println("Starting RaiseArms");
    	}
    	Robot.arm.pidMode = false;
    	Robot.arm.armMotorOutput = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(!Robot.arm.topLimitSwitch.get()) {
    		System.out.println("no power up");
    		Robot.arm.leftMotor.set(0);
        	Robot.arm.rightMotor.set(0);
        	Robot.arm.armMotorOutput = 0;
    	}
    	else {
    		System.out.println("yes power up");
    		if(Robot.arm.touringModeLevel == 0) {
    			Robot.arm.leftMotor.set(0.6);
            	Robot.arm.rightMotor.set(0.6);
            	Robot.arm.armMotorOutput = 0.6;
            	
            }
    		else if(Robot.arm.touringModeLevel == 1) {
    			Robot.arm.leftMotor.set(0.5);
            	Robot.arm.rightMotor.set(0.5);
            	Robot.arm.armMotorOutput = 0.5;
            }
    		else if(Robot.arm.touringModeLevel == 2) {
    			Robot.arm.leftMotor.set(0.2);
            	Robot.arm.rightMotor.set(0.2);
            	Robot.arm.armMotorOutput = 0.2;
            }
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.arm.stop();
    	if(Robot.isVerbose()) {
    		System.out.println("Stopping RaiseArms");
    	}
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
