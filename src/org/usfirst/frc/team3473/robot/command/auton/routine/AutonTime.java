package org.usfirst.frc.team3473.robot.command.auton.routine;

import org.usfirst.frc.team3473.robot.command.auton.ElevateIntakeToTime;
import org.usfirst.frc.team3473.robot.command.auton.MoveRollersAuto;
import org.usfirst.frc.team3473.robot.command.auton.MoveTime;
import org.usfirst.frc.team3473.robot.command.auton.StayStraightGyro;
import org.usfirst.frc.team3473.robot.command.auton.TurnAnglePID;
import org.usfirst.frc.team3473.robot.command.instant.ActuateIntake;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *	CommandGroup for autonomous robot control.
 */
public class AutonTime extends CommandGroup {
	public enum Position {LEFT, CENTER, RIGHT}
	public enum Mode {BASELINE, SWITCH_ONLY, SWITCH_PRIORITY, SCALE_PRIORITY, EXPERIMENTAL, SWITCH_SIDE_ONLY, SCALE_SIDE_ONLY, CENTER}

	public static final double baselineTime = 1.4;
	public static final double sideSwitchLongTime = 2.1;
	public static final double sideSwitchShortTime = 0.2;
	public static final double frontSwitchTime = 1.6;
	public static final double centerSwitch1 = 0;
	public static final double centerSwitch2 = 0;
	public static final double centerSwitch3 = 0;
	
	public static final double scaleLongTime = 3.7;
	
	public static final double rightTurnTime = 0;
	
	public static final double switchHeightTime = 0;
	public static final double scaleHeightTime = 0;

	public AutonTime(Position robotPosition, Mode mode, Position switchPosition, Position scalePosition) {
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
		addSequential(new MoveTime(baselineTime));
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
				addSequential(new MoveTime(-100));
				addParallel(new ElevateIntakeToTime(-0.5, 100));
				addSequential(new TurnAnglePID(-85));
				addSequential(new MoveTime(1300));
				addSequential(new TurnAnglePID(50));
				addSequential(new MoveTime(260));
				addSequential(new TurnAnglePID(53));
				addParallel(new MoveRollersAuto(1, 2.0));
				addSequential(new MoveTime(0.3, 500));
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
		addSequential(new ActuateIntake());
		addSequential(new MoveTime(1100));
		if(switchPosition == Position.LEFT) {
			addSequential(new ElevateIntakeToTime(800));
			addSequential(new MoveRollersAuto(-1, 2.0));
		}
	}
	
	// Drives forward and drops the cube (assumes robot is positioned directly in front of switch).
	private void fromRight(Position switchPosition, Position scalePosition) {
		addSequential(new ActuateIntake());
		addSequential(new MoveTime(1100));
		if(switchPosition == Position.RIGHT) {
			addSequential(new ElevateIntakeToTime(800));
			addSequential(new MoveRollersAuto(-1, 2.0));
		}
	}
	
	// Drops a cube into either alliance plate
	private void switchFromCenter(Position switchPosition) {
		addSequential(new ActuateIntake());
		addSequential(new MoveTime(400));
		if(switchPosition == Position.LEFT) {
//			addSequential(new TurnAnglePID(-0.75, -46));
			addSequential(new MoveTime(800));
//			addSequential(new TurnAnglePID(0.75, 46));
			addSequential(new MoveTime(400));
			addSequential(new ElevateIntakeToTime(900));
			addSequential(new MoveRollersAuto(-1, 0.75));
		}
		else if(switchPosition == Position.RIGHT) {
//			addSequential(new TurnAnglePID(0.75, 46));
			addSequential(new MoveTime(800));
//			addSequential(new TurnAnglePID(-0.75, -46));
			addSequential(new MoveTime(400));
			addSequential(new ElevateIntakeToTime(900));
			addSequential(new MoveRollersAuto(-1, 0.75));
		}
	}
	
	// Move forward, turn 90 degrees, drop cube
	private void switchFromSide(Position switchPosition) {
		addSequential(new ActuateIntake());
		addSequential(new MoveTime(2.1));
		if(switchPosition == Position.LEFT)
			addSequential(new TurnAnglePID(90));
		else if(switchPosition == Position.RIGHT)
			addSequential(new TurnAnglePID(-90));
		addSequential(new MoveTime(0.2));
		addSequential(new ElevateIntakeToTime(1));
		addSequential(new MoveRollersAuto(-1, 1.0));
	}
	
	// Move forward, turn 90 degrees, lift elevator to scale, drop cube
	private void scaleFromSide(Position scalePosition) {
		addSequential(new ActuateIntake());
		addSequential(new StayStraightGyro(5200));
		if(scalePosition == Position.LEFT) {
//			addSequential(new TurnAnglePID(0.75, 48));
			addSequential(new MoveTime(-140));
		}
		else if(scalePosition == Position.RIGHT) {
//			addSequential(new TurnAnglePID(-0.75, -48));
			addSequential(new MoveTime(-140));
		}
		addSequential(new ElevateIntakeToTime(0.8, 1650));
		addSequential(new MoveTime(320));//was 200
		addSequential(new MoveRollersAuto(-1, 0.75));
		addSequential(new MoveTime(-320));//was also 200
		addSequential(new ElevateIntakeToTime(-0.8, 300));
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
