package org.usfirst.frc.team3473.robot.subsystems;

import org.usfirst.frc.team3473.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *	A subsystem that represents the robot's drivetrain.
 */
public class Drivetrain extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public void setLeft(double speed) {
		RobotMap.frontLeft.set(speed);
		RobotMap.midLeft.set(speed);
		RobotMap.backLeft.set(speed);
	}
	
	public void setRight(double speed) {
		RobotMap.frontRight.set(-speed);
		RobotMap.midRight.set(-speed);
		RobotMap.backRight.set(-speed);
	}
	
	public void tankDrive(double left, double right) {
		setLeft(left);
		setRight(right);
	}
	
	public void arcadeDrive(double speed, double turn) {
		setLeft(speed + turn);
		setRight(speed - turn);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

