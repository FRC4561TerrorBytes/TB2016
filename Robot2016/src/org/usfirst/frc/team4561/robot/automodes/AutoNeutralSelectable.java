package org.usfirst.frc.team4561.robot.automodes;

import org.usfirst.frc.team4561.robot.Robot;
import org.usfirst.frc.team4561.robot.commands.DriveArcadeTimed;
import org.usfirst.frc.team4561.robot.commands.Fire;
import org.usfirst.frc.team4561.robot.commands.LowerArms;
import org.usfirst.frc.team4561.robot.commands.MoveArmTo;
import org.usfirst.frc.team4561.robot.commands.PIDGoalAlign;
import org.usfirst.frc.team4561.robot.commands.ShooterAlign;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutoNeutralSelectable extends CommandGroup {
    
	/**
	 * @param <b>defence</b><br>
	 * &nbsp&nbsp&nbsp&nbsp 1: Portcullis<br>
	 * &nbsp&nbsp&nbsp&nbsp 2: Cheval de Frise<br>
	 * &nbsp&nbsp&nbsp&nbsp 3: Moat<br>
	 * &nbsp&nbsp&nbsp&nbsp 4: Ramparts<br>
	 * &nbsp&nbsp&nbsp&nbsp 5: Drawbridge<br>
	 * &nbsp&nbsp&nbsp&nbsp 6: Sally Port<br>
	 * &nbsp&nbsp&nbsp&nbsp 7: Rock Wall<br>
	 * &nbsp&nbsp&nbsp&nbsp 8: Rough Terrain<br>
	 * &nbsp&nbsp&nbsp&nbsp 9: Low Bar<br>
	 * @param <b>pos</b><br>
	 * &nbsp&nbsp&nbsp&nbsp The position of the defence the robot starts
	 * behind. The low bar is always on position 1.
	 * @param <b>shoot</b><br>
	 * &nbsp&nbsp&nbsp&nbsp Whether or not the robot should fire a shot.
	 */
    public  AutoNeutralSelectable(int defence, int pos, boolean shoot) {
        if(defence == 3		// Moat
        || defence == 4		// Ramparts
        || defence == 7 	// Rock Wall
        || defence == 8		// Rough Terrain
        ) {
        	// Drive over defence
        	addSequential(new DriveArcadeTimed(-1, 0, 2));
        }
        else if (defence == 9) {	// Low Bar
        	addSequential(new LowerArms(), 1.2);
        	addSequential(new WaitCommand(3));
        	addSequential(new DriveArcadeTimed(-0.5, 0, 3));
        }
        else if (defence == 1) { // Portcullis
        	// Drive to portcullis
        	addSequential(new DriveArcadeTimed(1, 0, 1));
        	// Raise portcullis
        	addSequential(new MoveArmTo(Robot.arm.presets.get("Intake")));
        	// Drive under portcullis
        	addSequential(new DriveArcadeTimed(1, 0, 2));
        }
        else if (defence == 2) { // Cheval de Frise
        	// Drive to Cheval de Frise
        	addSequential(new DriveArcadeTimed(1, 0, 1));
        	// Lower arm onto Cheval de Frise
        	addSequential(new MoveArmTo(Robot.arm.presets.get("Bottom")));
        	// Drive over Cheval de Frise
        	addSequential(new DriveArcadeTimed(1, 0, 2));
        }
        else if (defence == 5  // Drawbridge
        	|| defence == 6) { // Sally Port
        	// Assumed not possible to go through drawbridge or sally port without assistance
        }
        if(shoot) {
        	if(pos <= 3) {
        		// Find goal preferring right
        		addSequential(new PIDGoalAlign(false));
        	}
        	else if(pos >= 4) {
        		// Find goal preferring left
        		addSequential(new PIDGoalAlign(true));
        	}
        	// Spin up shooter
        	addSequential(new ShooterAlign());
        	// Fire
        	addSequential(new Fire());
        }
    }
}
