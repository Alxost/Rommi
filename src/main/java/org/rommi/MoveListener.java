package org.rommi;

import java.util.ArrayList;

public class MoveListener {
    Move currentMove;
    Card selectedCard;
    Row sourceRow;
    Row targetRow;
    ArrayList<Move> moveHistory;

    MoveListener(){
        this.moveHistory = new ArrayList<>();
    }
    public void setSelectedCard(Card card){
        this.selectedCard = card;
    }
    public void setTargetRow(Row row){
        this.targetRow = row;
    }
    public void setSourceRow(Row row){
        this.sourceRow = row;
    };
    public Move makeMove(){
        return new Move(selectedCard,sourceRow,targetRow);
    }

}
