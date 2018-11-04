package org.usfirst.frc.team3473.util;

/**
 * TODO: Write documentation
 */
public class Util {
	/**
	 * Converts a char from the game data into the corresponding LR enum. Throws
	 * an IllegalArgumentException if the char is invalid.
	 * 
	 * @param letter The char from game data ('L' or 'R')
	 * @return The corresponding LR enum
	 */
	public static LR getPositionFromChar(char letter) {
		if(letter == 'L')
			return LR.LEFT;
		else if(letter == 'R')
			return LR.RIGHT;
		throw new IllegalArgumentException("Invalid character");
	}
}
