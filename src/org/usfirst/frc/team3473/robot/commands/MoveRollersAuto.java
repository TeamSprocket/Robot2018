package org.usfirst.frc.team3473.robot.commands;

import org.usfirst.frc.team3473.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *	A command that moves the rollers on the Robot's Intake subsystem,
 *	allowing the robot to intake/outtake power cubes.
 */
public class MoveRollersAuto extends Command {
	private int direction;
	
	public MoveRollersAuto(){
		requires(Robot.intake);
	}

	/**
	 * Moves the rollers in the specified direction.
	 * @param direction the direction to move the rollers (1=intake, -1=outtake, 0=stop)
	 */
	public MoveRollersAuto(int direction) {
		requires(Robot.intake);
		this.direction = -direction;
	}
	
	/**
	 * Moves the rollers in the specified direction for a given
	 * amount of time.
	 * @param direction the direction to move the rollers (1=intake, -1=outtake, 0=stop)
	 * @param timeout the time, in seconds, before this command stops
	 */
	public MoveRollersAuto(int direction, double timeout) {
		this(direction);
		setTimeout(timeout);
	}

	protected void initialize() {
	}

	protected void execute() {
		if(direction == 1)
			Robot.intake.intake();
		else if(direction == -1)
			Robot.intake.outtake();
		else
			Robot.intake.stop();
	}

	protected boolean isFinished() {
		return isTimedOut();
	}

	protected void end() {
		Robot.intake.stop();
	}

	protected void interrupted() {
		end();
	}
}