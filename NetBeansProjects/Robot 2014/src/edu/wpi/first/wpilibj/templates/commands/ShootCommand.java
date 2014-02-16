/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.can.CANTimeoutException;

/**
 *
 * @author Owner
 */
public class ShootCommand extends CommandBase {

    public static Joystick xbox;
    long time = -1;
    boolean shooting = false;
    long buttonTime = -1;
    DigitalInput pressureSwitch = null;

    public ShootCommand() {
    }

    protected void initialize() {
        if (pressureSwitch == null)
            pressureSwitch = new DigitalInput(1);
    }

    protected void execute() {
        if (pressureSwitch.get() == false && RobotMap.compressorEnabled) {
            RobotMap.compressorRelay.set(Relay.Value.kForward);
        } else {
            RobotMap.compressorRelay.set(Relay.Value.kOff);
        }
        if (xbox.getRawButton(RobotMap.shootButton) || RobotMap.autoShoot) {
            if (safeToShoot()) {
                if (buttonTime == -1) {
                    buttonTime = System.currentTimeMillis();
                }
                shooting = true;
//            if (System.currentTimeMillis()-buttonTime > 500) shooting = true;
            } else {
                System.out.println("not safe to shoot");
                shooting = false;
                time = -1;
            }
        } else {
            buttonTime = -1;
        }
        if (shooting) {
            if (time == -1) {
                time = System.currentTimeMillis();
//                RobotMap.solenoidRelay.set(Relay.Value.kForward);
                RobotMap.shooterValveSolenoid.set(true);
                System.out.println("turned shooter solenoid on");
            } else if (System.currentTimeMillis() - time >= 500) {
//                RobotMap.solenoidRelay.set(Relay.Value.kOff);
                RobotMap.shooterValveSolenoid.set(false);
                System.out.println("turned shooter solenoid off");
                shooting = false;
                time = -1;
            }
        }
    }

    public boolean safeToShoot() {
        double lowerClawPos = RobotMap.shootingPosMin - 1;
        try {
            lowerClawPos = RobotMap.lowerClaw.getPosition() - RobotMap.encoderOffset;
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
        System.out.println("safeToShoot lowerClawPos is "+ lowerClawPos);
        return (RobotMap.shootingPosMin < lowerClawPos) && (lowerClawPos < RobotMap.shootingPosMax);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }

}
