package org.usfirst.frc.team4561.robot.automodes;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutoDoNothing extends CommandGroup {
    
    public  AutoDoNothing() {
    	addSequential(new WaitCommand(20.0));
    }
}