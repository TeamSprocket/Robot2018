package org.usfirst.frc.team3473.robot.subsystem;

import org.usfirst.frc.team3473.robot.RobotMap;
import org.usfirst.frc.team3473.util.UD;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * A subsystem that represents the actuation piston, which can actuate up and
 * down. This subsystem is a singleton, which means the only instance of it is
 * instantiated statically when the class is loaded.
 */
public class Actuator extends Subsystem implements Testable {
	private final DoubleSolenoid actuationPiston = new DoubleSolenoid(
			RobotMap.Actuator.FORWARD_CHANNEL,
			RobotMap.Actuator.REVERSE_CHANNEL);

	private UD state;

	public UD getState() {
		return state;
	}

	public void actuateDown() {
		actuationPiston.set(DoubleSolenoid.Value.kForward);
		state = UD.DOWN;
	}

	public void actuateUp() {
		actuationPiston.set(DoubleSolenoid.Value.kReverse);
		state = UD.UP;
	}

	public void toggle() {
		if(state == UD.UP)
			actuateDown();
		else
			actuateUp();
	}

	@Override
	public void test() {
		// TODO: Implement
	}

	@Override
	public void initDefaultCommand() {
	}

	// Singleton instance, getter, and constructor
	private static final Actuator instance = new Actuator();

	public static Actuator getInstance() {
		return instance;
	}

	private Actuator() {
//		actuateUp();
	}
}
