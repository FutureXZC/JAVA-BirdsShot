/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pers.xiang.birdsshot;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author 祥
 */
public class BirdsShotGame extends JFrame{
    
    public static final int WIDTH = 1000;//面板宽
    public static final int HEIGHT = 640;//面板高
    
    private int state;//游戏的当前状态
    private static final int START = 0;//开启游戏
    private static final int RUNNING = 1;//正在游戏
    private static final int PAUSE = 2;//暂停游戏
    private static final int GAME_OVER = 3;//游戏失败
    
    public static JPanel bg;
        
    public BirdsShotGame(){
        
        bg = new BackgroundPanel();
        
        //初始化界面
        this.setLayout(null);
        this.setSize(WIDTH, HEIGHT);
        this.setResizable(false);
        this.setLocationRelativeTo(null);//居中  
        this.setTitle("JavaGame-BirdsShot");  
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(new ImageIcon("src/resources/icon.png").getImage());

        //设置游戏状态为初始状态
        this.state = START;
        this.add(bg);
        this.setVisible(true);
        
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BirdsShotGame();
            }
        });
    }
}


