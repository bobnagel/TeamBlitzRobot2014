/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.templates.RobotMap;

/**
 *
 * @author Owner
 */
public class ShootCommand extends CommandBase {
    
    long time = 0;
    boolean done = false;
    
    public ShootCommand() {
    }

    protected void initialize() {
    }

    protected void execute() {
        if (time == 0 && !done) {
            time = System.currentTimeMillis();
            RobotMap.solenoidRelay.set(Relay.Value.kForward);
        } else if (System.currentTimeMillis()-time >= 100) {
            RobotMap.solenoidRelay.set(Relay.Value.kOff);
            done = true;
        }
    }

    protected boolean isFinished() {
        return done;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
