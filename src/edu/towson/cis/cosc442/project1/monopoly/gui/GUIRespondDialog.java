package edu.towson.cis.cosc442.project1.monopoly.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import edu.towson.cis.cosc442.project1.monopoly.RespondDialog;
import edu.towson.cis.cosc442.project1.monopoly.TradeDeal;


public class GUIRespondDialog extends JDialog implements RespondDialog {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean response;
    JTextArea txtMessage = new JTextArea();
    
    /**
     * Constructs a modal dialog with Yes and No buttons and a message area to capture user responses.
     */
    public GUIRespondDialog() {
        JButton btnYes = new JButton("Yes");
        JButton btnNo = new JButton("No");
        txtMessage.setPreferredSize(new Dimension(300, 200));
        txtMessage.setEditable(false);
        txtMessage.setLineWrap(true);
        
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(txtMessage, BorderLayout.CENTER);
        JPanel pnlButtons = new JPanel();
        pnlButtons.add(btnYes);
        pnlButtons.add(btnNo);
        contentPane.add(pnlButtons, BorderLayout.SOUTH);
        
        btnYes.addActionListener(new ActionListener(){
            @SuppressWarnings("deprecation")
			/**
			 * Handles the action event by setting the response to false and hiding the dialog when the No button is clicked.
			 * @param e the action event triggered by clicking the No button
			 */
			public void actionPerformed(ActionEvent e) {
                response = true;
                hide();
            }
        });

        btnNo.addActionListener(new ActionListener(){
            @SuppressWarnings("deprecation")
			/**
			 * Handles the action event by setting the response to false and hiding the dialog when the No button is clicked.
			 * @param e the action event triggered by clicking the No button
			 */
			public void actionPerformed(ActionEvent e) {
                response = false;
                hide();
            }
        });
    
        setModal(true);
        pack();
    }

    /**
     * Returns the boolean response indicating whether the user clicked Yes (true) or No (false).
     * @return the user's response as a boolean value
     */
    public boolean getResponse() {
        return response;
    }
    
    /**
     * Sets the dialog message text based on the supplied trade deal's message.
     * @param deal the TradeDeal object containing the message to display
     */
    public void setDeal(TradeDeal deal) {
        txtMessage.setText(deal.makeMessage());
    }

}
