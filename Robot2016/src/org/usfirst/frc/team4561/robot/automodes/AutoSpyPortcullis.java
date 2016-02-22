package org.usfirst.frc.team4561.robot.automodes;

import org.usfirst.frc.team4561.robot.Robot;
import org.usfirst.frc.team4561.robot.commands.AlignGoalRight;
import org.usfirst.frc.team4561.robot.commands.DriveArcadeTimed;
import org.usfirst.frc.team4561.robot.commands.Fire;
import org.usfirst.frc.team4561.robot.commands.MoveArmTo;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoSpyPortcullis extends CommandGroup {
    
    public  AutoSpyPortcullis() {
    	// Aligns to goal prefer right
    	addSequential(new AlignGoalRight());
    	// fire
    	addSequential(new Fire());
    	// Start in courtyard, rotates to face defence, drives to defence, arm manipulates defence, drives under defence.
    	// Realign to drive backwards through defence.
    	addSequential(new DriveArcadeTimed(0, 1, 0.6));
    	// Realigns to drive backwards through defence, drives backwards through defence.
    	addSequential(new DriveArcadeTimed(1, 0, 1));
    	addSequential(new MoveArmTo(Robot.arm.presets.get("Loader")));
    	addSequential(new DriveArcadeTimed(1, 0, 1));
    }
}
