package edu.towson.cis.cosc442.project1.monopoly;

import java.util.ArrayList;
import java.util.Iterator;


public class GameMaster {

	private static GameMaster gameMaster;
	static final public int MAX_PLAYER = 8;	
	private Die[] dice;
	private GameBoard gameBoard;
	private MonopolyGUI gui;
	private int initAmountOfMoney;
	private ArrayList<Player> players = new ArrayList<Player>();
	private int turn = 0;
	private int utilDiceRoll;
	private boolean testMode;

	/**
	 * Returns the singleton instance of the GameMaster class.
	 * @return The singleton GameMaster instance.
	 */
	public static GameMaster instance() {
		if(gameMaster == null) {
			gameMaster = new GameMaster();
		}
		return gameMaster;
	}

	/**
	 * Constructs a GameMaster object initializing the starting amount of money and dice.
	 */
	public GameMaster() {
		initAmountOfMoney = 1500;
		dice = new Die[]{new Die(), new Die()};
	}

    /**
     * Handles the event when the Buy House button is clicked by showing the buy house dialog for the current player.
     */
    public void btnBuyHouseClicked() {
        gui.showBuyHouseDialog(getCurrentPlayer());
    }

    /**
     * Processes drawing a Community Chest or Chance card depending on the current position and applies its action.
     * @return The drawn Card object.
     */
    public Card btnDrawCardClicked() {
        gui.setDrawCardEnabled(false);
        CardCell cell = (CardCell)getCurrentPlayer().getPosition();
        Card card = null;
        if(cell.getType() == Card.TYPE_CC) {
            card = getGameBoard().drawCCCard();
            card.applyAction();
        } else {
            card = getGameBoard().drawChanceCard();
            card.applyAction();
        }
        gui.setEndTurnEnabled(true);
        return card;
    }

    /**
     * Handles the end of the current player's turn, disabling/enabling appropriate buttons and updating the GUI accordingly.
     */
    public void btnEndTurnClicked() {
		setAllButtonEnabled(false);
		getCurrentPlayer().getPosition().playAction();
		if(getCurrentPlayer().isBankrupt()) {
			gui.setBuyHouseEnabled(false);
			gui.setDrawCardEnabled(false);
			gui.setEndTurnEnabled(false);
			gui.setGetOutOfJailEnabled(false);
			gui.setPurchasePropertyEnabled(false);
			gui.setRollDiceEnabled(false);
			gui.setTradeEnabled(getCurrentPlayerIndex(),false);
			updateGUI();
		}
		else {
			switchTurn();
			updateGUI();
		}
    }

    /**
     * Handles the get out of jail event for the current player and updates button states based on bankrupt or jail status.
     */
    public void btnGetOutOfJailClicked() {
		getCurrentPlayer().getOutOfJail();
		if(getCurrentPlayer().isBankrupt()) {
			gui.setBuyHouseEnabled(false);
			gui.setDrawCardEnabled(false);
			gui.setEndTurnEnabled(false);
			gui.setGetOutOfJailEnabled(false);
			gui.setPurchasePropertyEnabled(false);
			gui.setRollDiceEnabled(false);
			gui.setTradeEnabled(getCurrentPlayerIndex(),false);
		}
		else {
			gui.setRollDiceEnabled(true);
			gui.setBuyHouseEnabled(getCurrentPlayer().canBuyHouse());
			gui.setGetOutOfJailEnabled(getCurrentPlayer().isInJail());
		}
    }

    /**
     * Processes the property purchase action for the current player and updates the GUI.
     */
    public void btnPurchasePropertyClicked() {
        Player player = getCurrentPlayer();
		player.purchase();
		gui.setPurchasePropertyEnabled(false);
		updateGUI();
    }
    
    /**
     * Rolls the dice, shows the roll result, moves the current player, and updates corresponding buttons.
     */
    public void btnRollDiceClicked() {
		int[] rolls = rollDice();
		if((rolls[0]+rolls[1]) > 0) {
			Player player = getCurrentPlayer();
			gui.setRollDiceEnabled(false);
			StringBuffer msg = new StringBuffer();
			msg.append(player.getName())
					.append(", you rolled ")
					.append(rolls[0])
					.append(" and ")
					.append(rolls[1]);
			gui.showMessage(msg.toString());
			movePlayer(player, rolls[0] + rolls[1]);
			gui.setBuyHouseEnabled(false);
		}
    }

    /**
     * Opens the trade dialog, processes the trade deal, and updates the GUI upon successful trade completion.
     */
    public void btnTradeClicked() {
        TradeDialog dialog = gui.openTradeDialog();
        TradeDeal deal = dialog.getTradeDeal();
        if(deal != null) {
            RespondDialog rDialog = gui.openRespondDialog(deal);
            if(rDialog.getResponse()) {
                completeTrade(deal);
                updateGUI();
            }
        }
    }

