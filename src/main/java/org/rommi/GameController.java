package org.rommi;
import java.util.ArrayList;
import org.rommi.gui.Gui;


public class GameController {
    private final RommiGame rommiGame;
    private final PlayerConfig playerConfig;
    private final RommiRobot bot;
    private int activePlayerIndex = 1;
    private final RuleChecker ruleChecker;
    Gui gui;


    public GameController(){
        this.playerConfig = new PlayerConfig();
        this.rommiGame = new RommiGame(playerConfig);
        this.ruleChecker = new RuleChecker();
        this.gui = new Gui(this);
        this.bot  = new RommiRobot(this, rommiGame);
    }

    public void drawStartingHands(RommiGame rommiGame){
        for (Player player : rommiGame.getPlayerList()){
            for(int i = 0; i<14;i++){
                Card drawnCard = rommiGame.getCardDeck().drawCard();
                player.addCard(drawnCard);
                drawnCard.setOwner(player.getHand());
            }
        }
    }
    public Player getActivePlayer(){
        return rommiGame.getPlayerByIndex(activePlayerIndex-1);
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
    public void addCardToRow(ArrayList<Card> cardsToAdd, Row targetRow){
        for(Card card: cardsToAdd){
            if (ruleChecker.isValidAddition(card, targetRow)) {
                card.getOwner().getRowContent().remove(card);
                targetRow.addCard(card);
                card.setOwner(targetRow);
                gui.update();
            }
        }
    }
    public ArrayList<Row> getPlayedRows(){
        return rommiGame.getPlayedRows();
    }

    public void createNewRow(){
        Row newRow = new Row(false);
        rommiGame.addRow(newRow);
        gui.update();
    }
    public void createNewRow(ArrayList<Card> cardsToAdd){
        Row newRow = new Row(false);
        for(Card card: cardsToAdd){
            if(ruleChecker.isValidAddition(card,newRow)){
                card.getOwner().getRowContent().remove(card);
                if(card.getOwner().getRowContent().isEmpty() && !card.getOwner().getIsHand()){
                    rommiGame.getPlayedRows().remove(card.getOwner());
                }
                newRow.addCard(card);
                card.setOwner(newRow);
            }
        }
        rommiGame.addRow(newRow);
        gui.update();
    }
    public void drawCards(int numCards, Player player){
        for(int i = 0; i<numCards;i++){
            Card drawnCard = rommiGame.getCardDeck().drawCard();
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
        return gui.getMoveListener();
    }
    public void start(){
        drawStartingHands(rommiGame);
        gui.createGui();
    }
    public void updateGui(){
        gui.update();
    }
}
