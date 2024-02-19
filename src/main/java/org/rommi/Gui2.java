package org.rommi;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Gui2 {
    GameController gameController;
    MoveListener moveListener;

    int cardWidth = 40;
    int cardHeight = 40;
    int boardHeight = 800;
    int boardWidth = 1200;

    JFrame frame = new JFrame("Rommi");
    JPanel gamePanel;
    JPanel playerPanel;
    JPanel toolbarPanel;


    Gui2(GameController gameController){
        this.gameController = gameController;
        toolbarPanel = createToolbarPanel(gameController.getActivePlayer());
        playerPanel = createPlayerPanel(gameController.getActivePlayer());
        gamePanel = createGamePanel(gameController.getPlayedRows());
        moveListener = new MoveListener();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gamePanel, BorderLayout.NORTH);
        frame.add(playerPanel, BorderLayout.CENTER);
        frame.add(toolbarPanel, BorderLayout.SOUTH);
        //frame.pack();
        frame.setSize(boardWidth, boardHeight);

    }
    public JPanel createToolbarPanel(Player activePlayer){
        JPanel toolbarPanel = new JPanel();
        JButton drawButton = createDrawButton();
        JButton nextButton = createNextButton();
        JButton newRowButton = createNewRowButton();
        toolbarPanel.add(drawButton);
        toolbarPanel.add(nextButton);
        toolbarPanel.add(newRowButton);
        if (activePlayer.getIsBot()){
            JPanel botPanel = new JPanel();
            botPanel.add(new JButton(activePlayer.getName()));
            return botPanel;
        }
        return toolbarPanel;
    }
    public JButton createDrawButton(){
        JButton drawButton = new JButton("Draw Card");
        drawButton.addActionListener(e->gameController.drawCard(gameController.getActivePlayer()));
        return drawButton;
    }
    public JButton createNextButton(){
        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(e->{gameController.nextPlayer(); update(gameController);});
        return nextButton;
    }
    public JButton createNewRowButton(){
        JButton newRowButton  = new JButton("Create new Row");
        newRowButton.addActionListener(e->{gameController.createNewRow();update(gameController);});
        return newRowButton;
    }
    public JButton createCardButton(Card card, Row sourceRow) {
        JButton cardButton = new JButton(new ImageIcon(card.getCardImage()));
        cardButton.setPreferredSize(new Dimension(cardWidth,cardHeight));
        cardButton.setBorderPainted(false);
        cardButton.setFocusPainted(false);
        cardButton.setContentAreaFilled(false);
        cardButton.addActionListener(e -> {
            moveListener.setSelectedCard(card);
            moveListener.setSourceRow(sourceRow);
        });
        //cardButton.setPreferredSize(new Dimension(192,192));
        return cardButton;
    }
    public JButton createCardDummy(Color color) {
        JButton cardButton = new JButton("#");
        //cardButton.setBackground(Color.gray);
        cardButton.setForeground(color);
        cardButton.setOpaque(true);
        cardButton.setPreferredSize(new Dimension(cardWidth,cardHeight));
        //cardButton.setPreferredSize(new Dimension(192,192));
        return cardButton;
    }
    public JPanel createDummyRowPanel(){
        JPanel dummyRowPanel = new JPanel();
        for (int i = 0; i< 13; i++){
            JButton dummyCard = createCardDummy(Color.GRAY);
            dummyRowPanel.add(dummyCard);
        }
        return dummyRowPanel;
    }
    public JButton createAddCardButton(Row targetRow){
        JButton addCardButton= new JButton("+");
        addCardButton.addActionListener(e->{moveListener.setTargetRow(targetRow);
            gameController.executeMove(moveListener.makeMove());});
        return addCardButton;
    }
    public JPanel createPlayerPanel(Player activePlayer){
        JPanel playerPanel = new JPanel();
        //playerPanel.setSize(new Dimension(boardWidth,boardWidth/5));
        //playerPanel.setLayout(new GridLayout(3,14));
        playerPanel.setLayout(new FlowLayout());
        for (Card card: activePlayer.getHand().getRowContent()){
            JButton cardButton;
            if(activePlayer.getIsBot()){
                cardButton = createCardDummy(Color.BLUE);
            }
            else{
                cardButton = createCardButton(card, activePlayer.getHand());
            }
            playerPanel.add(cardButton);
        }
        return playerPanel;
    }
    public JPanel createGamePanel(ArrayList<Row> playedRows){
        int maxNumRows = 12;
        int maxNumCards  =13;
        JPanel gamePanel = new JPanel();
        gamePanel.setSize(new Dimension(boardWidth, boardHeight/3));
        gamePanel.setLayout(new GridLayout(maxNumRows,1));
        for (Row row: playedRows){
            JPanel rowPanel = new JPanel();
            for (Card card: row.getRowContent()){
                rowPanel.add(createCardButton(card, row));
            }
            for (int numCards = row.getSize();numCards < maxNumCards; numCards++){
                rowPanel.add(createCardDummy(Color.GRAY));
            }
            rowPanel.add(createAddCardButton(row));
            gamePanel.add(rowPanel);
        }
        for (int i= playedRows.size(); i < maxNumRows; i++){
            JPanel dummyRowPanel = createDummyRowPanel();
            gamePanel.add(dummyRowPanel);
        }
        return gamePanel;
    }
    public void updateGamePanel(ArrayList<Row> playedRows){
        frame.remove(gamePanel);
        gamePanel = createGamePanel(playedRows);
        frame.add(gamePanel);
        frame.revalidate();
        frame.repaint();
    }
    public void updatePlayerPanel(Player activePlayer){
        frame.remove(playerPanel);
        playerPanel = createPlayerPanel(activePlayer);
        frame.add(playerPanel);
        frame.revalidate();
        frame.repaint();
    }
    public void update(GameController gameController){
        frame.remove(gamePanel);
        frame.remove(playerPanel);
        frame.remove(toolbarPanel);
        playerPanel = createPlayerPanel(gameController.getActivePlayer());
        gamePanel = createGamePanel(gameController.getPlayedRows());
        frame.add(gamePanel, BorderLayout.NORTH);
        frame.add(playerPanel, BorderLayout.CENTER);
        frame.add(toolbarPanel, BorderLayout.SOUTH);
        frame.revalidate();
        frame.repaint();
    }
}
