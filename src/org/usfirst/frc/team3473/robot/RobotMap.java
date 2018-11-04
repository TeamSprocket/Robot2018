/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3473.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;

/**
 * A mapping from the ports sensors and actuators into to a variable names as
 * well as static getters for obtaining said values. This provides flexibility
 * changing wiring, makes checking the wiring easier, and significantly reduces
 * the number of magic numbers floating around.
 */
public final class RobotMap {
	/**
	 * Drivetrain mappings
	 */
	public static class Drivetrain {
		public static final int FRONT_LEFT_TALON = 1;
		public static final int BACK_LEFT_TALON = 2;
		public static final int FRONT_RIGHT_TALON = 3;
		public static final int BACK_RIGHT_TALON = 4;
	}

	/**
	 * Gear Penumatics mappings
	 */
	public static class GearPneumatics {
		public static final int FORWARD_CHANNEL = 4;
		public static final int REVERSE_CHANNEL = 5;
	}

	/**
	 * Intake mappings
	 */
	public static class Intake {
		public static final int LEFT_TALON = 5;
		public static final int RIGHT_TALON = 6;
	}

	/**
	 * Actuator mappings
	 */
	public static class Actuator {
		public static final int FORWARD_CHANNEL = 0;
		public static final int REVERSE_CHANNEL = 1;
	}

	/**
	 * Elevator mappings
	 */
	public static class Elevator {
		public static final int TALON_1 = 7;
		public static final int TALON_2 = 8;
	}

	// TODO: Refactor sensor mappings to inner class
	public static class Sensors {

	}

	// Sensors
	public static Encoder leftEncoder = new Encoder(2, 3, true);
	public static Encoder rightEncoder = new Encoder(0, 1);
	public static Encoder intakeElevatorEncoder = new Encoder(4, 5, true);
	public static ADXRS450_Gyro gyro = new ADXRS450_Gyro();
//	public static AnalogGyro gyro = new AnalogGyro(0);
	public static DigitalInput limitSwitch = new DigitalInput(6);

	/**
	 * Private constructor to prohibit instantiation
	 */
	private RobotMap() {
		
	}
}
