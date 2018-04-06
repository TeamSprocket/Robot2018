package org.usfirst.frc.team3473.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *	CommandGroup for autonomous robot control.
 */
public class Auton extends CommandGroup {
	public enum Position {LEFT, CENTER, RIGHT}
	public enum Mode {BASELINE, SWITCH_ONLY, SWITCH_PRIORITY, SCALE_PRIORITY}
	

	public Auton(Position robotPosition, Mode mode, Position switchPosition, Position scalePosition) {
		if(mode == Mode.BASELINE) {
			crossBaseline();
		}
		else if(mode == Mode.SWITCH_ONLY) {
			simpleSwitchMode(robotPosition, switchPosition, scalePosition);
		}
		else if(mode == Mode.SWITCH_PRIORITY) {
			switchMode(robotPosition, switchPosition, scalePosition);
		}
		else if(mode == Mode.SCALE_PRIORITY) {
			scaleMode(robotPosition, switchPosition, scalePosition);
		}
	}
	
	private void crossBaseline() {
		addSequential(new MoveDistance(1000));
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
	}
	
	private void simpleSwitchMode(Position robotPosition, Position switchPosition, Position scalePosition) {
		if(robotPosition == Position.LEFT) {
			fromLeft(switchPosition, scalePosition);
		}
		else if(robotPosition == Position.RIGHT) {
			fromRight(switchPosition, scalePosition);
		}
		else if(robotPosition == Position.CENTER) {
			crossBaseline();
		}
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
	
	// Drives forward and drops the cube (assumes robot is positioned directly in front of switch).
	private void fromLeft(Position switchPosition, Position scalePosition) {
		addSequential(new ActuateIntake());
		addSequential(new MoveDistance(1100));
		if(switchPosition == Position.LEFT) {
			addSequential(new ElevateIntakeToHeight(800));
			addSequential(new MoveRollersAuto(-1, 2.0));
		}
	}
	
	// Drives forward and drops the cube (assumes robot is positioned directly in front of switch).
	private void fromRight(Position switchPosition, Position scalePosition) {
		addSequential(new ActuateIntake());
		addSequential(new MoveDistance(1100));
		if(switchPosition == Position.RIGHT) {
			addSequential(new ElevateIntakeToHeight(800));
			addSequential(new MoveRollersAuto(-1, 2.0));
		}
	}
	
	// Drops a cube into either alliance plate
	private void switchFromCenter(Position switchPosition) {
		addSequential(new MoveDistance(500));
		if(switchPosition == Position.LEFT) {
			addSequential(new TurnAngle(-0.75, -40));
			addSequential(new MoveDistance(500));
			addSequential(new TurnAngle(0.75, 40));
			addSequential(new MoveDistance(500));
			addSequential(new ActuateIntake());
			addSequential(new ElevateIntakeToHeight(800));
			addSequential(new MoveRollersAuto(-1, 0.75));
		}
		else if(switchPosition == Position.RIGHT) {
			addSequential(new TurnAngle(0.75, 40));
			addSequential(new MoveDistance(800));
			addSequential(new TurnAngle(-0.75, -40));
			addSequential(new MoveDistance(400));
			addSequential(new ActuateIntake());
			addSequential(new ElevateIntakeToHeight(800));
			addSequential(new MoveRollersAuto(-1, 0.75));
		}
	}
	
	// Move forward, turn 90 degrees, drop cube
	private void switchFromSide(Position switchPosition) {
		addSequential(new ActuateIntake());
		addSequential(new MoveDistance(2400));
		if(switchPosition == Position.LEFT)
			addSequential(new TurnAngle(0.75, 50));
		else if(switchPosition == Position.RIGHT)
			addSequential(new TurnAngle(-0.75, -50));
		addSequential(new MoveDistance(100));
		addSequential(new ElevateIntakeToHeight(800));
		addSequential(new MoveRollersAuto(-1, 1.0));
	}
	
	private void scaleFromSide(Position scalePosition) {
		addSequential(new ActuateIntake());
		addSequential(new MoveDistance(5600));
		if(scalePosition == Position.LEFT) {
			addSequential(new TurnAngle(0.75, 48));
			addSequential(new MoveDistance(-140));
		}
		else if(scalePosition == Position.RIGHT) {
			addSequential(new TurnAngle(-0.75, -48));
			addSequential(new MoveDistance(-100));
		}
		addSequential(new ElevateIntakeToHeight(0.8, 1650));
		addSequential(new MoveDistance(200));
		addParallel(new MoveRollersAuto(-1, 0.75));
		addSequential(new MoveDistance(-200));
		addSequential(new ElevateIntakeToHeight(-0.8, 300));
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
