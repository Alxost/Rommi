package org.rommi.gui;

import org.rommi.gameUtils.Card;
import org.rommi.MoveListener;

import javax.swing.*;
import java.awt.*;

public class CardSelectionPanel {
    JPanel cardSelectionPanel;
    Color cardSelectionPanelColor = new Color(229,215,206);
    public CardSelectionPanel(){
        this.cardSelectionPanel = new JPanel();
        cardSelectionPanel.setBackground(cardSelectionPanelColor);
    }
    public JPanel getCardSelectionPanel(MoveListener moveListener){
        for (Card card : moveListener.getSelectedCards()) {
            CardButton cardButton = new CardButton();
            cardSelectionPanel.add(cardButton.getSelectedCardButton(card, moveListener));}
        return cardSelectionPanel;
    }
}
