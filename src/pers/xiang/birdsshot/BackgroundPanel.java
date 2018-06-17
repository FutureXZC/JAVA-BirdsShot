/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pers.xiang.birdsshot;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author 祥
 */
public class BackgroundPanel extends JPanel implements Runnable{
    
    public static int score = 0;//分数
    public static int bullets = 0;//子弹数
    public static JButton start;
    
    private Timer timer;//计时器
    private int INTERVAL = 50;//时间间隔（ms)
    private int birdNum = 0;//鸟的数量
    public static Birds bird;
    
    public BackgroundPanel(){
  
        //初始化界面
        this.setLayout(null);
        this.setOpaque(true);
        this.setBounds(0, 0, 1000, 640);
        
        bird = new Birds(0, 0, 2, 1, 0);
        
        start = new JButton();
        start.setBounds(410, 260, 168, 168);
        start.setBorderPainted(false);
        start.setIcon(new ImageIcon("src/resources/start.png"));
        this.add(start); 
        
        start.addActionListener(new ActionListener() {
          // 事件处理
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(start);
                setBullets(10);
                add();
                repaint();
            }
        });
    }
    
    public void paint(Graphics g){
        super.paintComponent(g);
        g.drawImage((new ImageIcon("src/resources/background.jpg")).getImage(), 0, 0, this);
        g.drawImage(bird.getImage(), bird.getX(), bird.getY(), this);
        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 25));
        g.drawString("得分: " + score + "  " + "剩余子弹：" + bullets, 730, 50);
    }
    
    public void flying(){
        while (bird.getX() <= 1069 ) {            
            bird.fly();
        }
    }
    
    public void setScore (int s){
        score = s;
    }
    
    public int getScore (){
        return score;
    }
    
    public void setBullets(int b){
        bullets  = b;
    }
    
    public int getBullets(){
        return bullets;
    }

    @Override
    public void run() {
        try{
            for ( int i = 1; i <= 1000; i++ ){
                bird.fly();
                repaint();
                Thread.sleep(INTERVAL);
            }
        }catch ( InterruptedException e ){ }
    }
    
    public void add() {
        Thread t = new Thread(this);
        t.start();
        try{Thread.sleep(INTERVAL);
        }catch ( InterruptedException e ){

        }
    }
}
