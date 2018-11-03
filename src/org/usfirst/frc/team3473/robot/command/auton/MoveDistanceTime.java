package org.usfirst.frc.team3473.robot.command.auton;

import org.usfirst.frc.team3473.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveDistanceTime extends Command {
	private final double time;
	private static final double DEFAULT_SPEED = 0.5;
	
    public MoveDistanceTime(double time) {
        this.time = time;   	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(time / 1000);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.tankDrive(DEFAULT_SPEED, DEFAULT_SPEED);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(isTimedOut()) {
    		return true;
    	}
    	else {
        	return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
		System.out.println("auton ends");
    	Robot.drivetrain.tankDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
