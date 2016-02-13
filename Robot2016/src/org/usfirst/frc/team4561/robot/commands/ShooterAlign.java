package org.usfirst.frc.team4561.robot.commands;

import org.usfirst.frc.team4561.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.TreeMap;

/**
 *
 */
public class ShooterAlign extends Command {

	TreeMap<Double, Double> velocityTable = new TreeMap<Double, Double>(); 
	
	double centerY;
	double height;
	double width;
	double percent;
	double inches;
	
	NetworkTable visionTable;
	final double GOAL_HEIGHT = 97;
	final double CAMERA_HEIGHT = 13; // TODO: Verify
	final double CAMERA_HORIZONTAL_FOV = 59; // Degrees TODO: Verify
	final double CAMERA_ANGLE_FROM_FLOOR = 15; // Degrees TODO: Verify
	final double CAMERA_RESOLUTION_X = 640; // TODO: Verify
	final double CAMERA_RESOLUTION_Y = 480; // TODO: Verify
	final double GOAL_WIDTH = 20; // Inches
	
	final double SHOOTER_ANGLE_TO_GROUND = 30; // TODO: Verify
	
	double targetAngleDeg;
	double targetAngleRad;
	
	double lateralDistance;
	double directDistance;
	double desiredSpeed;
	
    public ShooterAlign() {
//    	requires(Robot.shooter);
    	visionTable = NetworkTable.getTable("Vision");
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	for(double i = 0; i < 270; i++) {
    		double velocity = Math.sqrt((85.63/i) + (10.57 * i)) // Get wheel speed
        			* 0.0254; // Convert to inches/sec
    		velocityTable.put(i, velocity);
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	centerY = visionTable.getNumber("Vision/centroidY", CAMERA_RESOLUTION_Y/2);
    	width = visionTable.getNumber("Vision/width", -1);
    	
    	if(width != -1) {
	    	percent = width/CAMERA_RESOLUTION_X;
	    	
	    	targetAngleDeg = percent * CAMERA_HORIZONTAL_FOV;
	    	
	    	inches = (100*GOAL_WIDTH)/percent;
	    	
	    	lateralDistance = Math.tan((CAMERA_HORIZONTAL_FOV/2) * (180/Math.PI))/inches;
	    	
	    	SmartDashboard.putNumber("DB/String 5", lateralDistance);
	    	
	    	desiredSpeed = velocityTable.floorKey(lateralDistance);
	    	
	    	SmartDashboard.putNumber("DB/String 6", desiredSpeed);
	    	
	    	Robot.shooter.setInchesPerSecond(desiredSpeed);
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
    	end();
    }
}
