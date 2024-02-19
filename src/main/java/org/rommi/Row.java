package org.rommi;

import java.util.ArrayList;
import java.util.Collections;

public class Row {
    private ArrayList<Card> rowContent;
    private final boolean isHand;
    //public Row(ArrayList<Card> cards, boolean isHand){
    //    rowContent = new ArrayList<>();
    //    rowContent.addAll(cards);
    //    isHand = isHand;
    // }
    public Row(boolean isHand){
        rowContent = new ArrayList<>();
        this.isHand = isHand;
    }
    public Card getCardByIndex(int index){
        return rowContent.get(index);
    }
    public ArrayList<Card> getRowContent(){
        return rowContent;
    }
    public void removeCard(Card card){rowContent.remove(card);}
    public boolean getIsHand(){return isHand;};
    public void addCard(Card card){
        rowContent.add(card);
        Collections.sort(rowContent);
    }
    public void addCards(ArrayList<Card> cards){
        rowContent.addAll(cards);
        Collections.sort(rowContent);
    }
    public int getSize(){return rowContent.size();}
}
