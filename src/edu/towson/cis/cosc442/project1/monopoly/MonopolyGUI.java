package edu.towson.cis.cosc442.project1.monopoly;

public interface MonopolyGUI {
	/**
	 * Enables or activates the End Turn button for the specified player.
	 * @param playerIndex The index of the player for whom to enable the End Turn button
	 */
	public void enableEndTurnBtn(int playerIndex);
	/**
	 * Activates the user interface elements to indicate it is the specified player's turn.
	 * @param playerIndex The index of the player whose turn is being enabled
	 */
	public void enablePlayerTurn(int playerIndex);
	/**
	 * Enables the Purchase button for the specified player, allowing them to buy properties.
	 * @param playerIndex The index of the player to enable the Purchase button for
	 */
	public void enablePurchaseBtn(int playerIndex);
	/**
	 * Retrieves the most recent dice roll values as an integer array.
	 * @return An array of integers representing the values rolled on each die
	 */
	public int[] getDiceRoll();
    /**
     * Checks if the Draw Card button is currently enabled in the user interface.
     * @return true if the Draw Card button is enabled; false otherwise
     */
    public boolean isDrawCardButtonEnabled();
    /**
     * Determines whether the End Turn button is currently enabled.
     * @return true if the End Turn button is enabled; false otherwise
     */
    public boolean isEndTurnButtonEnabled();
	/**
	 * Checks if the Get Out Of Jail button is enabled for the current player.
	 * @return true if the Get Out Of Jail button is enabled; false otherwise
	 */
	public boolean isGetOutOfJailButtonEnabled();
    /**
     * Checks if the Trade button is enabled for the specified player index.
     * @param i The index of the player whose Trade button state is queried
     * @return true if the Trade button is enabled for that player; false otherwise
     */
    public boolean isTradeButtonEnabled(int i);
	/**
	 * Animates moving a player from one board position to another.
	 * @param index The index of the player to move
	 * @param from The starting position index on the board
	 * @param to The destination position index on the board
	 */
	public void movePlayer(int index, int from, int to);
    /**
     * Opens a dialog window allowing the player to respond to a trade deal proposal.
     * @param deal The trade deal that the player must respond to
     * @return A RespondDialog instance for interacting with the trade response
     */
    public RespondDialog openRespondDialog(TradeDeal deal);
    /**
     * Opens a dialog to initiate a trade between players.
     * @return A TradeDialog instance for setting up trade offers
     */
    public TradeDialog openTradeDialog();
    /**
     * Sets whether the option to buy a house is enabled in the user interface.
     * @param b true to enable buying houses; false to disable
     */
    public void setBuyHouseEnabled(boolean b);
    /**
     * Enables or disables the Draw Card button in the interface.
     * @param b true to enable drawing cards; false to disable
     */
    public void setDrawCardEnabled(boolean b);
    /**
     * Enables or disables the End Turn button.
     * @param enabled true to enable; false to disable the End Turn button
     */
    public void setEndTurnEnabled(boolean enabled);
    /**
     * Sets the enabled state of the Get Out Of Jail button.
     * @param b true to enable; false to disable the Get Out Of Jail option
     */
    public void setGetOutOfJailEnabled(boolean b);
    /**
     * Enables or disables the Purchase Property button in the interface.
     * @param enabled true to enable; false to disable the Purchase Property button
     */
    public void setPurchasePropertyEnabled(boolean enabled);
    /**
     * Sets the enabled state of the Roll Dice button.
     * @param b true to enable; false to disable rolling dice
     */
    public void setRollDiceEnabled(boolean b);
    /**
     * Enables or disables the Trade button for a specific player.
     * @param index The index of the player
     * @param b true to enable; false to disable the Trade button for the player
     */
    public void setTradeEnabled(int index, boolean b);
    /**
     * Displays a dialog allowing the specified player to buy houses.
     * @param currentPlayer The player who is eligible to buy houses
     */
    public void showBuyHouseDialog(Player currentPlayer);
    /**
     * Displays a message string to the player through the user interface.
     * @param string The message text to display
     */
    public void showMessage(String string);
	/**
	 * Displays the utility dice roll and returns its value used in certain game scenarios.
	 * @return The integer value of the utility dice roll shown
	 */
	public int showUtilDiceRoll();
	/**
	 * Starts the Monopoly game, initializing all necessary components and UI elements.
	 */
	public void startGame();
	/**
	 * Refreshes or updates the user interface to reflect the current game state.
	 */
	public void update();
}
