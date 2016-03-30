package org.usfirst.frc.team4561.robot.commands;

import org.usfirst.frc.team4561.robot.Robot;
import org.usfirst.frc.team4561.robot.RobotMap;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * When started, if the robot doesn't see the goal, it starts turning at ALIGNMENT_SPEED.
 * The turn direction is based on the parameter left.
 * Once the goal is seen the PID loop kicks in to move the goal to the center of the camera view.
 * If the goal is lost after it is sighted, the robot will stop moving for 0.5 seconds.
 * If the goal is not reacquired by the end of the 0.5 seconds, the robot will begin turning
 * at ALIGNMENT_SPEED again. Its turn direction will not be affected.
 * 
 * The command will be stopped if the driver deflects the sticks or if the secondary operator presses the
 * DPad down button (the low goal button). 
 */
public class PIDGoalAlign extends PIDCommand {

	double centerX;
	NetworkTable visionTable;
	
	
	
	private static final int CAMERA_RESOLUTION_X = RobotMap.CAMERA_RESOLUTION_X;
	private static final double ALIGNMENT_SPEED = RobotMap.DRIVETRAIN_ALIGNMENT_SPEED;
	private boolean seeingGoal;
	private boolean preferLeft;
	
	private double currentTimeMillis = System.currentTimeMillis();
	
	private double timeLostTarget = -1000;
	
    public PIDGoalAlign(boolean left) {
    	super(0.4/(CAMERA_RESOLUTION_X/2), 0, 0); // TODO: I Term must be added eventually
    	requires(Robot.driveTrain);
    	getPIDController().setAbsoluteTolerance(1);
    	visionTable = NetworkTable.getTable("Vision");
    	preferLeft = left;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(Robot.isVerbose()) {
    		System.out.println("Starting PIDGoalAlign");
    	}
    	setSetpoint(0);
    	getPIDController().reset();
    	getPIDController().enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	centerX = visionTable.getNumber("centroidX", -12345);
    	if(centerX == -12345) {
    		if(seeingGoal) {
    			timeLostTarget = System.currentTimeMillis();
    			getPIDController().reset();
    		}
    		seeingGoal = false;
    	} else {
    		if(!seeingGoal) {
    			getPIDController().enable();
    		}
    		seeingGoal = true;
    	}
    	currentTimeMillis = System.currentTimeMillis();
    	
    	getPIDController().setPID(Robot.oi.getDashboardSlider0()/(CAMERA_RESOLUTION_X/2), 
				Robot.oi.getDashboardSlider1(),
				Robot.oi.getDashboardSlider2());

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(Robot.oi.getLeftStickX() != 0 ||
    		Robot.oi.getLeftStickY() != 0 ||
    		Robot.oi.getRightStickX() != 0 ||
    		Robot.oi.getRightStickY() != 0 ||
    		Robot.oi.getDPadUpPressed()) {
    			return true;
    		}
        return getPIDController().onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	if(Robot.isVerbose()) {
    		System.out.println("Stopping PIDGoalAlign");
    	}
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }

	protected double returnPIDInput() {
		return centerX - (CAMERA_RESOLUTION_X/2);
	}

	protected void usePIDOutput(double output) {
		if(seeingGoal) {
			if(Double.toString(output).length() > 8) {
				SmartDashboard.putString("DB/String 7", "Goal: " + Double.toString(output).substring(0, 7));
			} else {
				SmartDashboard.putString("DB/String 7", "Goal: " + Double.toString(output));
			}
			Robot.driveTrain.driveArcade(0, output);
			System.out.println("seeing goal");
		}
		else {
			SmartDashboard.putString("DB/String 7", "Goal: Goal not found");
			if(currentTimeMillis - timeLostTarget > 500) {
				System.out.println("alignspeed");
				if(preferLeft) {
					Robot.driveTrain.driveArcade(0, -ALIGNMENT_SPEED);
				} else {
					Robot.driveTrain.driveArcade(0, ALIGNMENT_SPEED);
				}
			}
			else {
				Robot.driveTrain.driveArcade(0, 0);
				System.out.println("curTimMil-timLosTar>500");
			}
		}
	}
}
