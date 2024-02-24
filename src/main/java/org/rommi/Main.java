package org.rommi;

import org.rommi.gui.Gui;

import java.awt.*;

public class Main {
    public static void main(String[] args){
        GameController gc  = new GameController();
        Runnable runnable = new Runnable() {
            public void run() {
            gc.start();            }
        };
        EventQueue.invokeLater(runnable);
    }
}
