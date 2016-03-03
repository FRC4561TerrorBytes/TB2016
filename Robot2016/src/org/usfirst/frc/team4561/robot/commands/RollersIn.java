package org.usfirst.frc.team4561.robot.commands;

import org.usfirst.frc.team4561.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RollersIn extends Command {

	boolean loadedSwitchStatusOnStart;
	boolean currentLoadedSwitchStatus;
	
    public RollersIn() {
          requires(Robot.rollers);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(Robot.isVerbose()) {
    		System.out.println("Starting RollersIn");
    	}
    	loadedSwitchStatusOnStart = !Robot.rollers.loadFinishedSwitch.get();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	currentLoadedSwitchStatus = !Robot.rollers.loadFinishedSwitch.get();
    	if(!loadedSwitchStatusOnStart && currentLoadedSwitchStatus) {
    		Robot.rollers.setRollers(0);
    	} else {
    		Robot.rollers.setRollers(1.0);
    	}
    	if(Robot.isInDebugMode()) {
    		Robot.rollers.putDebugInfo();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.rollers.stop();
    	System.out.println("Stopping RollersIn");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
