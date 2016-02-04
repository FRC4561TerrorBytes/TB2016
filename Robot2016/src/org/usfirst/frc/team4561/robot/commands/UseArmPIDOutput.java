package org.usfirst.frc.team4561.robot.commands;

import org.usfirst.frc.team4561.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class UseArmPIDOutput extends Command {

	private double distanceOutput;
	private double rightAlignment;
	private double leftAlignment;
	
    public UseArmPIDOutput() {
        requires(Robot.arm);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	distanceOutput = Robot.arm.distanceOutput;
    	rightAlignment = Robot.arm.rightAlignmentOutput;
    	leftAlignment = Robot.arm.leftAlignmentOutput;
    	
    	Robot.arm.leftArmMotor.set(distanceOutput + leftAlignment);
    	Robot.arm.rightArmMotor.set(distanceOutput + rightAlignment);
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
