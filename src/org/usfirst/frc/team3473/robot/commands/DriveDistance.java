package org.usfirst.frc.team3473.robot.commands;

import org.usfirst.frc.team3473.robot.Robot;
import org.usfirst.frc.team3473.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *	A simple command that drives the robot until the encoder reaches a certain value.
 */
public class DriveDistance extends Command {
	private static final double DEFAULT_SPEED = 0.25;
	
	private double leftSpeed;
	private double rightSpeed;
	private double targetDistance;
	
	/**
	 * Creates a new DriveDistance command that drives the robot forward
	 * at a default speed until a certain target value for the encoder is reached.
	 * @param target The encoder value at which this command will stop
	 */
	public DriveDistance(double target) {
		this(DEFAULT_SPEED, DEFAULT_SPEED, target);
	}
	
	/**
	 * Creates a new DriveDistance command that drives the robot forward
	 * at a given speed until a certain target value for the encoder is reached.
	 * @param target The encoder value at which this command will stop
	 */
	public DriveDistance(double speed, double target) {
		this(speed, speed, target);
	}

	/**
	 * Creates a new DriveDistance command with specified left, right,
	 * and target values.
	 * @param left The speed of the left-side wheels
	 * @param right The speed of the right-side wheels
	 * @param target The encoder value at which this command will stop
	 */
    public DriveDistance(double left, double right, double target) {
    	requires(Robot.drivetrain);
    	leftSpeed = left;
    	rightSpeed = right;
    	targetDistance = target;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	RobotMap.encoder.reset();
    	Robot.drivetrain.setLeft(leftSpeed);
    	Robot.drivetrain.setRight(rightSpeed);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Returns true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return RobotMap.encoder.getDistance() >= targetDistance;
    }

    // Stop the robot.
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
