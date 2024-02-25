package org.rommi;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class CardDeck {
    private int numCardsUsed;
    private final ArrayList<Card> cards;
    static final Color blackCardColor = new Color(0,0,0);
    static final Color blueCardColor = new Color(85,234,212);
    static final Color redCardColor = new Color(197,0,60);
    static final Color yellowCardColor = new Color(243,230,0);

    public CardDeck(){
        int numValues = 13;
        Color[] colors = {blackCardColor, blueCardColor, yellowCardColor, redCardColor};
        numCardsUsed = 0;
        cards = new ArrayList<>();
        for(int i = 1; i <= numValues; i++){
            for(Color color : colors){
                Card card = new Card(i, color, null);
                cards.add(card);
                Card cardDouble = new Card(i, color, null);
                cards.add(cardDouble);
            }
        }
        Collections.shuffle(cards);
    }
    public int numCardsLeft(){
        return cards.size() - numCardsUsed;
    }

    public Card drawCard(){
        numCardsUsed++;
        return cards.get(numCardsUsed-1);
    }
}
