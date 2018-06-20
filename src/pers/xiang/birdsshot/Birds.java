/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pers.xiang.birdsshot;

import java.awt.Image;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 *
 * @author 祥
 */
public class Birds{
    
    private static int x, y, vx, vy;//鸟最左上角的点运动到当前位置的横坐标、纵坐标、水平速度、垂直速度
    private static int id;//鸟的编号
    private static Image birdsImg;//鸟的图片
    private static boolean isShot;//判断鸟是否被击中的标记
    private static int t;//飞鸟下落的时间标记
    
//    构造函数，初始化参数
    public Birds(int id){
        Random rand = new Random();
        this.x = -70;//飞鸟从屏幕最左侧飞出
        
//        飞鸟出现点的纵坐标、飞行的速度均为随机的
        this.y = rand.nextInt(400)+1;//【1，400】
        this.vx = rand.nextInt(23)+5;//【5，28】
        this.vy = rand.nextInt(17)+8;//【8，25】
        this.birdsImg = new ImageIcon("src/resources/images/bird.png").getImage();
        this.id = id;//编号
        this.isShot = false;//生成鸟时，初始化其未被击中
        this.t = 1;
    }
    
//    使用 正弦函数曲线 作为飞行轨迹
    public void fly(){
        if(x <= 1000){
            x += vx;
        }
        if(y <= 640){
            y += Math.sin(x) * vy;
        }
    }
    
//    使用 平抛曲线 作为飞鸟被击落的轨迹
    public void drop(){
        if(x <= 1000){
            x += vx;
        }
        if (y <= 640){
            y += t * 9.8;
            t++;
        }
    }
    
//    各个参数的设置与获取
    public int getX() {  
        return x;  
    }  
  
    public void setX(int x) {  
        this.x = x;  
    }  
  
    public int getY() {  
        return y;  
    }  
  
    public void setY(int y) {  
        this.y = y;  
    }  
  
    public int getVx() {  
        return vx;  
    }  
  
    public void setVx(int vx) {  
        this.vx = vx;  
    }  
  
    public int getVy() {  
        return vy;  
    }  
  
    public void setVy(int vy) {  
        this.vy = vy;  
    }  
    
    public Image getImage(){
        return this.birdsImg;
    }
    
    public boolean getIsShot(){
        return isShot;
    }
    
    public void setIsShot(boolean is){
        isShot = is;
    }
}
