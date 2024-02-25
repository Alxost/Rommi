package org.rommi.gui;

import org.rommi.MoveListener;
import org.rommi.Row;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel {
    JPanel gamePanel;
    Color gamePanelColor = new Color(138,154,91);
    int maxNumRows = 12;
    public GamePanel(ArrayList<Row> playedRows, MoveListener moveListener){
        gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(maxNumRows,1));
        gamePanel.setBackground(gamePanelColor);
        for (Row row: playedRows){
            RowPanel rowPanel = new RowPanel(row, moveListener);
            gamePanel.add(rowPanel.getRowPanel());
        }
        RowPanel newRowPanel = new RowPanel(moveListener);
        gamePanel.add(newRowPanel.getRowPanel());
        for (int i= playedRows.size()+1; i < maxNumRows; i++){
            RowPanel emptyRowPanel = new RowPanel();
            gamePanel.add(emptyRowPanel.getRowPanel());
        }
    }
    public JPanel getGamePanel(){return gamePanel;}
}
