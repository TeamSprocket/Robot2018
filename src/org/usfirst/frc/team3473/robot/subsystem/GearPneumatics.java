package org.usfirst.frc.team3473.robot.subsystem;

import org.usfirst.frc.team3473.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * A subsystem that represents the robot's gear pneumatics, which can be shifted
 * into high or low gear. This subsystem is a singleton, which means the only
 * instance of it is instantiated statically when the class is loaded.
 */
@Deprecated
public class GearPneumatics extends Subsystem implements Testable {
	private final DoubleSolenoid gearPneumatics = new DoubleSolenoid(
			RobotMap.GearPneumatics.FORWARD_CHANNEL,
			RobotMap.GearPneumatics.REVERSE_CHANNEL);

	public boolean getToggled() {
		return gearPneumatics.get() == Value.kForward;
	}

	public void toggle() {
		if(getToggled())
			moveForward();
		else
			moveBackward();
	}

	public void moveForward() {
		gearPneumatics.set(DoubleSolenoid.Value.kForward);
	}

	public void moveBackward() {
		gearPneumatics.set(DoubleSolenoid.Value.kReverse);
	}

	@Override
	public void test() {
		// TODO: Implement
	}

	@Override
	protected void initDefaultCommand() {
	}

	// Singleton instance, getter, and constructor
	private static final GearPneumatics instance = new GearPneumatics();

	public static GearPneumatics getInstance() {
		return instance;
	}

	private GearPneumatics() {
	}
}
