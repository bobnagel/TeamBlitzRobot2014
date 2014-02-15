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
 * @author Owner
 */
public class LowerClawSubsystem extends PIDSubsystem {
    
    public CANJaguar lowerClaw = RobotMap.lowerClaw;
    
    public LowerClawSubsystem() {
        super("Lower Claw", RobotMap.lowerNormalP, RobotMap.lowerNormalI, RobotMap.lowerNormalD, 0);
        this.getPIDController().setOutputRange(-8, 8);
    }
    
    protected void initDefaultCommand() {
    }
    
    public void enableControl() {
        try {
            this.enable();
            lowerClaw.enableControl();
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }
    public void disableControl() {
        try {
            lowerClaw.disableControl();
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }
    
    public void setX(double d) {
        try {
            lowerClaw.setX(d);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }

    public double returnPIDInput() {
        try {
            return (lowerClaw.getPosition()-RobotMap.encoderOffset);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    protected void usePIDOutput(double d) {
        try {
//            System.out.println((lowerClaw.getPosition()-RobotMap.encoderOffset)+ " " + d);
            lowerClaw.setX(-d);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }
    
}
