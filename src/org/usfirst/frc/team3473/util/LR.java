package org.usfirst.frc.team3473.util;

/**
 * A mutipurpose enum that represents left or right
 */
public enum LR {
	LEFT, RIGHT;

	/**
	 * Returns the opposite side of the given LR
	 * 
	 * @param lr The LR to return the opposite of
	 * @return The opposite of lr
	 */
	public static LR oppositeOf(LR lr) {
		if(lr == LEFT)
			return RIGHT;
		else
			return LEFT;
	}
}
