package org.usfirst.frc.team3473.robot.command.teleop;

import org.usfirst.frc.team3473.robot.OI;
import org.usfirst.frc.team3473.robot.Robot;
import org.usfirst.frc.team3473.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Raises or lowers the intake elevator whenever the "raise" or "lower" buttons
 * are pressed. This is the default command for the IntakeElevator.
 */
public class ElevateIntake extends TeleopCommand {

	public ElevateIntake() {
		requires(Robot.elevator);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		boolean limitReached = RobotMap.limitSwitch.get();
//		if(OI.raiseIntakeButton.get() && limitReached){
//			Robot.intakeElevator.moveElevator(0.0);
//		}
//		else 
		if(OI.raiseIntakeButton.get()) {
			Robot.elevator.moveElevator(-1.0);
		} else if(OI.lowerIntakeButton.get()) {
			Robot.elevator.moveElevator(0.4);
		} else {
			Robot.elevator.moveElevator(0.0);
		}
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.elevator.moveElevator(0.0);
	}
}
