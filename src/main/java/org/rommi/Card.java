package org.rommi;

import java.awt.*;

public class Card implements Comparable<Card>{
    private final Color color;
    private final int value;
    Card(int value, Color color){
        this.value = value;
        this.color = color;
    }

    public int getValue() {
        return value;
    }

    public Color getColor() {
        return color;
    }


    @Override
    public int compareTo(Card otherCard) {
        //return getValue().compareTo(otherCard.getValue());
        return Integer.compare(getValue(), otherCard.getValue());
    }
    void display() {
        System.out.println("Number: " + value);
        System.out.println("Color: " + color);
    }
}
