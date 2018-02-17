/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3473.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	// Controllers
	public static Joystick leftJoystick = new Joystick(0);
	public static Joystick rightJoystick = new Joystick(1);
	public static XboxController gamepad = new XboxController(2);

	// Buttons
	public static Button changeGearButton = new JoystickButton(leftJoystick, 1);
	public static Button intakeButton = new JoystickButton(gamepad, 1);
	public static Button outtakeButton = new JoystickButton(gamepad, 2);
	public static Button actuateButton = new JoystickButton(gamepad, 3);

	// Axes to use (for elevators)
	public static GenericHID.Hand intakeHand = GenericHID.Hand.kLeft;
	public static GenericHID.Hand climbHand = GenericHID.Hand.kRight;
}
