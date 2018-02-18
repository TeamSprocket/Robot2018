package org.usfirst.frc.team3473.robot.subsystems;

import org.usfirst.frc.team3473.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearPneumatics extends Subsystem {
	private boolean toggled;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public boolean getToggled() {
    		return toggled;
    }
    public void movePneumaticsForward() {
    		RobotMap.gearPneumatics.set(DoubleSolenoid.Value.kForward);
    }
    
    public void movePneumaticsBackward() {
    		RobotMap.gearPneumatics.set(DoubleSolenoid.Value.kReverse);
    }
}

