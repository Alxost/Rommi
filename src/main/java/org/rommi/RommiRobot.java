package org.rommi;

public class RommiRobot {
    GameController gameController;
    RummyGame rummyGame;

    RommiRobot(GameController gameController, RummyGame rummyGame){
        this.gameController = gameController;
        this.rummyGame = rummyGame;
    }
    public void move(){
        gameController.createRandomRow();
    }
    public void createRow(){

    }
}
