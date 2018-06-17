/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pers.xiang.birdsshot;

import static pers.xiang.birdsshot.BackgroundPanel.bullets;
import static pers.xiang.birdsshot.BackgroundPanel.start;

/**
 *
 * @author 祥
 */
public class Flying implements Runnable{

    public static Birds bird;//当前的鸟（传入）
    public static int bulets;//子弹数目（传入）
    public static BackgroundPanel comp;//调用的面板
    public static final int INTERVAL =  50;
    private static final int GAME_OVER = 3;//游戏失败
    
    public Flying(Birds b, int bullets, BackgroundPanel bg){
        comp = bg;
        bulets = bullets;
        bird = b;
    }
    
    @Override
    public void run() {
        try{
            while(true){   
                if(!bird.getIsShot() && bullets > 0 && bird.getX() <= comp.getWidth()){
                    //飞鸟正常运动
                    System.out.println(bird.getIsShot());
                    bird.fly();
                    comp.repaint();
                    Thread.sleep(INTERVAL);    
                }else{
                    //Game over界面
                    bird.setX(-70);
                    bird.setY(-50);
                    comp.setState(GAME_OVER);
                    comp.repaint();
                    start.setVisible(true);
                    break;
                }
            }
        }catch ( InterruptedException e ){ }
    } 
}
