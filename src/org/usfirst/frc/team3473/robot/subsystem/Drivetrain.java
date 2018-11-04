/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3473.robot.subsystem;

import java.util.Arrays;
import java.util.List;

import org.usfirst.frc.team3473.robot.RobotMap;
import org.usfirst.frc.team3473.robot.command.teleop.Drive;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * A subsystem that represents the robot's drivetrain. This subsystem is a
 * singleton, which means the only instance of it is instantiated statically
 * when the class is loaded.
 */
public class Drivetrain extends Subsystem implements Testable {
	private final WPI_TalonSRX frontLeftTalon = new WPI_TalonSRX(
			RobotMap.Drivetrain.FRONT_LEFT_TALON);
	private final WPI_TalonSRX frontRightTalon = new WPI_TalonSRX(
			RobotMap.Drivetrain.FRONT_RIGHT_TALON);
	private final WPI_TalonSRX backLeftTalon = new WPI_TalonSRX(
			RobotMap.Drivetrain.BACK_LEFT_TALON);
	private final WPI_TalonSRX backRightTalon = new WPI_TalonSRX(
			RobotMap.Drivetrain.BACK_RIGHT_TALON);

	private final List<WPI_TalonSRX> talons = Arrays.asList(frontLeftTalon,
			frontRightTalon, backLeftTalon, backRightTalon);

	private void setLeft(double speed) {
		frontLeftTalon.set(speed);
		backLeftTalon.set(speed);
	}

	private void setRight(double speed) {
		frontRightTalon.set(-speed);
		backRightTalon.set(-speed);
	}

	public void tankDrive(double left, double right) {
		setLeft(left);
		setRight(right);
	}

	public void arcadeDrive(double speed, double turn) {
		setLeft(speed + turn);
		setRight(speed - turn);
	}

	public void stop() {
		setLeft(0);
		setRight(0);
	}
	
	public void setBrakeMode(boolean brake) {
		if(brake)
			talons.forEach(t -> t.setNeutralMode(NeutralMode.Brake));
		else
			talons.forEach(t -> t.setNeutralMode(NeutralMode.Coast));
	}

	@Override
	public void test() {
		// TODO: Implement
	}

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new Drive());
	}

	// Singleton instance, getter, and constructor
	private static final Drivetrain instance = new Drivetrain();

	public static Drivetrain getInstance() {
		return instance;
	}

	private Drivetrain() {
	}
}
