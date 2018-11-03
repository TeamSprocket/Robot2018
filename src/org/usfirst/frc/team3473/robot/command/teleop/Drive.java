package org.usfirst.frc.team3473.robot.command.teleop;

import org.usfirst.frc.team3473.robot.OI;
import org.usfirst.frc.team3473.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *	The default command for driving the robot using joysticks.
 */
public class Drive extends Command {

	public Drive() {
		requires(Robot.drivetrain);
	}

	protected void initialize() {
	}

	protected void execute() {
		double speed = -OI.leftJoystick.getY() * .8;
		double turn = OI.rightJoystick.getX();

		if(Math.abs(speed) < 0.1) speed = 0.0;
		if(Math.abs(turn) < 0.1) turn = 0.0;

		Robot.drivetrain.arcadeDrive(speed, turn);
		
//		Robot.drivetrain.tankDrive(OI.leftJoystick.getY(), OI.rightJoystick.getY());
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Robot.drivetrain.arcadeDrive(0.0, 0.0);
	}

	protected void interrupted() {
		end();
	}
}
