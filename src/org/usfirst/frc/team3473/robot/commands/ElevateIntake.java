package org.usfirst.frc.team3473.robot.commands;

import org.usfirst.frc.team3473.robot.OI;
import org.usfirst.frc.team3473.robot.Robot;
import org.usfirst.frc.team3473.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Raises or lowers the intake elevator whenever the "raise" or "lower"
 * buttons are pressed. This is the default command for the IntakeElevator.
 */
public class ElevateIntake extends Command {

	public ElevateIntake() {
		requires(Robot.intakeElevator);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
//		double limitDist = RobotMap.intakeElevatorEncoder.getDistance();
//		if(OI.raiseIntakeButton.get() && limitDist >= 1000){
//			Robot.intakeElevator.moveElevator(0.0);
//		}
		if(OI.raiseIntakeButton.get()) {
			Robot.intakeElevator.moveElevator(1.0);
		}
		else if(OI.lowerIntakeButton.get()) {
			Robot.intakeElevator.moveElevator(-0.4);
		}
		else {
			Robot.intakeElevator.moveElevator(0.0);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.intakeElevator.moveElevator(0.0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
