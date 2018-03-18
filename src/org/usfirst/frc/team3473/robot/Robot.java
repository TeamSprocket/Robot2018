/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3473.robot;

import org.usfirst.frc.team3473.robot.commands.ActuateIntake;
import org.usfirst.frc.team3473.robot.commands.Auton;
import org.usfirst.frc.team3473.robot.commands.GearToggle;
import org.usfirst.frc.team3473.robot.commands.MoveRollers;
import org.usfirst.frc.team3473.robot.commands.WrenchToggle;
import org.usfirst.frc.team3473.robot.subsystems.ClimbElevator;
import org.usfirst.frc.team3473.robot.subsystems.Drivetrain;
import org.usfirst.frc.team3473.robot.subsystems.GearPneumatics;
import org.usfirst.frc.team3473.robot.subsystems.Intake;
import org.usfirst.frc.team3473.robot.subsystems.IntakeActuation;
import org.usfirst.frc.team3473.robot.subsystems.IntakeElevator;
import org.usfirst.frc.team3473.robot.subsystems.WrenchPneumatics;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {

	public static Drivetrain drivetrain = new Drivetrain();
	public static Intake intake = new Intake();
	public static IntakeElevator intakeElevator = new IntakeElevator();
	public static ClimbElevator climbElevator = new ClimbElevator();
	public static IntakeActuation intakeActuation = new IntakeActuation();
	public static GearPneumatics gearPneumatics = new GearPneumatics();
	public static WrenchPneumatics wrenchPneumatics = new WrenchPneumatics();

	private Command autonomous;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		RobotMap.gyro.calibrate();
		RobotMap.leftEncoder.reset();
		RobotMap.rightEncoder.reset();
		RobotMap.intakeElevatorEncoder.reset();
		intakeElevator.setBrakeMode(true);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		updateDashboardValues();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		Auton.Position robotPosition;
		if(OI.leftJoystick.getRawAxis(3) > 0.33)
			robotPosition = Auton.Position.RIGHT;
		else if(OI.leftJoystick.getRawAxis(3) >= -0.33)
			robotPosition = Auton.Position.CENTER;
		else
			robotPosition = Auton.Position.LEFT;
		
		Auton.Mode autonMode = Auton.Mode.SWITCH;
//		if(OI.rightJoystick.getRawAxis(3) > 0.33)
//			autonMode = Auton.Mode.BASELINE;
//		else if(OI.rightJoystick.getRawAxis(3) >= -0.33)
//			autonMode = Auton.Mode.SWITCH;
//		else
//			autonMode = Auton.Mode.SCALE;
		
		SmartDashboard.putString("Starting Position", robotPosition.toString());
		SmartDashboard.putString("Auton Mode", autonMode.toString());
		
		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		SmartDashboard.putString("Game Data", gameData);

		Auton.Position switchPosition = Auton.getPositionFromChar(gameData.charAt(0));
		Auton.Position scalePosition = Auton.getPositionFromChar(gameData.charAt(1));
		autonomous = new Auton(robotPosition, autonMode, switchPosition, scalePosition);
		autonomous.start();
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		updateDashboardValues();
	}

	@Override
	public void teleopInit() {
		if(autonomous != null)
			autonomous.cancel();

		OI.intakeButton.whenPressed(new MoveRollers(1));
		OI.intakeButton.whenReleased(new MoveRollers(0));
		OI.outtakeButton.whenPressed(new MoveRollers(-1));
		OI.outtakeButton.whenReleased(new MoveRollers(0));
		OI.actuateButton.whenPressed(new ActuateIntake());
//		OI.ratchetWrenchToggle.whenPressed(new WrenchToggle());
		OI.changeGearButton.whenPressed(new GearToggle());
		
		RobotMap.gyro.reset();
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		updateDashboardValues();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}

	public void updateDashboardValues() {
		SmartDashboard.putNumber("Left Joystick X", OI.leftJoystick.getX());
		SmartDashboard.putNumber("Left Joystick Y", OI.leftJoystick.getY());
		SmartDashboard.putNumber("Right Joystick X", OI.rightJoystick.getX());
		SmartDashboard.putNumber("Right Joystick Y", OI.rightJoystick.getY());
		SmartDashboard.putBoolean("Gear Shifted", gearPneumatics.getToggled());
		SmartDashboard.putNumber("Gyro Angle", RobotMap.gyro.getAngle());
		SmartDashboard.putNumber("Left Encoder", RobotMap.leftEncoder.getDistance());
		SmartDashboard.putNumber("Right Encoder", RobotMap.rightEncoder.getDistance());
	}
}