    /**
     * Completes the trade between the current player and the specified seller according to the trade deal details.
     * @param deal The TradeDeal object containing trade details.
     */
    public void completeTrade(TradeDeal deal) {
        Player seller = getPlayer(deal.getPlayerIndex());
        Cell property = gameBoard.queryCell(deal.getPropertyName());
        seller.sellProperty(property, deal.getAmount());
        getCurrentPlayer().buyProperty(property, deal.getAmount());
    }

    /**
     * Draws and returns a Community Chest card from the game board.
     * @return The drawn Community Chest Card.
     */
    public Card drawCCCard() {
        return gameBoard.drawCCCard();
    }

    /**
     * Draws and returns a Chance card from the game board.
     * @return The drawn Chance Card.
     */
    public Card drawChanceCard() {
        return gameBoard.drawChanceCard();
    }

	
	/**
	 * Returns the player whose turn it currently is.
	 * @return The current Player object.
	 */
	public Player getCurrentPlayer() {
		return getPlayer(turn);
	}
    
    /**
     * Returns the index of the current player whose turn it is.
     * @return Integer index of the current player.
     */
    public int getCurrentPlayerIndex() {
        return turn;
    }

	/**
	 * Returns the game board being used in the game.
	 * @return The GameBoard instance.
	 */
	public GameBoard getGameBoard() {
		return gameBoard;
	}

    /**
     * Returns the GUI instance managing the game interface.
     * @return The MonopolyGUI instance.
     */
    public MonopolyGUI getGUI() {
        return gui;
    }

	/**
	 * Returns the initial amount of money assigned to players at the start of the game.
	 * @return The initial starting money amount.
	 */
	public int getInitAmountOfMoney() {
		return initAmountOfMoney;
	}
	
	/**
	 * Returns the current number of players in the game.
	 * @return The number of players.
	 */
	public int getNumberOfPlayers() {
		return players.size();
	}

    /**
     * Returns the number of possible sellers excluding the current player.
     * @return The count of other players available as sellers.
     */
    public int getNumberOfSellers() {
        return players.size() - 1;
    }

	/**
	 * Returns the Player object at the specified index.
	 * @param index The index of the player to retrieve.
	 * @return The Player at the given index.
	 */
	public Player getPlayer(int index) {
		return (Player)players.get(index);
	}
	
	/**
	 * Returns the index of the specified player in the players list.
	 * @param player The Player object to find.
	 * @return The index of the player, or -1 if not found.
	 */
	public int getPlayerIndex(Player player) {
		return players.indexOf(player);
	}

    /**
     * Returns a list of players excluding the current player for use as possible sellers in trades.
     * @return ArrayList of Player objects excluding the current player.
     */
    public ArrayList<Player> getSellerList() {
        ArrayList<Player> sellers = new ArrayList<Player>();
        for (Iterator<Player> iter = players.iterator(); iter.hasNext();) {
            Player player = (Player) iter.next();
            if(player != getCurrentPlayer()) sellers.add(player);
        }
        return sellers;
    }

	/**
	 * Returns the index representing the current turn's player.
	 * @return The current turn index.
	 */
	public int getTurn() {
		return turn;
	}

	/**
	 * Returns the utility dice roll value stored in the GameMaster.
	 * @return The utility dice roll integer value.
	 */
	public int getUtilDiceRoll() {
		return this.utilDiceRoll;
	}

	/**
	 * Moves the player at the given index forward by the specified dice value, updating position and GUI.
	 * @param playerIndex The index of the player to move.
	 * @param diceValue The number of spaces to move the player.
	 */
	public void movePlayer(int playerIndex, int diceValue) {
		Player player = (Player)players.get(playerIndex);
		movePlayer(player, diceValue);
	}
	
	/**
	 * Moves the specified player forward by the dice value, handles passing Go, updates position and GUI.
	 * @param player The Player to move.
	 * @param diceValue The number of spaces to advance the player.
	 */
	public void movePlayer(Player player, int diceValue) {
		Cell currentPosition = player.getPosition();
		int positionIndex = gameBoard.queryCellIndex(currentPosition.getName());
		int newIndex = (positionIndex+diceValue)%gameBoard.getCellNumber();
		if(newIndex <= positionIndex || diceValue > gameBoard.getCellNumber()) {
			player.setMoney(player.getMoney() + 200);
		}
		player.setPosition(gameBoard.getCell(newIndex));
		gui.movePlayer(getPlayerIndex(player), positionIndex, newIndex);
		playerMoved(player);
		updateGUI();
	}

	/**
	 * Updates the GUI controls based on the player's new position after moving.
	 * @param player The Player who just moved.
	 */
	public void playerMoved(Player player) {
		Cell cell = player.getPosition();
		int playerIndex = getPlayerIndex(player);
		if(cell instanceof CardCell) {
		    gui.setDrawCardEnabled(true);
		} else{
			checkPropertyAvailableAndEnoughMoney(player, cell, playerIndex);	
			gui.enableEndTurnBtn(playerIndex);
		}
        gui.setTradeEnabled(turn, false);
	}

