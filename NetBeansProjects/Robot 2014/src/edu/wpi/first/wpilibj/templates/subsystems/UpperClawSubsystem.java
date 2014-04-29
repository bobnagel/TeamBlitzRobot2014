/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;

/**
 *
 * @author Unknown
 */
public class UpperClawSubsystem extends PIDSubsystem {
    
    public CANJaguar upperClaw = RobotMap.upperClaw;
    
    public UpperClawSubsystem() {
        super("Upper Claw", 0, 0, .1, 9.5);
        this.getPIDController().setOutputRange(-10, 10);
    }

    protected void initDefaultCommand() {
    }
    
    public void enableControl() {
        try {
            this.enable();
            upperClaw.enableControl();
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }
    public void disableControl() {
        try {
            upperClaw.disableControl();
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }
    
    public void setX(double d) {
        try {
            upperClaw.setX(d);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }

    protected double returnPIDInput() {
        try {
            return upperClaw.getOutputCurrent();
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    protected void usePIDOutput(double d) {
        try {
            //Limit current a bit
            if (Math.abs(upperClaw.getOutputCurrent()) > 6) {
                upperClaw.setX(d*0.8);
            } else {
                upperClaw.setX(d);
            }
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }
    
}
