package org.usfirst.frc.team3473.robot.commands;

import org.usfirst.frc.team3473.robot.Robot;
import org.usfirst.frc.team3473.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StayStraightGyro extends Command {
	private static final double DEFAULT_SPEED = 0.5;
	private double targetDistance;
	private double rightSpeed;
	private double leftSpeed;
	private double kP = 0.03;

	public StayStraightGyro(double distance) {
		this(Math.signum(distance) * DEFAULT_SPEED, distance);
	}

	public StayStraightGyro(double speed, double distance) {
		requires(Robot.drivetrain);
		rightSpeed = speed;
		leftSpeed = speed;
		targetDistance = Math.abs(distance);
//		setTimeout(distance);
	}
	
	@Override
	public void start() {

		RobotMap.gyro.reset();
		RobotMap.leftEncoder.reset();
		RobotMap.rightEncoder.reset();
		
		super.start();
	}
	
	protected void initialize() {
		RobotMap.gyro.reset();
		RobotMap.leftEncoder.reset();
		RobotMap.rightEncoder.reset();
	}

	protected void execute() {	
		double currentAngle = RobotMap.gyro.getAngle();
//		if(currentAngle > 180) {
//			currentAngle = 360 - currentAngle;
//		}
		Robot.drivetrain.tankDrive(leftSpeed - kP * currentAngle, rightSpeed + kP * currentAngle);
	}

	protected boolean isFinished() {
		double leftDistance = RobotMap.leftEncoder.getDistance();
		double rightDistance = RobotMap.rightEncoder.getDistance();
		double robotDistance;
		if(MoveDistance.isLeftEncoderWorking() && MoveDistance.isRightEncoderWorking()) {
			robotDistance = Math.abs(leftDistance + rightDistance) / 2.0;
			if(leftDistance >= 500 && rightDistance <= 5.0) {
				MoveDistance.setRightEncoderWorking(false);
			}
			else if(rightDistance >= 500 && leftDistance <= 5.0) {
				MoveDistance.setLeftEncoderWorking(false);
			}
		}
		else if(!MoveDistance.isLeftEncoderWorking()) {
			robotDistance = Math.abs(rightDistance);
		}
		else {
			robotDistance = Math.abs(leftDistance);
		}
		System.out.println(leftDistance + "\t" + rightDistance + "\t" + robotDistance);
		return robotDistance >= Math.abs(targetDistance);
//		return isTimedOut();
	}

	protected void end() {
		Robot.drivetrain.tankDrive(0.0, 0.0);
	}

	protected void interrupted() {
		end();
	}
}
