package org.rommi;

import java.util.ArrayList;

public class PlayerConfig {
    ArrayList<Player> playerList = new ArrayList<>();
    private final String[] defaultNames = {"GISELA","NICOLA","HEINZ" ,"GUENTHER"};
    public PlayerConfig(int numPlayers){
        Player realPlayer = new Player("You", false);
        playerList.add(realPlayer);
        for( String defaultName: defaultNames){
            Player player = new Player(defaultName, true);
            playerList.add(player);
        }
    }

    public int getNumPlayers() {
        return playerList.size();
    }
    public ArrayList<Player> getPlayerList(){
        return playerList;
    }
}
