package org.usfirst.frc.team4561.robot.automodes;

import org.usfirst.frc.team4561.robot.Robot;
import org.usfirst.frc.team4561.robot.commands.DriveArcadeTimed;
import org.usfirst.frc.team4561.robot.commands.Fire;
import org.usfirst.frc.team4561.robot.commands.MoveArmTo;
import org.usfirst.frc.team4561.robot.commands.ShooterAlign;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoSpySelectable extends CommandGroup {
    
	/**
	 * 
	 * @param defence
	 * 1: Portcullis
	 * 2: Cheval de Frise
	 * 3: Moat
	 * 4: Ramparts
	 * 5: Drawbridge
	 * 6: Sally Port
	 * 7: Rock Wall
	 * 8: Rough Terrain
	 * 9: Low Bar
	 * @param pos
	 * @param shoot
	 */
    public  AutoSpySelectable(int defence, int pos, boolean shoot) {
    	
    	if(shoot) {
    		addSequential(new ShooterAlign());
    		addSequential(new Fire());
    	}
    	if(pos == 1) {
    		// Turn left to line up with the defence
    		addSequential(new DriveArcadeTimed(0, -1, 3));
    	}
    	else if(pos == 2) {
    		// TODO
    	} 
    	else if(pos == 3) {
    		// TODO
    	}
    	else if(pos == 4) {
    		// TODO
    	}
    	else if(pos == 5) {
    		// TODO
    	}
        if(defence == 3 || defence == 4 || defence == 7 || defence == 8 || defence == 9) {
        	// Drive over defence
        	addSequential(new DriveArcadeTimed(-1, 0, 3));
        }
        else if (defence == 1) {
        	// Drive to portcullis
        	addSequential(new DriveArcadeTimed(1, 0, 1));
        	// Raise portcullis
        	addSequential(new MoveArmTo(Robot.arm.presets.get("Intake")));
        	// Drive under portcullis
        	addSequential(new DriveArcadeTimed(1, 0, 2));
        }
        else if (defence == 2) {
        	// Drive to Cheval de Frise
        	addSequential(new DriveArcadeTimed(1, 0, 1));
        	// Lower arm onto Cheval de Frise
        	addSequential(new MoveArmTo(Robot.arm.presets.get("Bottom")));
        	// Drive over Cheval de Frise
        	addSequential(new DriveArcadeTimed(1, 0, 2));
        }
        else if (defence == 5 || defence == 6) {
        	// Assumed not possible to go through drawbridge or sally port without assistance
        }
    }
}
