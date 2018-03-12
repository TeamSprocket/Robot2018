package org.usfirst.frc.team3473.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *	CommandGroup for autonomous robot control.
 */
public class Auton extends CommandGroup {
	public enum Position {LEFT, CENTER, RIGHT}
	

	public Auton(Position robotPosition, Position switchPosition, Position scalePosition) {
		if(robotPosition == Position.LEFT) {
			fromLeft(switchPosition, scalePosition);
		}
		else if(robotPosition == Position.RIGHT) {
			fromRight(switchPosition,scalePosition);
		}
		else if(robotPosition ==Position.CENTER) {
			fromCenter(switchPosition, scalePosition);
		}
	}
	
	private void fromLeft(Position switchPosition, Position scalePosition) {
		addSequential(new MoveDistance(1100));
		if(switchPosition == Position.LEFT) {
			addSequential(new ActuateIntake());
			addSequential(new ElevateIntakeToHeight(600));
			addSequential(new MoveRollers(-1, 0.75));
		}
	}
	
	private void fromRight(Position switchPosition, Position scalePosition) {
		addSequential(new MoveDistance(1100));
		if(switchPosition == Position.RIGHT) {
			addSequential(new ActuateIntake());
			addSequential(new ElevateIntakeToHeight(600));
			addSequential(new MoveRollers(-1, 0.75));
		}
	}
	
	private void fromCenter(Position  switchPosition, Position scalePosition) {
		addSequential(new MoveDistance(1100));
//		addSequential(new MoveDistance(500));
//		if(switchPosition == Position.LEFT) {
//			addSequential(new TurnAngle(-0.75, -80));
//			addSequential(new MoveDistance(500));
//			addSequential(new TurnAngle(0.75, 80));
//			addSequential(new MoveDistance(500));
//			addSequential(new ActuateIntake());
//			addSequential(new ElevateIntakeToHeight(600));
//			addSequential(new MoveRollers(-1, 0.75));
//		}
//		else if(switchPosition == Position.RIGHT) {
//			
//		}
	}
	
	public static Position getPositionFromChar(char letter) {
		if(letter == 'L')
			return Position.LEFT;
		else if(letter == 'R')
			return Position.RIGHT;
		return null;
	}
}
