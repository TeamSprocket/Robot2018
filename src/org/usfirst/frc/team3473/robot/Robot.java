/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3473.robot;

import org.usfirst.frc.team3473.robot.commands.Drive;
import org.usfirst.frc.team3473.robot.commands.DriveDistance;
import org.usfirst.frc.team3473.robot.commands.Elevate;
import org.usfirst.frc.team3473.robot.commands.TurnAngle;
import org.usfirst.frc.team3473.robot.subsystems.Drivetrain;
import org.usfirst.frc.team3473.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.TimedCommand;
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
	public static Elevator elevator = new Elevator();
	
	private CommandGroup autonomous;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		RobotMap.gyro.reset();
		RobotMap.gyro.calibrate();
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
		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		SmartDashboard.putString("Game Data", gameData);
		
		if(autonomous == null) {
			autonomous = new CommandGroup();
			autonomous.addSequential(new DriveDistance(500));
			if(gameData.charAt(0) == 'L') {
				autonomous.addSequential(new TurnAngle(-0.4, 90));
				SmartDashboard.putString("Turn", "Left");
			}
			else if(gameData.charAt(0) == 'R') {
				autonomous.addSequential(new TurnAngle(0.4, 90));
				SmartDashboard.putString("Turn", "Right");
			}
			autonomous.addSequential(new DriveDistance(500));
			autonomous.addSequential(new Elevate(true));
			autonomous.addSequential(new TimedCommand(3.0));
			autonomous.addSequential(new Elevate(false));
		}
		autonomous.start();
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		if(autonomous != null)
			autonomous.cancel();
		
		Drive drive = new Drive();
		drive.start();
		
		OI.elevateButton.whenPressed(new Elevate(true));
		OI.elevateButton.whenReleased(new Elevate(false));
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
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
	}
}
