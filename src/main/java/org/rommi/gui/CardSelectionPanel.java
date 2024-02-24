package org.rommi.gui;

import org.rommi.Card;
import org.rommi.GameController;
import org.rommi.MoveListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CardSelectionPanel {
    JPanel cardSelectionPanel;
    Color cardSelectionPanelColor = new Color(229,215,206);
    public CardSelectionPanel(MoveListener moveListener){
        this.cardSelectionPanel = new JPanel();
        cardSelectionPanel.setBackground(cardSelectionPanelColor);
        for (Card card : moveListener.getSelectedCards()) {
            CardButton cardButton = new CardButton(card, moveListener);
            cardSelectionPanel.add(cardButton.getCardButton());}
    }
    public JPanel getCardSelectionPanel(){
        return cardSelectionPanel;
    }
}
