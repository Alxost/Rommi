package org.rommi;

import java.util.ArrayList;

public class Move {

    final Row sourceRow;
    final Row targetRow;
    final ArrayList<Card> cards;

    Move(ArrayList<Card> cards, Row sourceRow, Row targetRow){
        this.sourceRow = sourceRow;
        this.targetRow = targetRow;
        this.cards  = cards;
    }
    public ArrayList<Card> getCards() {
        return cards;
    }
}
