package org.rommi.gui;

import org.rommi.Card;
import org.rommi.MoveListener;
import org.rommi.Player;

import javax.swing.*;
import java.awt.*;

public class PlayerPanel {
    JPanel playerPanel;
    CardSelectionPanel cardSelectionPanel;
    JPanel handPanel;
    JButton playerIcon;
    Color playerPanelColor = new Color(229,203,206);
    Color iconColor  = new Color(255,255,255);
    public PlayerPanel(){
        playerPanel  = new JPanel(new BorderLayout());
        playerIcon = new JButton();
        playerIcon.setOpaque(true);
        playerIcon.setBorderPainted(false);
        playerIcon.setBackground(iconColor);
        playerPanel.add(playerIcon, BorderLayout.WEST);
        cardSelectionPanel = new CardSelectionPanel();
    }
    public JPanel getPlayerPanel(Player activePlayer, MoveListener moveListener){
        handPanel = new JPanel();
        handPanel.setLayout(new FlowLayout());
        handPanel.setBackground( playerPanelColor);
        if(activePlayer.getIsBot()) {
            for (Card card : activePlayer.getHand().getRowContent()) {
                CardButton cardButton = new CardButton();
                handPanel.add(cardButton.getDummyCardButton());
            }
            playerPanel.add(handPanel, BorderLayout.CENTER);
            return playerPanel;
        }
        for (Card card: activePlayer.getHand().getRowContent()){
            CardButton cardButton  = new CardButton();
            handPanel.add(cardButton.getDefaultCardButton(card, moveListener));
        }
        playerPanel.add(handPanel, BorderLayout.CENTER);
        playerPanel.add(cardSelectionPanel.getCardSelectionPanel(moveListener), BorderLayout.SOUTH);
        return playerPanel;
    }
}
