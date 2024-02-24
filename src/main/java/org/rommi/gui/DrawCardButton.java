package org.rommi.gui;

import org.rommi.GameController;

import javax.swing.*;
import java.awt.*;

public class DrawCardButton {
    JButton drawCardButton;
    Color textColorButton = new Color(229,203,206);
    Color toolbarButtonColor = new Color(128,0,0);
    public DrawCardButton(GameController gameController){
        drawCardButton = new JButton("Draw Card");
        drawCardButton.setBackground(toolbarButtonColor);
        drawCardButton.setForeground(textColorButton);
        drawCardButton.setOpaque(true);
        drawCardButton.setBorderPainted(false);
        drawCardButton.addActionListener(e->gameController.drawCards(1,gameController.getActivePlayer()));
    }
    public JButton getDrawCardButton(){
        return drawCardButton;
    }
}
