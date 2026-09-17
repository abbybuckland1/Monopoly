package edu.towson.cis.cosc442.project1.monopoly;

public abstract class Cell {
	private boolean available = true;
	private String name;
	protected Player theOwner;

	/**
	 * Returns the name of the cell.
	 * @return The name of the cell.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the player who owns this cell.
	 * @return The owner player of this cell, or null if unowned.
	 */
	public Player getTheOwner() {
		return theOwner;
	}
	
	/**
	 * Returns the price of the cell, defaulting to zero.
	 * @return The price of the cell as an integer.
	 */
	public int getPrice() {
		return 0;
	}

	/**
	 * Checks whether the cell is available for purchase or use.
	 * @return True if the cell is available; false otherwise.
	 */
	public boolean isAvailable() {
		return available;
	}
	
	/**
	 * Performs the action associated with landing on this cell.
	 */
	public abstract void playAction();

	/**
	 * Sets the availability status of the cell.
	 * @param available The new availability status to set.
	 */
	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	/**
	 * Sets the name of the cell.
	 * @param name The new name to set for the cell.
	 */
	void setName(String name) {
		this.name = name;
	}

	/**
	 * Assigns ownership of the cell to a specific player.
	 * @param owner The player to set as the owner.
	 */
	public void setTheOwner(Player owner) {
		this.theOwner = owner;
	}
    
    /**
     * Returns the string representation of the cell.
     * @return The name of the cell as its string representation.
     */
    public String toString() {
        return name;
    }
}
