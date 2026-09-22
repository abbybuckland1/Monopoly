package edu.towson.cis.cosc442.project1.monopoly;

public class RailRoadCell extends Cell {
	static private int baseRent;
	static public String COLOR_GROUP = "RAILROAD";
	static private int price;

	/**
	 * Sets the base rent amount for all RailRoadCell instances.
	 * @param baseRent the base rent value to be set
	 */
	public static void setBaseRent(int baseRent) {
		RailRoadCell.baseRent = baseRent;
	}

	/**
	 * Sets the purchase price for all RailRoadCell instances.
	 * @param price the price value to be set
	 */
	public static void setPrice(int price) {
		RailRoadCell.price = price;
	}
	
	/**
	 * Retrieves the purchase price of the RailRoadCell.
	 * @return the current price of the RailRoadCell
	 */
	public int getPrice() {
		return RailRoadCell.price;
	}

	/**
	 * Calculates the rent owed based on the number of RailRoadCells owned by the owner.
	 * @return the calculated rent amount for this RailRoadCell
	 */
	public int getRent() {
		return RailRoadCell.baseRent * (int)Math.pow(2, theOwner.numberOfRR() - 1);
	}
	
	/**
	 * Executes the action when a player lands on this RailRoadCell, including rent payment if owned by another player.
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
}
