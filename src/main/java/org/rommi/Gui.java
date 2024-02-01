package org.rommi;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Gui {
    RummyGame game;
    GameController gameController;
    MoveListener moveListener;
    JPanel playerPanel;
    JPanel fieldPanel;
    Gui(GameController gameController) {
        this.gameController = gameController;
        this.moveListener = new MoveListener();
    }
    public void drawGameState(){
        JFrame frame = new JFrame();
        frame.setSize(new Dimension(700,700));
        JPanel mainPanel = createMainPanel();
        frame.add(mainPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public JButton createCardButton(Card card, Row sourceRow){
        JButton cardButton = new JButton(Integer.toString(card.getValue()));
        cardButton.setForeground(card.getColor());
        cardButton.addActionListener(e -> {moveListener.setSelectedCard(card);
        moveListener.setSourceRow(sourceRow);});
        cardButton.setPreferredSize(new Dimension(30,40));
        return cardButton;
    }
    public JPanel createRowPanel(Row row){
        JPanel rowPanel = new JPanel();
        if (row.getIsHand()) {
            //rowPanel.setLayout(new BoxLayout(rowPanel, BoxLayout.LINE_AXIS));
            rowPanel.setLayout(new FlowLayout());
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
        JPanel playerPanel = new JPanel(new GridLayout(2,1));
        playerPanel.setPreferredSize(new Dimension(500,10));
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
    public JPanel createMainPanel(){
        JPanel mainPanel = new JPanel(new GridLayout(2,1));
        playerPanel = createPlayerPanel(gameController.getActivePlayer());
        fieldPanel = createFieldPanel(gameController.getPlayedRows());
        mainPanel.add(fieldPanel);
        mainPanel.add(playerPanel);
        return mainPanel;
    }
    public JPanel createPlayerInfoPanel(Player activePlayer){
        //JPanel infoPanel =  new JPanel(new GridLayout(1,2));
        JPanel infoPanel = new JPanel(new FlowLayout());
        infoPanel.setPreferredSize(new Dimension(500,50));
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
        nameButton.setPreferredSize(new Dimension(90,30));
        return nameButton;
    }
    public JButton createDrawCardButton(Player activePlayer){
        JButton drawCardButton = new JButton("Draw Card");
        drawCardButton.setForeground(Color.BLACK);
        drawCardButton.setPreferredSize(new Dimension(90,30));
        drawCardButton.addActionListener(e -> gameController.drawCard(activePlayer));
        return drawCardButton;
    }
    public JButton createNewRowButton(){
        JButton newRowButton = new JButton("create new Row");
        newRowButton.setForeground(Color.MAGENTA);
        newRowButton.addActionListener(e -> {gameController.createNewRow();});
        return newRowButton;

    }
    public JButton createNextButton(){
        JButton nextButton = new JButton("next");
        nextButton.setForeground(Color.MAGENTA);
        nextButton.setPreferredSize(new Dimension(90,30));
        nextButton.addActionListener(e -> {gameController.nextPlayer();});
        return nextButton;
    }
    public void updatePlayerPanel(){
        playerPanel = createPlayerPanel(gameController.getActivePlayer());
        playerPanel.revalidate();
        playerPanel.repaint();
    }
    public void updateFieldPanel(){
        fieldPanel = createFieldPanel(gameController.getPlayedRows());
        fieldPanel.revalidate();
        fieldPanel.repaint();

    }
    public void updateMainPanel(){

    }
}
