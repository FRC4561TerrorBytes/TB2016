package org.usfirst.frc.team4561.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.CANTalon;
import org.usfirst.frc.team4561.robot.RobotMap;
import edu.wpi.first.wpilibj.DigitalInput;

// Warning: this subsystem is only compatible with the Talon SRX motor controller

public class Loader extends Subsystem {
	private final String MOTOR_TYPE = "CANTalonSRX";
	public SpeedController rollerMotor;
	public SpeedController leftArmMotor;
	public SpeedController rightArmMotor;
	public DigitalInput loadFinishedSwitch = new DigitalInput(1); //TODO: find port
	
	public void initDefaultCommand(){
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
	}
	public Loader() {
		constructMotorControllers();
	}
	
	

	
public void constructMotorControllers() {
	if(MOTOR_TYPE.equals("CANTalonSRX")) {
		rollerMotor = new CANTalon(/*RobotMap.rollerMotor*/0); //TODO: kaiz I swear if you miss this
		leftArmMotor = new CANTalon(/*RobotMap.leftArmMotor*/1);
		rightArmMotor = new CANTalon(/*RobotMap.rightArmMotor*/2);
		}
	else {
		System.out.println("The current motor controller is not connected or incompatible");
		System.out.println("Replace crappy motor controller(s) with Talon SRX(s)");
		System.out.println("or check wire connection with Talon SRX's");
		}
	}


public void moveLoaderMechanism(int motorID) {
	if(motorID==/*RobotMap.rollerMotor*/0); //TODO: kaiz
	
	else if(motorID==/*RobotMap.leftArmMotor*/1);
	
	else if(motorID==/*RobotMap.rightArmMotor*/2);
	
	else {
		System.out.println("Mechanism not found");
		}
	}
}
	







