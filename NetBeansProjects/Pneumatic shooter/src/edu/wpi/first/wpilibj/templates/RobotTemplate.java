/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.camera.*;
import edu.wpi.first.wpilibj.camera.AxisCamera.*;
import edu.wpi.first.wpilibj.image.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotTemplate extends SimpleRobot {
    
    Joystick xbox;
    Relay compressor;
    Relay solenoid;
    ColorImage image;
    BinaryImage bImage;
    AxisCamera camera;
    
    public RobotTemplate() {
        xbox = new Joystick(1);
        compressor = new Relay(1);
        compressor.setDirection(Relay.Direction.kForward);
        solenoid = new Relay(2);
        solenoid.setDirection(Relay.Direction.kForward);
        
        camera = AxisCamera.getInstance();
        camera.writeMaxFPS(1);
        camera.writeCompression(0);
        camera.writeWhiteBalance(WhiteBalanceT.fixedFlour1);
        //camera.writeColorLevel(100);
        camera.writeBrightness(100);
        camera.writeExposureControl(ExposureT.hold);
        camera.writeExposurePriority(ExposurePriorityT.imageQuality);
        image = null;
        bImage = null;
    }
    
    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    public void autonomous() {
        
    }

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl() {
        while (isOperatorControl() && isEnabled()) {
            if (xbox.getRawButton(1)) {
                compressor.set(Relay.Value.kOn);
            } else {
                compressor.set(Relay.Value.kOff);
            }
            if (xbox.getRawButton(2)) {
                solenoid.set(Relay.Value.kOn);
            } else {
                solenoid.set(Relay.Value.kOff);
            }
            
            //camera
            if(camera.freshImage()) {
                try {
                    if (image != null) {
                        image.free();
                        image = null;
                    }
                    image = camera.getImage();
                } catch (AxisCameraException ex) {
                    ex.printStackTrace();
                } catch (NIVisionException ex) {
                    ex.printStackTrace();
                }
                try {
                    if (bImage != null) {
                        bImage.free();
                        bImage = null;
                    }
                    bImage = image.thresholdRGB(150, 255, 230, 255, 150, 255);
                } catch (NIVisionException ex) {
                    ex.printStackTrace();
                }
                try {
                    ParticleAnalysisReport[] particles = bImage.getOrderedParticleAnalysisReports();
                    for (int i = 0; i < particles.length; i++) {
                        ParticleAnalysisReport particle = particles[i];
                        System.out.print (particle.particleArea + " ");
                    }
                    System.out.println();
                } catch (NIVisionException ex) {
                    ex.printStackTrace();
                }
                
                
            }
        }
    }
    
    /**
     * This function is called once each time the robot enters test mode.
     */
    public void test() {
    
    }
}
