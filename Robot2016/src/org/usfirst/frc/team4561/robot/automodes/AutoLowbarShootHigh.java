package org.usfirst.frc.team4561.robot.automodes;

import org.usfirst.frc.team4561.robot.commands.DriveArcadeTimed;
import org.usfirst.frc.team4561.robot.commands.Fire;
import org.usfirst.frc.team4561.robot.commands.PIDGoalAlign;
import org.usfirst.frc.team4561.robot.commands.ShooterAlign;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLowbarShootHigh extends CommandGroup {
    
    public  AutoLowbarShootHigh() {
    	// Align to goal prefer right
    	addSequential(new DriveArcadeTimed(0, 1, 1)); //TODO: must verify values
    	// Spin up shooter
    	addSequential(new ShooterAlign());
    	// Fire
    	addSequential(new Fire());
    	// Realign to drive backwards through low bar
    	addSequential(new DriveArcadeTimed(0, -1, 1)); //TODO: Let us test FIRST!!
    	// Drives backwards through low bar
    	addSequential(new DriveArcadeTimed(-1, 0, 3));
    }
}
