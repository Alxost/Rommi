package org.rommi;

public class RommiRobot {
    GameController gameController;

    RommiRobot(GameController gameController){
        this.gameController = gameController;
    }
    public void move(){
        gameController.nextPlayer();
    }
}