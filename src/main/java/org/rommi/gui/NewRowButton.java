package org.rommi.gui;

import org.rommi.GameController;

import javax.swing.*;
import java.awt.*;

public class NewRowButton {
    JButton newRowButton;
    Color textColorButton = new Color(229,203,206);
    Color toolbarButtonColor = new Color(128,0,0);
    public NewRowButton(GameController gameController){
        newRowButton  = new JButton("Create new Row");
        newRowButton.setBackground(toolbarButtonColor);
        newRowButton.setForeground(textColorButton);
        newRowButton.setOpaque(true);
        newRowButton.setBorderPainted(false);
        newRowButton.addActionListener(e->{gameController.createNewRow();});
    }
    public JButton getNewRowButton(){
        return newRowButton;
    }
}
