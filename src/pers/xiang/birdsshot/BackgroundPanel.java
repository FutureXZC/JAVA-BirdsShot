/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pers.xiang.birdsshot;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author 祥
 */
public class BackgroundPanel extends JPanel{

    public static JButton start;//开始按钮    
    public static int score = 0;//分数
    public static int bullets = 0;//子弹数

    private static int INTERVAL = 50;//时间间隔（ms)
    private static int birdNum = 5;//鸟的数量
    private static int mx = 0;//鼠标点击处的横坐标
    private static int my = 0;//鼠标点击处的纵坐标

    private List<Birds> birdsList = new ArrayList<Birds>();
    private static int BirdsIndex = 0;//飞鸟数组的索引
    
    private int state;//游戏的当前状态
    private static final int START = 0;//开启游戏
    private static final int RUNNING = 1;//正在游戏
    private static final int GAME_OVER = 2;//游戏失败
    
    public BackgroundPanel(){
  
//        初始化界面
        this.setLayout(null);
        this.setOpaque(true);
        this.setBounds(0, 0, 1000, 640);
        
        start = new JButton();
        start.setBounds(410, 260, 168, 168);
        start.setBorderPainted(false);
        start.setIcon(new ImageIcon("src/resources/start.png"));
        start.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.add(start); 
        
        state = START;//开启游戏
        
//        开始按钮的点击事件
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                state = RUNNING;
                start.setVisible(false);
                bullets = 10;//初始化子弹数目
                score = 0;//重置分数为0分
                birdNum = 5;//剩余飞鸟为5只
                BirdsIndex = 0;//从第一只开始飞
                for(int i = 0; i < birdNum; i++){
                    Birds b = new Birds(i);
                    birdsList.add(b);
                    System.out.println(birdsList.get(i) + ".yyy = " + birdsList.get(i).getY() + ".xxxx" + birdsList.get(i).getVx());
                }
                addBirds();
//                System.out.println("Vx = " + bird.getVx() + "," + "Vy = " + bird.getVy() + ", x = " + bird.getX() + ", y = " + bird.getY());//在控制台输出飞鸟的速度
//                System.out.println("Vx = " + birdsList.get(BirdsIndex).getVx() + "," + "Vy = " + birdsList.get(BirdsIndex).getVy() + ", x = " + birdsList.get(BirdsIndex).getX() + ", y = " + birdsList.get(BirdsIndex).getY());//在控制台输出飞鸟的速度
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
                        Birds thisBirds = birdsList.get(BirdsIndex);
                        int lx = thisBirds.getX(),rx = thisBirds.getX() + 70;
                        int ty = thisBirds.getY(),by = thisBirds.getY() + 50;
                        if(mx >= lx && mx <= rx && my >= ty && my <= by && !thisBirds.getIsShot()){
                            score++;
                            birdsList.get(BirdsIndex).setIsShot(true);
                        }
                        System.out.println("mx = " + mx + "," + "my = " + my + ", isShot = " + thisBirds.getIsShot());
                    }
                }
            }
        });
    }
    
//    绘制背景、记分板、飞鸟、GAMEOVER图标
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        g.drawImage((new ImageIcon("src/resources/background.jpg")).getImage(), 0, 0, this);
        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 25));
        g.drawString("得分: " + score + "  " + "剩余子弹：" + bullets, 690, 50);
        
        if(state == RUNNING){
            try {
                Birds thisBirds = birdsList.get(BirdsIndex);
                g.drawImage(thisBirds.getImage(), birdsList.get(BirdsIndex).getX(), birdsList.get(BirdsIndex).getY(), this);
//                System.out.println("Vx = " + thisBirds.getVx() + "," + "Vy = " + thisBirds.getVy() + ", x = " + thisBirds.getX() + ", y = " + thisBirds.getY());//在控制台输出飞鸟的速度
            } catch (Exception e){}
        }
        if(state == GAME_OVER){
            try {g.drawImage(new ImageIcon("src/resources/gameover.png").getImage(), 120, 150, this);
                System.out.println("00000000000000000");
            } catch (Exception e){}
        }
    }
    
//    创建飞鸟线程
    public void addBirds() {
        
        System.out.println(birdsList);
//        for(int i = 0; i < birdNum; i++){
            Flying f = new Flying(birdsList, bullets, this);
            Thread t = new Thread(f);
            t.start();
            try{Thread.sleep(INTERVAL);
            }catch ( InterruptedException e ){}
//       }  
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
        birdNum--;
    }

    public int getBirdsIndex() {
        return BirdsIndex;
    }
    
    public void setBirdIndex(){
        if(BirdsIndex < birdsList.size() - 1)
            BirdsIndex ++;
    }
}