package org.usfirst.frc.team3473.robot.subsystems;

import org.usfirst.frc.team3473.robot.RobotMap;
import org.usfirst.frc.team3473.robot.commands.ElevateIntake;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *	A subsystem that raises and lowers the elevator,
 *	which is used to raise the power cube to the scale.
 */
public class IntakeElevator extends Subsystem {

	/**
	 * Moves the elevator at the given speed. Positive speeds
	 * move the elevator up, while negative speeds move the
	 * elevator down.
	 * @param speed between -1.0 and 1.0
	 */
	public void moveElevator(double speed) {
		RobotMap.intakeElevator.set(speed);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new ElevateIntake());
	}
}

