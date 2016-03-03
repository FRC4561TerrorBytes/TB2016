package org.usfirst.frc.team4561.robot.triggers;

import org.usfirst.frc.team4561.robot.Robot;

import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Allows the SmartDashboard Button 0 to be treated like a conventional button.
 */
public class DPadUpTrigger extends Trigger {
    public boolean get() {
		if (Robot.oi != null) {
			return Robot.oi.getControllerDpad() == 0 || Robot.oi.getControllerDpad() == 360;
		}
		return false;
    }
}
