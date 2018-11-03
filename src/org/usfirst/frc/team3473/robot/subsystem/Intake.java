package org.usfirst.frc.team3473.robot.subsystem;

import org.usfirst.frc.team3473.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.NeutralMode;

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
		RobotMap.intakeLeft.set(speed);
		RobotMap.intakeRight.set(-speed);
	}

	public void intake() {
		moveRollers(INTAKE_SPEED);
	}

	public void outtake() {
		moveRollers(-OUTTAKE_SPEED);
	}
	
	public void setBrakeMode(boolean brake) {
		if(brake) {
			RobotMap.intakeLeft.setNeutralMode(NeutralMode.Brake);
			RobotMap.intakeRight.setNeutralMode(NeutralMode.Brake);
		}
		else {
			RobotMap.intakeLeft.setNeutralMode(NeutralMode.Coast);
			RobotMap.intakeRight.setNeutralMode(NeutralMode.Coast);
		}
	}

	public void stop() {
		moveRollers(0.0);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
	}
}

