package org.usfirst.frc.team4561.robot.automodes;

import org.usfirst.frc.team4561.robot.commands.DriveArcadeTimed;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutoReach extends CommandGroup {
    
    public  AutoReach() {
    	// Drives forward until any part of the robot is within the outer works
        addSequential(new DriveArcadeTimed(1, 0, 0.5));
        addSequential(new WaitCommand(20.0));
    }
}