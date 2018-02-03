package org.usfirst.frc.team3473.robot.commands;

import org.usfirst.frc.team3473.robot.Robot;
import org.usfirst.frc.team3473.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *	A simple command that drives the robot until the gyro reaches a certain value.
 */
public class TurnAngle extends Command {
	private static final double DEFAULT_SPEED = 0.375;
	
	private double leftSpeed;
	private double rightSpeed;
	private double gyroAngle;
	
	/**
	 * Creates a new TurnAngle command that turns the robot on its center at
	 * a default speed until the gyro reaches a certain target value.
	 * @param target The gyro value at which the command will stop
	 */
	public TurnAngle(double target) {
		this(DEFAULT_SPEED, -DEFAULT_SPEED, target);
	}
	
	/**
	 * Creates a new TurnAngle command that turns the robot on its center at
	 * a given speed until the gyro reaches a certain target value.
	 * @param target The gyro value at which the command will stop
	 */
	public TurnAngle(double speed, double target) {
		this(speed, -speed, target);
	}

	/**
	 * Creates a new TurnAngle command that drives the robot at the given speeds
	 * until the gyro reaches a certain target value.
	 * @param left The speed of the left-side wheels
	 * @param right The speed of the right-side wheels
	 * @param target The gyro value at which the command will stop
	 */
    public TurnAngle(double left, double right, double target) {
        requires(Robot.drivetrain);
        leftSpeed = left;
        rightSpeed = right;
        gyroAngle = target;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	RobotMap.gyro.reset();
    	Robot.drivetrain.setLeft(leftSpeed);
    	Robot.drivetrain.setRight(rightSpeed);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(RobotMap.gyro.getAngle()) >= Math.abs(gyroAngle);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.setLeft(0.0);
    	Robot.drivetrain.setRight(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drivetrain.setLeft(0.0);
    	Robot.drivetrain.setRight(0.0);
    }
}
