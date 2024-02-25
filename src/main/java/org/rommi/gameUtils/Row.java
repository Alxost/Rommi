package org.rommi.gameUtils;

import org.rommi.gameUtils.Card;

import java.util.ArrayList;
import java.util.Collections;

public class Row {
    private final ArrayList<Card> rowContent;
    private final boolean isHand;
    private int rowSum;
    public Row(ArrayList<Card> cards, boolean isHand){
        rowContent = new ArrayList<>();
        rowContent.addAll(cards);
        Collections.sort(rowContent);
        this.isHand = isHand;
        rowSum = 0;
        for(Card card: cards){
            rowSum += card.getValue();
        }
    }
    public Row(boolean isHand){
        rowContent = new ArrayList<>();
        this.isHand = isHand;
        rowSum = 0;
    }
    public ArrayList<Card> getRowContent(){
        return rowContent;
    }
    public void removeCard(Card card){rowContent.remove(card);}
    public boolean getIsHand(){return isHand;}
    public void addCard(Card card){
        rowContent.add(card);
        rowSum += card.getValue();
        Collections.sort(rowContent);
    }

    public int getSize(){return rowContent.size();}
    public int getRowSum(){return rowSum;}
    @Override
    public String toString(){
        StringBuilder rowDisplay = new StringBuilder();
        for (Card card: rowContent){
            rowDisplay.append(card.toString());
            rowDisplay.append(" ");
        }
        return rowDisplay.toString();
    }
}
