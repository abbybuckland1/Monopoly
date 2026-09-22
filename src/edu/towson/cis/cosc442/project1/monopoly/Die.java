package edu.towson.cis.cosc442.project1.monopoly;

public class Die {
	/**
	 * Executes getRoll.
	 * @return the result
	 */
	public int getRoll() {
		return (int)(Math.random() * 6) + 1;
	}
}
