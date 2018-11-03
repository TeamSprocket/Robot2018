package org.usfirst.frc.team3473.util.drivers;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;

/**
 * TODO: Add documentation
 */
public class Inverted_ADXRS450_Gyro extends ADXRS450_Gyro {
	@Override
	public double getRate() {
		return -super.getRate();
	}
	
	@Override
	public double getAngle() {
		return -super.getAngle();
	}
}
