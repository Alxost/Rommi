package org.rommi.gui;
import org.rommi.*;
import org.rommi.gameUtils.Card;
import org.rommi.gameUtils.Row;

import javax.swing.*;
import java.awt.*;

public class RowPanel {
    JPanel rowPanel;
    int maxNumCards  = 13;
    Color gamePanelColor;
    public RowPanel(Row row, MoveListener moveListener){
        rowPanel = new JPanel();
        rowPanel.setBackground(gamePanelColor);
        for (Card card: row.getRowContent()){
            CardButton cardButton = new CardButton();
            rowPanel.add(cardButton.getDefaultCardButton(card, moveListener));
        }
        AddCardButton addCardButton = new AddCardButton(){};
        rowPanel.add(addCardButton.getAddCardButtonRow(row, moveListener));
        for (int numCards = row.getSize()+1;numCards < maxNumCards; numCards++){
            CardButton cardButton = new CardButton();
            rowPanel.add(cardButton.getDummyCardButton());
        }
    }
    public RowPanel(MoveListener moveListener){
        rowPanel = new JPanel();
        rowPanel.setBackground(gamePanelColor);
        AddCardButton addCardButton  = new AddCardButton();
        rowPanel.add(addCardButton.getAddCardButtonDefault(moveListener));
        for (int i=1;i < maxNumCards; i++){
            CardButton cardButton  = new CardButton();
            rowPanel.add(cardButton.getDummyCardButton());
        }
    }
    public RowPanel(){
        rowPanel = new JPanel();
        rowPanel.setBackground(gamePanelColor);
        for (int i=0;i < maxNumCards; i++){
            CardButton emptyCardButton  = new CardButton();
            rowPanel.add(emptyCardButton.getDummyCardButton());
        }
    }
    public JPanel getRowPanel(){return rowPanel;}

}
