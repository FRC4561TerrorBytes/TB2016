package org.usfirst.frc.team4561.robot.triggers;

import org.usfirst.frc.team4561.robot.Robot;

import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Allows the SmartDashboard Button 1 to be treated like a conventional button.
 */
public class DSButton1Trigger extends Trigger {
    public boolean get() {
		if (Robot.oi != null) {
			return SmartDashboard.getBoolean("DB/Button 1");
		}
		return false;
    }
}
