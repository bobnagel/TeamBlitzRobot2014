package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static final int leftMotor = 1;
    // public static final int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static final int rangefinderPort = 1;
    // public static final int rangefinderModule = 1;
    
    public static final int leftForwardMotor = 5;
    public static final int leftBackMotor = 3;
    public static final int rightForwardMotor = 2;
    public static final int rightBackMotor = 12;
    public static final int upperClawMotor = 7;
    public static final int lowerClawMotor = 8;
    
    public static CANJaguar leftFront;
    public static CANJaguar leftBack;
    public static CANJaguar rightFront;
    public static CANJaguar rightBack;
    
    public static CANJaguar upperClaw;
    public static CANJaguar lowerClaw;
    
    public static Relay compressorRelay;
    public static Relay solenoidRelay;
    public static Solenoid shooterValveSolenoid;
    
    public static double encoderOffset;
    
    

    public static int shootButton = 6;
    public static int downButton = 4;
    public static int upButton = 3;
    public static int openButton = 2;
    public static int closeButton = 1;
    
    public static double upPosition = -0.23;
    
    public static int valveSwitch = 1;
    
    
    public static double lowerNormalP = 120;
    public static double lowerNormalI = 0.15;
    public static double lowerNormalD = 0;
    public static double lowerCloseP = 120;
    public static double lowerCloseI = 0.1;
    public static double lowerCloseD = 0;
    public static double shootingPosMin = -0.02;
    public static double shootingPosMax =  0.02;
    
    public static boolean compressorEnabled = true;
}
