package org.usfirst.frc.team3473.robot.subsystems;

import org.usfirst.frc.team3473.robot.RobotMap;
import org.usfirst.frc.team3473.robot.commands.ElevateIntake;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *	A subsystem that raises and lowers the elevator,
 *	which is used to raise the power cube to the scale.
 */
public class IntakeElevator extends Subsystem {
	private static final double MAX_SPEED = 1.0;

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
			RobotMap.intakeElevator.set(speed * MAX_SPEED);
		else
			RobotMap.intakeElevator.set(speed * MAX_SPEED);
	}
	
	public void setBrakeMode(boolean brake) {
		if(brake)
			RobotMap.intakeElevator.setNeutralMode(NeutralMode.Brake);
		else
			RobotMap.intakeElevator.setNeutralMode(NeutralMode.Coast);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new ElevateIntake());
	}
}

