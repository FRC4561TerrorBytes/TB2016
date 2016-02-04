package org.usfirst.frc.team4561.robot.commands;

import org.usfirst.frc.team4561.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShootLow extends Command {

    public ShootLow() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.rollers);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.rollers.rollerMotor.set(-1);
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

//Additionally required code

//OI
//-private JoystickButton lowShotButton = new JoystickButton(leftStick,
//			RobotMap.LOWSHOT_BUTTON); [under the Loader Button]
//-lowShotButton.whileHeld(new ShootLow()); [in the Loader button command assignment]
//-lowShotButton.whenReleased(Robot.rollers.setRoller(0)); [Same as above]
//-import org.usfirst.frc.team4561.robot.commands.ShootLow; [You know where this goes]

//RobotMap
//-public static int LOWSHOT_BUTTON = 13; [Under the loader button] 
