package org.rommi.gui;

import org.rommi.GameController;
import org.rommi.MoveListener;
import org.rommi.Row;

import javax.swing.*;
import java.awt.*;

public class AddCardButton {
    JButton addCardButton;
    Color toolBarButtonColor = new Color(128,0,0);
    Color textColorButton = new Color(229,203,206);
    Dimension cardButtonDim = new Dimension(40,40);
    public AddCardButton(Row targetRow, MoveListener moveListener){
        addCardButton = new JButton("+");
        addCardButton.setBackground(toolBarButtonColor);
        addCardButton.setForeground(textColorButton);
        addCardButton.setPreferredSize(cardButtonDim);
        addCardButton.setMargin(new Insets(0, 0, 0, 0));
        addCardButton.addActionListener(e -> {moveListener.setTargetRow(targetRow);
        moveListener.makeMove();});

    }
    public AddCardButton(MoveListener moveListener){
        addCardButton = new JButton("+");
        addCardButton.setBackground(toolBarButtonColor);
        addCardButton.setForeground(textColorButton);
        addCardButton.setPreferredSize(cardButtonDim);
        addCardButton.setMargin(new Insets(0, 0, 0, 0));
        addCardButton.addActionListener(e -> {moveListener.createNewRow();});
    }

    public JButton getAddCardButton() {
        return addCardButton;
    }
}
