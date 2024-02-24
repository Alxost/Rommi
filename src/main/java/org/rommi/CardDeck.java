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
        int deckSize = colors.length * numValues * 2;
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
    int numCardsLeft(){
        return cards.size() - numCardsUsed;
    }

    public Card drawCard(){
        numCardsUsed++;
        Card drawnCard = cards.get(numCardsUsed-1);
        return cards.get(numCardsUsed-1);
    }
    public ArrayList<Card> drawCards(int numCards){
        ArrayList<Card> drawnCards = new ArrayList<>();
        for(int i=0;i<numCards;i++){
            drawnCards.add(drawCard());
        }
        return drawnCards;
    }
}
