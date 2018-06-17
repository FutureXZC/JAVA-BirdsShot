/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdsshot;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author 祥
 */
public class BackgroundPanel extends JPanel{
    
//    Image bg;  
    ImageIcon bg;
    
    public BackgroundPanel(ImageIcon bg){  
        this.bg = bg;
        this.setOpaque(true);
        this.setBounds(0, 0, 1000, 640);
    }  
   
    public void paintComponent(Graphics g){  
        super.paintComponents(g);  
        bg.paintIcon(this, g,0, 0);
        //g.drawImage(bg,0,0,this.getWidth(),this.getHeight(),this);
    }
}
