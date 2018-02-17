package org.usfirst.frc.team3473.robot.commands;

import org.usfirst.frc.team3473.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *	A command that moves the rollers on the Robot's Intake subsystem,
 *	allowing the robot to intake/outtake power cubes.
 */
public class MoveRollers extends Command {
	private int direction;

	/**
	 * Moves the rollers in the specified direction.
	 * @param direction the direction to move the rollers (1=intake, -1=outtake, 0=stop)
	 */
	public MoveRollers(int direction) {
		requires(Robot.intake);
		this.direction = direction;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		if(direction == 1)
			Robot.intake.intake();
		else if(direction == -1)
			Robot.intake.outtake();
		else
			Robot.intake.stop();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
