package org.usfirst.frc.team4561.robot.commands;

import org.usfirst.frc.team4561.robot.Robot;
import org.usfirst.frc.team4561.robot.RobotMap;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class PIDGoalAlign extends PIDCommand {

	double centerX;
	NetworkTable visionTable;
	private static final int CAMERA_RESOLUTION_X = RobotMap.CAMERA_RESOLUTION_X;
	private static final double ALIGNMENT_SPEED = RobotMap.DRIVETRAIN_ALIGNMENT_SPEED;
	private boolean seeingGoal;
	private boolean preferLeft;
	
    public PIDGoalAlign(boolean left) {
    	super(0.5/(CAMERA_RESOLUTION_X/2), 0, 0);
    	requires(Robot.driveTrain);
    	visionTable = NetworkTable.getTable("Vision");
    	preferLeft = left;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(Robot.isVerbose()) {
    		System.out.println("Starting PIDGoalAlign");
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	centerX = visionTable.getNumber("centroidX", -12345);
    	if(centerX == -12345) {
    		seeingGoal = false;
    	} else {
    		seeingGoal = true;
    	}
    	
    	setSetpoint(0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	if(Robot.isVerbose()) {
    		System.out.println("Stopping PIDGoalAlign");
    	}
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

	protected double returnPIDInput() {
		return centerX - (CAMERA_RESOLUTION_X/2);
	}

	protected void usePIDOutput(double output) {
		if(seeingGoal) {
			SmartDashboard.putString("DB/String 7", "Goal: " + output);
			Robot.driveTrain.driveArcade(0, output);
		}
		else {
			SmartDashboard.putString("DB/String 7", "Goal: Goal not found");
			if(preferLeft) {
				Robot.driveTrain.driveArcade(0, -ALIGNMENT_SPEED);
			} else {
				Robot.driveTrain.driveArcade(0, ALIGNMENT_SPEED);
			}
		}
	}
}
