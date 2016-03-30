package org.usfirst.frc.team4561.robot.triggers;

import org.usfirst.frc.team4561.robot.Robot;
import org.usfirst.frc.team4561.robot.RobotMap;

import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 * Allows the SmartDashboard Button 0 to be treated like a conventional button.
 */
public class ArmPreset1Trigger extends Trigger {
    public boolean get() {
		if (Robot.oi != null) {
			return Robot.oi.getArmPIDAxis() > RobotMap.ARM_PRESET_1_LOWER
					&& Robot.oi.getArmPIDAxis() < RobotMap.ARM_PRESET_1_UPPER;
		}
		return false;
    }
}
