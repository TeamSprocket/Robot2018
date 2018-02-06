package org.usfirst.frc.team3473.robot.subsystems;

import org.usfirst.frc.team3473.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *	A subsystem that raises and lowers the elevator,
 *	which is used to raise the power cube to the scale.
 */
public class Elevator extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public void moveElevator(double speed) {
		RobotMap.climbLeft.set(speed);
		RobotMap.climbRight.set(speed);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

