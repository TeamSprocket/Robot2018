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
	private static final int MINIMUM_PASSES = 5;

	private boolean outputPositive = true;
	private double passes;

	public TurnAnglePID(double angle) {
		super(kP, kI, kD);

		requires(Robot.drivetrain);

		passes = 0;
		getPIDController().setSetpoint(RobotMap.gyro.getAngle() + angle);

		// TODO: Make it accurate enough so that we don't actually need timeout
		setTimeout(angle * 1.5 / 90.0);
		
		System.out.println(getPIDController().getSetpoint());
		System.out.println(RobotMap.gyro.getAngle());
	}

	@Override
	protected void initialize() {
		getPIDController().enable();
		
		kP = SmartDashboard.getNumber("kP", 1.0);
		kI = SmartDashboard.getNumber("kI", 1.0);
		kD = SmartDashboard.getNumber("kD", 1.0);
	}

	@Override
	protected double returnPIDInput() {
		return RobotMap.gyro.getAngle();
	}

	@Override
	protected void usePIDOutput(double output) {
		if(output > 0 && !outputPositive) {
			passes++;
			outputPositive = true;
		} else if(output < 0 && outputPositive) {
			passes++;
			outputPositive = false;
		}
		Robot.drivetrain.arcadeDrive(0, -output);
	}

	@Override
	protected boolean isFinished() {
		// TODO: Maybe use RobotMap.gyro.getRate() to check instead?
		return passes > MINIMUM_PASSES
				&& Math.abs(getPIDController().getSetpoint()
						- RobotMap.gyro.getAngle()) < ANGLE_TOLERANCE;
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
