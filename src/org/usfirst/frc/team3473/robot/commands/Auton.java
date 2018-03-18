package org.usfirst.frc.team3473.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *	CommandGroup for autonomous robot control.
 */
public class Auton extends CommandGroup {
	public enum Position {LEFT, CENTER, RIGHT}
	public enum Mode {BASELINE, SWITCH, SCALE}
	

	public Auton(Position robotPosition, Mode mode, Position switchPosition, Position scalePosition) {
		if(mode == Mode.BASELINE) {
			crossBaseline();
		}
		else if(mode == Mode.SWITCH) {
			switchMode(robotPosition, switchPosition, scalePosition);
		}
		else if(mode == Mode.SCALE) {
			scaleMode(robotPosition, switchPosition, scalePosition);
		}
	}
	
	private void crossBaseline() {
		addSequential(new MoveDistance(1100));
	}
	
	private void switchMode(Position robotPosition, Position switchPosition, Position scalePosition) {
		if(robotPosition == Position.CENTER) {
			switchFromCenter(switchPosition);
		}
		else if(robotPosition == switchPosition) {
			switchFromSide(switchPosition);
		}
		else if(robotPosition == scalePosition) {
			scaleFromSide(scalePosition);
		}
		else {
			crossBaseline();
		}
//		if(robotPosition == Position.LEFT) {
//			fromLeft(switchPosition, scalePosition);
//		}
//		else if(robotPosition == Position.RIGHT) {
//			fromRight(switchPosition, scalePosition);
//		}
//		else if(robotPosition == Position.CENTER) {
//			switchFromCenter(switchPosition);
//		}
	}
	
	private void scaleMode(Position robotPosition, Position switchPosition, Position scalePosition) {
		if(robotPosition == Position.CENTER) {
			switchFromCenter(switchPosition);
		}
		else if(robotPosition == scalePosition) {
			scaleFromSide(scalePosition);
		}
		else if(robotPosition == switchPosition) {
			switchFromSide(switchPosition);
		}
		else {
			crossBaseline();
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
	
	// Drops a cube into either alliance plate
	private void switchFromCenter(Position  switchPosition) {
		addSequential(new MoveDistance(500));
		if(switchPosition == Position.LEFT) {
//			addSequential(new TurnAngle(-0.75, -80));
//			addSequential(new MoveDistance(500));
//			addSequential(new TurnAngle(0.75, 80));
//			addSequential(new MoveDistance(500));
//			addSequential(new ActuateIntake());
//			addSequential(new ElevateIntakeToHeight(600));
//			addSequential(new MoveRollers(-1, 0.75));
		}
		else if(switchPosition == Position.RIGHT) {
			addSequential(new TurnUsingTime(0.75, 1.0));
			addSequential(new MoveDistance(800));
			addSequential(new TurnUsingTime(-0.75, 0.5));
			addSequential(new MoveDistance(400));
			addSequential(new ActuateIntake());
			addSequential(new ElevateIntakeToHeight(600));
			addSequential(new MoveRollers(-1, 0.75));
		}
	}
	
	// Move forward, turn 90 degrees, drop cube
	private void switchFromSide(Position switchPosition) {
		addSequential(new MoveDistance(1300));
		if(switchPosition == Position.LEFT)
			addSequential(new TurnAngle(0.75, 80));
		else if(switchPosition == Position.RIGHT)
			addSequential(new TurnAngle(-0.75, -80));
		addSequential(new ActuateIntake());
		addSequential(new ElevateIntakeToHeight(600));
		addSequential(new MoveRollers(-1, 1.0));
	}
	
	private void scaleFromSide(Position scalePosition) {
		addSequential(new MoveDistance(2200));
		if(scalePosition == Position.LEFT)
			addSequential(new TurnAngle(0.75, 80));
		else if(scalePosition == Position.RIGHT)
			addSequential(new TurnAngle(-0.75, -80));
		addSequential(new ActuateIntake());
		addSequential(new ElevateIntakeToHeight(1500));
		addSequential(new MoveRollers(-1, 1.0));
	}
	
	/**
	 * Converts a character from the game data into the corresponding Position.
	 * @param letter the letter ('L' or 'R')
	 * @return the corresponding Position (or null if letter is invalid)
	 */
	public static Position getPositionFromChar(char letter) {
		if(letter == 'L')
			return Position.LEFT;
		else if(letter == 'R')
			return Position.RIGHT;
		return null;
	}
}
