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

    private Birds thisBird;//当前飞鸟
    public static int bulets;//子弹数目（传入）
    public static BackgroundPanel comp;//调用的面板
    public static final int INTERVAL =  50;
    private static final int GAME_OVER = 2;//游戏失败
    
    public Flying(Birds b, int bullets, BackgroundPanel bg){
        comp = bg;
        bulets = bullets;
        thisBird = b;
    }
    
    @Override
    public void run() {
        
//        System.out.println("First bird: Vx = " + thisBird.getVx() + "," + "Vy = " + thisBird.getVy() + ", x = " + thisBird.getX() + ", y = " + thisBird.getY());//在控制台输出飞鸟的速度
        
        try{
            
            while(true){
//                System.out.println("This bird: Vx = " + thisBird.getVx() + "," + "Vy = " + thisBird.getVy() + ", x = " + thisBird.getX() + ", y = " + thisBird.getY());//在控制台输出飞鸟的速度
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
                           
                        //飞鸟飞出了屏幕
                        }else{
                            comp.setBirdIndex();
                            comp.setBirdNum();
                            comp.repaint();//重悔
                            thisBird = new Birds(comp.getBirdsIndex());
                            continue; 
                        }
                        
                    //飞鸟被击中了
                    }else{
                            comp.setBirdIndex();
                            comp.setBirdNum();
                            comp.repaint();//重悔
                            thisBird = new Birds(comp.getBirdsIndex());
                            continue; 
                    }
                    
                //没子弹了 或 没有剩余飞鸟了  Game over界面  
                }else{
                    thisBird.setX(-70);
                    thisBird.setY(-50);//将鸟的位置设置在屏幕外面，以隐藏最后这只鸟
                    comp.setState(GAME_OVER);
                    comp.repaint();
                    start.setVisible(true);//放回开始按钮，可以重新开始游戏
                    break;
                }
            }
        }catch ( InterruptedException e ){ }
    } 
}
