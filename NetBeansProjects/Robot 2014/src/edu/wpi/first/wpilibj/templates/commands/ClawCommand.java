/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.templates.RobotMap;

/**
 *
 * @author Owner
 */
public class ClawCommand extends CommandBase {

    public static Joystick xbox;
    public boolean initialized = false;
    public double encoderValue = -1;
    public double referenceTime = 0;

    public ClawCommand() {
        requires(upperClaw);
        requires(lowerClaw);
    }

    protected void initialize() {
    }

    public void enableControl() {
        upperClaw.enableControl();
        lowerClaw.enableControl();
    }

    public void disableControl() {
        upperClaw.disableControl();
        lowerClaw.disableControl();
    }

    protected void execute() {
        // initialize encoder
        if (!initialized) {
            lowerClaw.disable();
            try {
                RobotMap.lowerClaw.setX(-6);
                if (referenceTime == 0 ) {
                    referenceTime = System.currentTimeMillis();
                }
                if (Math.abs(lowerClaw.lowerClaw.getPosition()-encoderValue) > 0.01) {
                    encoderValue = lowerClaw.lowerClaw.getPosition();
                    referenceTime = System.currentTimeMillis();
                }
                if (Math.abs(System.currentTimeMillis()-referenceTime) > 500) {
                    initialized = true;
                    RobotMap.encoderOffset = RobotMap.lowerClaw.getPosition();
                    RobotMap.lowerClaw.setX(0);
                    lowerClaw.enable();
                    lowerClaw.setSetpoint(RobotMap.upPosition); // Starting Position
                }
            } catch (CANTimeoutException ex) {
                ex.printStackTrace();
            }
        } else {

            if (xbox.getRawButton(2)) {
                upperClaw.setSetpoint(1.000);
            } else if (xbox.getRawButton(1)) {
                upperClaw.setSetpoint(-1.000);
            } else {
                upperClaw.setSetpoint(0.000);
            }

            if (xbox.getRawButton(RobotMap.downButton)) {
                lowerClaw.setSetpoint(0);
            } else if (xbox.getRawButton(RobotMap.upButton)) {
                lowerClaw.setSetpoint(RobotMap.upPosition);
            }
            /*double rightY = xbox.getRawAxis(5);
            if (rightY > 0.5) {
                try {
                    RobotMap.lowerClaw.setX(6);
                } catch (CANTimeoutException ex) {
                    ex.printStackTrace();
                }
            } else if (rightY < -0.5) {
                try {
                    RobotMap.lowerClaw.setX(-6);
                } catch (CANTimeoutException ex) {
                    ex.printStackTrace();
                }
            } else {
                try {
                    RobotMap.lowerClaw.setX(0);
                } catch (CANTimeoutException ex) {
                    ex.printStackTrace();
                }
            }*/
        }
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }

}
