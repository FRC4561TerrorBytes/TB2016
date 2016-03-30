package org.usfirst.frc.team4561.robot.triggers;

import org.usfirst.frc.team4561.robot.Robot;

import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 * Allows the SmartDashboard Button 0 to be treated like a conventional button.
 */
public class DPadDownTrigger extends Trigger {
    public boolean get() {
		if (Robot.oi != null) {
			return Robot.oi.getControllerDpad() == 180 || Robot.oi.getControllerDpad() == -180;
		}
		return false;
    }
}
