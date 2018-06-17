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
    private static int INTERVAL = 50;//时间间隔（ms)
    private static int birdNum = 0;//鸟的数量
    private static int mx = 0;//鼠标点击处的横坐标
    private static int my = 0;//鼠标点击处的纵坐标
    
    public static Birds bird;//鸟
    public static JButton start;//开始按钮
    
    private int state;//游戏的当前状态
    private static final int START = 0;//开启游戏
    private static final int RUNNING = 1;//正在游戏
    private static final int PAUSE = 2;//暂停游戏
    private static final int GAME_OVER = 3;//游戏失败
    
    public BackgroundPanel(){
  
//        初始化界面
        this.setLayout(null);
        this.setOpaque(true);
        this.setBounds(0, 0, 1000, 640);
        
        start = new JButton();
        start.setBounds(410, 260, 168, 168);
        start.setBorderPainted(false);
        start.setIcon(new ImageIcon("src/resources/start.png"));
        this.add(start); 
        
        state = START;//开启游戏
        
//        开始按钮的点击事件
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                state = RUNNING;
                remove(start);
                setBullets(10);//初始化子弹数目
                setScore(0);//重置分数为0分
                Random rand = new Random();
                bird = new Birds(0, 300, 10, 5, 0);//随机生成飞鸟的飞行速度
                System.out.println("Vx = " + bird.getVx() + "," + "Vy = " + bird.getVy());//在控制台输出飞鸟的速度
                addBirds();
                repaint();
            }
        });
        
//        鼠标点击事件监听，即“射击动作”
        this.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                
                if(e.getButton() == MouseEvent.BUTTON1){ //鼠标左键单击时
                    
//                    还有剩余子弹时
                    if(bullets > 0){
                        bullets--;//子弹减一
//                        鼠标当前点击的位置
                        mx = e.getX();
                        my = e.getY();
//                        飞鸟的位置的范围
                        int lx = bird.getX(),rx = bird.getX() + 70;
                        int ty = bird.getY(),by = bird.getY() + 50;
                        if(mx >= lx && mx <= rx && my >= ty && my <= by && !bird.getIsShot()){
                            score++;
                            bird.setIsShot(true);
                        }
                    }
                }
            }
        });
    }
    
//    绘制背景、记分板、飞鸟、GAMEOVER图标
    public void paint(Graphics g){
        super.paintComponent(g);
        
        g.drawImage((new ImageIcon("src/resources/background.jpg")).getImage(), 0, 0, this);
        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 25));
        g.drawString("得分: " + score + "  " + "剩余子弹：" + bullets, 690, 50);

        if(state == RUNNING){
            g.drawImage(bird.getImage(), bird.getX(), bird.getY(), this);
        }
        if(state == GAME_OVER){
            g.drawImage(new ImageIcon("src/resources/gameover.png").getImage(), 120, 150, this);
        }
    }
    
//    启动线程
    @Override
    public void run() {
        try{
            while(bird.getX() <= this.getWidth()){   
                if(!bird.getIsShot() && bullets > 0){
                    //飞鸟正常运动
                    bird.fly();
                    repaint();
                    Thread.sleep(INTERVAL);    
                }else{
                    //Game over界面
                    bird.setX(-70);
                    bird.setY(-50);
                    state = GAME_OVER;
                    repaint();
                    add(start);
                    break;
                }
            }
        }catch ( InterruptedException e ){ }
    }
    
//    创建飞鸟线程
    public void addBirds() {
        
            Thread t = new Thread(this);
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
}
