package org.usfirst.frc.team3473.robot.subsystem;

import java.util.Arrays;
import java.util.List;

import org.usfirst.frc.team3473.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * A subsystem that represents the intake of the robot, which can intake and
 * outtake by moving rollers to pick up and release power cubes. This subsystem
 * is a singleton, which means the only instance of it is instantiated
 * statically when the class is loaded.
 */
public class Intake extends Subsystem implements Testable {
	private static final double INTAKE_SPEED = 1.0;
	private static final double OUTTAKE_SPEED = 1.0;

	private final WPI_TalonSRX leftRoller = new WPI_TalonSRX(
			RobotMap.Intake.LEFT_TALON);
	private final WPI_TalonSRX rightRoller = new WPI_TalonSRX(
			RobotMap.Intake.RIGHT_TALON);

	private final List<WPI_TalonSRX> rollers = Arrays.asList(leftRoller,
			rightRoller);

	public void moveRollers(double speed) {
		leftRoller.set(speed);
		rightRoller.set(-speed);
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

	public void setBrakeMode(boolean brake) {
		if(brake)
			rollers.forEach(r -> r.setNeutralMode(NeutralMode.Brake));
		else
			rollers.forEach(r -> r.setNeutralMode(NeutralMode.Coast));
	}

	@Override
	public void test() {
		// TODO: Implement
	}

	@Override
	public void initDefaultCommand() {
	}

	// Singleton instance, getter, and constructor
	private static final Intake instance = new Intake();

	public static Intake getInstance() {
		return instance;
	}

	private Intake() {
	}
}
