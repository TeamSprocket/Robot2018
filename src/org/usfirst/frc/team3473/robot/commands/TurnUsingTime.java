package org.usfirst.frc.team3473.robot.commands;

import org.usfirst.frc.team3473.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnUsingTime extends Command {
	private double leftSpeed;
	private double rightSpeed;
	
	public TurnUsingTime(double turn, double time) {
		this(turn, -turn, time);
	}

    public TurnUsingTime(double left, double right, double time) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	leftSpeed = left;
    	rightSpeed = right;
    	setTimeout(time);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.tankDrive(leftSpeed, rightSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return this.isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.tankDrive(0.0, 0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
