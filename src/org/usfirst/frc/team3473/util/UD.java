package org.usfirst.frc.team3473.util;

/**
 * A mutipurpose enum that represents up or down
 */
public enum UD {
	UP, DOWN;

	/**
	 * Returns the opposite side of the given UD
	 * 
	 * @param ud The UD to return the opposite of
	 * @return The opposite of ud
	 */
	public static UD oppositeOf(UD ud) {
		if(ud == UP)
			return DOWN;
		else
			return UP;
	}
}
