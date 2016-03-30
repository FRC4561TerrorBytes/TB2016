package org.usfirst.frc.team4561.robot.automodes;

import org.usfirst.frc.team4561.robot.commands.DriveArcadeTimed;
import org.usfirst.frc.team4561.robot.commands.LowerArms;
import org.usfirst.frc.team4561.robot.commands.PIDGoalAlign;
import org.usfirst.frc.team4561.robot.commands.RaiseArms;
import org.usfirst.frc.team4561.robot.commands.RollersIn;
import org.usfirst.frc.team4561.robot.commands.SetShooterSpeed;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * 
 */
public class AutoTerrainHighGoalRightVision extends CommandGroup {
    
	// Time: 14.5 Seconds
	// Start: Facing Low Bar
	// End: Facing Tower, Enemy Courtyard, Shooter off, Arm up
	
	
    public  AutoTerrainHighGoalRightVision() {
    	// Lower Arms to prep for low bar
    	addSequential(new LowerArms(), 0.5);
    	// Wait for gravity
    	addSequential(new WaitCommand(3.0));
    	// Drive through low bar
    	addSequential(new DriveArcadeTimed(-1, 0, 2));
    	// Raise arms to shooter position
    	addSequential(new RaiseArms(), 2.0);
    	// Turn to face goal
    	addSequential(new PIDGoalAlign(false));
    	// Spin up shooter to MAX
    	addSequential(new SetShooterSpeed(1.0));
    	// Wait a bit for shooter to spin up
    	addSequential(new WaitCommand(1.0));
    	// Fire
    	addSequential(new RollersIn(), 3.0);
    	// Stop Shooter
    	addSequential(new SetShooterSpeed(0));
    }
}
