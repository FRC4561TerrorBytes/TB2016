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
    	if(Robot.isVerbose()) {
    		System.out.println("Starting LowerArms");
    	}
    	Robot.arm.pidMode = false;
    	Robot.arm.armMotorOutput = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(!Robot.arm.bottomLimitSwitch.get()) {
    		Robot.arm.leftMotor.set(0);
        	Robot.arm.rightMotor.set(0);
        	Robot.arm.armMotorOutput = 0;
    	}
    	else {
//    		if(Robot.oi.getArmPIDButton()) {
//	    		double min = -0.6;
//	    		if(Robot.arm.getCorrectedLeftEncoder() < -45) {
//					if(Robot.arm.getCorrectedLeftEncoder() > -60) {
//						min = (-0.01333333333 * Robot.arm.getCorrectedLeftEncoder()) - 1.2;
//					} else {
//						min = -0.4;
//					}
//				}
//				Robot.arm.leftMotor.set(min);
//	        	Robot.arm.rightMotor.set(min);
//	        	Robot.arm.armMotorOutput = min;
//    		} else {
    			Robot.arm.leftMotor.set(-0.4);
	        	Robot.arm.rightMotor.set(-0.4);
	        	Robot.arm.armMotorOutput = -0.4;
//    		}
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
    		System.out.println("Stopping LowerArms");
    	}
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
