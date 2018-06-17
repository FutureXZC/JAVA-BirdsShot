/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdsshot;

import java.awt.Color;
import java.awt.Container;
import java.util.Timer;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author 祥
 */
public class BirdsShotGame extends JFrame{
    
    public static final int WIDTH = 1000;//面板宽
    public static final int HEIGHT = 640;//面板高
    
    private int state;//游戏的当前状态
    private static final int START = 0;//开启游戏
    private static final int RUNNING = 1;//正在游戏
    private static final int PAUSE = 2;//暂停游戏
    private static final int GAME_OVER = 3;//游戏失败
    
    public static ScorePanel scoreBoard;//记分板
    private int score = 0;//分数
    private Timer timer;//计时器
    private int interval = 1000;//时间间隔（ms)
    
    //Container ct;//容器
    public static BackgroundPanel background;
    public static StartButton start;
    public static Birds bird;
    //public static BufferedImage gameover;
    //public static BufferedImage aim;

    private int bullets =0;//子弹数量
    private int birdNum = 0;//鸟的数量
    
            
    public BirdsShotGame(){
        
        background = new BackgroundPanel((new ImageIcon("src/resources/background.jpg")));
        start = new StartButton(new ImageIcon("src/resources/start.png"));
        bird = new Birds(0, 0, 70, 50, background, 0);
        scoreBoard = new ScorePanel(score, bullets);
            //gameover = ImageIO.read(BirdsShot.class.getResource("gameover.png"));
            //aim = ImageIO.read(BirdsShot.class.getResource("aim.png"));
        
        //初始化界面
        //ct = this.getContentPane();
        this.setLayout(null);
        this.setSize(WIDTH, HEIGHT);
        this.setResizable(false);
        this.setLocationRelativeTo(null);//居中  
        this.setTitle("JavaGame-BirdsShot");  
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(new ImageIcon("src/resources/icon.png").getImage());
        
//      添加背景和开始按钮
        this.add(scoreBoard);
        this.add(start); 
        this.add(background);
        //设置游戏状态为初始状态
        this.state = START;
        this.setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new BirdsShotGame();
    }
}
