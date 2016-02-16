package org.usfirst.frc.team4561.robot.commands;

import org.usfirst.frc.team4561.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 *
 */
public class AutoAlignToGoal1 extends Command {

    private int distanceFromGoal;
    public static int rots;

	public AutoAlignToGoal1() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	requires(Robot.camera);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		//Take values from networkTables to autoalign to goal if not aligned already
    	rots = 0;
    	if (Robot.camera.goalsBeingSeen() >= 1 & distanceFromGoal < 6) {
    		System.out.println("Target too close");
    		Robot.driveTrain.driveArcade(-1, 0); //TODO: verify
    	}
    	if (Robot.camera.goalsBeingSeen() < 1) {
    		System.out.println("No target found");
    		Robot.driveTrain.driveArcade(0, 45); //TODO: verify
    		rots++;
    	}
    	else {
    		System.out.println("On target, ready to fire!");
    	}
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
}