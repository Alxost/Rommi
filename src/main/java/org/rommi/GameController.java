package org.rommi;
import java.util.ArrayList;
import org.rommi.gui.Gui;


public class GameController {
    private final RummyGame rummyGame;
    private final PlayerConfig playerConfig;
    private final RommiRobot bot;
    private int activePlayerIndex = 1;
    Gui gui;


    public GameController(){
        this.playerConfig = new PlayerConfig(5);
        this.rummyGame = new RummyGame(playerConfig);
        this.gui = new Gui(this);
        this.bot  = new RommiRobot(this, rummyGame);
    }

    public void drawStartingHands(RummyGame rummyGame){
        for (Player player : rummyGame.getPlayerList()){
            for(int i = 0; i<14;i++){
                Card drawnCard = rummyGame.getCardDeck().drawCard();
                player.addCard(drawnCard);
                drawnCard.setOwner(player.getHand());
            }
        }
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
        gui.update();
    }
    public void executeMove(Move move){
        for(Card card: move.getCards()){
            card.getOwner().getRowContent().remove(card);
            move.targetRow.addCard(card);
            card.setOwner(move.targetRow);
        }
        gui.update();
    }
    public ArrayList<Row> getPlayedRows(){
        return rummyGame.getPlayedRows();
    }

    public void createNewRow(){
        Row newRow = new Row(false);
        rummyGame.addRow(newRow);
        gui.update();
    }
    public void createNewRow(ArrayList<Card> cardsToAdd){
        Row newRow = new Row(false);
        for(Card card: cardsToAdd){
            card.getOwner().getRowContent().remove(card);
            if(card.getOwner().getRowContent().isEmpty() && !card.getOwner().getIsHand()){
                rummyGame.getPlayedRows().remove(card.getOwner());
            }
            newRow.addCard(card);
            card.setOwner(newRow);
        }
        rummyGame.addRow(newRow);
        gui.update();
    }
    public void drawCards(int numCards, Player player){
        for(int i = 0; i<numCards;i++){
            Card drawnCard = rummyGame.getCardDeck().drawCard();
            player.addCard(drawnCard);
            drawnCard.setOwner(player.getHand());
        }
        nextPlayer();
        gui.update();
    }
    public void botMove(){
        bot.move();
        gui.update();
    }
    public MoveListener getMoveListener(){
        if (gui == null){
            return null;
        }
        return gui.getMoveListener();
    }
    public void start(){
        drawStartingHands(rummyGame);
        gui.createGui();
    }
    public void updateGui(){
        gui.update();
    }
}
