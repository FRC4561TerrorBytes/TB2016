package org.usfirst.frc.team4561.robot.commands;

import org.usfirst.frc.team4561.robot.Robot;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 *
 */
public class PIDGoalAlign extends PIDCommand {

	double centerX;
	NetworkTable visionTable;
    public PIDGoalAlign() {
    	super((1/80/*half of camera width*/), 0, 0);
//    	requires(Robot.driveTrain);
    	visionTable = NetworkTable.getTable("Vision");
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	centerX = visionTable.getNumber("Vision/centroidX", 80 /*half of camera width*/);
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
    }

	protected double returnPIDInput() {
		return centerX - 80 /*half of camera width*/;
	}

	protected void usePIDOutput(double output) {
//		Robot.driveTrain.driveArcade(0, output);
	}
}
