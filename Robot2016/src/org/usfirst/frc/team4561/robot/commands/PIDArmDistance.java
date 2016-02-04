package org.usfirst.frc.team4561.robot.commands;

import edu.wpi.first.wpilibj.command.PIDCommand;

import org.usfirst.frc.team4561.robot.Robot;

/**
 *
 */
public class PIDArmDistance extends PIDCommand {

    public PIDArmDistance() {
    	super(0, 0, 0);
    	getPIDController().setOutputRange(0.9, 0.9);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	setSetpoint(Robot.arm.setPoint);
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
    	end();
    }

	protected double returnPIDInput() {
		return (Robot.arm.armEncoderLeft.getDistance() + Robot.arm.armEncoderRight.getDistance()) * 0.5;
	}

	protected void usePIDOutput(double output) {
		Robot.arm.distanceOutput = output;
	}
}
