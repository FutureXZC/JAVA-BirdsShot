/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdsshot;

import java.awt.image.BufferedImage;
import java.util.Timer;
import javax.imageio.ImageIO;

/**
 *
 * @author 祥
 */
public class BirdsShot {
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
    
    public static BufferedImage background;
    public static BufferedImage start;
    public static BufferedImage bird;
    public static BufferedImage Bullet;
    public static BufferedImage pause;
    public static BufferedImage gameover;

    private int bullets =0;//子弹数量
    private int birdNum = 0;//鸟的数量
    
    //初始化图片资源
    static {
        try {
            background = ImageIO.read(BirdsShot.class.getResource("background.jpg"));
            start = ImageIO.read(BirdsShot.class.getResource("start.png"));
            bird = ImageIO.read(BirdsShot.class.getResource("bird.png"));
            pause = ImageIO.read(BirdsShot.class.getResource("pause.png"));
            gameover = ImageIO.read(BirdsShot.class.getResource("gameover.png"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
