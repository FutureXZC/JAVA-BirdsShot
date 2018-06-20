/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pers.xiang.birdsshot;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.MalformedURLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author 祥
 */
public class BackgroundPanel extends JPanel{

    private static final int START = 0;//开启游戏
    private static final int RUNNING = 1;//游戏中
    private static final int GAME_OVER = 2;//游戏失败
    private static final int INTERVAL = 50;//时间间隔（ms)
    private static final int BIRDS_MAX_NUM = 50;//飞鸟最大数目常量，用于初始化birdNum和total
    private static final int BULLETS = 100;//子弹数常量，用于初始化bullets
        
    public static int bullets = 0;//子弹数
    public static int score = 0;//分数
    public static JButton start;//开始按钮
    
    private static Birds bird;//飞鸟
    private static int birdNum;//当前剩余飞鸟的数量
    private static int totalBirds;//飞鸟总数
    private static int BirdsIndex = 0;//飞鸟数组的索引
    
    private static AudioClip gunfire;//枪声
    private static int mx = 0;//鼠标点击处的横坐标
    private static int my = 0;//鼠标点击处的纵坐标
    
    private int state;//游戏的当前状态
    
    public BackgroundPanel(){
  
//        初始化面板，光标设为自定义的瞄准十字
        this.setLayout(null);
        this.setOpaque(true);
        this.setBounds(0, 0, 1000, 640);
        try {
            Cursor coursor = Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("src/resources/images/aim.png").getImage(),new Point(10,20), "stick");
            this.setCursor(coursor);   
        } catch (Exception e) {}
        
//        开始按钮
        start = new JButton();
        start.setBounds(410, 260, 168, 168);
        start.setBorderPainted(false);
        start.setIcon(new ImageIcon("src/resources/images/start.png"));
        this.add(start); 
        
        state = START;//设置游戏状态为“开启游戏”
        
        try {
            File file1 = new File("src/resources/music/gunfire.wav");
            gunfire = Applet.newAudioClip(file1.toURL());
        } catch (MalformedURLException ex){}
        
//        开始按钮的点击事件
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
//                    音频时长为一秒钟，为防止高速点击时无法让每次点击都产生枪声，必须先stop，再play
                    gunfire.stop();
                    gunfire.play();
                }finally{    
                    System.out.println("Running.");
                    
                    state = RUNNING;//程序状态修改为“游戏中”
                    start.setVisible(false);//隐藏开始按钮
                    bullets = BULLETS;//初始化子弹数目
                    score = 0;//重置分数为0分
                    birdNum = BIRDS_MAX_NUM;//剩余飞鸟数
                    totalBirds = BIRDS_MAX_NUM;//飞鸟总数
                    BirdsIndex = 0;//从第一只开始飞
                    
                    addBirds();//加入飞鸟线程   
                }
            }
        });
        
//        鼠标点击事件监听，即“射击动作”
        this.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                    
                    try{
//                    音频时长为一秒钟，为防止高速点击时无法让每次点击都产生枪声，必须先stop，再play
                        gunfire.stop();
                        gunfire.play();
                    }finally{
//                    子弹还有剩的时候：
                        if(bullets > 0){
                        bullets--;//子弹减一
                        
//                        飞鸟的位置的范围，当鼠标点击的坐标在此范围内，即认为飞鸟被击中
                        mx = e.getX();
                        my = e.getY();
                        int lx = bird.getX(),rx = bird.getX() + 70;
                        int ty = bird.getY(),by = bird.getY() + 50;
                        if(mx >= lx && mx <= rx && my >= ty && my <= by && !bird.getIsShot()){
                            score++;
                            bird.setIsShot(true);
                        }
                        System.out.println("Click: mx = " + mx + "," + "my = " + my + ", isShot = " + bird.getIsShot());
                        }
                    }                 
            }
        });
        System.out.println("Start.");
    }
    
//    绘制背景、记分板、飞鸟、GAMEOVER图标
    public void paintComponent(Graphics g){
        super.paintComponent(g);

//        基础绘制，绘制背景和右上角的记分板
        g.drawImage((new ImageIcon("src/resources/images/background.jpg")).getImage(), 0, 0, this);
        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 25));
        g.drawString("得分: " + score + "  剩余子弹：" + bullets + "  剩余飞鸟：" + birdNum, 540, 50);
        
//        当处于“游戏中”状态时，绘制飞鸟
        if(state == RUNNING){
            try {
                g.drawImage(bird.getImage(), bird.getX(), bird.getY(), this);
            } catch (Exception e){}
        }
        
//        当游戏结束时，绘制“GAME OVER”图标
        if(state == GAME_OVER){
            try {g.drawImage(new ImageIcon("src/resources/images/gameover.png").getImage(), 120, 150, this);
                System.out.println("Game over.");
            } catch (Exception e){}
        }
    }
    
//    创建飞鸟线程
    public void addBirds() {
        
        bird = new Birds(BirdsIndex);
        Flying f = new Flying(bird, bullets, this);
        Thread t = new Thread(f);
        t.start();
        try{Thread.sleep(INTERVAL);
        }catch ( InterruptedException e ){}
    }
    
//    相关参数的获取
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
    
    public int getState(){
        return state;
    }
    
    public void setState(int s){
        state = s;
    }
    
    public int getBirdNum(){
        return birdNum;
    }
    
    public void setBirdNum(){
        birdNum--;//当鸟飞出屏幕或被击中时，当前剩余飞鸟数减一
    }

    public int getBirdsIndex() {
        return BirdsIndex;
    }
    
    public void setBirdIndex(){
        if(BirdsIndex < totalBirds - 1)
            BirdsIndex ++;//切换下一只鸟
    }
}