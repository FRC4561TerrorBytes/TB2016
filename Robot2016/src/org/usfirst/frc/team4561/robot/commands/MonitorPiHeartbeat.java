package org.usfirst.frc.team4561.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.ArrayList;

import org.usfirst.frc.team4561.robot.Robot;

/**
 *
 */
public class MonitorPiHeartbeat extends Command {

	NetworkTable visionTable;
	double coolLookingNumber;
	static ArrayList<Integer> coolLookingNumbers = new ArrayList<Integer>();
	boolean alive = false;
	
    public MonitorPiHeartbeat() {
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(Robot.isVerbose()) {
    		System.out.println("Starting MonitorPiHeartBeat");
    	}
    	visionTable = NetworkTable.getTable("Vision");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	coolLookingNumber = visionTable.getNumber("coolLookingNumber", 0);
    	coolLookingNumbers.add(0, (int)coolLookingNumber);
    	try {
    		coolLookingNumbers.remove(12);
    	} catch(Exception e) {
    		
    	}
	    alive = isAlive();
	    if(alive) {
	    	SmartDashboard.putString("DB/String 5", "Pi Online");
	    } else {
	    	SmartDashboard.putString("DB/String 5", "Pi Offline");
	    }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("Stopping MonitorPiHeartBeat");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
    
    private boolean isAlive() {
		try {
	        if(!coolLookingNumbers.get(0).equals(coolLookingNumbers.get(10))) {
	        	return true;
	        }
		} catch(Exception e) {
			return false;
		}
	    return false;
    }
}
