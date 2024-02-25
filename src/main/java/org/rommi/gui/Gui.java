package org.rommi.gui;
import org.rommi.GameController;
import org.rommi.MoveListener;

import javax.swing.*;
import java.awt.*;

public class Gui {
    GameController gameController;
    int boardHeight = 800;
    int boardWidth = 1200;

    JFrame frame = new JFrame("Rommi");
    GamePanel gamePanel;
    PlayerPanel playerPanel;
    ToolbarPanel toolbarPanel;
    MoveListener moveListener;


    public Gui(GameController gameController){
        try {
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.gameController = gameController;
        this.moveListener = new MoveListener(gameController);
    }
    public void createGui(){
        this.toolbarPanel = new ToolbarPanel(gameController);
        this.playerPanel = new PlayerPanel();
        this.gamePanel = new GamePanel(gameController.getPlayedRows(), moveListener);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gamePanel.getGamePanel(), BorderLayout.NORTH);
        frame.add(playerPanel.getPlayerPanel(gameController.getActivePlayer(), moveListener), BorderLayout.CENTER);
        frame.add(toolbarPanel.getToolbarPanel(), BorderLayout.SOUTH);
        //frame.pack();
        frame.setSize(boardWidth, boardHeight);
    }
    public void update(){
        frame.getContentPane().removeAll();
        if(gameController.getActivePlayer().getNumCards() == 0){
            frame.add(new JFormattedTextField("Game Over"));
            frame.revalidate();
            frame.repaint();
        }
        toolbarPanel = new ToolbarPanel(gameController);
        playerPanel = new PlayerPanel();
        gamePanel = new GamePanel(gameController.getPlayedRows(), moveListener);
        frame.add(gamePanel.getGamePanel(), BorderLayout.NORTH);
        frame.add(playerPanel.getPlayerPanel(gameController.getActivePlayer(), moveListener), BorderLayout.CENTER);
        frame.add(toolbarPanel.getToolbarPanel(), BorderLayout.SOUTH);
        frame.revalidate();
        frame.repaint();
    }
    public MoveListener getMoveListener(){
        return moveListener;
    }
}
