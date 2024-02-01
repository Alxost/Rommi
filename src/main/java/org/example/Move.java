package org.example;

public class Move {

    final Row sourceRow;
    final Row targetRow;
    final Card card;

    Move(Card card, Row sourceRow, Row targetRow){
        this.sourceRow = sourceRow;
        this.targetRow = targetRow;
        this.card  = card;
    }

    public Row getSourceRow() {
        return sourceRow;
    }

    public Row getTargetRow() {
        return targetRow;
    }

    public Card getCard() {
        return card;
    }
}
