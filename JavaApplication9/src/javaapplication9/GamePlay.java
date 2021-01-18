
package javaapplication9;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;


public class GamePlay extends JPanel implements KeyListener,ActionListener{
    private boolean play= false;
      private int score = 0;
      private int totalbricks=21;
       private Timer timer;
       private int delay=8;
    private int playerX= 310;
      private int bposX=120;
     private int bposY= 350;
      private int bdiX= -1;
        private int bdiY= 2;
        private MapGenerator map;
        
     public GamePlay(){
         map=new MapGenerator(3,7);
         addKeyListener(this);
         setFocusable(true);
         setFocusTraversalKeysEnabled(false);
         timer = new Timer(delay,this);
         timer.start();
    
}
     public void paint(Graphics g){
         g.setColor(Color.black);
           g.fillRect(1,1,692,592);
           map.draw((Graphics2D)g);
           g.setColor(Color.white);
           g.setFont(new Font("serif",Font.BOLD,25));
           g.drawString(""+score, 590, 30);
           //
             g.setColor(Color.yellow);
              g.fillRect(0,0,3,592);
               g.fillRect(0,0,692,3);
                 g.fillRect(691,0,3,592);
                 //
                   g.setColor(Color.green);
                      g.fillRect(playerX,550,100,8); 
                    //
                        g.setColor(Color.yellow);
                       g.fillOval(bposX,bposY,20,20);  
                       if(totalbricks<=0){
                            play = false;
                           bdiX=0;
                           bdiY=0;
                           g.setColor(Color.red);
                           g.setFont(new Font("serif",Font.BOLD,30));
                           g.drawString("You Won!",260,300);
                            g.setFont(new Font("serif",Font.BOLD,20));
                               g.drawString("Press Enter to Restart", 230, 350);
                       }
                       if(bposY>570){
                           play = false;
                           bdiX=0;
                           bdiY=0;
                           g.setColor(Color.red);
                           g.setFont(new Font("serif",Font.BOLD,30));
                           g.drawString("Game over,Scores:",190,300);
                            g.setFont(new Font("serif",Font.BOLD,20));
                               g.drawString("Press Enter to Restart", 230, 350);
                           
                       }
                       g.dispose();
     }

    
   @Override
    public void keyTyped(KeyEvent ke) {
      
    }

    @Override
    public void keyPressed(KeyEvent ke) {
       if(ke.getKeyCode()== KeyEvent.VK_RIGHT){
           if(playerX>=600){
               playerX=600;
           }
           else{
               moveRight();
           }
           
       }
        if(ke.getKeyCode()== KeyEvent.VK_LEFT){
             if(playerX<10){
               playerX=10;
           }
           else{
               moveLeft();
           }
           
           
       }
        if(ke.getKeyCode()==KeyEvent.VK_ENTER){
            play=true;
            bposX=350;
            bposY=120;
            bdiX=-1;
            bdiY=-2;
            playerX=310;
            score=0;
            totalbricks=21;
            map=new MapGenerator(3,7);
            repaint();
            
            
        }
 }
      public void moveRight(){
          play = true;
          playerX+=20;
      }
    public void moveLeft(){
          play = true;
          playerX-=20;
      }
    



    @Override
    public void actionPerformed(ActionEvent ae) {
        timer.start();
        if(play){
            if(new Rectangle(bposX,bposY,20,20).intersects(new Rectangle(playerX,550,100,8))){
        bdiY=-bdiY;
    }
            for(int i=0;i<map.map.length;i++){
                for(int j=0;j<map.map[0].length;j++){
                    if(map.map[i][j]>0){
                        int brickX=j*map.brickWidth +80;
                        int brickY= i*map.brickHight+50;
                        int brickWidth=map.brickWidth;
                        int brickHight=map.brickHight;
                        Rectangle rect =new Rectangle(brickX,brickY,brickWidth,brickHight);
                        Rectangle bRect =  new Rectangle(bposX,bposY,20,20);
                        Rectangle brickRect =rect;
                        if(bRect.intersects(brickRect)){
                            map.setBrickValue(0, i, j);
                            totalbricks--;
                            score+=5;
                            if(bposX+19<=brickRect.x || bposX+1>=brickRect.x+brickRect.width){
                                bdiX=-bdiX;
                            }
                            else{
                                bdiY=-bdiY;
                            }
                        }
                    }
                    
                }
            }
            bposX+=bdiX;
            bposY+=bdiY;  
        if(bposX<0){
             bdiX=-bdiX;
        }
        if(bposY<0){
             bdiY=-bdiY;
        }
        if(bposX>670){
             bdiX=-bdiX;
        }
        }
          repaint();
    }
 
  
    
    @Override
    public void keyReleased(KeyEvent ke){}
   
   
        
    
}
