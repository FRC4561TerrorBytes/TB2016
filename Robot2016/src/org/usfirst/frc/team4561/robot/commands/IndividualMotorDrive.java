package org.usfirst.frc.team4561.robot.commands;

import org.usfirst.frc.team4561.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Tests the four motors of the DriveTrain individually.
 */
public class IndividualMotorDrive extends Command {
	
	private int motorID;

    public IndividualMotorDrive(int motorID) {
    	requires(Robot.driveTrain);
    	this.motorID = motorID;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(Robot.isVerbose()) {
    		System.out.println("Starting Individual Motor Drive for motor ID " + motorID);
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.driveSingleMotor(motorID);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.stop();
    	if(Robot.isVerbose()) {
    		System.out.println("Stopping Individual Motor Drive for motor ID " + motorID);
    	}
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
