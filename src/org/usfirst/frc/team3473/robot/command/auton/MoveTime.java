package org.usfirst.frc.team3473.robot.command.auton;

import org.usfirst.frc.team3473.robot.Robot;
import org.usfirst.frc.team3473.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *	Moves the robot forward (or backwards) at a given speed until the
 *	encoder reaches a certain value.
 */
public class MoveTime extends Command {
	private static boolean leftEncoderWorking = true;
	private static boolean rightEncoderWorking = true;
	
	private static final double DEFAULT_SPEED = 0.5;
	private double speed;	

	public MoveTime(double time) {
		requires(Robot.drivetrain);
		this.speed = DEFAULT_SPEED;
		setTimeout(time);
	}

	public MoveTime(double speed, double time) {
		requires(Robot.drivetrain);
		this.speed = speed;
		setTimeout(time);
	}

	protected void initialize() {
		RobotMap.leftEncoder.reset();
		RobotMap.rightEncoder.reset();

	}

	protected void execute() {
		Robot.drivetrain.tankDrive(speed, speed);
	}

	protected boolean isFinished() {
			return isTimedOut();	
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
