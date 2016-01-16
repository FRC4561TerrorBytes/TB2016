
package org.usfirst.frc.team4561.robot.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team4561.robot.RobotMap;
import org.usfirst.frc.team4561.robot.commands.ArcadeDrive;

/**
 *
 */
public class DriveTrain extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public CANTalon leftFront = new CANTalon(RobotMap.FRONT_LEFT_MOTOR_CAN);
	 public CANTalon leftRear = new CANTalon(RobotMap.BACK_LEFT_MOTOR_CAN);
	 public CANTalon rightFront = new CANTalon(RobotMap.FRONT_RIGHT_MOTOR_CAN);
	 public CANTalon rightRear = new CANTalon(RobotMap.BACK_RIGHT_MOTOR_CAN);
	 private CANTalon[] motorList = {leftFront, leftRear, rightFront, rightRear}; // for easily referencing all of the motors at once
	 private RobotDrive robotDrive = new RobotDrive(leftFront, leftRear,
				rightFront, rightRear);
	 
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new ArcadeDrive()); // is it arcade drive? kaiz pls tell me
    }

	public void ahead(double drive) {
		for (CANTalon motor : motorList){
			motor.set(drive);
		}
		
	}
	public void left(double drive){
		rightFront.set(drive);
		rightRear.set(drive);
	}
	public void right(double drive){
		leftFront.set(drive);
		leftRear.set(drive);
	}
}

