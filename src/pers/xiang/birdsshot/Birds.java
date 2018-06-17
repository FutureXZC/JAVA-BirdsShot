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
    private Image birdsImg;//鸟的图片
    private boolean isShot = false;
    
//    构造函数，初始化参数
    public Birds(int id){
        Random rand = new Random();
        this.x = -70;
        this.y = rand.nextInt(400)+1;
        this.vx = rand.nextInt(20)+5;
        this.vy = rand.nextInt(16)+8;
        this.birdsImg = new ImageIcon("src/resources/bird.png").getImage();
        this.id = id;
    }
    
    public void fly(){
        if(x <= 1000){
            x += vx;
        }
        if(y <= 640){
            y += Math.sin(x) * vy;
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
