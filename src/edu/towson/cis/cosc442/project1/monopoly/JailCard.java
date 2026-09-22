package edu.towson.cis.cosc442.project1.monopoly;


public class JailCard extends Card {
    int type;
    
    /**
     * Constructs a JailCard with a specified card type.
     * @param cardType the integer representing the type of the jail card
     */
    public JailCard(int cardType) {
        type = cardType;
    }

    /**
     * Applies the jail card action by sending the current player to jail.
     */
    public void applyAction() {
        Player currentPlayer = GameMaster.instance().getCurrentPlayer();
		GameMaster.instance().getGameBoard().queryCell("Jail");
		GameMaster.instance().sendToJail(currentPlayer);
    }

    /**
     * Returns the type identifier of this jail card.
     * @return the integer type of the jail card
     */
    public int getCardType() {
        return type;
    }

    /**
     * Returns a descriptive label explaining the jail card effect.
     * @return a string describing the jail card's action
     */
    public String getLabel() {
        return "Go to Jail immediately without collecting" +
        		" $200 when passing the GO cell";
    }
}
