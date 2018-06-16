/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdsshot;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author 祥
 */
public class StartButton extends JButton{
    
    ImageIcon im;
    
    public StartButton(ImageIcon im){
        this.im = im;
        this.setBounds(410, 260, 168, 168);
        this.setBorderPainted(false);
        this.setIcon(im);
    }   
}
