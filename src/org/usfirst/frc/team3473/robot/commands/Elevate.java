package org.usfirst.frc.team3473.robot.commands;

import org.usfirst.frc.team3473.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Elevate extends Command {
	private static final double DEFAULT_SPEED = 0.4;
	
	private double elevatorSpeed;
	
	/**
	 * Creates a new Elevate command, which moves the elevator at a default speed.
	 * @param raiseElevator Set this to 1 to raise the elevator, -1 to lower the elevator,
	 * and 0 to stop the elevator.
	 */
    public Elevate(int raiseElevator) {
        this(raiseElevator * DEFAULT_SPEED);
    }
    
    /**
     * Creates a new Elevate command that moves the elevator at a given speed.
     * @param elevatorSpeed The speed at which to move the elevator (positive values
     * indicate the elevator is moving up, negative values indicate the elevator is
     * moving down)
     */
    public Elevate(double elevatorSpeed) {
    	requires(Robot.elevator);
    	this.elevatorSpeed = elevatorSpeed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.elevator.moveElevator(elevatorSpeed);
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
