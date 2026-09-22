package edu.towson.cis.cosc442.project1.monopoly;

public abstract class Card {

    public static int TYPE_CHANCE = 1;
    public static int TYPE_CC = 2;

    /**
     * Returns the label or name of the card.
     * @return the label of the card as a String
     */
    public abstract String getLabel();
    /**
     * Executes the action associated with this card.
     */
    public abstract void applyAction();
    /**
     * Returns the type identifier of the card.
     * @return an integer representing the card type, e.g., TYPE_CHANCE or TYPE_CC
     */
    public abstract int getCardType();
}
