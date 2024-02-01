package org.rommi;

import java.util.ArrayList;

public class RummyGame {

    private CardDeck cardDeck;
    private ArrayList<Player> playerList;
    private ArrayList<Row> playedRows;
    private boolean gameOver;

    public RummyGame(PlayerConfig playerConfig){
        playerList  = playerConfig.playerList;
        cardDeck = new CardDeck();
        playedRows = new ArrayList<>();
    }
    public void addPlayer(Player player){
        playerList.add(player);
    }
    public void addRow(Row row){
        playedRows.add(row);
    }
    public boolean gameOver(){return gameOver;
    }
    public ArrayList<Player> getPlayerList(){
        return playerList;
    }
    public CardDeck getCardDeck(){
        return cardDeck;
    }
    public Player getPlayerByIndex(int index){
        return playerList.get(index);
    }
    public ArrayList<Row> getPlayedRows(){
        return playedRows;
    }
}
