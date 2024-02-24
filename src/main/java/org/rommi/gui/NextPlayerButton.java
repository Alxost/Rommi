package org.rommi.gui;

import org.rommi.GameController;

import javax.swing.*;
import java.awt.*;

public class NextPlayerButton {
    JButton nextPlayerButton;
    Color textColorButton = new Color(229,203,206);
    Color toolbarButtonColor = new Color(128,0,0);
    public NextPlayerButton(GameController gameController){
        nextPlayerButton = new JButton("Next");
        nextPlayerButton.setBackground(toolbarButtonColor);
        nextPlayerButton.setForeground(textColorButton);
        nextPlayerButton.setOpaque(true);
        nextPlayerButton.setBorderPainted(false);
        nextPlayerButton.addActionListener(e->{
            gameController.getMoveListener().resetSelectedCards();
            gameController.nextPlayer();
        });
    }
    public JButton getNextPlayerButton(){
        return nextPlayerButton;
    }
}
