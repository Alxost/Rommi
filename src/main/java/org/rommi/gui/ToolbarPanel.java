package org.rommi.gui;

import org.rommi.GameController;

import javax.swing.*;
import java.awt.*;

public class ToolbarPanel {
    JPanel toolbarPanel;
    Color toolbarColor  = new Color(204,119,34);
    Color textColorButton = new Color(229,203,206);
    Color toolbarButtonColor = new Color(128,0,0);
    public ToolbarPanel(GameController gc){
        toolbarPanel  = new JPanel();
        DrawCardButton drawCardButton = new DrawCardButton(gc);
        NextPlayerButton nextPlayerButton = new NextPlayerButton(gc);
        NewRowButton newRowButton = new NewRowButton(gc);
        toolbarPanel.add(drawCardButton.getDrawCardButton());
        toolbarPanel.add(nextPlayerButton.getNextPlayerButton());
        toolbarPanel.add(newRowButton.getNewRowButton());
        toolbarPanel.setBackground(toolbarColor);
        if (gc.getActivePlayer().getIsBot()){
            toolbarPanel = new JPanel();
            toolbarPanel.setBackground(toolbarColor);
            JButton nameButton = new JButton(gc.getActivePlayer().getName());
            nameButton.setBackground(toolbarButtonColor);
            nameButton.setForeground(textColorButton);
            nameButton.setOpaque(true);
            nameButton.setBorderPainted(false);
            nameButton.addActionListener(e-> gc.botMove());
            toolbarPanel.add(nameButton);
            toolbarPanel.add(nextPlayerButton.getNextPlayerButton());
        }
    }

    public JPanel getToolbarPanel(){
        return toolbarPanel;
    }
}
