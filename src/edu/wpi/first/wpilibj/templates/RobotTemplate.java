/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Timer;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotTemplate extends SimpleRobot {

    private final DigitalOutput test = new DigitalOutput(8);

    public RobotTemplate() {
        System.out.println("now in constructor)");
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
        System.out.println("Hi there");

        int iocount = 0;

        while (isOperatorControl() && isEnabled()) {
            if (iocount % 40 == 0) {
                test.set(true);
                System.out.print(iocount);
                System.out.println(" high");
            } else if (iocount % 20 == 0) {
                test.set(false);
                System.out.println("low");
            }
            iocount++;

            Timer.delay(0.1);

        }
    }
}
