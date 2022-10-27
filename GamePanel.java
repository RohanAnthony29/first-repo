package com.javaprojects;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.Timer;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.List;

import static java.awt.Font.BOLD;
import static java.awt.Font.ROMAN_BASELINE;
//import static java.awt.util.Random;

public class GamePanel extends JPanel implements ActionListener {
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int Unit_size = 25;
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / Unit_size;
    static final int DELAY = 75;
    final int[] x = new int[GAME_UNITS];
    final int[] y = new int[GAME_UNITS];
    int Body_parts = 7;
    int appleX;
    int appley;
    char direction = 'R';
    boolean running = false;
    int appleseaten;
    Timer timer;
    Random random;
    //private String player;
    //private int highscore=-1;


    GamePanel() {
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }

    public void startGame() {
        new_apple();
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();

    }


    public void draw(Graphics g) {
        if (running) {
        /*for(int i=0;i<Screen_height/unit_size;i++){
            g.drawLine(i*unit_size,0,i*unit_size,Screen_height);
            g.drawLine(0,i*unit_size,Screen_Width,i*unit_size);
        }
        */
            g.setColor(Color.red);
            g.fillOval(appleX, appley, Unit_size, Unit_size);

            for (int i = 0; i < Body_parts; i++) {
                if (i == 0) {
                    g.setColor(Color.GREEN);
                    g.fillRect(x[i], y[i], Unit_size, Unit_size);
                } else {
                    g.setColor(new Color(45, 180, 0));
                    g.fillRect(x[i], y[i], Unit_size, Unit_size);
                }
            }

            g.setColor(Color.red);
            g.setFont(new Font("IN Free", BOLD, 20));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: " + appleseaten, (SCREEN_WIDTH - metrics.stringWidth("Score+")) / 1, SCREEN_HEIGHT / 1);
        }
//
//
        else {
            gameOver(g);

        }
    }

    public void new_apple() {
        appleX = random.nextInt(SCREEN_WIDTH / Unit_size) * Unit_size;
        appley = random.nextInt(SCREEN_HEIGHT / Unit_size) * Unit_size;
    }

    public void move() {
        for (int i = Body_parts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];

        }
        switch (direction) {
            case 'U':
                y[0] = y[0] - Unit_size;
                break;
            case 'D':
                y[0] = y[0] + Unit_size;
                break;
            case 'L':
                x[0] = x[0] - Unit_size;
                break;
            case 'R':
                x[0] = x[0] + Unit_size;
                break;
        }
    }

    public void checkApple() {
        if ((x[0] == appleX) && (y[0] == appley)) {
            Body_parts++;
            appleseaten++;
            new_apple();
        }
    }


    public void checkcollasions() {
        //check wheather the head collides with the body
        for (int i = Body_parts; i > 0; i--) {
            if (x[0] == x[i] && y[0] == y[i]) {
                running = false;
            }
        }
        //check if head touches left border
        if (x[0] < 0) {
            running = false;
        }
        if (x[0] > SCREEN_WIDTH) {
            running = false;
        }
        if (y[0] < 0) {
            running = false;
        }
        //head touches bottom border
        if (y[0] > SCREEN_HEIGHT) {
            running = false;
        }
        if (!running) {
            timer.stop();
        }
    }


    public void gameOver(Graphics g) {
        g.setColor(Color.red);
        g.setFont(new Font("Hello", Font.HANGING_BASELINE, 55));
        //FontMetrics metrics=getFontMetrics(g.getFont());
        //g.drawString("Score:"+appleseaten,(SCREEN_WIDTH-metrics.stringWidth("Score:"+appleseaten))/2,g.getFont().getSize());
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH - metrics.stringWidth("Game Over")) / 4, SCREEN_HEIGHT / 4);
        g.drawString(" Score:" + appleseaten, (SCREEN_WIDTH - metrics.stringWidth("Score+")) / 5, SCREEN_HEIGHT / 2);
        g.setColor(Color.blue);
        g.setFont((new Font("Thanks For Playing Rohan's Snake Game", ROMAN_BASELINE, 25)));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Thanks For Playing Rohan's Snake Game", (SCREEN_WIDTH - metrics1.stringWidth("Thanks For Playing Rohan's Snake Game")) / 1, SCREEN_HEIGHT / 1);
        //g.drawString(,(SCREEN_WIDTH-metrics1.stringWidth(SortedMap))/1,SCREEN_HEIGHT);
    }


    public void checkapples() {
        if ((x[0] == appleX) && (y[0] == appley)) {
            Body_parts++;
            appleseaten++;
            new_apple();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkApple();
            checkcollasions();
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
             switch (e.getKeyCode()) {

                case KeyEvent.VK_LEFT:
                    if (direction != 'R') {
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction != 'L') {
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (direction != 'D') {
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (direction != 'U') {
                        direction = 'D';
                    }
                    break;
            }

        }
    }
}







