/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.CANJaguar.ControlMode;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.*;
import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj.templates.RobotMap;

/**
 *
 * @author Owner
 */
public class LeftDriveSubsystem extends PIDSubsystem {
    
    CANJaguar leftFront = RobotMap.leftFront;
    CANJaguar leftBack = RobotMap.leftBack;
    
    public LeftDriveSubsystem() {
        super("Left Drive",0.00,0.0,0,0.030);
        this.enable();
        this.getPIDController().setOutputRange(-12, 12);
    }
    protected void initDefaultCommand() {
    }
    
    public void enableControl() {
        try {
            leftBack.enableControl();
            leftFront.enableControl();
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }
    public void disableControl() {
        try {
            leftBack.disableControl();
            leftFront.disableControl();
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }

    public double returnPIDInput() {
        try {
            return leftFront.getSpeed();
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public void usePIDOutput(double d) {
        try {
            System.out.println("Left " + getSetpoint() + " " + returnPIDInput() + " " + d + " " + leftFront.getOutputCurrent() + " " + leftBack.getOutputCurrent());
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
        try {
            leftBack.setX(-d);
            leftFront.setX(-d);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }
    
}
