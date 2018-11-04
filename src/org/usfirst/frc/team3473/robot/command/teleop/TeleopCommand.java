package org.usfirst.frc.team3473.robot.command.teleop;

import edu.wpi.first.wpilibj.command.Command;

public abstract class TeleopCommand extends Command {
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	@Override
	protected void interrupted() {
		end();
	}
	
	@Override
	protected abstract void end();
}
