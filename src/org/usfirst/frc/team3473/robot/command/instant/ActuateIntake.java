package org.usfirst.frc.team3473.robot.command.instant;

import org.usfirst.frc.team3473.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * An instantaneous command that toggles the actuation piston
 */
public class ActuateIntake extends Command {
	public ActuateIntake() {
		requires(Robot.actuator);
	}

	@Override
	protected void initialize() {
		Robot.actuator.toggle();
	}

	@Override
	protected boolean isFinished() {
		return true;
	}
}