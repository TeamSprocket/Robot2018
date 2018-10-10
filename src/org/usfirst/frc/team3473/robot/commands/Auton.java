package org.usfirst.frc.team3473.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *	CommandGroup for autonomous robot control.
 */
public class Auton extends CommandGroup {
	public enum Position {LEFT, CENTER, RIGHT}
	public enum Mode {BASELINE, SWITCH_ONLY, SWITCH_PRIORITY, SCALE_PRIORITY, EXPERIMENTAL, SWITCH_SIDE_ONLY, SCALE_SIDE_ONLY, CENTER}
	

	public Auton(Position robotPosition, Mode mode, Position switchPosition, Position scalePosition) {
		System.out.println(mode);
		if(mode == Mode.BASELINE) {
			crossBaseline();
		}
		else if(mode == Mode.SWITCH_ONLY) {
			simpleSwitchMode(robotPosition, switchPosition, scalePosition);
		}
		else if(mode == Mode.SWITCH_PRIORITY) {
			switchMode(robotPosition, switchPosition, scalePosition, false);
		}
		else if(mode == Mode.SCALE_PRIORITY) {
			scaleMode(robotPosition, switchPosition, scalePosition, false);
		}
		else if(mode == Mode.SWITCH_SIDE_ONLY) {
			switchMode(robotPosition, switchPosition, scalePosition, true);
		}
		else if(mode == Mode.SCALE_SIDE_ONLY) {
			scaleMode(robotPosition, switchPosition, scalePosition, true);
		}
		else if(mode == Mode.CENTER) {
			switchFromCenter(switchPosition);
		}
		else if(mode == Mode.EXPERIMENTAL) {
			experimentalMode(robotPosition, switchPosition, scalePosition);
		}
	}
	
	private void crossBaseline() {
		addSequential(new StayStraightGyro(1200));
	}
	
	private void switchMode(Position robotPosition, Position switchPosition, Position scalePosition, boolean exclusive) {
		if(exclusive == true) {
			if(robotPosition == Position.CENTER) {
				switchFromCenter(switchPosition);
			}
			else if (robotPosition == switchPosition) {
				switchFromSide(switchPosition);
			}
			else {
				crossBaseline();
			}
		}
		else {
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
	
	private void scaleMode(Position robotPosition, Position switchPosition, Position scalePosition, boolean exclusive) {
		if(exclusive == true) {
			if(robotPosition == Position.CENTER) {
				switchFromCenter(switchPosition);
			}
			else if(robotPosition == scalePosition) {
				scaleFromSide(scalePosition);
			}
			else {
				crossBaseline();
			}
		}
		else {
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
	}
	
	private void experimentalMode(Position robotPosition, Position switchPosition, Position scalePosition) {
		if(robotPosition == Position.CENTER) {
			switchFromCenter(switchPosition);
		}
		else if(robotPosition == Position.LEFT) {
			if(switchPosition == Position.LEFT) {
				switchFromSide(switchPosition);
				addSequential(new StayStraightGyro(-100));
				addParallel(new ElevateIntakeToTime(-0.5, 100));
				addSequential(new TurnAnglePID(-85));
				addSequential(new StayStraightGyro(1300));
				addSequential(new TurnAnglePID(50));
				addSequential(new StayStraightGyro(260));
				addSequential(new TurnAnglePID(53));
				addParallel(new MoveRollersAuto(1, 2.0));
				addSequential(new StayStraightGyro(0.3, 500));
				addSequential(new ElevateIntakeToTime(600));
				addSequential(new MoveRollersAuto(-1, 1.0));
			}
			else if(scalePosition == Position.LEFT) {
				scaleFromSide(scalePosition);
			}
			else {
				crossBaseline();
			}
		}
		else if(robotPosition == Position.RIGHT) {
			//switchMode(robotPosition, switchPosition, scalePosition);
		}
		else {
			crossBaseline();
		}
	}
	
	// Drives forward and drops the cube (assumes robot is positioned directly in front of switch).
	private void fromLeft(Position switchPosition, Position scalePosition) {
		System.out.println();
		addSequential(new ActuateIntake());
		addSequential(new StayStraightGyro(1100));
		if(switchPosition == Position.LEFT) {
			addSequential(new ElevateIntakeToTime(0.8, 1.2));
			addSequential(new MoveRollersAuto(-1, 2.0));
		}
	}
	
	// Drives forward and drops the cube (assumes robot is positioned directly in front of switch).
	private void fromRight(Position switchPosition, Position scalePosition) {
		addSequential(new ActuateIntake());
		addSequential(new StayStraightGyro(1100));
		if(switchPosition == Position.RIGHT) {
			addSequential(new ElevateIntakeToTime(0.8, 1.2));
			addSequential(new MoveRollersAuto(-1, 2.0));
		}
	}
	
	// Drops a cube into either alliance plate
	private void switchFromCenter(Position switchPosition) {
		addSequential(new ActuateIntake());
		addSequential(new StayStraightGyro(400));
		if(switchPosition == Position.LEFT) {
			addSequential(new TurnAnglePID(-90));
			addSequential(new StayStraightGyro(800));
			addSequential(new TurnAnglePID(90));
			addSequential(new StayStraightGyro(400));
			addSequential(new ElevateIntakeToTime(0.2));
			addSequential(new MoveRollersAuto(-1, 0.75));
		}
		else if(switchPosition == Position.RIGHT) {
			addSequential(new TurnAnglePID(90));
			addSequential(new StayStraightGyro(800));
			addSequential(new TurnAnglePID(-90));
			addSequential(new StayStraightGyro(400));
			addSequential(new ElevateIntakeToTime(0.2));
			addSequential(new MoveRollersAuto(-1, 0.75));
		}
	}
	
	// Move forward, turn 90 degrees, drop cube
	private void switchFromSide(Position switchPosition) {
		addSequential(new ActuateIntake());
		addSequential(new StayStraightGyro(2400));
		
		// TODO: Get rid of anon class
		addSequential(new Command() {
			@Override
			protected void initialize() {
				Timer.delay(1);
			}
			
			@Override
			protected boolean isFinished() {
				return true;
			}
		});
		
		if(switchPosition == Position.LEFT)
			addSequential(new TurnAnglePID(90));
		else if(switchPosition == Position.RIGHT)
			addSequential(new TurnAnglePID(-90));
		addSequential(new StayStraightGyro(200));
		addSequential(new ElevateIntakeToTime(0.8, 1.2));
		addSequential(new MoveRollersAuto(-1, 1.0));
	}
	
	// Move forward, turn 90 degrees, lift elevator to scale, drop cube
	private void scaleFromSide(Position scalePosition) {
		addSequential(new ActuateIntake());
		addSequential(new StayStraightGyro(4850));
		
		// TODO: Get rid of anon class
		addSequential(new Command() {
			@Override
			protected void initialize() {
				Timer.delay(1);
			}
			
			@Override
			protected boolean isFinished() {
				return true;
			}
		});
		
		if(scalePosition == Position.LEFT) {
			addSequential(new TurnAnglePID(90));
			addSequential(new StayStraightGyro(-140));
		}
		else if(scalePosition == Position.RIGHT) {
			addSequential(new TurnAnglePID(-90));
			addSequential(new StayStraightGyro(-140));
		}
		addSequential(new ElevateIntakeToTime(0.8, 2.5));
		addSequential(new StayStraightGyro(220));//was 200
		addSequential(new MoveRollersAuto(-1, 0.75));
		addSequential(new StayStraightGyro(-220));//was also 200
		addSequential(new ElevateIntakeToTime(-0.8, 1.5));
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
