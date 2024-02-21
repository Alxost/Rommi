package org.rommi;

import javax.swing.*;
import java.awt.*;
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
    Color playerPanelColor = new Color(229,203,206);
    Color gamePanelColor = new Color(138,154,91);
    Color dummyCardColor = new Color(255,117,24);
    Color toolBarColor  = new Color(204,119,34);
    Color toolBarButtonColor = new Color(128,0,0);
    Color textColorButton = new Color(229,203,206);


    Gui2(GameController gameController){
        try {
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        toolbarPanel.setBackground(toolBarColor);
        if (activePlayer.getIsBot()){
            JPanel botPanel = new JPanel();
            botPanel.setBackground(toolBarColor);
            JButton nameButton = new JButton(activePlayer.getName());
            nameButton.setBackground(toolBarButtonColor);
            nameButton.setForeground(textColorButton);
            nameButton.setOpaque(true);
            nameButton.setBorderPainted(false);
            nameButton.addActionListener(e-> gameController.botMove());
            botPanel.add(nameButton);
            botPanel.add(nextButton);
            return botPanel;
        }
        return toolbarPanel;
    }
    public JButton createDrawButton(){
        JButton drawButton = new JButton("Draw Card");
        drawButton.setBackground(toolBarButtonColor);
        drawButton.setForeground(textColorButton);
        drawButton.setOpaque(true);
        drawButton.setBorderPainted(false);
        drawButton.addActionListener(e->gameController.drawCard(gameController.getActivePlayer()));
        return drawButton;
    }
    public JButton createNextButton(){
        JButton nextButton = new JButton("Next");
        nextButton.setBackground(toolBarButtonColor);
        nextButton.setForeground(textColorButton);
        nextButton.setOpaque(true);
        nextButton.setBorderPainted(false);
        nextButton.addActionListener(e->gameController.nextPlayer());
        return nextButton;
    }
    public JButton createNewRowButton(){
        JButton newRowButton  = new JButton("Create new Row");
        newRowButton.setBackground(toolBarButtonColor);
        newRowButton.setForeground(textColorButton);
        newRowButton.setOpaque(true);
        newRowButton.setBorderPainted(false);
        newRowButton.addActionListener(e->{gameController.createNewRow();});
        return newRowButton;
    }
    public JButton createCardButton(Card card, Row sourceRow) {
        JButton cardButton = new JButton(new ImageIcon(card.getCardImage()));
        cardButton.setPreferredSize(new Dimension(cardWidth,cardHeight));
        cardButton.setBorderPainted(false);
        cardButton.setFocusPainted(false);
        cardButton.setContentAreaFilled(false);
        cardButton.setOpaque(false);
        cardButton.addActionListener(e -> {
            moveListener.setSelectedCard(card);
            moveListener.setSourceRow(sourceRow);
        });
        //cardButton.setPreferredSize(new Dimension(192,192));
        return cardButton;
    }
    public JButton createCardDummy() {
        JButton cardButton = new JButton();
        cardButton.setBackground(dummyCardColor);
        cardButton.setForeground(Color.black);
        cardButton.setOpaque(true);
        cardButton.setPreferredSize(new Dimension(cardWidth,cardHeight));
        cardButton.setMargin(new Insets(0, 0, 0, 0));
        cardButton.setBorderPainted(false);
        //cardButton.setFont(new Font("Arial", Font.PLAIN, 12));
        return cardButton;
    }
    public JPanel createDummyRowPanel(){
        JPanel dummyRowPanel = new JPanel();
        for (int i = 0; i< 13; i++){
            JButton dummyCard = createCardDummy();
            dummyRowPanel.add(dummyCard);
        }
        return dummyRowPanel;
    }
    public JButton createAddCardButton(Row targetRow){
        JButton addCardButton= new JButton("+");
        addCardButton.setPreferredSize(new Dimension(cardWidth,cardHeight));
        addCardButton.setMargin(new Insets(0, 0, 0, 0));
        addCardButton.setBackground(toolBarButtonColor);
        addCardButton.setForeground(textColorButton);
        addCardButton.setOpaque(true);
        addCardButton.setBorderPainted(false);
        //addCardButton.setMargin(new Insets(0, 0, 0, 0));
        addCardButton.addActionListener(e->{
            moveListener.setTargetRow(targetRow);
            gameController.executeMove(moveListener.makeMove());});
        return addCardButton;
    }
    public JPanel createPlayerPanel(Player activePlayer){
        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new FlowLayout());
        playerPanel.setBackground( playerPanelColor);
        for (Card card: activePlayer.getHand().getRowContent()){
            JButton cardButton;
            if(activePlayer.getIsBot()){
                //cardButton = createCardDummy();
                cardButton = createCardButton(card, activePlayer.getHand());
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
        int maxNumCards  = 13;
        JPanel gamePanel = new JPanel();
        //gamePanel.setSize(new Dimension(boardWidth, boardHeight/3));
        gamePanel.setLayout(new GridLayout(maxNumRows,1));
        gamePanel.setBackground(gamePanelColor);
        for (Row row: playedRows){
            JPanel rowPanel = new JPanel();
            rowPanel.setBackground(gamePanelColor);
            for (Card card: row.getRowContent()){
                rowPanel.add(createCardButton(card, row));
            }
            rowPanel.add(createAddCardButton(row));
            for (int numCards = row.getSize()+1;numCards < maxNumCards; numCards++){
                rowPanel.add(createCardDummy());
            }
            gamePanel.add(rowPanel);
        }
        for (int i= playedRows.size(); i < maxNumRows; i++){
            JPanel dummyRowPanel = createDummyRowPanel();
            dummyRowPanel.setBackground(gamePanelColor);
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
        frame.getContentPane().removeAll();
        if(gameController.getActivePlayer().getNumCards() == 0){
            frame.add(new JFormattedTextField("Game Over"));
            frame.revalidate();
            frame.repaint();
        }
        playerPanel = createPlayerPanel(gameController.getActivePlayer());
        gamePanel = createGamePanel(gameController.getPlayedRows());
        toolbarPanel = createToolbarPanel(gameController.getActivePlayer());
        frame.add(gamePanel, BorderLayout.NORTH);
        frame.add(playerPanel, BorderLayout.CENTER);
        frame.add(toolbarPanel, BorderLayout.SOUTH);
        frame.revalidate();
        frame.repaint();
    }
}
