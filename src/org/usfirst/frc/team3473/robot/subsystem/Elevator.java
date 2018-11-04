package org.usfirst.frc.team3473.robot.subsystem;

import java.util.Arrays;
import java.util.List;

import org.usfirst.frc.team3473.robot.RobotMap;
import org.usfirst.frc.team3473.robot.command.teleop.ElevateIntake;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * A subsystem that represents the elevator, which can be raised or lowered to
 * bring cubes onto the Switch and Scale. This subsystem is a singleton, which
 * means the only instance of it is instantiated statically when the class is
 * loaded.
 */
public class Elevator extends Subsystem implements Testable {
	private final WPI_TalonSRX talon1 = new WPI_TalonSRX(
			RobotMap.Elevator.TALON_1);
	private final WPI_TalonSRX talon2 = new WPI_TalonSRX(
			RobotMap.Elevator.TALON_2);

	private final List<WPI_TalonSRX> talons = Arrays.asList(talon1, talon2);

	public void moveElevator(double speed) {
		talons.forEach(t -> t.set(speed));
	}

	public void stop() {
		moveElevator(0);
	}

	public void setBrakeMode(boolean brake) {
		if(brake)
			talons.forEach(t -> t.setNeutralMode(NeutralMode.Brake));
		else
			talons.forEach(t -> t.setNeutralMode(NeutralMode.Coast));
	}

	@Override
	public void test() {
		// TODO: Implement
	}

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new ElevateIntake());
	}

	// Singleton instance, getter, and constructor
	private static final Elevator instance = new Elevator();

	public static Elevator getInstance() {
		return instance;
	}

	private Elevator() {
	}
}
