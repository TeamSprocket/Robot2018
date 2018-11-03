package org.usfirst.frc.team3473.robot.command.auton;

import org.usfirst.frc.team3473.robot.Robot;
import org.usfirst.frc.team3473.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *	Turns the robot to a specified angle. This class uses proportional
 *	control.
 */
public class TurnAngleP extends Command {
	private static double kP = SmartDashboard.getNumber("kP", 0.01);
	private static double kI = SmartDashboard.getNumber("kI", 0.01);
	private static final double TOLERANCE = 3.0;
	private double integral = 0;
	private double targetAngle;

	public TurnAngleP(double target) {
		requires(Robot.drivetrain);
		targetAngle = target;
		setTimeout(targetAngle * 1.5 / 90.0);
	}

	protected void initialize() {
		RobotMap.gyro.reset();
		kP = SmartDashboard.getNumber("kP", 0.01);
		kI = SmartDashboard.getNumber("kI", 0.01);
	}

	protected void execute() {
		double currentAngle = RobotMap.gyro.getAngle();
		double difference = targetAngle + currentAngle;
		integral += difference * .02; //Integral is increased by error*time (which is .02 seconds using normal IterativeRobot)
		Robot.drivetrain.arcadeDrive(0.0, difference * kP + integral * kI);
	}

	protected boolean isFinished() {
		double difference = targetAngle - RobotMap.gyro.getAngle();
		System.out.println(RobotMap.gyro.getAngle());
		return (Math.abs(RobotMap.gyro.getAngle()) <= Math.abs(targetAngle) + TOLERANCE) &&
			(Math.abs(RobotMap.gyro.getAngle()) >= Math.abs(targetAngle) - TOLERANCE) ;
	}

	protected void end() {
		Robot.drivetrain.tankDrive(0.0, 0.0);
	}

	protected void interrupted() {
		end();
	}
}
