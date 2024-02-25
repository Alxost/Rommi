package org.rommi.gui;

import org.rommi.MoveListener;
import org.rommi.gameUtils.Row;

import javax.swing.*;
import java.awt.*;

public class AddCardButton {
    JButton addCardButton;
    Color toolBarButtonColor = new Color(128,0,0);
    Color textColorButton = new Color(229,203,206);
    Dimension cardButtonDim = new Dimension(40,40);
    public AddCardButton(){
        addCardButton = new JButton("+");
        addCardButton.setBackground(toolBarButtonColor);
        addCardButton.setForeground(textColorButton);
        addCardButton.setPreferredSize(cardButtonDim);
        addCardButton.setMargin(new Insets(0, 0, 0, 0));
    }

    public JButton getAddCardButtonRow(Row targetRow, MoveListener moveListener) {
        addCardButton.addActionListener(e -> {
            moveListener.setTargetRow(targetRow);
            moveListener.addCards();});
        return addCardButton;
    }
    public JButton getAddCardButtonDefault(MoveListener moveListener){
        addCardButton.addActionListener(e -> moveListener.createNewRow());
        return addCardButton;
    }
}
