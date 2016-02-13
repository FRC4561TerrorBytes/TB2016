package org.usfirst.frc.team4561.robot.commands;

import org.usfirst.frc.team4561.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoPIDShootHigh1 extends Command {

    public AutoPIDShootHigh1() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.rollers);
    	requires(Robot.shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//initialize in auto after command AutoAlignToGoal
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//FIRE
    	System.out.println("Shot firing!");
    	Robot.rollers.setRollers(1); //TODO: verify
    	Robot.shooter.setSetpoint(1); //TODO: verify
    	System.out.println("Shot Fired");
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
