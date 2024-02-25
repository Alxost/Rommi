package org.rommi;


public class Player {
    private final String name;
    private final Row hand;
    private final boolean isBot;
    Player(String name, Boolean isBot){
        this.name = name;
        this.hand = new Row(true);
        this.isBot = isBot;

    }

    public String getName() {
        return name;
    }
    public Row getHand(){
        return hand;
    }
    public int getNumCards(){return hand.getSize();}
    public void addCard(Card card){
        hand.addCard(card);
    }
    public boolean getIsBot(){return isBot;}
}
