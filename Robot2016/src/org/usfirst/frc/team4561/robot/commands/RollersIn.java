package org.usfirst.frc.team4561.robot.commands;

import org.usfirst.frc.team4561.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.DigitalInput;
/**
 *
 */
public class RollersIn extends Command {

    public RollersIn() {
          requires(Robot.rollers);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//TODO: Define what motor for belt, Loader team input required.
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.rollers.setRollers(1); //TODO: Find speed values
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(Robot.rollers.loadFinishedSwitch.get()){
    		return true;
    	}
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.rollers.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
