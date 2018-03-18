package org.usfirst.frc.team3473.robot.subsystems;

import org.usfirst.frc.team3473.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *	A subsystem that intakes and outtakes a power cube.
 */
public class Intake extends Subsystem {
	private static final double INTAKE_SPEED = 1.0;
	private static final double OUTTAKE_SPEED = 1.0;

	/**
	 * Moves the rollers at a given speed.
	 * Positive values move the rollers inward;
	 * negative values move the rollers outward.
	 * @param speed the speed at which to move the rollers
	 */
	public void moveRollers(double speed) {
		RobotMap.intakeLeft.set(-speed);
		RobotMap.intakeRight.set(speed);
	}

	public void intake() {
		moveRollers(INTAKE_SPEED);
	}

	public void outtake() {
		moveRollers(-OUTTAKE_SPEED);
	}

	public void stop() {
		moveRollers(0.0);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
	}
}

