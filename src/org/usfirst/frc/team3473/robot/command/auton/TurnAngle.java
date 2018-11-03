package org.usfirst.frc.team3473.robot.command.auton;

import org.usfirst.frc.team3473.robot.Robot;
import org.usfirst.frc.team3473.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *	Turns the robot at a given speed until the gyro
 *	reaches a given value.
 */
public class TurnAngle extends Command {
	private static final double DEFAULT_TURNING_SPEED = 0.75;
	private double turnSpeed;
	private double targetAngle;
	
	public TurnAngle(double angle) {
		this(Math.signum(angle) * DEFAULT_TURNING_SPEED, angle);
	}

	public TurnAngle(double turn, double angle) {
		requires(Robot.drivetrain);
		turnSpeed = turn;
		targetAngle = angle;
	}

	protected void initialize() {
		RobotMap.gyro.reset();
	}

	protected void execute() {
		Robot.drivetrain.arcadeDrive(0.0, turnSpeed);
	}

	protected boolean isFinished() {
		return Math.abs(RobotMap.gyro.getAngle()) >= Math.abs(targetAngle);
	}

	protected void end() {
		Robot.drivetrain.arcadeDrive(0.0, 0.0);
	}

	protected void interrupted() {
		end();
	}
}
