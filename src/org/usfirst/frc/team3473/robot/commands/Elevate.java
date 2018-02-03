package org.usfirst.frc.team3473.robot.commands;

import org.usfirst.frc.team3473.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Elevate extends Command {
	private boolean raiseElevator; // true = raise, false = lower

	/**
	 * Creates a new Elevate command.
	 * @param raiseElevator Set this to true to raise the elevator, false to lower the elevator.
	 */
    public Elevate(boolean raiseElevator) {
        requires(Robot.elevator);
        this.raiseElevator = raiseElevator;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(raiseElevator)
    		Robot.elevator.elevate();
    	else
    		Robot.elevator.lower();
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
