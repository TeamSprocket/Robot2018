package org.usfirst.frc.team3473.robot.commands;

import org.usfirst.frc.team3473.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *	A single-step command that toggles the gear on the drivetrain.
 */
public class GearToggle extends Command {

	public GearToggle() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.gearPneumatics);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		if(Robot.gearPneumatics.getToggled()) {
			Robot.gearPneumatics.movePneumaticsBackward();
		}
		else {
			Robot.gearPneumatics.movePneumaticsForward();
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
