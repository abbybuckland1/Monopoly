package edu.towson.cis.cosc442.project1.monopoly;

public class GoCell extends Cell {
	/**
	 * Constructs a GoCell object initializing its name to "Go" and setting availability to false.
	 */
	public GoCell() {
		super.setName("Go");
		setAvailable(false);
	}

	/**
	 * Executes the action associated with landing on the Go cell, but currently performs no operation.
	 */
	public void playAction() {
	}
	
	/**
	 * Overrides the setName method but does not change the cell's name.
	 * @param name the new name to set for the cell
	 */
	void setName(String name) {
	}
}
