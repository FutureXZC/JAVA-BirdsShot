/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdsshot;

import java.awt.Container;
import java.awt.image.BufferedImage;
import java.util.Timer;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author 祥
 */
public class BirdsShot extends JFrame{
    
    public static final int WIDTH = 1000;//面板宽
    public static final int HEIGHT = 640;//面板高
    
    private int state;//游戏的当前状态
    private static final int START = 0;
    private static final int RUNNING = 1;
    private static final int PAUSE = 2;
    private static final int GAME_OVER = 3;
    
    private int score = 0;//分数
    private Timer timer;//计时器
    private int interval = 1000/100;//时间间隔（ms)
    
    Container ct;//容器
    public static Background background;
    public static BufferedImage start;
    public static BufferedImage bird;
    public static BufferedImage gameover;
    public static BufferedImage aim;

    private int bullets =0;//子弹数量
    private int birdNum = 0;//鸟的数量
    
    //初始化图片资源
    static {
        try {
            start = ImageIO.read(BirdsShot.class.getResource("start.png"));
            bird = ImageIO.read(BirdsShot.class.getResource("bird.png"));
            gameover = ImageIO.read(BirdsShot.class.getResource("gameover.png"));
            aim = ImageIO.read(BirdsShot.class.getResource("aim.png"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public BirdsShot(){
        //添加背景图
        ct = this.getContentPane();
        background = new Background((new ImageIcon("src/resources/background.jpg")).getImage());
        background.setBounds(0, 0, 1000, 620);
        ct.add(background);
        
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);  
        this.setTitle("JavaGame-BirdsShot");  
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(new ImageIcon("src/resources/icon.png").getImage());
        this.setVisible(true); 
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new BirdsShot();
    }
}
