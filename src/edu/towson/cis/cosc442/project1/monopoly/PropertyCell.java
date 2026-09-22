package edu.towson.cis.cosc442.project1.monopoly;

public class PropertyCell extends Cell {
	private String colorGroup;
	private int housePrice;
	private int numHouses;
	private int rent;
	private int sellPrice;

	/**
	 * Returns the color group of this property.
	 * @return The color group of the property as a String.
	 */
	public String getColorGroup() {
		return colorGroup;
	}

	/**
	 * Retrieves the cost to build a house on this property.
	 * @return The price of a house as an integer.
	 */
	public int getHousePrice() {
		return housePrice;
	}

	/**
	 * Gets the current number of houses built on this property.
	 * @return The number of houses on the property as an integer.
	 */
	public int getNumHouses() {
		return numHouses;
	}
    
    /**
     * Returns the selling price of this property.
     * @return The price for which the property can be sold as an integer.
     */
    public int getPrice() {
		return sellPrice;
	}

	/**
	 * Calculates and returns the rent amount owed for landing on this property, adjusted for monopolies and houses.
	 * @return The rent to charge as an integer.
	 */
	public int getRent() {
		int rentToCharge = rent;
		String [] monopolies = theOwner.getMonopolies();
		rentToCharge = calculateMonopoliesRent(rentToCharge, monopolies);
		if(numHouses > 0) {
			rentToCharge = rent * (numHouses + 1);
		}
		return rentToCharge;
	}

	/**
	 * Calculates adjusted rent if the property color group is part of the player's monopolies.
	 * @param rentToCharge The base rent amount before adjustment.
	 * @param monopolies An array of color groups that the owner monopolizes.
	 * @return The potentially doubled rent amount as an integer.
	 */
	private int calculateMonopoliesRent(int rentToCharge, String[] monopolies) {
		for(int i = 0; i < monopolies.length; i++) {
			if(monopolies[i].equals(colorGroup)) {
				rentToCharge = rent * 2;
			}
		}
		return rentToCharge;
	}

	/**
	 * Executes the action that occurs when a player lands on this property, including rent payment if owned by another player.
	 */
	public void playAction() {
		Player currentPlayer = null;
		if(!isAvailable()) {
			currentPlayer = GameMaster.instance().getCurrentPlayer();
			if(theOwner != currentPlayer) {
				currentPlayer.payRentTo(theOwner, getRent());
			}
		}
	}

	/**
	 * Sets the color group of this property.
	 * @param colorGroup The color group to assign to this property.
	 */
	public void setColorGroup(String colorGroup) {
		this.colorGroup = colorGroup;
	}

	/**
	 * Sets the cost to build a house on this property.
	 * @param housePrice The price of a house to set.
	 */
	public void setHousePrice(int housePrice) {
		this.housePrice = housePrice;
	}

	/**
	 * Sets the number of houses currently built on this property.
	 * @param numHouses The number of houses to set.
	 */
	public void setNumHouses(int numHouses) {
		this.numHouses = numHouses;
	}

	/**
	 * Sets the selling price of this property.
	 * @param sellPrice The price at which the property can be sold.
	 */
	public void setPrice(int sellPrice) {
		this.sellPrice = sellPrice;
	}

	/**
	 * Sets the base rent for this property.
	 * @param rent The base rent amount to set.
	 */
	public void setRent(int rent) {
		this.rent = rent;
	}
}
