package org.rommi.gui;
import org.rommi.*;

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
            CardButton cardButton = new CardButton(card, moveListener);
            rowPanel.add(cardButton.getCardButton());
        }
        AddCardButton addCardButton = new AddCardButton(row, moveListener){};
        rowPanel.add(addCardButton.getAddCardButton());
        for (int numCards = row.getSize()+1;numCards < maxNumCards; numCards++){
            CardButton cardButton = new CardButton();
            rowPanel.add(cardButton.getCardButton());
        }
    }
    public RowPanel(MoveListener moveListener){
        rowPanel = new JPanel();
        rowPanel.setBackground(gamePanelColor);
        AddCardButton addCardButton  = new AddCardButton(moveListener);
        rowPanel.add(addCardButton.getAddCardButton());
        for (int i=1;i < maxNumCards; i++){
            CardButton emptyCardButton  = new CardButton();
            rowPanel.add(emptyCardButton.getCardButton());
        }
    }
    public RowPanel(){
        rowPanel = new JPanel();
        rowPanel.setBackground(gamePanelColor);
        for (int i=0;i < maxNumCards; i++){
            CardButton emptyCardButton  = new CardButton();
            rowPanel.add(emptyCardButton.getCardButton());
        }
    }
    public JPanel getRowPanel(){return rowPanel;}

}
