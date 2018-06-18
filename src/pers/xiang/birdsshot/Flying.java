/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pers.xiang.birdsshot;

import java.util.ArrayList;
import java.util.List;
import static pers.xiang.birdsshot.BackgroundPanel.bullets;
import static pers.xiang.birdsshot.BackgroundPanel.start;

/**
 *
 * @author 祥
 */
public class Flying implements Runnable{

//    public static Birds bird;//当前的鸟（传入）
    private List<Birds> birdsList;
    public static int bulets;//子弹数目（传入）
    public static BackgroundPanel comp;//调用的面板
    public static final int INTERVAL =  50;
    private static final int GAME_OVER = 3;//游戏失败
    
    public Flying(List<Birds> b, int bullets, BackgroundPanel bg){
        comp = bg;
        bulets = bullets;
        birdsList = b;
    }
    
    @Override
    public void run() {
        
        try{
            while(true){
                Birds thisBird = birdsList.get(comp.getBirdsIndex());
                //System.out.println(thisBird);
                System.out.println(birdsList.get(comp.getBirdsIndex()) + ".y = " + birdsList.get(comp.getBirdsIndex()).getY());
//                子弹数大于零 且 剩余飞鸟数不为0
                if(bullets > 0  && comp.getBirdNum() > 0){
//                    飞鸟未被射中
                    if(!thisBird.getIsShot()){
 //                        飞鸟未飞出屏幕
                        if(thisBird.getX() <= comp.getWidth()){
                            //飞鸟正常运动
                           thisBird.fly();
                           comp.repaint();
                           Thread.sleep(INTERVAL);     
                        }else{
                            comp.setBirdIndex();
                            comp.setBirdNum();
                            comp.repaint();
                            System.out.println(comp.getBirdsIndex());
                            thisBird = new Birds(comp.getBirdsIndex());
                            continue; 
                        }    
                    }else{
                            comp.setBirdIndex();
                            comp.setBirdNum();
                            comp.repaint();
                            System.out.println(comp.getBirdsIndex());
                            thisBird = new Birds(comp.getBirdsIndex());
                            continue; 
                    }
                    
                //没子弹了 或 没有剩余飞鸟了  Game over界面  
                }else{
                    thisBird.setX(-70);
                    thisBird.setY(-50);
                    comp.setState(GAME_OVER);
                    comp.repaint();
                    start.setVisible(true);
                    break;
                }
            }
        }catch ( InterruptedException e ){ }
    } 
}
