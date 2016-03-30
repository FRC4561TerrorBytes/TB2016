package org.usfirst.frc.team4561.robot.automodes;

import org.usfirst.frc.team4561.robot.commands.DriveArcadeTimed;
import org.usfirst.frc.team4561.robot.commands.LowerArms;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutoLowBarBack extends CommandGroup {
    
    public  AutoLowBarBack() {
    	// Lower Arms to prep for low bar
    	addSequential(new LowerArms(), 0.5);
    	// Wait for gravity
    	addSequential(new WaitCommand(3));
    	// Drive through low bar
    	addSequential(new DriveArcadeTimed(-0.5, 0, 3));
    	// Back up through the low bar again to prepare for teleop crossing
    	addSequential(new DriveArcadeTimed(0.5, 0, 3));
    }
}
