/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;

/**
 *
 * @author Owner
 */
public class RightDriveSubsystem extends PIDSubsystem {
    
    public CANJaguar rightFront = RobotMap.rightFront;
    public CANJaguar rightBack = RobotMap.rightBack;
    
    public RightDriveSubsystem() {
        super("Right Drive",0.00,0.0,0,0.030);
        this.enable();
        this.getPIDController().setOutputRange(-12, 12);
    }

    protected void initDefaultCommand() {
    }
    
    public void enableControl() {
        try {
            rightBack.enableControl();
            rightFront.enableControl();
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }
    public void disableControl() {
        try {
            rightBack.disableControl();
            rightFront.disableControl();
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }

    public double returnPIDInput() {
        try {
            return -rightFront.getSpeed();
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public void usePIDOutput(double d) {
        try {
            System.out.println("Right " + getSetpoint() + " " + returnPIDInput() + " " + d + " " + rightFront.getOutputCurrent() + " " + rightBack.getOutputCurrent());
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
        try {
            rightFront.setX(d);
            rightBack.setX(d);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }
    
}
