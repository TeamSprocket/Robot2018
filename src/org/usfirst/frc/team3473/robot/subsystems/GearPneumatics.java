package org.usfirst.frc.team3473.robot.subsystems;

import org.usfirst.frc.team3473.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *	A subsystem that shifts the drivetrain into high or low gear.
 */
public class GearPneumatics extends Subsystem {
	private boolean toggled;

	public void initDefaultCommand() {
	}

	public boolean getToggled() {
		return toggled;
	}
	public void movePneumaticsForward() {
		RobotMap.gearPneumatics.set(DoubleSolenoid.Value.kForward);
		toggled = true;
	}

	public void movePneumaticsBackward() {
		RobotMap.gearPneumatics.set(DoubleSolenoid.Value.kReverse);
		toggled = false;
	}
}

