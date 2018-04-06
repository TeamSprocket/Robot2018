/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3473.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// Drivetrain
	public static WPI_TalonSRX frontLeft = new WPI_TalonSRX(1);
	public static WPI_TalonSRX backLeft = new WPI_TalonSRX(2);
	public static WPI_TalonSRX frontRight = new WPI_TalonSRX(3);
	public static WPI_TalonSRX backRight = new WPI_TalonSRX(4);
	public static DoubleSolenoid gearPneumatics = new DoubleSolenoid(0, 1);

	// Intake
	public static WPI_TalonSRX intakeLeft = new WPI_TalonSRX(5);
	public static WPI_TalonSRX intakeRight = new WPI_TalonSRX(6);
	public static DoubleSolenoid actuationPiston = new DoubleSolenoid(4, 5);
	
	// Intake Elevator
	public static WPI_TalonSRX intakeElevator1 = new WPI_TalonSRX(7);
	public static WPI_TalonSRX intakeElevator2 = new WPI_TalonSRX(8);
	
	// Sensors
	public static Encoder leftEncoder = new Encoder(0, 1);
	public static Encoder rightEncoder = new Encoder(4, 5, true);
	public static Encoder intakeElevatorEncoder = new Encoder(2, 3, true);
	public static ADXRS450_Gyro gyro = new InvertedGyro();
	public static DigitalInput limitSwitch = new DigitalInput(6);
	
	// This subclass reverses the direction of a gyro
	private static class InvertedGyro extends ADXRS450_Gyro {
		public double getRate() {
			return -super.getRate();
		}
		public double getAngle() {
			return -super.getAngle();
		}
	}
}
