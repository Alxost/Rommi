package org.rommi.gameUtils;

import java.awt.*;
import java.util.Map;


public class Card implements Comparable<Card>{
    private final Color color;
    private final int value;
    private Row owner;
    static final Color blackCardColor = new Color(0,0,0);
    static final Color blueCardColor = new Color(85,234,212);
    static final Color redCardColor = new Color(197,0,60);
    static final Color yellowCardColor = new Color(243,230,0);

    static Map<Color, String> colorMap = Map.ofEntries(Map.entry(redCardColor, "red"),
            Map.entry(blueCardColor, "blue"),
            Map.entry(blackCardColor, "black"),
            Map.entry(yellowCardColor, "yellow")
            );

    public Card(int value, Color color, Row owner){
        this.value = value;
        this.color = color;
        this.owner = owner;
    }

    public int getValue() {
        return value;
    }

    public Color getColor() {
        return color;
    }

    public Row getOwner(){return owner;}
    public void setOwner(Row row){owner = row;}
    @Override
    public int compareTo(Card otherCard) {
        return Integer.compare(getValue(), otherCard.getValue());
    }
    @Override
    public String toString(){
        return value + " " + colorMap.get(color);
    }

}
