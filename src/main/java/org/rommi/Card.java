package org.rommi;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;


public class Card implements Comparable<Card>{
    private final Color color;
    private final int value;
    private final BufferedImage cardImage;

    static Map<Color, String> colorMap = Map.ofEntries(Map.entry(Color.RED, "red"),
            Map.entry(Color.BLUE, "blue"),
            Map.entry(Color.BLACK, "black"),
            Map.entry(Color.GREEN, "green"),
            Map.entry(Color.ORANGE, "orange")
            );

    //private final BufferedImage cardImage;
    Card(int value, Color color){
        this.value = value;
        this.color = color;
        String path = "/CardImages/small_crop_%d%s_nobg.png".formatted(value,colorMap.get(color));
        try {
            cardImage = ImageIO.read(Card.class.getResource(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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
    @Override
    public String toString(){
        return value + " " + colorMap.get(color);
    }
    void display() {
        System.out.println("Number: " + value);
        System.out.println("Color: " + color);
    }
    public BufferedImage getCardImage(){
        return cardImage;
    }
}
