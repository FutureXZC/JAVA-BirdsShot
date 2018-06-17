/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdsshot;

import java.awt.Font;
import static java.awt.Font.BOLD;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author 祥
 */
public class ScorePanel extends JPanel{
    
    private int score;
    private int bullets;
    private Font f;
    private JLabel scoreLabel;
    private JLabel bulletsLeftJLabel;
    
    public ScorePanel(int score, int bullets){
        
        this.bullets = bullets;
        this.score = score;
        f = new Font("Microsoft YaHei", BOLD, 20);
        scoreLabel = new  JLabel();
        scoreLabel.setFont(f);
        scoreLabel.setText("得分: " + this.score);
        scoreLabel.setBounds(100, 30, 100, 50);
        
        bulletsLeftJLabel = new JLabel();
        bulletsLeftJLabel.setFont(f);
        bulletsLeftJLabel.setText("剩余子弹：" + this.bullets);
        bulletsLeftJLabel.setBounds(0, 20, 100, 50);
        
        this.add(scoreLabel);
        this.add(bulletsLeftJLabel);
        this.setBounds(750, 10, 210, 40);
    }
}
