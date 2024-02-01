package org.rommi;

import java.util.ArrayList;

public class PlayerConfig {
    ArrayList<Player> playerList = new ArrayList<>();
    private final String[] defaultNames = {"GISELA","NICOLA","HEINZ" ,"GUENTHER"};
    public PlayerConfig(int numPlayers){
        int i=0;
        for( String defaultName: defaultNames){
            Player player = new Player(defaultName, true);
            playerList.add(player);
            i++;
        }
        Player realPlayer = new Player("You", false);
        playerList.add(realPlayer);
    }

    public int getNumPlayers() {
        return playerList.size();
    }
    public ArrayList<Player> getPlayerList(){
        return playerList;
    }
}
