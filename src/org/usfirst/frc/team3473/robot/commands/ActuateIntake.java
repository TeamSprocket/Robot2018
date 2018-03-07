package org.usfirst.frc.team3473.robot.commands;

import org.usfirst.frc.team3473.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *	A single-step command that actuates the intake piston once.
 */
public class ActuateIntake extends Command {
	//private boolean actuate;

	/**
	 * Creates a new Actuate command.
	 */
	public ActuateIntake(/*boolean actuateOutwards*/) {
		//actuate = actuateOutwards;
		requires(Robot.intakeActuation);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		if(Robot.intakeActuation.getToggled()) {
			Robot.intakeActuation.actuateUp();
		}
		else {
			Robot.intakeActuation.actuateDown();
		}
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return true;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
