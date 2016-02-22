package org.usfirst.frc.team4561.robot.automodes;

import org.usfirst.frc.team4561.robot.commands.AlignGoalRight;
import org.usfirst.frc.team4561.robot.commands.DriveArcadeTimed;
import org.usfirst.frc.team4561.robot.commands.Fire;
import org.usfirst.frc.team4561.robot.commands.ShooterAlign;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoSpyLowBar extends CommandGroup {
    
    public  AutoSpyLowBar() {
    	// Align to goal prefer right
    	addSequential(new AlignGoalRight());
    	// Spin up shooter
    	addSequential(new ShooterAlign());
    	// Fire
    	addSequential(new Fire());
    	// Realign to drive backwards through low bar
    	addSequential(new DriveArcadeTimed(0, 1, 0.6));
    	// Drives backwards through low bar
    	addSequential(new DriveArcadeTimed(-1, 0, 3));
    }
}
