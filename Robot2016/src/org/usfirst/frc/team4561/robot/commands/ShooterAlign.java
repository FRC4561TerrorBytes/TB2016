package org.usfirst.frc.team4561.robot.commands;

import org.usfirst.frc.team4561.robot.Robot;
import org.usfirst.frc.team4561.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.TreeMap;

/**
 *
 */
public class ShooterAlign extends Command {

	TreeMap<Double, Double> velocityTable = new TreeMap<Double, Double>(); 
	
	double height;
	double width;
	double percent;
	double inches;
	
	NetworkTable visionTable;
	final double GOAL_WIDTH = 20; // Inches
	private final double CAMERA_HORIZONTAL_FOV = RobotMap.CAMERA_HORIZONTAL_FOV; // Degrees
	private final double CAMERA_RESOLUTION_X = RobotMap.CAMERA_RESOLUTION_X;
	
	final double SHOOTER_ANGLE_TO_GROUND = 30; // TODO: Verify
	
	double directDistance;
	double desiredSpeed;
	
    public ShooterAlign() {
    	requires(Robot.shooter);
    	visionTable = NetworkTable.getTable("Vision");
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(Robot.isVerbose()) {
    		System.out.println("Starting ShooterAlign");
    	}
    	for(double i = 0; i < 270; i++) {
    		double velocity = Math.sqrt((85.63/i) + (10.57 * i)) // Calculate desired wheel speed
        			* 12; // Convert to inches/sec
    		velocityTable.put(i, velocity);
    	}
    	System.out.println("ShooterAlign is running.");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	width = visionTable.getNumber("width", -12345);
    	
    	if(width != -12345) {
    		SmartDashboard.putString("DB/String 5", "W: " + width);
	    	percent = width/CAMERA_RESOLUTION_X;
	    	inches = GOAL_WIDTH/percent;
	    	directDistance = inches/(2*Math.tan((CAMERA_HORIZONTAL_FOV/2)*(Math.PI/180)));
	    	directDistance = directDistance * 1.4; // Fudge factor
	    	SmartDashboard.putString("DB/String 6", "D: " + directDistance);
	    	desiredSpeed = velocityTable.floorEntry(new Double(directDistance)).getValue().doubleValue();
	    	System.out.println(desiredSpeed);
	    	SmartDashboard.putString("DB/String 7", "S: " + (int)desiredSpeed);
	    	
//	    	Robot.shooter.setInchesPerSecond(desiredSpeed);
    	}
    	
    	else {
    		SmartDashboard.putString("DB/String 5", "W: Goal not found");
    		SmartDashboard.putString("DB/String 6", "D: Goal not found");
    		SmartDashboard.putString("DB/String 7", "S: Goal not found");
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.shooter.getPIDController().onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	if(Robot.isVerbose()) {
    		System.out.println("Stopping ShooterAlign");
    	}
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
