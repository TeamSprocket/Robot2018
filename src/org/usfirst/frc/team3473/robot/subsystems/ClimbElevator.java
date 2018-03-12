package org.usfirst.frc.team3473.robot.subsystems;

import org.usfirst.frc.team3473.robot.RobotMap;
import org.usfirst.frc.team3473.robot.commands.ElevateClimb;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * A subsystem that raises and lowers the elevator allowing
 * the robot to climb the scale.
 */
public class ClimbElevator extends Subsystem {

	public void moveElevator(double speed) {
		//RobotMap.climbRight.set(speed);
		//RobotMap.climbLeft.set(-speed);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new ElevateClimb());
	}
}

