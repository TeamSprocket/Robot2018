package org.usfirst.frc.team3473.robot.commands;

import org.usfirst.frc.team3473.robot.Robot;
import org.usfirst.frc.team3473.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StayStraightEncoder extends Command {
	private static final double DEFAULT_SPEED = 0.5;
	private double targetDistance;
	private double rightSpeed;
	private double leftSpeed;
	private double kP = 0.03;
	
	public StayStraightEncoder(double distance) {
		this(Math.signum(distance) * DEFAULT_SPEED, distance);
	}

	public StayStraightEncoder(double speed, double distance) {
		requires(Robot.drivetrain);
		rightSpeed = speed;
		leftSpeed = speed;
		targetDistance = Math.abs(distance);
	}

    // Called just before this Command runs the first time
    protected void initialize() {
		RobotMap.leftEncoder.reset();
		RobotMap.rightEncoder.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    		double leftDistance = RobotMap.leftEncoder.getDistance();
    		double rightDistance = RobotMap.rightEncoder.getDistance();
    		double difference = leftDistance - rightDistance;
    		leftSpeed -= difference * kP;
    		rightSpeed += difference * kP;
    		Robot.drivetrain.tankDrive(leftSpeed, rightSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	double averageDistance = Math.abs(RobotMap.leftEncoder.getDistance()
				+ RobotMap.rightEncoder.getDistance()) / 2.0;
		return averageDistance >= targetDistance;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
