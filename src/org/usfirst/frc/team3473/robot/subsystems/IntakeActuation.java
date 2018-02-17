package org.usfirst.frc.team3473.robot.subsystems;

import org.usfirst.frc.team3473.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *	A subsystem for the acutation piston on the intake/outtake.
 */
public class IntakeActuation extends Subsystem {

	/**
	 * Actuates the piston.
	 * @param actuate Moves the piston (true to move piston outwards; false to move back)
	 */
	public void actuate(boolean actuate) {
		RobotMap.actuationPiston.set(true);
	}

	public void initDefaultCommand() {
	}
}

