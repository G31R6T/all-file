package com.fourgame.board;

import javax.swing.*;
import java.awt.*;

/**
 * @描叙:
 * @作者: mzdora
 * @邮箱: mzdora@qq.com
 * @创建时间: 2021/11/9 7:37 下午
 **/
public class createBoard {
    private static int turn = 1;
    private static int score_red = 0;
    private static int score_blue = 0;

    /**
     * The picklevel method creates three different JButtons.
     * After player clicks any Button, the component listener will get the mouse click event,
     * then call the startGame method and pass in the parameter n .
     * The parameter n is determined by the button.
     */
    public static void pickLevel(){
        JFrame frame=new JFrame("LevelSelect");
        JPanel panel=new JPanel();
        panel.setLayout(new GridLayout(3,1,30,5));

        JButton jButton = new JButton("3x3");
        jButton.addActionListener(e -> {
            startGame(3);
            frame.setVisible(false);
        });
        panel.add(jButton);
        JButton jButton1 = new JButton("5x5");
        jButton1.addActionListener(e -> {
            startGame(5);
            frame.setVisible(false);
        });
        panel.add(jButton1);
        JButton jButton2 = new JButton("7x7");
        jButton2.addActionListener(e -> {
            startGame(7);
            frame.setVisible(false);
        });
        panel.add(jButton2);
        frame.add(panel);
        frame.setBounds(300,200,600,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * The startgame method is the main method of the game.
     * It will create an n*n two-dimensional array of type JButton.
     * Here, the responsibilities of each JButton are the same,
     * because each JButton has the same listener. After clicking the button,
     * if the value of field is less than 4, it will increase by 1.
     * A method named isBound will be called to determine whether the field is within the boundary.
     * @param n
     */
    public static void startGame(int n){
        JFrame frame=new JFrame("FourGame");
        JLabel Player=new JLabel("It's Red turn!");
        frame.add(Player,BorderLayout.NORTH);
        JPanel panel=new JPanel();
        JButton[][] jButton = new JButton[n][n];
        // init jButton arrays
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                jButton[i][j] = new JButton("0");
            }
        }
        panel.setLayout(new GridLayout(n,n,5,5));
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int finalI = i;
                int finalJ = j;
                panel.add(jButton[i][j]);
                jButton[i][j].addActionListener(e -> {
                    int[][] edges = {{0,1},{1,0},{0,-1},{-1,0},{1,1},{-1,-1},{-1,1},{1,-1},{0,0}};
                    for (int k = 0; k < 9; k++) {
                        int newx = finalI + edges[k][0];
                        int newy = finalJ + edges[k][1];
                        if( isBound(n,newx,newy)){
                            if(Integer.parseInt(jButton[newx][newy].getText())<4) {
                                jButton[newx][newy].setText(Integer.toString((Integer.parseInt(jButton[newx][newy].getText()) + 1)));
                            }
                        }
                    }
                    for (int k = 0; k < n; k++) {
                        for (int l = 0; l < n; l++) {
                            if("4".equals(jButton[k][l].getText()) && jButton[k][l].getForeground().equals(Color.BLACK)){
                                if(turn % 2 == 0) {
                                    jButton[k][l].setForeground(Color.BLUE);
                                    score_blue ++;
                                }else{
                                    jButton[k][l].setForeground(Color.RED);
                                    score_red ++;
                                }
                            }
                        }
                    }
                    turn++;
                    if(turn % 2 == 0) {
                        Player.setText("It's Blue turn!");
                    }else{
                        Player.setText("It's Red turn!");
                    }

                    //end the game
                    int count = 0;
                    for (int k = 0; k < n; k++) {
                        for (int l = 0; l < n; l++) {
                            if(jButton[k][l].getForeground().equals(Color.BLACK)) {
                                count++;
                            }
                        }
                    }
                    if(count == 0){
                        String winner = score_red>score_blue ? "Red" : "Blue";
                        if(score_red>score_blue) {
                            JOptionPane.showMessageDialog(panel, "Player " + winner + " win the game! score is " + score_red, "胜利", JOptionPane.PLAIN_MESSAGE);
                        }else{
                            JOptionPane.showMessageDialog(panel, "Player " + winner + " win the game! score is " + score_blue, "胜利", JOptionPane.PLAIN_MESSAGE);
                        }
                        frame.dispose();
                        score_blue = 0;
                        score_red = 0;
                        turn = 1;
                        pickLevel();
                    }
                });
            }
        }
        frame.add(panel);
        frame.setBounds(300,200,600,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * To determine whether the field is within the boundary.
     * @param n
     * @param x
     * @param y
     * @return
     */
    private static boolean isBound(int n,int x,int y){
        return x >= 0 && y >= 0 && x < n && y < n;
    }
}
