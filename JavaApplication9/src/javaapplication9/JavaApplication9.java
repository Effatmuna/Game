
package javaapplication9;

import java.awt.PopupMenu;
import javax.swing.JFrame;
 
public class JavaApplication9 {


    public static void main(String[] args) {
        
        JFrame obj= new JFrame();
        GamePlay game = new GamePlay();
        obj.setBounds(10,0,700,600);
        obj.setTitle("Breakout Ball");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
         obj.add(game);
    }
    
}
       
