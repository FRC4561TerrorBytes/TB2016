package org.usfirst.frc.team4561.robot.subsystems;

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
    		cam2 = NIVision.IMAQdxOpenCamera("cam2", NIVision.IMAQdxCameraControlMode.CameraControlModeController);
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
	        server.setImage(frame);
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

