package org.usfirst.frc.team4561.robot.subsystems;

import org.usfirst.frc.team4561.robot.Robot;
import org.usfirst.frc.team4561.robot.commands.StreamCamera;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

import java.util.ArrayList;

/**
 * Allows switching between camera feeds. 
 * When a camera is not being streamed, it neither 
 * consumes bandwidth nor places a load on the roboRIO.
 */
public class Camera extends Subsystem {
	
	private final int CAMERA_QUALITY = 30;

    public void initDefaultCommand() {
    	setDefaultCommand(new StreamCamera());
    }
    
    // Stores ID values of the cameras.
    public int cam0;
    public int cam2;
    private int currentCam;
    private ArrayList<Integer> camList;
    
    boolean noCamerasInitialized;
    
    // Holds the frame to send to the DS.
	private Image frame;
	
	private CameraServer server;
	
	private NetworkTable contours;
    
    public Camera() {
    	if(Robot.isVerbose()) {
    		System.out.println("Initializing Camera Subsystem");
    	}
    	camList = new ArrayList<Integer>();
    	// Store the camera IDs
    	try {
    		cam0 = NIVision.IMAQdxOpenCamera("cam0", NIVision.IMAQdxCameraControlMode.CameraControlModeController);
    		camList.add(cam0);
    		noCamerasInitialized = false;
    	}
    	catch(Exception e) {
    		System.out.println("Camera \"cam0\" was not initialized properly, check \n"
    						 + "to make sure cameras are properly installed.");
    	}
    	try {
    		cam2 = NIVision.IMAQdxOpenCamera("cam1", NIVision.IMAQdxCameraControlMode.CameraControlModeController);
    		camList.add(cam2);
    		noCamerasInitialized = false;
    	}
    	catch(Exception e) {
    		System.out.println("Camera \"cam2\" was not initialized properly, check \n"
    						 + "to make sure cameras are properly installed.");
    	}
        // Set the starting camera to cam1.
    	try {
    		currentCam = camList.get(0);
    	} catch(Exception e) {
    		System.out.println("No cameras found. No video will be streamed.");
    		noCamerasInitialized = true;
    	}
    	if(!noCamerasInitialized) {
	        // Create an empty image to capture to
	        frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
	        server = CameraServer.getInstance();
	        // Set stream quality
	        server.setQuality(CAMERA_QUALITY);
	        // Begin streaming first camera
	        switchCam(camList.get(0));
    	}
    }
    
    /** 
     * Switch the streamed camera.
     * @param cam Camera ID
     */
    public void switchCam(int cam) {
    	if(!noCamerasInitialized) {
			NIVision.IMAQdxStopAcquisition(currentCam);
	    	NIVision.IMAQdxConfigureGrab(cam);
	    	NIVision.IMAQdxStartAcquisition(cam);
	    	currentCam = cam;
	    }
    }
    /**
     * Toggle the camera stream between two cameras.
     */
    public void switchCam() {
    	if (!noCamerasInitialized) {
    		if(camList.size() > camList.indexOf(currentCam) + 1) {
    			switchCam(camList.get(camList.indexOf(currentCam) + 1));
    		} else {
    			switchCam(camList.get(0));
    		}
    	}
    }
    
