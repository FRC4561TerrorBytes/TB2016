
package org.usfirst.frc.team4561.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

import org.usfirst.frc.team4561.robot.automodes.AutoDoNothing;
import org.usfirst.frc.team4561.robot.automodes.AutoLowBarHighGoalNonVision;
import org.usfirst.frc.team4561.robot.automodes.AutoNeutralSelectable;
import org.usfirst.frc.team4561.robot.automodes.AutoReach;
import org.usfirst.frc.team4561.robot.commands.ArcadeDrive;
import org.usfirst.frc.team4561.robot.commands.MonitorPiHeartbeat;
import org.usfirst.frc.team4561.robot.subsystems.Arm;
import org.usfirst.frc.team4561.robot.subsystems.Camera;
import org.usfirst.frc.team4561.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4561.robot.subsystems.Rollers;
import org.usfirst.frc.team4561.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	
	public static OI oi;
	public static DriveTrain driveTrain;
	public static Rollers rollers;
	public static Arm arm;
	public static Camera camera;
	public static Shooter shooter;
	static NetworkTable debugTable;
	NetworkTable visionTable = NetworkTable.getTable("Vision");

    Command autonomousCommand;
    SendableChooser chooser;
    
    private static Robot robotSingleton;
    
    private static boolean useSecondaryController = true;
	
	public static Robot getInstance() {
		return robotSingleton;
	}

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	driveTrain = new DriveTrain();
    	rollers = new Rollers();
    	arm = new Arm();
    	camera = new Camera();
    	shooter = new Shooter();
    	oi = new OI();
    	robotSingleton = this;
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", new ArcadeDrive());
//        chooser.addObject("My Auto", new MyAutoCommand());
        SmartDashboard.putData("Auto mode", chooser);
        SmartDashboard.putData("loadFinishedSwitch", rollers.loadFinishedSwitch);
        if(isInDebugMode()) { 
			debugTable = NetworkTable.getTable("Debugging");
		}
    }
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit() {
    	arm.getPIDController().disable();
    	shooter.getPIDController().disable();
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		if(Robot.isInDebugMode()) {
			getDebugTable().putNumber("Left Joy X", oi.getLeftStickX());
			getDebugTable().putNumber("Left Joy Y", oi.getLeftStickY());
			getDebugTable().putNumber("Right Joy X", oi.getRightStickX());
			getDebugTable().putNumber("Right Joy Y", oi.getRightStickY());
			getDebugTable().putNumber("Left Joy Throttle", oi.getLeftStickThrottle());
			getDebugTable().putNumber("Corrected Left Joy Throttle", oi.getCorrectedLeftStickThrottle());
			getDebugTable().putBoolean("Touring Mode", oi.isTouringMode());
        	getDebugTable().putBoolean("Robot/IsAutonomous", this.isAutonomous());
        	getDebugTable().putBoolean("Robot/IsDisabled", this.isDisabled());
        	getDebugTable().putBoolean("Robot/IsEnabled", this.isEnabled());
        	getDebugTable().putBoolean("Robot/IsOperatorControl", this.isOperatorControl());
        	getDebugTable().putBoolean("Robot/IsTest", this.isTest());
        	getDebugTable().putBoolean("Robot/IsReal", Robot.isReal());
        	getDebugTable().putBoolean("Robot/IsSimulation", Robot.isSimulation());
        	getDebugTable().putBoolean("Robot/IsVerbose", Robot.isVerbose());
		}
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
    public void autonomousInit() {
        autonomousCommand = (Command)chooser.getSelected();
        
		/* String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		switch(autoSelected) {
		case "My Auto":
			autonomousCommand = new MyAutoCommand();
			break;
		case "Default Auto":
		default:
			autonomousCommand = new ExampleCommand();
			break;
		} */
        
		try {
			int slider1 = (int)SmartDashboard.getNumber("DB/Slider 0");
			int slider2 = (int)SmartDashboard.getNumber("DB/Slider 1");
			int slider3 = (int)SmartDashboard.getNumber("DB/Slider 2");
			switch (slider1) {
				case 0:
					autonomousCommand = new AutoDoNothing();
					break;
				case 1:
					autonomousCommand = new AutoLowBarHighGoalNonVision();
				case 2:
					autonomousCommand = new AutoReach();
				case 3:
					// Rock Wall etc. Auto
					if(slider3 > 0) {
						autonomousCommand = new AutoNeutralSelectable(3, slider2, true);
					} else {
						autonomousCommand = new AutoNeutralSelectable(3, slider2, false);
					}
					break;
				case 4:
					if(slider3 > 0) {
						autonomousCommand = new AutoNeutralSelectable(4, slider2, true);
					} else {
						autonomousCommand = new AutoNeutralSelectable(4, slider2, false);
					}
					break;
				case 5:
					if(slider3 > 0) {
						autonomousCommand = new AutoNeutralSelectable(5, slider2, true);
					} else {
						autonomousCommand = new AutoNeutralSelectable(5, slider2, false);
					}
					break;
				case 6:
					if(slider3 > 0) {
						autonomousCommand = new AutoNeutralSelectable(6, slider2, true);
					} else {
						autonomousCommand = new AutoNeutralSelectable(6, slider2, false);
					}
					break;
				case 7:
					if(slider3 > 0) {
						autonomousCommand = new AutoNeutralSelectable(7, slider2, true);
					} else {
						autonomousCommand = new AutoNeutralSelectable(7, slider2, false);
					}
					break;
				case 8:
					if(slider3 > 0) {
						autonomousCommand = new AutoNeutralSelectable(8, slider2, true);
					} else {
						autonomousCommand = new AutoNeutralSelectable(8, slider2, false);
					}
					break;
				case 9:
					// Low Bar auto
					if(slider3 > 0) {
						autonomousCommand = new AutoNeutralSelectable(9, slider2, true);
					} else {
						autonomousCommand = new AutoNeutralSelectable(9, slider2, false);
					}
					break;
				default:
					autonomousCommand = new AutoDoNothing();
					break;
			}
		} catch(Throwable t) {
			System.out.println("Autonomous Picking Failed.");
		}
		
		if (autonomousCommand != null) {
			//Start automode
			autonomousCommand.start();
		}
		
        arm.getPIDController().enable();
        shooter.getPIDController().enable();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        if(Robot.isInDebugMode()) {
			getDebugTable().putNumber("Left Joy X", oi.getLeftStickX());
			getDebugTable().putNumber("Left Joy Y", oi.getLeftStickY());
			getDebugTable().putNumber("Right Joy X", oi.getRightStickX());
			getDebugTable().putNumber("Right Joy Y", oi.getRightStickY());
			getDebugTable().putNumber("Left Joy Throttle", oi.getLeftStickThrottle());
			getDebugTable().putNumber("Corrected Left Joy Throttle", oi.getCorrectedLeftStickThrottle());
			getDebugTable().putBoolean("Touring Mode", oi.isTouringMode());
        	getDebugTable().putBoolean("Robot/IsAutonomous", this.isAutonomous());
        	getDebugTable().putBoolean("Robot/IsDisabled", this.isDisabled());
        	getDebugTable().putBoolean("Robot/IsEnabled", this.isEnabled());
        	getDebugTable().putBoolean("Robot/IsOperatorControl", this.isOperatorControl());
        	getDebugTable().putBoolean("Robot/IsTest", this.isTest());
        	getDebugTable().putBoolean("Robot/IsReal", Robot.isReal());
        	getDebugTable().putBoolean("Robot/IsSimulation", Robot.isSimulation());
        	getDebugTable().putBoolean("Robot/IsVerbose", Robot.isVerbose());
		}
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
        arm.getPIDController().enable();
        shooter.getPIDController().enable();
        Scheduler.getInstance().add(new MonitorPiHeartbeat());
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        if(Robot.isInDebugMode()) {
			getDebugTable().putNumber("Left Joy X", oi.getLeftStickX());
			getDebugTable().putNumber("Left Joy Y", oi.getLeftStickY());
			getDebugTable().putNumber("Right Joy X", oi.getRightStickX());
			getDebugTable().putNumber("Right Joy Y", oi.getRightStickY());
			getDebugTable().putNumber("Left Joy Throttle", oi.getLeftStickThrottle());
			getDebugTable().putNumber("Corrected Left Joy Throttle", oi.getCorrectedLeftStickThrottle());
			getDebugTable().putBoolean("Touring Mode", oi.isTouringMode());
        	getDebugTable().putBoolean("Robot/IsAutonomous", this.isAutonomous());
        	getDebugTable().putBoolean("Robot/IsDisabled", this.isDisabled());
        	getDebugTable().putBoolean("Robot/IsEnabled", this.isEnabled());
        	getDebugTable().putBoolean("Robot/IsOperatorControl", this.isOperatorControl());
        	getDebugTable().putBoolean("Robot/IsTest", this.isTest());
        	getDebugTable().putBoolean("Robot/IsReal", Robot.isReal());
        	getDebugTable().putBoolean("Robot/IsSimulation", Robot.isSimulation());
        	getDebugTable().putBoolean("Robot/IsVerbose", Robot.isVerbose());
		}
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
        if(Robot.isInDebugMode()) {
			getDebugTable().putNumber("Left Joy X", oi.getLeftStickX());
			getDebugTable().putNumber("Left Joy Y", oi.getLeftStickY());
			getDebugTable().putNumber("Right Joy X", oi.getRightStickX());
			getDebugTable().putNumber("Right Joy Y", oi.getRightStickY());
			getDebugTable().putNumber("Left Joy Throttle", oi.getLeftStickThrottle());
			getDebugTable().putNumber("Corrected Left Joy Throttle", oi.getCorrectedLeftStickThrottle());
			getDebugTable().putBoolean("Touring Mode", oi.isTouringMode());
        	getDebugTable().putBoolean("Robot/IsAutonomous", this.isAutonomous());
        	getDebugTable().putBoolean("Robot/IsDisabled", this.isDisabled());
        	getDebugTable().putBoolean("Robot/IsEnabled", this.isEnabled());
        	getDebugTable().putBoolean("Robot/IsOperatorControl", this.isOperatorControl());
        	getDebugTable().putBoolean("Robot/IsTest", this.isTest());
        	getDebugTable().putBoolean("Robot/IsReal", Robot.isReal());
        	getDebugTable().putBoolean("Robot/IsSimulation", Robot.isSimulation());
        	getDebugTable().putBoolean("Robot/IsVerbose", Robot.isVerbose());
		}
    }
    
	private static final boolean verbose = false;
	
	public static boolean isVerbose() {
		return verbose;
	}
	
	private static final boolean debug = false;
	
	public static boolean isInDebugMode() {
		return debug;
	}
	public static NetworkTable getDebugTable() {
		return debugTable;
	}
	
	public static boolean useSecondaryController() {
		return useSecondaryController;
	}
}
