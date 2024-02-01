package org.example;
import java.util.ArrayList;

public class GameController {
    private final RummyGame rummyGame;
    private final PlayerConfig playerConfig;
    private int activePlayerIndex = 1;
    Gui gui;


    GameController(){
        playerConfig = new PlayerConfig(5);
        rummyGame = new RummyGame(playerConfig);
        gui = new Gui(this);
        drawStartingHands(rummyGame);
        gui.drawGameState();
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
        gui.updatePlayerPanel();
    }
    public void executeMove(Move move){
        move.sourceRow.removeCard(move.card);
        move.targetRow.addCard(move.card);
        gui.updatePlayerPanel();
    }
    public ArrayList<Row> getPlayedRows(){
        return rummyGame.getPlayedRows();
    }

    public void createNewRow(){
        Row newRow = new Row(false);
        rummyGame.addRow(newRow);
        gui.updateFieldPanel();
    }
    public void drawCard(Player player){
        player.addCard(rummyGame.getCardDeck().drawCard());
        nextPlayer();
        gui.drawGameState();
    }
}
