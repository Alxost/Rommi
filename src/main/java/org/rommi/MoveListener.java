package org.rommi;

import java.util.ArrayList;

public class MoveListener {
    ArrayList<Card> selectedCards;
    Row targetRow;
    ArrayList<Move> moveHistory;
    GameController gameController;

    public MoveListener(GameController gameController){
        this.moveHistory = new ArrayList<>();
        this.selectedCards = new ArrayList<>();
        this.gameController  = gameController;
    }
    public void setSelectedCard(Card card){
        selectedCards.add(card);
        gameController.updateGui();
    }
    public void removeSelectedCard(Card card){
        selectedCards.remove(card);
        gameController.updateGui();
    }
    public ArrayList<Card> getSelectedCards(){return selectedCards;}
    public void setTargetRow(Row row){
        this.targetRow = row;
    }

    public void resetSelectedCards(){
        selectedCards = new ArrayList<>();
    }
    public void addCards(){
        ArrayList<Card> cardsToAdd = selectedCards;
        resetSelectedCards();
        gameController.addCardToRow(cardsToAdd, targetRow);
    }
    public void createNewRow(){
        if(selectedCards.isEmpty()) {
            gameController.createNewRow();
        }
        else{
            ArrayList<Card> cardsToAdd  = selectedCards;
            resetSelectedCards();
            gameController.createNewRow(cardsToAdd);
        }
    }
}
