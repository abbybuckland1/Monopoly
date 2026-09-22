package edu.towson.cis.cosc442.project1.monopoly;

public class MoneyCard extends Card {
    private int amount;
    private int cardType;
    
    private String label;
    
    /**
     * Constructs a MoneyCard with the specified label, monetary amount, and card type.
     * @param label the description label of the card
     * @param amount the monetary value associated with the card
     * @param cardType the type identifier of the card
     */
    public MoneyCard(String label, int amount, int cardType){
        this.label = label;
        this.amount = amount;
        this.cardType = cardType;
    }

    /**
     * Applies the monetary action of this card to the current player, adjusting their money balance accordingly.
     */
    public void applyAction() {
        Player currentPlayer = GameMaster.instance().getCurrentPlayer();
		currentPlayer.setMoney(currentPlayer.getMoney() + amount);
    }

    /**
     * Returns the type identifier of this card.
     * @return the integer representing the card's type
     */
    public int getCardType() {
        return cardType;
    }

    /**
     * Retrieves the label or description of this card.
     * @return the string label of the card
     */
    public String getLabel() {
        return label;
    }
}
