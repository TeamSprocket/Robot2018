package org.usfirst.frc.team3473.robot.subsystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.usfirst.frc.team3473.robot.RobotMap;
import org.usfirst.frc.team3473.robot.command.teleop.Drive;
import org.usfirst.frc.team3473.util.FB;
import org.usfirst.frc.team3473.util.LR;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * A subsystem that represents the robot's drivetrain.
 */
public class Drivetrain extends Subsystem {
	/**
	 * The singleton instance of the Drivetrain
	 */
	private static final Drivetrain instance = new Drivetrain();
	
	/**
	 * Get the singleton instance of the Drivetrain
	 * @return The singleton Drivetrain instance
	 */
	public static Drivetrain getInstance() {
		return instance;
	}
	
	/**
	 * Private constructor to prohibit public instantiation
	 */
	private Drivetrain() {
	}
	
	// Individual talon fields
	private final WPI_TalonSRX frontLeftTalon = new WPI_TalonSRX(
			RobotMap.getTalon(FB.FRONT, LR.LEFT));
	private final WPI_TalonSRX frontRightTalon = new WPI_TalonSRX(
			RobotMap.getTalon(FB.FRONT, LR.RIGHT));
	private final WPI_TalonSRX backLeftTalon = new WPI_TalonSRX(
			RobotMap.getTalon(FB.BACK, LR.LEFT));
	private final WPI_TalonSRX backRightTalon = new WPI_TalonSRX(
			RobotMap.getTalon(FB.BACK, LR.RIGHT));
	
	// A List of talons for performing batch operations
	private final List<WPI_TalonSRX> talons = new ArrayList<>(Arrays.asList(
			frontLeftTalon, frontRightTalon, backLeftTalon, backRightTalon));

	// Side speed setting methods
	public void setLeft(double speed) {
		frontLeftTalon.set(speed);
		backLeftTalon.set(speed);
	}

	public void setRight(double speed) {
		frontRightTalon.set(-speed);
		backRightTalon.set(-speed);
	}

	// Drive methods
	public void tankDrive(double left, double right) {
		setLeft(left);
		setRight(right);
	}

	public void arcadeDrive(double speed, double turn) {
		setLeft(speed + turn);
		setRight(speed - turn);
	}

	// Brake mode configuration
	public void setBrakeMode(boolean brake) {
		if(brake)
			talons.forEach(t -> t.setNeutralMode(NeutralMode.Brake));
		else
			talons.forEach(t -> t.setNeutralMode(NeutralMode.Coast));
	}

	// Initialize default command
	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new Drive());
	}
}
