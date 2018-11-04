package org.usfirst.frc.team3473.robot.subsystem;

/**
 * An interface that defines a testable subsystem
 */
public interface Testable {
	/**
	 * Test the subsystem. Tests should see if the subsystem is functioning
	 * properly. Test results should be printed to both the driver station and
	 * the RioLog using DriverStation.reportWarning() and System.out.println()
	 * respectively.
	 */
	public void test();
}
