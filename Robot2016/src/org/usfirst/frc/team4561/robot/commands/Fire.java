package org.usfirst.frc.team4561.robot.commands;

import org.usfirst.frc.team4561.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Fire extends CommandGroup {
    
    public Fire() {
//    	if(Robot.isVerbose()) {
//    		System.out.println("Starting Fire Macro");
//    	}
    	addSequential(new SetArmPID(true));
        addSequential(new MoveArmTo(Robot.arm.presets.get("Loader")));
        addSequential(new RollersOut());
//        if(Robot.isVerbose()) {
//        	System.out.println("Stopping Fire Macro");
//        }
    }
}
