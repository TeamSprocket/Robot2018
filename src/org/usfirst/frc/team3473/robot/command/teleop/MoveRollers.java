package org.usfirst.frc.team3473.robot.command.teleop;

import org.usfirst.frc.team3473.robot.OI;
import org.usfirst.frc.team3473.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *	A command that moves the rollers on the Robot's Intake subsystem
 *	according to joystick values, allowing the robot to intake/outtake
 *	power cubes.
 */
public class MoveRollers extends Command {
	/**
	 * Creates a new MoveRollers object.
	 */
	public MoveRollers() {
		requires(Robot.intake);
	}

	protected void initialize() {
	}

	protected void execute() {
		double outtakeSpeed = -OI.gamepad.getRawAxis(1);
		Robot.intake.moveRollers(outtakeSpeed);
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Robot.intake.stop();
	}

	protected void interrupted() {
		end();
	}
}
