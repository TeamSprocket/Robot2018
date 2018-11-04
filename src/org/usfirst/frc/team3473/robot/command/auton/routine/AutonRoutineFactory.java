package org.usfirst.frc.team3473.robot.command.auton.routine;

import org.usfirst.frc.team3473.util.RobotPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonRoutineFactory {
	private static final AutonRoutineFactory instance = new AutonRoutineFactory();
	
	private AutonRoutineFactory() {
		
	}
	
	public static AutonRoutineFactory getInstance() {
		return instance;
	}
	
	public CommandGroup createRoutine(RobotPosition position) {
		
		return null;
	}
}
