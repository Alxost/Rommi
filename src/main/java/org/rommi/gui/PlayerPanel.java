package org.rommi.gui;

import org.rommi.Card;
import org.rommi.GameController;
import org.rommi.MoveListener;
import org.rommi.Player;

import javax.swing.*;
import java.awt.*;

public class PlayerPanel {
    JPanel playerPanel;
    Color playerPanelColor = new Color(229,203,206);
    Color iconColor  = new Color(255,255,255);
    public PlayerPanel(Player activePlayer, MoveListener moveListener){
        playerPanel  = new JPanel(new BorderLayout());
        JButton playerIcon = new JButton(activePlayer.getName());
        playerIcon.setOpaque(true);
        playerIcon.setBorderPainted(false);
        playerIcon.setBackground(iconColor);
        playerPanel.add(playerIcon, BorderLayout.WEST);
        JPanel handPanel = new JPanel();
        handPanel.setLayout(new FlowLayout());
        handPanel.setBackground( playerPanelColor);
        for (Card card: activePlayer.getHand().getRowContent()){
            CardButton cardButton;
            if(activePlayer.getIsBot()){
                cardButton = new CardButton();
            }
            else{
                cardButton = new CardButton(card, moveListener);
            }
            handPanel.add(cardButton.getCardButton());
        }
        playerPanel.add(handPanel, BorderLayout.CENTER);
        CardSelectionPanel cardSelectionPanel = new CardSelectionPanel(moveListener);
        playerPanel.add(cardSelectionPanel.getCardSelectionPanel(), BorderLayout.SOUTH);
    }
    public JPanel getPlayerPanel(){
        return playerPanel;
    }
}
