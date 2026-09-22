package edu.towson.cis.cosc442.project1.monopoly.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.towson.cis.cosc442.project1.monopoly.GameMaster;

public class UtilDiceRoll extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("deprecation")
	/**
	 * Displays a modal dialog to roll dice and returns the resulting dice value.
	 * @return the total value resulting from the dice roll.
	 */
	public static int showDialog() {
		UtilDiceRoll dialog = new UtilDiceRoll();
		dialog.show();
		return dialog.diceValue;
	}
	JButton btnDice = new JButton("Roll the Dice!");
	private JButton btnOK = new JButton("OK");
	private int diceValue;
	private JLabel lblPrompt = new JLabel();

	/**
	 * Constructs a modal dialog for rolling dice to determine a utility bill.
	 */
	public UtilDiceRoll() {
		setModal(true);
		btnOK.setEnabled(false);
		lblPrompt.setText("Please roll the dice to determine your utility bill.");
		Container contentPane = getContentPane();
		JPanel pnlButtons = new JPanel();
		pnlButtons.add(btnDice);
		pnlButtons.add(btnOK);
		contentPane.setLayout(new BorderLayout());
		contentPane.add(lblPrompt, BorderLayout.CENTER);
		contentPane.add(pnlButtons, BorderLayout.SOUTH);
		btnDice.addActionListener(new ActionListener(){
			/**
			 * Handles the action event triggered by the OK button to close the dialog.
			 * @param arg0 the event object representing the OK action
			 */
			public void actionPerformed(ActionEvent arg0) {
				rollDice();
			}
		});
		btnOK.addActionListener(new ActionListener(){
			/**
			 * Handles the action event triggered by the OK button to close the dialog.
			 * @param arg0 the event object representing the OK action
			 */
			public void actionPerformed(ActionEvent arg0) {
				okClicked();
			}
		});
		this.pack();
	}
	
	/**
	 * Closes and disposes of the dice rolling dialog window.
	 */
	public void okClicked(){
		this.dispose();
	}
	
	/**
	 * Rolls two dice using the GameMaster instance, updates the dialog with the roll result, and enables the OK button.
	 */
	public void rollDice() {
		int[] diceRoll = GameMaster.instance().rollDice();
		this.diceValue = diceRoll[0] + diceRoll[1];
		lblPrompt.setText("You rolled " + diceValue);
		btnDice.setEnabled(false);
		btnOK.setEnabled(true);
	}
}
