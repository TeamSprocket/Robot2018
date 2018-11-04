package org.usfirst.frc.team3473.robot.command.instant;

import org.usfirst.frc.team3473.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * An instantaneous command that toggles the drivetrain gear
 */
@Deprecated
public class ToggleGear extends Command {
	public ToggleGear() {
		requires(Robot.gearPneumatics);
	}

	@Override
	protected void initialize() {
		Robot.gearPneumatics.toggle();
	}

	@Override
	protected boolean isFinished() {
		return true;
	}
}
