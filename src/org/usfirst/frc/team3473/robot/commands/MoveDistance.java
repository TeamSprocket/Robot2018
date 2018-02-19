package org.usfirst.frc.team3473.robot.commands;

import org.usfirst.frc.team3473.robot.Robot;
import org.usfirst.frc.team3473.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *	Moves the robot forward (or backwards) at a given speed until the
 *	encoder reaches a certain value.
 */
public class MoveDistance extends Command {
	private static final double DEFAULT_SPEED = 0.3;
	private double speed;
	private int targetDistance;

	public MoveDistance(int distance) {
		this(DEFAULT_SPEED, distance);
	}

	public MoveDistance(double speed, int distance) {
		requires(Robot.drivetrain);
		this.speed = speed;
		targetDistance = distance;
	}

	protected void initialize() {
		RobotMap.leftEncoder.reset();
		RobotMap.rightEncoder.reset();

		Robot.drivetrain.arcadeDrive(speed, 0.0);
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		int averageDistance = (RobotMap.leftEncoder.get()
				+ RobotMap.rightEncoder.get()) / 2;
		return averageDistance >= targetDistance;
	}

	protected void end() {
		Robot.drivetrain.arcadeDrive(0.0, 0.0);
	}

	protected void interrupted() {
		end();
	}
}
