package org.rommi.gui;

import org.rommi.*;
import org.rommi.gameUtils.Card;

import javax.swing.*;
import java.awt.*;

public class CardButton {
    JButton cardButton;
    Color dummyCardColor = new Color(255,117,24);
    Color cardBackgroundColor = new Color(255,255,255);
    int cardWidth = 40;
    int cardHeight = 40;
    public CardButton(){
        cardButton = new JButton();
        cardButton.setPreferredSize(new Dimension(cardWidth,cardHeight));
        cardButton.setMargin(new Insets(0, 0, 0, 0));
        cardButton.setBorderPainted(false);
        cardButton.setFont(new Font("Arial", Font.PLAIN, 28));
        cardButton.setBackground(cardBackgroundColor);
    }


    public JButton getDefaultCardButton(Card card, MoveListener moveListener) {
        cardButton.setText(Integer.toString(card.getValue()));
        cardButton.setForeground(card.getColor());
        cardButton.addActionListener(e -> moveListener.setSelectedCard(card));
        return cardButton;
    }
    public JButton getDummyCardButton(){
        cardButton.setBackground(dummyCardColor);
        return cardButton;
    }
    public JButton getSelectedCardButton(Card card, MoveListener moveListener){
        cardButton.setText(Integer.toString(card.getValue()));
        cardButton.setForeground(card.getColor());
        cardButton.addActionListener(e -> moveListener.removeSelectedCard(card));
        return cardButton;
    }
}
