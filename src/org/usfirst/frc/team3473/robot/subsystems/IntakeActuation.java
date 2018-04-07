package org.usfirst.frc.team3473.robot.subsystems;

import org.usfirst.frc.team3473.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *	A subsystem for the acutation piston on the intake/outtake.
 */
public class IntakeActuation extends Subsystem {
	public boolean getToggled(){
		return RobotMap.actuationPiston.get() == DoubleSolenoid.Value.kReverse;
	}
	
	public void actuateDown(){
		RobotMap.actuationPiston.set(DoubleSolenoid.Value.kForward);
	}
	
	public void actuateUp(){
		RobotMap.actuationPiston.set(DoubleSolenoid.Value.kReverse);
	}
//	/**
//	 * Actuates the piston.
//	 * @param actuate Moves the piston (true to move piston outwards; false to move back)
//	 */
//	public void actuate(boolean actuate) {
//		if(actuate) {
//			RobotMap.actuationPiston.set(DoubleSolenoid.Value.kForward);
//		}
//		else {
//			RobotMap.actuationPiston.set(DoubleSolenoid.Value.kReverse);
//		}
//	}

	public void initDefaultCommand() {
	}
}

