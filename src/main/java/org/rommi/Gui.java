package org.rommi;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Gui {
    RummyGame game;
    GameController gameController;
    MoveListener moveListener;
    JPanel playerPanel;
    JPanel fieldPanel;

    JFrame frame;
    int frame_width = 900;
    int frame_height = 900;
    int card_height = 65;
    int card_width = 65;
    int toolbar_height = 90;
    Gui(GameController gameController) {
        this.gameController = gameController;
        this.moveListener = new MoveListener();
        this.frame = new JFrame();
        this.frame.setLayout(new BorderLayout());
        this.frame.setSize(new Dimension(frame_width,frame_height));
    }
    public void drawGameState(){
        fieldPanel = createFieldPanel(gameController.getPlayedRows());
        playerPanel = createPlayerPanel(gameController.getActivePlayer());
        frame.getContentPane().removeAll();
        frame.repaint();
        frame.add(fieldPanel, BorderLayout.NORTH);
        frame.add(playerPanel, BorderLayout.SOUTH);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public JButton createCardButton(Card card, Row sourceRow){
        JButton  cardButton = new JButton(new ImageIcon(card.getCardImage()));
        cardButton.setBorderPainted(false);
        cardButton.setFocusPainted(false);
        cardButton.setContentAreaFilled(false);
        cardButton.addActionListener(e -> {moveListener.setSelectedCard(card);
        moveListener.setSourceRow(sourceRow);});
        cardButton.setPreferredSize(new Dimension(card_width,card_height));
        return cardButton;
    }
    public JPanel createRowPanel(Row row){
        JPanel rowPanel = new JPanel();
        if (row.getIsHand()) {
            rowPanel.setLayout(new GridLayout(2,7));
            rowPanel.setPreferredSize(new Dimension(frame_width, card_height*3));
        }
        for (Card card: row.getRowContent()){
            JButton cardButton = createCardButton(card, row);
            rowPanel.add(cardButton);
        }
        if (!row.getIsHand()){
            rowPanel.add(createAddButton(row));
        }
        return rowPanel;
    }
    public JPanel createPlayerPanel(Player activePlayer){
        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.PAGE_AXIS));
        JPanel rowPanel = createRowPanel(activePlayer.getHand());
        playerPanel.add(rowPanel);
        JPanel infoPanel = createPlayerInfoPanel(activePlayer);
        playerPanel.add(infoPanel);
        return playerPanel;
    }
    public JPanel createFieldPanel(ArrayList<Row> playedRows){
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.PAGE_AXIS));
        for (Row row : playedRows){
            JPanel rowPanel = createRowPanel(row);
            fieldPanel.add(rowPanel);
        }
        JButton newRowButton = createNewRowButton();
        fieldPanel.add(newRowButton);
        return fieldPanel;
    }
    public JPanel createPlayerInfoPanel(Player activePlayer){
        //JPanel infoPanel =  new JPanel(new GridLayout(1,2));
        JPanel infoPanel = new JPanel();
        JButton name = createNameButton(activePlayer);
        infoPanel.add(name);
        JButton drawCardButton = createDrawCardButton(activePlayer);
        infoPanel.add(drawCardButton);
        JButton nextButton = createNextButton();
        infoPanel.add(nextButton);
        return infoPanel;

    }
    public JButton createAddButton(Row row){
        JButton addCardButton = new JButton("add Card");
        addCardButton.addActionListener(e-> {moveListener.setTargetRow(row);
        gameController.executeMove(moveListener.makeMove());});
        addCardButton.setForeground(Color.CYAN);
        return addCardButton;
    }
    public JButton createNameButton(Player activePlayer){
        JButton nameButton = new JButton(activePlayer.getName());
        nameButton.setPreferredSize(new Dimension(150,toolbar_height));
        return nameButton;
    }
    public JButton createDrawCardButton(Player activePlayer){
        JButton drawCardButton = new JButton("Draw Card");
        drawCardButton.setForeground(Color.BLACK);
        drawCardButton.setPreferredSize(new Dimension(150,toolbar_height));
        drawCardButton.addActionListener(e -> gameController.drawCard(activePlayer));
        return drawCardButton;
    }
    public JButton createNewRowButton(){
        JButton newRowButton = new JButton("create new Row");
        newRowButton.setForeground(Color.MAGENTA);
        newRowButton.addActionListener(e -> gameController.createNewRow());
        return newRowButton;

    }
    public JButton createNextButton(){
        JButton nextButton = new JButton("next");
        nextButton.setForeground(Color.MAGENTA);
        nextButton.setPreferredSize(new Dimension(150,toolbar_height));
        nextButton.addActionListener(e -> gameController.nextPlayer());
        return nextButton;
    }

}
