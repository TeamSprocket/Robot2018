package org.usfirst.frc.team3473.robot.subsystems;

import org.usfirst.frc.team3473.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *	A subsystem for the acutation piston on the intake/outtake.
 */
public class IntakeActuation extends Subsystem {
	private boolean toggled;
	
	public boolean getToggled(){
		return toggled;
	}
	
	public void actuateDown(){
		RobotMap.actuationPiston.set(DoubleSolenoid.Value.kForward);
		toggled = true;
	}
	
	public void actuateUp(){
		RobotMap.actuationPiston.set(DoubleSolenoid.Value.kReverse);
		toggled = false;
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

