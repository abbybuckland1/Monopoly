package edu.towson.cis.cosc442.project1.monopoly;

public class CardCell extends Cell {
    private int type;
    
    /**
     * Constructs a CardCell with the specified type and name.
     * @param type the integer type identifier of the card cell
     * @param name the name of the card cell
     */
    public CardCell(int type, String name) {
        setName(name);
        this.type = type;
    }
    
    /**
     * Executes the action associated with this CardCell, currently with no effect.
     */
    public void playAction() {
    }
    
    /**
     * Returns the integer type identifier of this CardCell.
     * @return the type of the card cell
     */
    public int getType() {
        return type;
    }
}
