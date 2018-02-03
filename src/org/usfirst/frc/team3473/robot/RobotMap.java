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
	public static WPI_TalonSRX frontLeft = new WPI_TalonSRX(5);
	public static WPI_TalonSRX midLeft = new WPI_TalonSRX(6);
	public static WPI_TalonSRX backLeft = new WPI_TalonSRX(8);
	public static WPI_TalonSRX frontRight = new WPI_TalonSRX(1);
	public static WPI_TalonSRX midRight = new WPI_TalonSRX(2);
	public static WPI_TalonSRX backRight = new WPI_TalonSRX(7);
	
	public static WPI_TalonSRX climbLeft = new WPI_TalonSRX(3);
	public static WPI_TalonSRX climbRight = new WPI_TalonSRX(4);
	
	public static DoubleSolenoid gate = new DoubleSolenoid(0, 1);
	
	public static Encoder encoder = new Encoder(2, 3);
	
	public static Gyro gyro = new ADXRS450_Gyro();
}
