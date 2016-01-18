package org.usfirst.frc.team4561.robot.subsystems;

import org.usfirst.frc.team4561.robot.commands.StartCameraStream;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Allows switching between camera feeds. 
 * When a camera is not being streamed, it neither 
 * consumes bandwidth nor places a load on the roboRIO.
 */
public class Camera extends Subsystem {
	
	private final int CAMERA_QUALITY = 30;

    public void initDefaultCommand() {
    	setDefaultCommand(new StartCameraStream());
    }
    
    // Stores ID values of the cameras.
    public final int cam1;
    public final int cam2;
    private int currentCam;
    
    // Holds the frame to send to the DS.
	private Image frame;
	
	private CameraServer server;
    
    public Camera() {
    	// Store the camera IDs
        cam1 = NIVision.IMAQdxOpenCamera("cam0", NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        cam2 = NIVision.IMAQdxOpenCamera("cam2", NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        // Set the starting camera to cam1.
        currentCam = cam1;
        // Create an empty image to capture to
        frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
        server = CameraServer.getInstance();
        // Set stream quality
        server.setQuality(CAMERA_QUALITY);
        // Begin streaming cam1
        switchCam(cam1);
    }
    
    /** 
     * Switch the streamed camera.
     * @param cam Camera ID
     */
    public void switchCam(int cam) {
		NIVision.IMAQdxStopAcquisition(currentCam);
    	NIVision.IMAQdxConfigureGrab(cam);
    	NIVision.IMAQdxStartAcquisition(cam);
    	currentCam = cam;
    }
    /**
     * Toggle the camera stream between two cameras.
     */
    public void switchCam() {
    	if(currentCam == cam1) {
    		switchCam(cam2);
    	}
    	else {
    		switchCam(cam1);
    	}
    }
    
    /**
     * Captures a frame and sends it to the Driver Station.
     */
    public void update() {
    	NIVision.IMAQdxGrab(currentCam, frame, 1);
        server.setImage(frame);
    }
}

