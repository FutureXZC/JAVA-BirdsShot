/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdsshot;

//import static birdsshot.BirdsShot.background;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author 祥
 */
public class Background extends JPanel{
    Image bg;  
    
    public Background(Image bg){  
        this.bg=bg;  
        this.setOpaque(true);//设置控件不透明
    }  
   
    public void paintComponent(Graphics g){  
        super.paintComponents(g);  
        g.drawImage(bg,0,0,this.getWidth(),this.getHeight(),this);
    }
}
