package org.usfirst.frc.team3473.robot.commands;

import org.usfirst.frc.team3473.robot.Robot;
import org.usfirst.frc.team3473.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *	Moves the robot forward (or backwards) at a given speed until the
 *	encoder reaches a certain value.
 */
public class MoveDistance extends Command {
	private static boolean leftEncoderWorking = true;
	private static boolean rightEncoderWorking = true;
	
	private static final double DEFAULT_SPEED = 0.5;
	private double speed;
	private double targetDistance;

	public MoveDistance(double distance) {
		this(Math.signum(distance) * DEFAULT_SPEED, distance);
	}

	public MoveDistance(double speed, double distance) {
		requires(Robot.drivetrain);
		this.speed = speed;
		targetDistance = Math.abs(distance);
	}

	protected void initialize() {
		RobotMap.leftEncoder.reset();
		RobotMap.rightEncoder.reset();
	}

	protected void execute() {
		Robot.drivetrain.tankDrive(speed, speed);
	}

	protected boolean isFinished() {
		double leftDistance = RobotMap.leftEncoder.getDistance();
		double rightDistance = RobotMap.rightEncoder.getDistance();
		double robotDistance;
		if(leftEncoderWorking && rightEncoderWorking) {
			robotDistance = Math.abs(leftDistance + rightDistance) / 2.0;
			if(leftDistance >= 500 && rightDistance <= 5.0) {
				rightEncoderWorking = false;
			}
			else if(rightDistance >= 500 && leftDistance <= 5.0) {
				leftEncoderWorking = false;
			}
		}
		else if(!leftEncoderWorking) {
			robotDistance = Math.abs(rightDistance);
		}
		else {
			robotDistance = Math.abs(leftDistance);
		}
		return robotDistance >= Math.abs(targetDistance);
	}
	
	public static boolean isLeftEncoderWorking() {
		return leftEncoderWorking;
	}
	
	public static boolean isRightEncoderWorking() {
		return rightEncoderWorking;
	}

	protected static void setLeftEncoderWorking(boolean newValue) {
		leftEncoderWorking = newValue;
	}
	
	protected static void setRightEncoderWorking(boolean newValue) {
		rightEncoderWorking = newValue;
	}
	
	
	protected void end() {
		Robot.drivetrain.arcadeDrive(0.0, 0.0);
	}

	protected void interrupted() {
		end();
	}
}
