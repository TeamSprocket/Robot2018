/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3473.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.interfaces.Gyro;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// Drivetrain
	public static WPI_TalonSRX frontLeft = new WPI_TalonSRX(6);
	public static WPI_TalonSRX backLeft = new WPI_TalonSRX(7);
	public static WPI_TalonSRX frontRight = new WPI_TalonSRX(5);
	public static WPI_TalonSRX backRight = new WPI_TalonSRX(4);
	public static DoubleSolenoid gearPneumatics = new DoubleSolenoid(0, 1);

	// Intake
	public static WPI_TalonSRX intakeLeft = new WPI_TalonSRX(8);
	public static WPI_TalonSRX intakeRight = new WPI_TalonSRX(9);
	public static DoubleSolenoid actuationPiston = new DoubleSolenoid(2, 3);
	
	// Intake Elevator
	public static WPI_TalonSRX intakeElevator = new WPI_TalonSRX(3);
	public static DoubleSolenoid elevatorPneumatics = new DoubleSolenoid(4, 5);

	// Climb
	// as of 03/07/18 we have no climb. intake elevator is now doubled as a
	// climb elevator
	//public static WPI_TalonSRX climbRight = new WPI_TalonSRX(2);
	//public static WPI_TalonSRX climbLeft = new WPI_TalonSRX(8);
	
	// Sensors
	public static Encoder leftEncoder = new Encoder(7, 6);
	public static Encoder rightEncoder = new Encoder(5, 4, true);
	public static Encoder intakeElevatorEncoder = new Encoder(0, 1);
	public static Gyro gyro = new ADXRS450_Gyro();
}
