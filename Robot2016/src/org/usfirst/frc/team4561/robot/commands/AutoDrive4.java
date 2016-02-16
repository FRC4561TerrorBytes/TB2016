package org.usfirst.frc.team4561.robot.commands;

import org.usfirst.frc.team4561.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDrive4 extends Command {

    public AutoDrive4() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (AutoAlignToGoal1.rots == 1) {
    		Robot.driveTrain.driveArcade(0, -45); 
    	}
    	if (AutoAlignToGoal1.rots == 2) {
    		Robot.driveTrain.driveArcade(0, -90);
    	}
    	
    	Robot.driveTrain.driveArcade(-1, 0); //TODO: verify drive
    	Robot.arm.setPoint = Robot.arm.presets.get("Bottom");
    	Robot.arm.setPoint = Robot.arm.presets.get("Loader"); //TODO: verify a point for arms to lift to
    	Robot.driveTrain.driveArcade(-1, 0); //TODO: verify drive
    	Robot.arm.setPoint = Robot.arm.presets.get("Bottom");
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
