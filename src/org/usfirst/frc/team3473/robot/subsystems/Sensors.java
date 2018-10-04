package org.usfirst.frc.team3473.robot.subsystems;

import org.usfirst.frc.team3473.robot.Robot;
import org.usfirst.frc.team3473.robot.RobotMap;
import org.usfirst.frc.team3473.robot.commands.TurnTime;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

/*
 * A subsystem that manages all of the sensors. Other subsystems and commands
 * should reference sensors from this subsystems rather than from RobotMap.
 */
public class Sensors extends Subsystem implements Testable {
	private final Encoder leftEncoder = RobotMap.leftEncoder;
	private final Encoder rightEncoder = RobotMap.leftEncoder;
	private final Encoder intakeElevatorEncoder = RobotMap.intakeElevatorEncoder;
	private final ADXRS450_Gyro gyro = RobotMap.gyro;
	private final DigitalInput limitSwitch = RobotMap.limitSwitch;
	
    @Override
	public void initDefaultCommand() {
    	
    }
    
    public void resetAll() {
		gyro.calibrate();
		leftEncoder.reset();
		rightEncoder.reset();
		intakeElevatorEncoder.reset();
    }
    
    @Override
	public void test() {
    	resetAll();
    	Robot.drivetrain.tankDrive(0.5, -0.5);
    	try {
			Thread.sleep(1000);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
    	System.out.println("Left Encoder");
    	System.out.println("Direction: " + leftEncoder.getDirection());
    	System.out.println("Distance traveled:" + leftEncoder.getDistance());
    	System.out.println("Expected rate: 0.5\tActual: " + leftEncoder.getRate());
    	System.out.println("Right Encoder");
    	System.out.println("Direction: " + rightEncoder.getDirection());
    	System.out.println("Distance traveled:" + rightEncoder.getDistance());
    	System.out.println("Expected rate: 0.5\tActual: " + rightEncoder.getRate());
    	System.out.println("Gyro");
    	System.out.println("Angle change: " + gyro.getAngle());
    	System.out.println("Expected rate: ?\tActual: " + gyro.getRate());
    	Robot.drivetrain.tankDrive(0, 0);
    }
}

