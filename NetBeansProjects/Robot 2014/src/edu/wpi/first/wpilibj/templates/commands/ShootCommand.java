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

/**
 *
 * @author Owner
 */
public class ShootCommand extends CommandBase {
    
    public static Joystick xbox;
    long time = -1;
    boolean shooting = false;
    long buttonTime = -1;
    DigitalInput pressureSwitch;
    
    public ShootCommand() {
    }

    protected void initialize() {
        pressureSwitch = new DigitalInput(1);
    }

    protected void execute() {
        if (pressureSwitch.get() == false && RobotMap.compressorEnabled) {
            RobotMap.compressorRelay.set(Relay.Value.kForward);
        } else {
            RobotMap.compressorRelay.set(Relay.Value.kOff);
        }
        if (xbox.getRawButton(RobotMap.shootButton))  {
            if (buttonTime == -1) buttonTime = System.currentTimeMillis();
            if (System.currentTimeMillis()-buttonTime > 500) shooting = true;
        } else {
            buttonTime = -1;
        }
        if (shooting) {
            if (time == -1) {
                time = System.currentTimeMillis();
                RobotMap.solenoidRelay.set(Relay.Value.kForward);
            } else if (System.currentTimeMillis() - time >= 500) {
                RobotMap.solenoidRelay.set(Relay.Value.kOff);
                shooting = false;
                time = -1;
            }
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