    /**
     * Captures a frame and sends it to the Driver Station.
     */
    public void update() {
    	if (!noCamerasInitialized) {
	    	NIVision.IMAQdxGrab(currentCam, frame, 1);
	    	if(currentCam == cam0) {	    		
	    		NIVision.Point outsideSquareLeftStart1 = new NIVision.Point(309, 229);
	    		NIVision.Point outsideSquareLeftEnd1 = new NIVision.Point(309, 238);
	    		NIVision.Point outsideSquareLeftStart2 = new NIVision.Point(309, 242);
	    		NIVision.Point outsideSquareLeftEnd2 = new NIVision.Point(309, 250);
	    		
	    		NIVision.Point outsideSquareBotStart1 = new NIVision.Point(309, 251);
	    		NIVision.Point outsideSquareBotEnd1 = new NIVision.Point(318, 251);
	    		NIVision.Point outsideSquareBotStart2 = new NIVision.Point(322, 251);
	    		NIVision.Point outsideSquareBotEnd2 = new NIVision.Point(330, 251);
	    		
	    		NIVision.Point outsideSquareRightStart1 = new NIVision.Point(331, 251);
	    		NIVision.Point outsideSquareRightEnd1 = new NIVision.Point(331, 242);
	    		NIVision.Point outsideSquareRightStart2 = new NIVision.Point(331, 238);
	    		NIVision.Point outsideSquareRightEnd2 = new NIVision.Point(331, 230);
	    		
	    		NIVision.Point outsideSquareTopStart1 = new NIVision.Point(331, 229);
	    		NIVision.Point outsideSquareTopEnd1 = new NIVision.Point(322, 229);
	    		NIVision.Point outsideSquareTopStart2 = new NIVision.Point(318, 229);
	    		NIVision.Point outsideSquareTopEnd2 = new NIVision.Point(309, 229);
	    		
	    		NIVision.Point insideSquareLeftStart1 = new NIVision.Point(310, 230);
	    		NIVision.Point insideSquareLeftEnd1 = new NIVision.Point(310, 238);
	    		NIVision.Point insideSquareLeftStart2 = new NIVision.Point(310, 242);
	    		NIVision.Point insideSquareLeftEnd2 = new NIVision.Point(310, 249);
	    		
	    		NIVision.Point insideSquareBotStart1 = new NIVision.Point(310, 250);
	    		NIVision.Point insideSquareBotEnd1 = new NIVision.Point(318, 250);
	    		NIVision.Point insideSquareBotStart2 = new NIVision.Point(322, 250);
	    		NIVision.Point insideSquareBotEnd2 = new NIVision.Point(329, 250);
	    		
	    		NIVision.Point insideSquareRightStart1 = new NIVision.Point(330, 250);
	    		NIVision.Point insideSquareRightEnd1 = new NIVision.Point(330, 242);
	    		NIVision.Point insideSquareRightStart2 = new NIVision.Point(330, 238);
	    		NIVision.Point insideSquareRightEnd2 = new NIVision.Point(330, 231);
	    		
	    		NIVision.Point insideSquareTopStart1 = new NIVision.Point(330, 230);
	    		NIVision.Point insideSquareTopEnd1 = new NIVision.Point(322, 230);
	    		NIVision.Point insideSquareTopStart2 = new NIVision.Point(318, 230);
	    		NIVision.Point insideSquareTopEnd2 = new NIVision.Point(311, 230);
	    		
	    		NIVision.Point vertLeftStart1 = new NIVision.Point(319, 226);
	    		NIVision.Point vertLeftEnd1 = new NIVision.Point(319, 238);
	    		NIVision.Point vertMidStart1 = new NIVision.Point(320, 226);
	    		NIVision.Point vertMidEnd1 = new NIVision.Point(320, 238);
	    		NIVision.Point vertRightStart1 = new NIVision.Point(319, 226);
	    		NIVision.Point vertRightEnd1 = new NIVision.Point(319, 238);
	    		
	    		NIVision.Point vertLeftStart2 = new NIVision.Point(319, 242);
	    		NIVision.Point vertLeftEnd2 = new NIVision.Point(319, 254);
	    		NIVision.Point vertMidStart2 = new NIVision.Point(320, 242);
	    		NIVision.Point vertMidEnd2 = new NIVision.Point(320, 254);
	    		NIVision.Point vertRightStart2 = new NIVision.Point(319, 242);
	    		NIVision.Point vertRightEnd2 = new NIVision.Point(319, 254);
	    		
	    		NIVision.Point horizTopStart1 = new NIVision.Point(306, 239);
	    		NIVision.Point horizTopEnd1 = new NIVision.Point(318, 239);
	    		NIVision.Point horizMidStart1 = new NIVision.Point(306, 240);
	    		NIVision.Point horizMidEnd1 = new NIVision.Point(318, 240);
	    		NIVision.Point horizBotStart1 = new NIVision.Point(306, 241);
	    		NIVision.Point horizBotEnd1 = new NIVision.Point(318, 241);
	    		
	    		NIVision.Point horizTopStart2 = new NIVision.Point(322, 239);
	    		NIVision.Point horizTopEnd2 = new NIVision.Point(334, 239);
	    		NIVision.Point horizMidStart2 = new NIVision.Point(322, 240);
	    		NIVision.Point horizMidEnd2 = new NIVision.Point(334, 240);
	    		NIVision.Point horizBotStart2 = new NIVision.Point(322, 241);
	    		NIVision.Point horizBotEnd2 = new NIVision.Point(334, 241);
	    		
	    		NIVision.imaqDrawLineOnImage(frame, frame, NIVision.DrawMode.DRAW_INVERT, 
	    				vertLeftStart1, vertLeftEnd1, 5);
	    		NIVision.imaqDrawLineOnImage(frame, frame, NIVision.DrawMode.DRAW_INVERT, 
	    				vertMidStart1, vertMidEnd1, 5);
	    		NIVision.imaqDrawLineOnImage(frame, frame, NIVision.DrawMode.DRAW_INVERT, 
	    				vertRightStart1, vertRightEnd1, 5);
	    		NIVision.imaqDrawLineOnImage(frame, frame, NIVision.DrawMode.DRAW_INVERT, 
	    				vertLeftStart2, vertLeftEnd2, 5);
	    		NIVision.imaqDrawLineOnImage(frame, frame, NIVision.DrawMode.DRAW_INVERT, 
	    				vertMidStart2, vertMidEnd2, 5);
	    		NIVision.imaqDrawLineOnImage(frame, frame, NIVision.DrawMode.DRAW_INVERT, 
	    				vertRightStart2, vertRightEnd2, 5);
	    		NIVision.imaqDrawLineOnImage(frame, frame, NIVision.DrawMode.DRAW_INVERT, 
	    				horizTopStart1, horizTopEnd1, 5);
	    		NIVision.imaqDrawLineOnImage(frame, frame, NIVision.DrawMode.DRAW_INVERT, 
	    				horizMidStart1, horizMidEnd1, 5);
	    		NIVision.imaqDrawLineOnImage(frame, frame, NIVision.DrawMode.DRAW_INVERT, 
	    				horizBotStart1, horizBotEnd1, 5);
	    		NIVision.imaqDrawLineOnImage(frame, frame, NIVision.DrawMode.DRAW_INVERT, 
	    				horizTopStart2, horizTopEnd2, 5);
	    		NIVision.imaqDrawLineOnImage(frame, frame, NIVision.DrawMode.DRAW_INVERT, 
	    				horizMidStart2, horizMidEnd2, 5);
	    		NIVision.imaqDrawLineOnImage(frame, frame, NIVision.DrawMode.DRAW_INVERT, 
	    				horizBotStart2, horizBotEnd2, 5);
	    		
	    		NIVision.imaqDrawLineOnImage(frame, frame, NIVision.DrawMode.DRAW_INVERT, 
	    				outsideSquareLeftStart1, outsideSquareLeftEnd1, 5);
	    		NIVision.imaqDrawLineOnImage(frame, frame, NIVision.DrawMode.DRAW_INVERT, 
	    				outsideSquareLeftStart2, outsideSquareLeftEnd2, 5);
	    		NIVision.imaqDrawLineOnImage(frame, frame, NIVision.DrawMode.DRAW_INVERT, 
	    				outsideSquareBotStart1, outsideSquareBotEnd1, 5);
	    		NIVision.imaqDrawLineOnImage(frame, frame, NIVision.DrawMode.DRAW_INVERT, 
	    				outsideSquareBotStart2, outsideSquareBotEnd2, 5);
	    		NIVision.imaqDrawLineOnImage(frame, frame, NIVision.DrawMode.DRAW_INVERT, 
	    				outsideSquareRightStart1, outsideSquareRightEnd1, 5);
	    		NIVision.imaqDrawLineOnImage(frame, frame, NIVision.DrawMode.DRAW_INVERT, 
	    				outsideSquareRightStart2, outsideSquareRightEnd2, 5);
	    		NIVision.imaqDrawLineOnImage(frame, frame, NIVision.DrawMode.DRAW_INVERT, 
	    				outsideSquareTopStart1, outsideSquareTopEnd1, 5);
	    		NIVision.imaqDrawLineOnImage(frame, frame, NIVision.DrawMode.DRAW_INVERT, 
	    				outsideSquareTopStart2, outsideSquareTopEnd2, 5);
	    		
	    		NIVision.imaqDrawLineOnImage(frame, frame, NIVision.DrawMode.DRAW_INVERT, 
	    				insideSquareLeftStart1, insideSquareLeftEnd1, 5);
	    		NIVision.imaqDrawLineOnImage(frame, frame, NIVision.DrawMode.DRAW_INVERT, 
	    				insideSquareLeftStart2, insideSquareLeftEnd2, 5);
	    		NIVision.imaqDrawLineOnImage(frame, frame, NIVision.DrawMode.DRAW_INVERT, 
	    				insideSquareBotStart1, insideSquareBotEnd1, 5);
	    		NIVision.imaqDrawLineOnImage(frame, frame, NIVision.DrawMode.DRAW_INVERT, 
	    				insideSquareBotStart2, insideSquareBotEnd2, 5);
	    		NIVision.imaqDrawLineOnImage(frame, frame, NIVision.DrawMode.DRAW_INVERT, 
	    				insideSquareRightStart1, insideSquareRightEnd1, 5);
	    		NIVision.imaqDrawLineOnImage(frame, frame, NIVision.DrawMode.DRAW_INVERT, 
	    				insideSquareRightStart2, insideSquareRightEnd2, 5);
	    		NIVision.imaqDrawLineOnImage(frame, frame, NIVision.DrawMode.DRAW_INVERT, 
	    				insideSquareTopStart1, insideSquareTopEnd1, 5);
	    		NIVision.imaqDrawLineOnImage(frame, frame, NIVision.DrawMode.DRAW_INVERT, 
	    				insideSquareTopStart2, insideSquareTopEnd2, 5);
	    	}
	        server.setImage(frame);
    	}
    	if(Robot.isInDebugMode()) {
    		Robot.getDebugTable().putNumber("Camera/cam0", cam0);
    		Robot.getDebugTable().putNumber("Camera/cam2", cam2);
    		Robot.getDebugTable().putNumber("Camera/currentCam", currentCam);
    		Robot.getDebugTable().putBoolean("Camera/noCamerasInitialized", noCamerasInitialized);
    	}
    }
    
    public int goalsBeingSeen() {
    	contours = NetworkTable.getTable("GRIP/contours");
    	int numberOfGoals = 0;
    	double[] defaultArray = {0.0};
    	double[] heights = contours.getNumberArray("height", defaultArray);
    	double[] widths = contours.getNumberArray("width", defaultArray);
    	for(int i = 0; i < heights.length; i++) {
    		try {
    			if(heights[i] > widths[i]) {
    				numberOfGoals++;
    			}
    		}
    		catch(ArrayIndexOutOfBoundsException aioobe) {
    		}
    	}
    	return numberOfGoals;
    }
}

