package org.usfirst.frc.team4561.robot.commands;

import org.usfirst.frc.team4561.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveArcadeTimed extends Command {

	double drive;
	double rot;
	double seconds;
	
    public DriveArcadeTimed(double drive, double rot, double seconds) {
        requires(Robot.driveTrain);
        this.drive = drive;
        this.rot = rot;
        this.seconds = seconds;
        setTimeout(seconds);
    }
    

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.driveArcade(drive, rot);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	if(Robot.isVerbose()) {
    		System.out.println("Stopping DriveArcadeTimed");
    	}
    	Robot.driveTrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
