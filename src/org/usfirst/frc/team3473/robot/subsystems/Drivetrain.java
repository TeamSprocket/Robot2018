package org.usfirst.frc.team3473.robot.subsystems;

import org.usfirst.frc.team3473.robot.RobotMap;
import org.usfirst.frc.team3473.robot.commands.Drive;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *	A subsystem that represents the robot's drivetrain.
 */
public class Drivetrain extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void setLeft(double speed) {
		RobotMap.frontLeft.set(speed);
		RobotMap.backLeft.set(speed);
	}

	public void setRight(double speed) {
		RobotMap.frontRight.set(-speed);
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

	public void setBrakeMode(boolean brake) {
		if(brake) {
			RobotMap.frontLeft.setNeutralMode(NeutralMode.Brake);
			RobotMap.frontRight.setNeutralMode(NeutralMode.Brake);
			RobotMap.backLeft.setNeutralMode(NeutralMode.Brake);
			RobotMap.backRight.setNeutralMode(NeutralMode.Brake);
		}
		else {
			RobotMap.frontLeft.setNeutralMode(NeutralMode.Coast);
			RobotMap.frontRight.setNeutralMode(NeutralMode.Coast);
			RobotMap.backLeft.setNeutralMode(NeutralMode.Coast);
			RobotMap.backRight.setNeutralMode(NeutralMode.Coast);

		}
	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new Drive());
	}
}

