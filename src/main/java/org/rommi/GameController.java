package org.rommi;
import java.util.ArrayList;

public class GameController {
    private final RummyGame rummyGame;
    private final PlayerConfig playerConfig;
    private final RommiRobot bot;
    private int activePlayerIndex = 1;
    Gui2 gui;


    GameController(){
        playerConfig = new PlayerConfig(5);
        rummyGame = new RummyGame(playerConfig);
        bot  = new RommiRobot(this, rummyGame);
        drawStartingHands(rummyGame);
        createRandomRow();
        createRandomRow();
        this.gui = new Gui2(this);
    }

    public void drawStartingHands(RummyGame rummyGame){
        for (Player player : rummyGame.getPlayerList()){
            drawStartingHand(player, rummyGame.getCardDeck());
        }
    }
    public void drawStartingHand(Player player, CardDeck cardDeck){
        player.addCards(cardDeck.drawCards(14));
    }
    public Player getActivePlayer(){
        return rummyGame.getPlayerByIndex(activePlayerIndex-1);
    }
    public void nextPlayer(){
        if (activePlayerIndex == playerConfig.getNumPlayers()){
            activePlayerIndex = 1;
        }
        else{
            activePlayerIndex++;
        }
        gui.update(this);
    }
    public void executeMove(Move move){
        move.sourceRow.removeCard(move.card);
        move.targetRow.addCard(move.card);
        gui.update(this);
    }
    public ArrayList<Row> getPlayedRows(){
        return rummyGame.getPlayedRows();
    }

    public void createNewRow(){
        Row newRow = new Row(false);
        rummyGame.addRow(newRow);
        gui.update(this);
    }
    public void drawCard(Player player){
        player.addCard(rummyGame.getCardDeck().drawCard());
        nextPlayer();
        gui.update(this);
    }
    public void createRandomRow(){
        Row newRow = new Row(false);
        newRow.addCards(rummyGame.getCardDeck().drawCards(4));
        rummyGame.addRow(newRow);
    }
    public void botMove(){
        bot.move();
        gui.update(this);
    }
}
