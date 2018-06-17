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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
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
    
    private static Timer timer;//计时器
    private static int INTERVAL = 10;//时间间隔（ms)
    private static int birdNum = 0;//鸟的数量
    private static int mx = 0;//鼠标点击处的横坐标
    private static int my = 0;//鼠标点击处的纵坐标
    
    public static Birds bird;//鸟
    public static JButton start;//开始按钮
    
    public BackgroundPanel(){
  
        //初始化界面
        this.setLayout(null);
        this.setOpaque(true);
        this.setBounds(0, 0, 1000, 640);
        
        start = new JButton();
        start.setBounds(410, 260, 168, 168);
        start.setBorderPainted(false);
        start.setIcon(new ImageIcon("src/resources/start.png"));
        this.add(start); 
        
        //开始按钮的点击事件
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(start);
                setBullets(10);
                bird = new Birds(0, 0, 1, 0, 0);
                addBirds();
                repaint();
            }
        });
        
        //鼠标点击事件监听，即“射击动作”
        this.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                if(e.getButton() == MouseEvent.BUTTON1){ //鼠标左键单击时
                    
                    //还有剩余子弹时
                    if(bullets > 0){
                        bullets--;//子弹减一
                        //鼠标当前点击的位置
                        mx = e.getX();
                        my = e.getY();
                        //鸟的位置的范围
                        int lx = bird.getX(),rx = bird.getX() + 70;
                        int ty = bird.getY(),by = bird.getY() + 50;
                        if(mx >= lx && mx <= rx && my >= ty && my <= by ){
                            score++;
                        }
                    }else {//没子弹了，即Gameover
                        
                    }
                }
            }
        });
    }
    
//    绘制背景、记分板、飞鸟
    public void paint(Graphics g){
        super.paintComponent(g);
        g.drawImage((new ImageIcon("src/resources/background.jpg")).getImage(), 0, 0, this);
        try {
            g.drawImage(bird.getImage(), bird.getX(), bird.getY(), this);    
        } catch (Exception e) {
        
        }
        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 25));
        g.drawString("得分: " + score + "  " + "剩余子弹：" + bullets, 690, 50);
    }
    
    //启动线程
    @Override
    public void run() {
        try{
            for ( int i = 0; i <= 200; i++ ){
                bird.fly();
                repaint();
                Thread.sleep(INTERVAL);
            }
        }catch ( InterruptedException e ){ }
    }
    
    public void addBirds() {
        Thread t = new Thread(this);
        t.start();
        try{Thread.sleep(INTERVAL);
        }catch ( InterruptedException e ){

        }
        repaint();
    }
    
    //相关参数的获取
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
}
