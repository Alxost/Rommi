package org.rommi;

import java.awt.*;

public class Rommi {
    public static void main(String[] args){
        GameController gc  = new GameController();
        Runnable runnable = gc::start;
        EventQueue.invokeLater(runnable);
    }
}
