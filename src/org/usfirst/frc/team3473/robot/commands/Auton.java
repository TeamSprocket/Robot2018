package org.usfirst.frc.team3473.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *	CommandGroup for autonomous robot control.
 */
public class Auton extends CommandGroup {
	public enum Position {LEFT, CENTER, RIGHT}
	
	// TODO: fromCenter, fromRight
	public Auton(Position robotPosition, Position switchPosition, Position scalePosition) {
//		if(robotPosition == Position.LEFT) {
//			fromLeft(switchPosition, scalePosition);
//		}
		addSequential(new MoveDistance(300));
		addSequential(new ElevateIntakeToHeight(2500));
	}
	
	private void fromLeft(Position switchPosition, Position scalePosition) {
		addSequential(new MoveDistance(50));
		if(switchPosition == Position.LEFT) {
			addSequential(new MoveRollers(-1, 1.0));
			addSequential(new MoveRollers(0, 0.5));
		}
		else if(scalePosition == Position.LEFT) {
			addSequential(new ElevateIntakeToHeight(2500));
			addSequential(new MoveRollers(-1, 1.0));
			addSequential(new MoveRollers(0, 0.5));
		}
	}
	
	public static Position getPositionFromChar(char letter) {
		if(letter == 'L')
			return Position.LEFT;
		else if(letter == 'R')
			return Position.RIGHT;
		return null;
	}
}
