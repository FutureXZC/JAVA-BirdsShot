/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdsshot;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author 祥
 */
public class Birds extends Thread{
    
    private int x, y, vx, vy;//鸟最左上角的点运动到当前位置的横坐标、纵坐标、水平速度、垂直速度
    private int id;//鸟的编号
    private Image birdsImg;//鸟的图片
    private JPanel birdsPanel;
    
    //构造函数，初始化参数
    public Birds(int x, int y, int vx, int vy, BackgroundPanel bg, int id){
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.birdsImg = new ImageIcon("src/resources/bird.png").getImage();
        this.id = id;
        birdsPanel = bg;
    }
    
    //重写run方法，让鸟动起来
    public void run(){
        super.run();
    }
    
    //各个参数的设置与获取
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
}
