package org.usfirst.frc.team3473.util;

/**
 * A mutipurpose enum that represents front or back
 */
public enum FB {
	FRONT, BACK;

	/**
	 * Returns the opposite side of the given FB
	 * 
	 * @param fb The FB to return the opposite of
	 * @return The opposite of fb
	 */
	public static FB oppositeOf(FB fb) {
		if(fb == FRONT)
			return BACK;
		else
			return FRONT;
	}
}
