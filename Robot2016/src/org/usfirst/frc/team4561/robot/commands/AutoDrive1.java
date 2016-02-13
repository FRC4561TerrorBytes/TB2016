package org.usfirst.frc.team4561.robot.commands;

import org.usfirst.frc.team4561.robot.Robot;
import org.usfirst.frc.team4561.robot.commands.AutoAlignToGoal1;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDrive1 extends Command {

    public AutoDrive1() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//activate in automode after command AutoPIDShootHigh
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (AutoAlignToGoal1.rots == 1) {
    		Robot.driveTrain.driveArcade(0, -45); 
    	}
    	if (AutoAlignToGoal1.rots == 2) {
    		Robot.driveTrain.driveArcade(0, -90);
    	}
    	
    	Robot.driveTrain.driveArcade(1, 0); //TODO: verify drive
    	
    	//Rotate so the robot can drive backwards through lowbar
    	//drive backwards through lowbar
    	//20 points in auto, beat that
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
