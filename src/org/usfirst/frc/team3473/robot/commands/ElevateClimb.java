package org.usfirst.frc.team3473.robot.commands;

import org.usfirst.frc.team3473.robot.OI;
import org.usfirst.frc.team3473.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Raises or lowers the climb elevator according to joystick values.
 */
public class ElevateClimb extends Command {

	public ElevateClimb() {
		requires(Robot.climbElevator);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double speed = OI.gamepad.getY(OI.climbHand);
		if(Math.abs(speed) < 0.1)
			speed = 0.0;
		Robot.climbElevator.moveElevator(speed);
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
