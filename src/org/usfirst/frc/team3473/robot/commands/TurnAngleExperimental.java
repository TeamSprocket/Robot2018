package org.usfirst.frc.team3473.robot.commands;

import org.usfirst.frc.team3473.robot.Robot;
import org.usfirst.frc.team3473.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *	Turns the robot to a specified angle. This class uses proportional
 *	control.
 */
public class TurnAngleExperimental extends PIDCommand {
//	private static double kP = SmartDashboard.getNumber("kP", 0.01);
//	private static double kI = SmartDashboard.getNumber("kI", 0.01);
	private static final double TOLERANCE = 3.0;
	private boolean outputPositive = true;
//	private double integral = 0;
//	private double targetAngle;

	private final double targetAngle;
	private double passes;
	
	public TurnAngleExperimental(double target) {
		super(1, 1, 1);
		requires(Robot.drivetrain);
		targetAngle = target;
		setTimeout(targetAngle * 1.5 / 90.0);
		passes = 0;
	}

	protected void initialize() {
		RobotMap.gyro.reset();
		
		getPIDController().setSetpoint(targetAngle);
		getPIDController().enable();
		
//		kP = SmartDashboard.getNumber("kP", 0.01);
//		kI = SmartDashboard.getNumber("kI", 0.01);
	}

	protected void execute() {
//		double currentAngle = RobotMap.gyro.getAngle();
//		double difference = targetAngle + currentAngle;
//		integral += difference * .02; //Integral is increased by error*time (which is .02 seconds using normal IterativeRobot)
//		Robot.drivetrain.arcadeDrive(0.0, difference * kP + integral * kI);
	}

	protected boolean isFinished() {
		return passes > 6 && Math.abs(RobotMap.gyro.getAngle() - targetAngle) < TOLERANCE;
		
		
//		double difference = targetAngle - RobotMap.gyro.getAngle();
//		System.out.println(RobotMap.gyro.getAngle());
//		return (Math.abs(RobotMap.gyro.getAngle()) <= Math.abs(targetAngle) + TOLERANCE) &&
//			(Math.abs(RobotMap.gyro.getAngle()) >= Math.abs(targetAngle) - TOLERANCE) ;
	}

	protected void end() {
		Robot.drivetrain.tankDrive(0.0, 0.0);
	}

	protected void interrupted() {
		end();
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
			System.out.print(passes);
		}
		else if(output < 0 && outputPositive) {
			passes++;
			outputPositive = false;
			System.out.print(passes);
		}
		Robot.drivetrain.arcadeDrive(0, -output);
	}
}
