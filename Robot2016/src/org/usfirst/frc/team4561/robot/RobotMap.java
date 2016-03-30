package org.usfirst.frc.team4561.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// Motors (PWM)
	// DriveTrain
    public static final int FRONT_LEFT_MOTOR_CAN = 8;
    public static final int FRONT_RIGHT_MOTOR_CAN = 5;
    public static final int REAR_LEFT_MOTOR_CAN = 6;
    public static final int REAR_RIGHT_MOTOR_CAN = 2;
    // Shooter
    public static final int LEFT_SHOOTER_MOTOR = 3;
    public static final int RIGHT_SHOOTER_MOTOR = 4;
    // Flashlight
    public static final int FLASHLIGHT_MOTOR = 7;
    // Arm
    public static final int LEFT_ARM_MOTOR = 9;
    public static final int RIGHT_ARM_MOTOR = 10;
    // Roller
    public static final int ROLLER_MOTOR = 1;
    
    // Joystick ports (USB)
    public static final int LEFT_JOYSTICK = 0;
    public static final int RIGHT_JOYSTICK = 1;
    public static final int CONTROLLER = 2;
    
    // Declarations
    public static int CAMERA_TOGGLE_BUTTON = 1;
    public static int ROLL_OUT_BUTTON_1 = 5;
    public static int ROLL_OUT_BUTTON_2 = 6;
    public static int ROLL_OUT_BUTTON_3 = 5;
    public static int ROLL_OUT_BUTTON_4 = 6;
    public static int ROLL_IN_BUTTON_1 = 3;
    public static int ROLL_IN_BUTTON_2 = 4;
    public static int ROLL_IN_BUTTON_3 = 3;
    public static int ROLL_IN_BUTTON_4 = 4;
    public static int REVERSE_DIRECTION_BUTTON = 2;
    public static int TOURING_MODE_BUTTON = 2;
    
	public static int SHOOTER_FULL_BUTTON = 9;
    public static int SHOOTER_LOW_BUTTON_1 = 13;
    public static int SHOOTER_LOW_BUTTON_2 = 10;
    public static int SHOOTER_OFF_BUTTON = 4;
    public static int ARM_TOURING_BUTTON_1 = 5;
    public static int ARM_TOURING_BUTTON_2 = 7;
    public static int ALIGN_LEFT_BUTTON = 9;
    public static int ALIGN_RIGHT_BUTTON = 10;
    public static int ARM_PID_BUTTON = 1;
    
    // Preset 1 = -0.67
    public static final double ARM_PRESET_1_LOWER = -1.0;
    public static final double ARM_PRESET_1_UPPER = -0.505;
    // Preset 2 = -0.34
    public static final double ARM_PRESET_2_LOWER = -0.505;
    public static final double ARM_PRESET_2_UPPER = -0.17;
    // Default Value = 0
    public static final double ARM_PRESET_DEFAULT_LOWER = -0.17;
    public static final double ARM_PRESET_DEFAULT_UPPER = 0.155;
    // Preset 3 = 0.31
    public static final double ARM_PRESET_3_LOWER = 0.155;
    public static final double ARM_PRESET_3_UPPER = 0.475;
    // Preset 4 = 0.64
    public static final double ARM_PRESET_4_LOWER = 0.475;
    public static final double ARM_PRESET_4_UPPER = 0.805;
    // Preset 5 = 0.97
    public static final double ARM_PRESET_5_LOWER = 0.805;
    public static final double ARM_PRESET_5_UPPER = 1.0;
    
 
    public RobotMap() {
    	if(Robot.useSecondaryController()) {
/*--------------------------------------------------------------------------------------------------------------*/
/*			  *//*								 *//*						   *//*					 			*/
/*			  *//*			Left Stick			 *//*		Right Stick		   *//*			Controller			*/
/*			  *//*								 *//*						   *//*							    */
/*--------------------------------------------------------------------------------------------------------------*/
/*				*/						         /**/						   /**/ 						  /**/
/*	  			*/ REVERSE_DIRECTION_BUTTON = 2; /**/ TOURING_MODE_BUTTON = 2; /**/  ALIGN_LEFT_BUTTON = 9;   /**/
/*	   Drive	*/								 /**/		 				   /**/  ALIGN_RIGHT_BUTTON = 10; /**/
/*	   Train	*/								 /**/		 				   /**/  						  /**/
/*--------------------------------------------------------------------------------------------------------------*/
/*				*/	   							 /**/						   /**/ 						  /**/
/*	  			*/	   							 /**/						   /**/ SHOOTER_OFF_BUTTON = 4;  /**/
/*	  Shooter	*/	   							 /**/						   /**/ SHOOTER_LOW_BUTTON_1 = 10;/**/
/*				*/	   							 /**/						   /**/ SHOOTER_LOW_BUTTON_2 = 13;/**/
/*				*/	   							 /**/						   /**/ SHOOTER_FULL_BUTTON = 9; /**/
/*				*/	   							 /**/						   /**/ 						  /**/
/*--------------------------------------------------------------------------------------------------------------*/
/*				*/	   							 /**/						   /**/ 						  /**/
/*				*/	   							 /**/						   /**//*ALIGN_LEFT = DPad Left*/ /**/
/*		Arm		*/	   							 /**/						   /**//*ALIGN_RIGHT = DPad Right*//**/
/*				*/	   							 /**/						   /**/ ARM_TOURING_BUTTON_1 = 5; /**/
/*				*/	   							 /**/						   /**/ ARM_TOURING_BUTTON_2 = 7; /**/
/*				*/	   							 /**/						   /**/ 	ARM_PID_BUTTON = 1;   /**/
/*				*/	   							 /**/						   /**/ 						  /**/
/*--------------------------------------------------------------------------------------------------------------*/
/*				*/	   							 /**/						   /**/ 						  /**/
/*	 			*/	   ROLL_IN_BUTTON_1 = 3;	 /**/	ROLL_IN_BUTTON_3 = 3;  /**/ 						  /**/
/*	 Rollers	*/	   ROLL_IN_BUTTON_2 = 4;	 /**/	ROLL_IN_BUTTON_4 = 4;  /**/ 						  /**/
/*				*/	   ROLL_OUT_BUTTON_1 = 5;	 /**/	ROLL_OUT_BUTTON_3 = 5; /**/ 						  /**/
/*				*/	   ROLL_OUT_BUTTON_2 = 6;	 /**/	ROLL_OUT_BUTTON_4 = 6; /**/ 						  /**/
/*				*/	   							 /**/						   /**/ 						  /**/
/*--------------------------------------------------------------------------------------------------------------*/
/*				*/	   							 /**/						   /**/ 						  /**/
/*	  Camera	*/	 CAMERA_TOGGLE_BUTTON = 11;  /**/						   /**/ 						  /**/
/*				*/	   							 /**/						   /**/ 						  /**/
/*--------------------------------------------------------------------------------------------------------------*/    
    	}
    	else {
/*--------------------------------------------------------------------------------------------------------------*/
/*			  *//*								 *//*						   *//*					 			*/
/*			  *//*			Left Stick			 *//*		Right Stick		   *//*			Controller			*/
/*			  *//*								 *//*						   *//*							    */
/*--------------------------------------------------------------------------------------------------------------*/
/*				*/						         /**/						   /**/ 						  /**/
/*	  			*/ REVERSE_DIRECTION_BUTTON = 2; /**/ TOURING_MODE_BUTTON = 2; /**/  ALIGN_LEFT_BUTTON = 9;   /**/
/*	   Drive	*/								 /**/		 				   /**/  ALIGN_RIGHT_BUTTON = 10; /**/
/*	   Train	*/								 /**/		 				   /**/  						  /**/
/*--------------------------------------------------------------------------------------------------------------*/
/*				*/	   							 /**/						   /**/ 						  /**/
/*	  			*/	   							 /**/						   /**/ SHOOTER_OFF_BUTTON = 11;  /**/
/*	  Shooter	*/	   							 /**/						   /**/ SHOOTER_LOW_BUTTON_1 = 11;/**/
/*				*/	   							 /**/						   /**/ SHOOTER_LOW_BUTTON_2 = 11;/**/
/*				*/	   							 /**/						   /**/ SHOOTER_FULL_BUTTON = 11; /**/
/*				*/	   							 /**/						   /**/ 						  /**/
/*--------------------------------------------------------------------------------------------------------------*/
/*				*/	   							 /**/						   /**/ 						  /**/
/*				*/	   							 /**/						   /**/    /*ARM_UP = DPad Up*/	  /**/
/*		Arm		*/	   							 /**/						   /**/ /*ARM_DOWN = DPad Down */ /**/
/*				*/	   							 /**/						   /**/ ARM_TOURING_BUTTON_1 = 5; /**/
/*				*/	   							 /**/						   /**/ ARM_TOURING_BUTTON_2 = 7; /**/
/*				*/	   							 /**/						   /**/ 						  /**/
/*--------------------------------------------------------------------------------------------------------------*/
/*				*/	   							 /**/						   /**/ 						  /**/
/*	 			*/	   ROLL_IN_BUTTON_1 = 3;	 /**/	ROLL_IN_BUTTON_3 = 3;  /**/ 						  /**/
/*	 Rollers	*/	   ROLL_IN_BUTTON_2 = 4;	 /**/	ROLL_IN_BUTTON_4 = 4;  /**/ 						  /**/
/*				*/	   ROLL_OUT_BUTTON_1 = 5;	 /**/	ROLL_OUT_BUTTON_3 = 5; /**/ 						  /**/
/*				*/	   ROLL_OUT_BUTTON_2 = 6;	 /**/	ROLL_OUT_BUTTON_4 = 6; /**/ 						  /**/
/*				*/	   							 /**/						   /**/ 						  /**/
/*--------------------------------------------------------------------------------------------------------------*/
/*				*/	   							 /**/						   /**/ 						  /**/
/*	  Camera	*/	 CAMERA_TOGGLE_BUTTON = 11;  /**/						   /**/ 						  /**/
/*				*/	   							 /**/						   /**/ 						  /**/
/*--------------------------------------------------------------------------------------------------------------*/    
    	}    
    }
    
    // Shooter buttons (Left Stick)
    public static final int SHOOTER_PID_FLIP_BUTTON = 3; // TODO: Verify
    
    // Shooter buttons (Controller)
    public static final int FIRE_BUTTON = 10; // TODO: Verify
    
    // Arm buttons (Left Stick)
    public static final int LOADER_BUTTON = 6; // TODO: Verify
    public static final int LOWSHOT_BUTTON = 3; // TODO: Verify
    
    // Arm buttons (Controller)
    public static final int LOWER_ARMS_BUTTON = 10; // TODO: Verify
    public static final int RAISE_ARMS_BUTTON = 9; // TODO: Verify
    public static final int MOVE_ARM_DELTA_UP = 6; // TODO: Verify
    
    // Encoders (DIO)
    public static final int SHOOTER_ENCODER_A_SOURCE = 0; // TODO: Verify
    public static final int SHOOTER_ENCODER_B_SOURCE = 1; // TODO: Verify
    public static final int ARM_LEFT_ENCODER_A_SOURCE = 2; // TODO: Verify
    public static final int ARM_LEFT_ENCODER_B_SOURCE = 3; // TODO: Verify
    public static final int ARM_RIGHT_ENCODER_A_SOURCE = 4; // TODO: Verify
    public static final int ARM_RIGHT_ENCODER_B_SOURCE = 5; //TODO: Verify
    
    // Limit Switches (DIO)
    public static final int ROLLER_LIMIT_SWITCH = 6; // TODO: Verify
    public static final int ARM_TOP_LIMIT_SWITCH = 7; // TODO: Verify
    public static final int ARM_BOTTOM_LIMIT_SWITCH = 8; // TODO: Verify
    
    // Camera Specifications
    public static final double CAMERA_HORIZONTAL_FOV = 59; // Degrees
    public static final double CAMERA_VERTICAL_FOV = 33.1; // Degrees
    public static final int CAMERA_RESOLUTION_X = 640; // Pixels
    public static final int CAMERA_RESOLUTION_Y = 480; // Pixels
    
    // DriveTrain constants
    public static final double TOURING_MODE_MULTIPLIER = 0.5;
    public static final double DRIVETRAIN_ALIGNMENT_SPEED = 0.2;
    
    // Arm constants
    public static final double ARM_DELTA_INCREMENT = 2; // Degrees
}
