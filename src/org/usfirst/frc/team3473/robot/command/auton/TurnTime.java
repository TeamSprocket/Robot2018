package org.usfirst.frc.team3473.robot.command.auton;

import org.usfirst.frc.team3473.robot.Robot;
import org.usfirst.frc.team3473.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *	Turns the robot at a given speed until the gyro
 *	reaches a given value.
 */
public class TurnTime extends Command {
	private static final double DEFAULT_TURNING_SPEED = 0.75;
	private double turnSpeed;
	
	public TurnTime(double time) {
		requires(Robot.drivetrain);
		this.turnSpeed = DEFAULT_TURNING_SPEED;
		setTimeout(time);
	}

	public TurnTime(double turn, double time) {
		requires(Robot.drivetrain);
		this.turnSpeed = turn;
		setTimeout(time);
	}

	protected void initialize() {
		RobotMap.gyro.reset();
	}

	protected void execute() {
		Robot.drivetrain.arcadeDrive(0.0, turnSpeed);
	}

	protected boolean isFinished() {
		return isTimedOut();
	}

	protected void end() {
		Robot.drivetrain.arcadeDrive(0.0, 0.0);
	}

	protected void interrupted() {
		end();
	}
}
