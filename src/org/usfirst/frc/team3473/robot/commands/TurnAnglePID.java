package org.usfirst.frc.team3473.robot.commands;

import org.usfirst.frc.team3473.robot.Robot;
import org.usfirst.frc.team3473.robot.RobotMap;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Turns the robot to a specified angle using PID control through WPILib's
 * PIDController and PIDCommand classes
 */
public class TurnAnglePID extends PIDCommand {
	private static final double ANGLE_TOLERANCE = 1.0;

	// TODO: Tweak kP, kI, and kD values instead of counting passes
	private static double kP = 1.0, kI = 1.0, kD = 1.0;
	private static final int MAXIMUM_PASSES = 5;
	private static final double PASS_TIME = 150;
	
	private boolean outputPositive = true;
	private double passes;
	private double lastPassTimestamp = 0;
	private boolean finished = false;

	private double targetAngle;
	
	public TurnAnglePID(double angle) {
		super(SmartDashboard.getNumber("kP", 0.5), SmartDashboard.getNumber("kI", 1.25), SmartDashboard.getNumber("kD", 2.0));

		requires(Robot.drivetrain);

		passes = 0;
		targetAngle = angle;

		// TODO: Make it accurate enough so that we don't actually need timeout
		setTimeout(Math.abs(angle) * 1.5 / 90.0);
	}

	@Override
	protected void initialize() {
		getPIDController().setSetpoint(RobotMap.gyro.getAngle() + targetAngle);
		getPIDController().enable();
	}

	@Override
	protected double returnPIDInput() {
		return RobotMap.gyro.getAngle();
	}

	@Override
	protected void usePIDOutput(double output) {
		if(output > 0 && !outputPositive) {
			if(System.currentTimeMillis() - lastPassTimestamp < PASS_TIME)
				finished = true;
			
			passes++;
			outputPositive = true;
			lastPassTimestamp = System.currentTimeMillis();
		} else if(output < 0 && outputPositive) {
			if(System.currentTimeMillis() - lastPassTimestamp < PASS_TIME)
				finished = true;
			
			passes++;
			outputPositive = false;
			lastPassTimestamp = System.currentTimeMillis();
		}
		Robot.drivetrain.arcadeDrive(0, output);
	}

	@Override
	protected boolean isFinished() {
		// TODO: Maybe use RobotMap.gyro.getRate() to check instead?
//		return passes > MINIMUM_PASSES
//				&& Math.abs(getPIDController().getSetpoint()
//						- RobotMap.gyro.getAngle()) < ANGLE_TOLERANCE;
		return finished || Math.abs(RobotMap.gyro.getRate()) < 1 &&
				Math.abs(getPIDController().getSetpoint() -
				RobotMap.gyro.getAngle()) < ANGLE_TOLERANCE ||
				passes >= MAXIMUM_PASSES;
	}

	@Override
	protected void end() {
		getPIDController().disable();
	}

	@Override
	protected void interrupted() {
		end();
	}
}
