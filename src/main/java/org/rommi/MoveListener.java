package org.rommi;

import org.rommi.gui.Gui;

import java.util.ArrayList;

public class MoveListener {
    Move currentMove;
    ArrayList<Card> selectedCards;
    Row sourceRow;
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
    public ArrayList<Card> getSelectedCards(){return selectedCards;}
    public void setTargetRow(Row row){
        this.targetRow = row;
    }
    public void setSourceRow(Row row){
        this.sourceRow = row;
    };
    public void resetSelectedCards(){
        selectedCards = new ArrayList<>();
    }
    public void makeMove(){
        Move move = new Move(selectedCards,sourceRow,targetRow);
        selectedCards = new ArrayList<>();
        gameController.executeMove(move);
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
