package org.usfirst.frc.team3473.robot.subsystem;

import org.usfirst.frc.team3473.robot.RobotMap;
import org.usfirst.frc.team3473.robot.command.teleop.ElevateIntake;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *	A subsystem that raises and lowers the elevator,
 *	which is used to raise the power cube to the scale.
 */
public class IntakeElevator extends Subsystem {
	private static final double MAX_SPEED = 1.0;
	
	public static Counter counter = new Counter(RobotMap.limitSwitch);

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
		RobotMap.intakeElevator1.set(speed * MAX_SPEED);
		RobotMap.intakeElevator2.set(speed * MAX_SPEED);
	}
	
	public void setBrakeMode(boolean brake) {
		if(brake) {
			RobotMap.intakeElevator1.setNeutralMode(NeutralMode.Brake);
			RobotMap.intakeElevator2.setNeutralMode(NeutralMode.Brake);
		}
		else {
			RobotMap.intakeElevator1.setNeutralMode(NeutralMode.Coast);
			RobotMap.intakeElevator2.setNeutralMode(NeutralMode.Coast);
		}
	}
	
	public void initializeCounter() {
		counter.reset();
	}
	
	/**
	 * Returns true if the switch has been pressed any time
	 * since it was last initialized.
	 * @return whether the switch has been pressed
	 */
	public boolean isSwitchSet() {
		return counter.get() > 0;
	}

	public void initDefaultCommand() {
		setDefaultCommand(new ElevateIntake());
	}
}

