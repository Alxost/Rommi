package org.rommi;

public class Main {
    public static void main(String[] args){
        PlayerConfig config = new PlayerConfig(4);
        GameController gc  = new GameController();
        while(true){
            if(gc.getActivePlayer().getIsBot()){
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                gc.nextPlayer();
            }
        }
    }
}
