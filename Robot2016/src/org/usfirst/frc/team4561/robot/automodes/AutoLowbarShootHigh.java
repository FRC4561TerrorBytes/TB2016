package org.usfirst.frc.team4561.robot.automodes;

import org.usfirst.frc.team4561.robot.Robot;

import org.usfirst.frc.team4561.robot.commands.DriveArcadeTimed;
import org.usfirst.frc.team4561.robot.commands.Fire;
import org.usfirst.frc.team4561.robot.commands.PIDGoalAlign;
import org.usfirst.frc.team4561.robot.commands.SetShooterSpeed;
import org.usfirst.frc.team4561.robot.commands.ShooterAlign;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLowbarShootHigh extends CommandGroup {
    
    public  AutoLowbarShootHigh() {
    	 
    	// Drives forwards through low bar
    	addSequential(new DriveArcadeTimed(-1, 0, 3));
    	// Align to goal prefer right
    	addSequential(new DriveArcadeTimed(0, 1, 1)); //TODO: must verify values
    	// Spin up shooter
    	//TODO: let us test FIRST!!
    	// Fire
    	addSequential(new SetShooterSpeed(1)); //TODO: verify
    	addSequential(new Fire());
    }
}
