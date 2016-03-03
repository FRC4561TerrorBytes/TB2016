package org.usfirst.frc.team4561.robot.commands;


import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ShootLow extends CommandGroup {
    
    public  ShootLow() {
//    	if(Robot.isVerbose()) {
//    		System.out.println("Starting ShootLow Macro");
//    	}
    	addSequential(new SetArmPID(true));
    	addSequential(new MoveArmTo(0.0));
    	addSequential(new RollersOut());
//        if(Robot.isVerbose()) {
//        	System.out.println("Stopping ShootLow Macro");
//        }
    }
}
