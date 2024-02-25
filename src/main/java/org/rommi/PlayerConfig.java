package org.rommi;

import java.util.ArrayList;

public class PlayerConfig {
    ArrayList<Player> playerList = new ArrayList<>();

    public PlayerConfig(){
        Player realPlayer = new Player("You", false);
        playerList.add(realPlayer);
        String[] defaultNames = {"GISELA", "NICOLA", "HEINZ", "GUENTHER"};
        for( String defaultName: defaultNames){
            Player player = new Player(defaultName, true);
            playerList.add(player);
        }
    }

    public int getNumPlayers() {
        return playerList.size();
    }

}
