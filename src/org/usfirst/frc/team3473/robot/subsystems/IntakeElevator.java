package org.usfirst.frc.team3473.robot.subsystems;

import org.usfirst.frc.team3473.robot.RobotMap;
import org.usfirst.frc.team3473.robot.commands.ElevateIntake;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *	A subsystem that raises and lowers the elevator,
 *	which is used to raise the power cube to the scale.
 */
public class IntakeElevator extends Subsystem {
	private static final double MAX_UP_SPEED = 1.0;
	private static final double MAX_DOWN_SPEED = 0.5;

	/**
	 * Moves the elevator at the given speed. Positive speeds
	 * move the elevator up, while negative speeds move the
	 * elevator down.
	 * @param speed between -1.0 and 1.0
	 */
	public void moveElevator(double speed) {
		if(speed > 1.0)
			speed = 1.0;
		else if(speed < -1.0)
			speed = -1.0;
		if(speed >= 0.0)
			RobotMap.intakeElevator.set(speed * MAX_UP_SPEED);
		else
			RobotMap.intakeElevator.set(speed * MAX_UP_SPEED);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new ElevateIntake());
	}
}

