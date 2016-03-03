package org.usfirst.frc.team4561.robot.triggers;

import org.usfirst.frc.team4561.robot.Robot;

import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Allows the SmartDashboard Button 0 to be treated like a conventional button.
 */
public class DPadLeftTrigger extends Trigger {
    public boolean get() {
		if (Robot.oi != null) {
			return Robot.oi.getControllerDpad() == 270 || Robot.oi.getControllerDpad() == -90;
		}
		return false;
    }
}
