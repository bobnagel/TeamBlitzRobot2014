/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.CANJaguar.ControlMode;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.templates.commands.ClawCommand;
import edu.wpi.first.wpilibj.templates.commands.CommandBase;
import edu.wpi.first.wpilibj.templates.commands.DriveCommand;
import edu.wpi.first.wpilibj.templates.commands.ShootCommand;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotTemplate extends IterativeRobot {

    DriveCommand driveCommand;
    ClawCommand clawCommand;
    ShootCommand shootCommand;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        try {
            RobotMap.leftFront = new CANJaguar(RobotMap.leftForwardMotor, ControlMode.kVoltage);
            RobotMap.leftBack = new CANJaguar(RobotMap.leftBackMotor, ControlMode.kVoltage);
            RobotMap.rightFront = new CANJaguar(RobotMap.rightForwardMotor, ControlMode.kVoltage);
            RobotMap.rightBack = new CANJaguar(RobotMap.rightBackMotor, ControlMode.kVoltage);
            
            RobotMap.upperClaw = new CANJaguar(RobotMap.upperClawMotor, ControlMode.kVoltage);
            RobotMap.upperClaw.configNeutralMode(CANJaguar.NeutralMode.kBrake);
            RobotMap.upperClaw.setX(0);
            RobotMap.lowerClaw = new CANJaguar(RobotMap.lowerClawMotor, ControlMode.kVoltage);
            RobotMap.lowerClaw.configNeutralMode(CANJaguar.NeutralMode.kBrake);
            RobotMap.lowerClaw.setPositionReference(CANJaguar.PositionReference.kQuadEncoder);
            RobotMap.lowerClaw.configEncoderCodesPerRev(360);
            RobotMap.lowerClaw.setX(0);
            RobotMap.lowerClaw.enableControl();
            
            RobotMap.leftFront.setSpeedReference(CANJaguar.SpeedReference.kQuadEncoder);
            RobotMap.rightFront.setSpeedReference(CANJaguar.SpeedReference.kQuadEncoder);
            
            RobotMap.leftFront.configEncoderCodesPerRev(360);
            RobotMap.rightFront.configEncoderCodesPerRev(250);
            
            RobotMap.compressorRelay = new Relay(1);
            RobotMap.compressorRelay.setDirection(Relay.Direction.kForward);
//            RobotMap.solenoidRelay = new Relay(2);
//            RobotMap.solenoidRelay.setDirection(Relay.Direction.kForward);
            RobotMap.shooterValveSolenoid = new Solenoid(1);
            RobotMap.shooterValveSolenoid.set(false);
                        
            // Initialize all subsystems
            CommandBase.init();
            driveCommand = new DriveCommand();
            clawCommand = new ClawCommand();
            DriveCommand.xbox = new Joystick(1);
            ClawCommand.xbox = DriveCommand.xbox;
            shootCommand = new ShootCommand();
            ShootCommand.xbox = DriveCommand.xbox;
            
           
            
            clawCommand.disableControl();
            driveCommand.disableControl();
            
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }

    public void autonomousInit() {
        // schedule the autonomous command (example)
        //autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        System.out.println("TELEOP INIT");
        driveCommand.enableControl();
        driveCommand.start();
        clawCommand.enableControl();
        clawCommand.start();
        shootCommand.start();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
