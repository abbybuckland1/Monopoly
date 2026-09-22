package edu.towson.cis.cosc442.project1.monopoly;

import java.util.ArrayList;
import java.util.Hashtable;

public class GameBoard {

	private ArrayList<Cell> cells = new ArrayList<Cell>();
    private ArrayList<Card> chanceCards = new ArrayList<Card>();
	//the key of colorGroups is the name of the color group.
	private Hashtable<String, Integer> colorGroups = new Hashtable<String, Integer>();
	private ArrayList<Card> communityChestCards = new ArrayList<Card>();
	/**
	 * Constructs a new GameBoard instance and initializes it with a 'Go' cell.
	 */
	public GameBoard() {
		Cell go = new GoCell();
		addCell(go);
	}

    /**
     * Adds a card to the appropriate deck based on its type.
     * @param card the card to add to the game board's decks
     */
    public void addCard(Card card) {
        if(card.getCardType() == Card.TYPE_CC) {
            communityChestCards.add(card);
        } else {
            chanceCards.add(card);
        }
    }
	
	/**
	 * Adds a generic cell to the game board's list of cells.
	 * @param cell the cell to be added to the board
	 */
	public void addCell(Cell cell) {
		cells.add(cell);
	}
	
	/**
	 * Adds a property cell to the board and updates the color group count accordingly.
	 * @param cell the property cell to add to the board
	 */
	public void addCell(PropertyCell cell) {
		String colorGroup = cell.getColorGroup();
		int propertyNumber = getPropertyNumberForColor(colorGroup);
		colorGroups.put(colorGroup, new Integer(propertyNumber + 1));
        cells.add(cell);
	}

    /**
     * Draws the top Community Chest card, removes it from the deck, then re-adds it to the bottom.
     * @return the Community Chest card drawn
     */
    public Card drawCCCard() {
        Card card = communityChestCards.get(0);
        communityChestCards.remove(0);
        addCard(card);
        return card;
    }

    /**
     * Draws the top Chance card, removes it from the deck, then re-adds it to the bottom.
     * @return the Chance card drawn
     */
    public Card drawChanceCard() {
        Card card = (Card)chanceCards.get(0);
        chanceCards.remove(0);
        addCard(card);
        return card;
    }

	/**
	 * Retrieves the cell at the specified index on the game board.
	 * @param newIndex the index of the cell to retrieve
	 * @return the cell at the given index
	 */
	public Cell getCell(int newIndex) {
		return (Cell)cells.get(newIndex);
	}
	
	/**
	 * Returns the total number of cells on the game board.
	 * @return the number of cells
	 */
	public int getCellNumber() {
		return cells.size();
	}
	
	/**
	 * Returns an array of property cells belonging to the specified color group (monopoly).
	 * @param color the color group name to retrieve properties for
	 * @return an array of property cells in the specified color group
	 */
	public PropertyCell[] getPropertiesInMonopoly(String color) {
		PropertyCell[] monopolyCells = 
			new PropertyCell[getPropertyNumberForColor(color)];
		int counter = 0;
		for (int i = 0; i < getCellNumber(); i++) {
			Cell c = getCell(i);
			if(c instanceof PropertyCell) {
				PropertyCell pc = (PropertyCell)c;
				if(pc.getColorGroup().equals(color)) {
					monopolyCells[counter] = pc;
					counter++;
				}
			}
		}
		return monopolyCells;
	}
	
	/**
	 * Returns the number of properties for a specified color group.
	 * @param name the name of the color group
	 * @return the count of properties in that color group
	 */
	public int getPropertyNumberForColor(String name) {
		Integer number = (Integer)colorGroups.get(name);
		if(number != null) {
			return number.intValue();
		}
		return 0;
	}

	/**
	 * Finds and returns the cell with the specified name.
	 * @param string the name of the cell to search for
	 * @return the cell with the matching name or null if not found
	 */
	public Cell queryCell(String string) {
		for(int i = 0; i < cells.size(); i++){
			Cell temp = (Cell)cells.get(i); 
			if(temp.getName().equals(string)) {
				return temp;
			}
		}
		return null;
	}
	
	/**
	 * Finds and returns the index of the cell with the specified name.
	 * @param string the name of the cell to search for
	 * @return the index of the matching cell or -1 if not found
	 */
	public int queryCellIndex(String string){
		for(int i = 0; i < cells.size(); i++){
			Cell temp = (Cell)cells.get(i); 
			if(temp.getName().equals(string)) {
				return i;
			}
		}
		return -1;
	}

    /**
     * Clears all Community Chest cards from the deck.
     */
    public void removeCards() {
        communityChestCards.clear();
    }
}
