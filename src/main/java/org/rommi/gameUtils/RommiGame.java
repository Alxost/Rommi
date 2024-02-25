package org.rommi.gameUtils;

import org.rommi.Player;
import org.rommi.PlayerConfig;
import org.rommi.gameUtils.CardDeck;
import org.rommi.gameUtils.Row;

import java.util.ArrayList;

public class RommiGame {

    private final CardDeck cardDeck;
    private final ArrayList<Player> playerList;
    private final ArrayList<Row> playedRows;

    public RommiGame(PlayerConfig playerConfig){
        playerList  = playerConfig.getPlayerList();
        cardDeck = new CardDeck();
        playedRows = new ArrayList<>();
    }
    public void addRow(Row row){
        playedRows.add(row);
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
