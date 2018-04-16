package org.usfirst.frc.team3473.robot.commands;

import org.usfirst.frc.team3473.robot.Robot;
import org.usfirst.frc.team3473.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *	Turns the robot to a specified angle. This class uses proportional
 *	control.
 */
public class TurnAngleP extends Command {
	private static final double kP = 0.01;
	private static final double TOLERANCE = 3.0;
	
	private double targetAngle;

	public TurnAngleP(double target) {
		requires(Robot.drivetrain);
		targetAngle = target;
		setTimeout(targetAngle * 1.5 / 90.0);
	}

	protected void initialize() {
		RobotMap.gyro.reset();
	}

	protected void execute() {
		double currentAngle = RobotMap.gyro.getAngle();
		double difference = targetAngle - currentAngle;
		Robot.drivetrain.arcadeDrive(0.0, difference * kP);
	}

	protected boolean isFinished() {
		double difference = targetAngle - RobotMap.gyro.getAngle();
		return Math.abs(difference) < TOLERANCE || isTimedOut();
	}

	protected void end() {
		Robot.drivetrain.tankDrive(0.0, 0.0);
	}

	protected void interrupted() {
		end();
	}
}
