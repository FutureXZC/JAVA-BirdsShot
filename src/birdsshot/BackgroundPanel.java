/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdsshot;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

/**
 *
 * @author 祥
 */
public class BackgroundPanel extends JPanel{
    
    Image bg;  
    
    public BackgroundPanel(Image bg){  
        this.bg = bg;  
        this.setOpaque(true);
        this.setBounds(0, 0, 1000, 600);
    }  
   
    public void paintComponent(Graphics g){  
        super.paintComponents(g);  
        g.drawImage(bg,0,0,this.getWidth(),this.getHeight(),this);
    }
}
