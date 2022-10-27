package com.javaprojects;

import javax.swing.*;

public class GameFrame extends JFrame {

    GameFrame(){
        GamePanel panel=new GamePanel();

        this.add(new GamePanel());
        this.setTitle("Snake Game By Rohan.A");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);

    }
}
