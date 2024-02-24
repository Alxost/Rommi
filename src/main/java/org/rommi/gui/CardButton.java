package org.rommi.gui;

import org.rommi.*;

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
        cardButton.setBackground(dummyCardColor);
        cardButton.setForeground(Color.black);
        cardButton.setOpaque(true);
        cardButton.setPreferredSize(new Dimension(cardWidth,cardHeight));
        cardButton.setMargin(new Insets(0, 0, 0, 0));
        cardButton.setBorderPainted(false);
    }
    public CardButton(Card card, MoveListener moveListener){
        cardButton = new JButton(Integer.toString(card.getValue()));
        cardButton.setPreferredSize(new Dimension(cardWidth,cardHeight));
        cardButton.setForeground(card.getColor());
        cardButton.setBackground(cardBackgroundColor);
        cardButton.setMargin(new Insets(0, 0, 0, 0));
        cardButton.setFont(new Font("Arial", Font.PLAIN, 28));
        //cardButton.setBorderPainted(false);
        //cardButton.setFocusPainted(false);
        //cardButton.setContentAreaFilled(false);
        //cardButton.setOpaque(false);
        cardButton.addActionListener(e -> {
            moveListener.setSelectedCard(card);
            //moveListener.setSourceRow(sourceRow);
        });
    }

    public JButton getCardButton() {
        return cardButton;
    }
}