	/**
	 * Checks if a property is available and if the player has enough money to purchase it, enabling purchase button accordingly.
	 * @param player The Player attempting to purchase.
	 * @param cell The Cell property to check.
	 * @param playerIndex The index of the player for GUI enabling.
	 */
	private void checkPropertyAvailableAndEnoughMoney(Player player, Cell cell, int playerIndex) {
		if(cell.isAvailable()) {
			int price = cell.getPrice();
			if(price <= player.getMoney() && price > 0) {
				gui.enablePurchaseBtn(playerIndex);
			}
		}
	}

	/**
	 * Resets the game state by moving all players to the starting position and removing cards from the board.
	 */
	public void reset() {
		for(int i = 0; i < getNumberOfPlayers(); i++){
			Player player = (Player)players.get(i);
			player.setPosition(gameBoard.getCell(0));
		}
		if(gameBoard != null) gameBoard.removeCards();
		turn = 0;
	}
	
	/**
	 * Rolls the dice to generate dice values, using test mode values if enabled.
	 * @return An int array containing two dice roll results.
	 */
	public int[] rollDice() {
		if(testMode) {
			return gui.getDiceRoll();
		}
		else {
			return new int[]{
					dice[0].getRoll(),
					dice[1].getRoll()
			};
		}
	}
	
	/**
	 * Sends the specified player to the Jail position, marks them as in jail, and updates the GUI.
	 * @param player The Player to be sent to Jail.
	 */
	public void sendToJail(Player player) {
	    int oldPosition = gameBoard.queryCellIndex(getCurrentPlayer().getPosition().getName());
		player.setPosition(gameBoard.queryCell("Jail"));
		player.setInJail(true);
		int jailIndex = gameBoard.queryCellIndex("Jail");
		gui.movePlayer(
		        getPlayerIndex(player),
		        oldPosition,
		        jailIndex);
	}
    
	/**
	 * Enables or disables all main game action buttons in the GUI based on the given flag.
	 * @param enabled True to enable all buttons; false to disable.
	 */
	private void setAllButtonEnabled(boolean enabled) {
		gui.setRollDiceEnabled(enabled);
		gui.setPurchasePropertyEnabled(enabled);
		gui.setEndTurnEnabled(enabled);
        gui.setTradeEnabled(turn, enabled);
        gui.setBuyHouseEnabled(enabled);
        gui.setDrawCardEnabled(enabled);
        gui.setGetOutOfJailEnabled(enabled);
	}

	/**
	 * Sets the game board to be used for this game session.
	 * @param board The GameBoard object to set.
	 */
	public void setGameBoard(GameBoard board) {
		this.gameBoard = board;
	}
	
	/**
	 * Sets the GUI interface for the game.
	 * @param gui The MonopolyGUI instance to set.
	 */
	public void setGUI(MonopolyGUI gui) {
		this.gui = gui;
	}

	/**
	 * Sets the initial amount of money given to each player at the start of the game.
	 * @param money The amount of money to assign at game start.
	 */
	public void setInitAmountOfMoney(int money) {
		this.initAmountOfMoney = money;
	}

	/**
	 * Sets the number of players and initializes each player with the starting money.
	 * @param number The total number of players to initialize.
	 */
	public void setNumberOfPlayers(int number) {
		players.clear();
		for(int i =0;i<number;i++) {
			Player player = new Player();
			player.setMoney(initAmountOfMoney);
			players.add(player);
		}
	}

	/**
	 * Stores a utility dice roll value for use in the game.
	 * @param diceRoll The dice roll value to store.
	 */
	public void setUtilDiceRoll(int diceRoll) {
		this.utilDiceRoll = diceRoll;
	}
	
	/**
	 * Starts the game by triggering the GUI to initiate and enable the first player's turn.
	 */
	public void startGame() {
		gui.startGame();
		gui.enablePlayerTurn(0);
        gui.setTradeEnabled(0, true);
	}

	/**
	 * Moves to the next player's turn, updating GUI controls based on jail status and player capabilities.
	 */
	public void switchTurn() {
		turn = (turn + 1) % getNumberOfPlayers();
		if(!getCurrentPlayer().isInJail()) {
			gui.enablePlayerTurn(turn);
			gui.setBuyHouseEnabled(getCurrentPlayer().canBuyHouse());
            gui.setTradeEnabled(turn, true);
		}
		else {
			gui.setGetOutOfJailEnabled(true);
		}
	}
	
	/**
	 * Updates the GUI to reflect the current state of the game.
	 */
	public void updateGUI() {
		gui.update();
	}

	/**
	 * Displays a utility dice roll dialog and stores the result in the utility dice roll field.
	 */
	public void utilRollDice() {
		this.utilDiceRoll = gui.showUtilDiceRoll();
	}

	/**
	 * Sets whether the game is in test mode, affecting dice roll behavior.
	 * @param b True to enable test mode, false to disable.
	 */
	public void setTestMode(boolean b) {
		testMode = b;
	}
}
