package org.usfirst.frc.team4561.robot.automodes;

import org.usfirst.frc.team4561.robot.commands.AlignGoalRight;
import org.usfirst.frc.team4561.robot.commands.DriveArcadeTimed;
import org.usfirst.frc.team4561.robot.commands.Fire;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoSpyRoughTerrain extends CommandGroup {
    
    public  AutoSpyRoughTerrain() {
    	// Align to goal prefer right
    	addSequential(new AlignGoalRight());
    	// Fire
    	addSequential(new Fire());
    	// Realign to drive backwards through defence.
    	addSequential(new DriveArcadeTimed(0, 1, 0.6));
    	// Realigns to drive backwards through defence, drives backwards through defence.
    	addSequential(new DriveArcadeTimed(1, 0, 3));
    }
}
