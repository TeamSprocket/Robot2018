package org.usfirst.frc.team3473.robot.commands;

import org.usfirst.frc.team3473.robot.Robot;
import org.usfirst.frc.team3473.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *	Moves the robot forward (or backwards) at a given speed until the
 *	encoder reaches a certain value.
 */
public class MoveDistance extends Command {
	private static final double DEFAULT_SPEED = 0.5;
	private double speed;
	private double targetDistance;

	public MoveDistance(double distance) {
		this(DEFAULT_SPEED, distance);
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
		Robot.drivetrain.arcadeDrive(speed, 0.0);
	}

	protected boolean isFinished() {
//		double averageDistance = Math.abs(RobotMap.leftEncoder.getDistance()
//				+ RobotMap.rightEncoder.getDistance()) / 2.0;
//		return averageDistance >= targetDistance;
		return RobotMap.leftEncoder.getDistance() >= targetDistance;
	}

	protected void end() {
		Robot.drivetrain.arcadeDrive(0.0, 0.0);
	}

	protected void interrupted() {
		end();
	}
}
