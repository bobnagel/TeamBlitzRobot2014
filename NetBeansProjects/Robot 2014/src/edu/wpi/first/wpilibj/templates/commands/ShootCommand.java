/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.DigitalInput;

/**
 *
 * @author Owner
 */
public class ShootCommand extends CommandBase {
    
    long time = 0;
    boolean shooting = false;
    DigitalInput pressureSwitch;
    
    public ShootCommand() {
    }

    protected void initialize() {
        pressureSwitch = new DigitalInput(1);
    }

    protected void execute() {
        if (pressureSwitch.get() == false) {
            RobotMap.compressorRelay.set(Relay.Value.kForward);
        } else {
            RobotMap.compressorRelay.set(Relay.Value.kOff);
        }
        if (shooting) {
            if (time == 0) {
                time = System.currentTimeMillis();
                RobotMap.solenoidRelay.set(Relay.Value.kForward);
            } else if (System.currentTimeMillis() - time >= 100) {
                RobotMap.solenoidRelay.set(Relay.Value.kOff);
                shooting = false;
                time = 0;
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
