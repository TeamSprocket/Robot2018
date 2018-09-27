package org.usfirst.frc.team3473.robot.commands;

import org.usfirst.frc.team3473.robot.Robot;
import org.usfirst.frc.team3473.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *	Raises or lowers the intake elevator at a constant speed until
 *	the encoder reaches a certain value.
 *	Useful for autonomous period.
 */
public class ElevateIntakeToHeight extends Command {
	private static final double DEFAULT_SPEED = 1.0;
	private static final double TIME = 4.0;
	private long startingTime;

	private double elevatorSpeed;
	private double position;

	/**
	 * Creates a new Elevate command, which moves until the encoder reaches a given target value.
	 * @param target The encoder value at which this command should stop.
	 */
	public ElevateIntakeToHeight(double target) {
		this(DEFAULT_SPEED, target);
		setTimeout(TIME);
	}

	/**
	 * Creates a new Elevate command that moves the elevator at a given speed.
	 * @param elevatorSpeed The speed at which to move the elevator (positive values
	 * indicate the elevator is moving up, negative values indicate the elevator is
	 * moving down)
	 * @param target The encoder value at which this command should stop.
	 */
	public ElevateIntakeToHeight(double elevatorSpeed, double target) {
		requires(Robot.intakeElevator);
		this.elevatorSpeed = elevatorSpeed;
		position = target;
		setTimeout(TIME);
	}

	@Override
	protected void initialize() {
		Robot.intakeElevator.initializeCounter();
		startingTime = System.currentTimeMillis();
		if(RobotMap.intakeElevatorEncoder.getDistance() < position) {
			elevatorSpeed = Math.abs(elevatorSpeed);
		}
		else if(RobotMap.intakeElevatorEncoder.getDistance() > position) {
			elevatorSpeed = -Math.abs(elevatorSpeed);
		}
	}

	@Override
	protected void execute() {
		Robot.intakeElevator.moveElevator(elevatorSpeed);
	}

	// This method assumes that the encoder is positioned such that raising the
	// elevator will cause the encoder value to increase, while lowering the
	// elevator will cause the encoder value to decrease.
	@Override
	protected boolean isFinished() {
		if(elevatorSpeed > 0.0 && (Robot.intakeElevator.isSwitchSet() || RobotMap.intakeElevatorEncoder.getDistance() > position)) {
			return true;
		}
		else if(elevatorSpeed < 0.0 && RobotMap.intakeElevatorEncoder.getDistance() < position) {
			return true;
		}
		return isTimedOut();
	}

	@Override
	protected void end() {
		Robot.intakeElevator.moveElevator(0.0);
		System.out.println(System.currentTimeMillis() - startingTime);
	}

	@Override
	protected void interrupted() {
		end();
	}
}
