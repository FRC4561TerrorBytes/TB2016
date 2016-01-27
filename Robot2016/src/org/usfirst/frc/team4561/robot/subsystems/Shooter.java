package org.usfirst.frc.team4561.robot.subsystems;

import org.usfirst.frc.team4561.robot.OI;
import org.usfirst.frc.team4561.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class Shooter extends PIDSubsystem {
	//private Talon fly1 = new Talon(RobotMap.FLY_ONE);
	//private Talon fly2 = new Talon(RobotMap.FLY_TWO);
	
	//private Encoder encoder = new Encoder(aSource, bSource);
	public Shooter() {
		super(0,0,0);// TODO: Kaiz do this PID black magic
	}

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected void usePIDOutput(double output) {
		//fly1.set(output);
		//fly2.set(output);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	protected void stop(){
		//fly1.set(0);
		//fly2.set(0);
	}
}
