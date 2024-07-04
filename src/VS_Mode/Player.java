package VS_Mode;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import mega.man.GameObject;
import mega.man.Handler;
import mega.man.ObjectId;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;


public abstract class Player extends GameObject{
    
    
    private float width=48,height=96;
    private float gravity=0.5f;
    private final float MAX_SPEED=10;
    private Handler handler;
    
    private BufferedImage[] Hero_Running;
    private BufferedImage[] ReverseHero_Running;
    private float imageCount;
    
    private BufferedImage JumpingRight;
    private BufferedImage JumpingLeft;
    private BufferedImage hero;
    
    public Player(BufferedImage hero,BufferedImage JumpingRight,BufferedImage JumpingLeft,BufferedImage[]Hero_Running,BufferedImage[] ReverseHero_Running,float x,float y,Handler handler, ObjectId id)
    {
        super(x, y, id);
        this.handler=handler;
        this.hero=hero;
        this.JumpingRight=JumpingRight;
        this.JumpingLeft=JumpingLeft;
        this.Hero_Running=Hero_Running;
        this.ReverseHero_Running = ReverseHero_Running;
        imageCount=0;
    }

    @Override
    public void tick(ArrayList<GameObject> object) 
    {
        x+=velX;
        y+=velY;
        if(falling||jumping)
        {
            velY+=gravity;
            if(velY>MAX_SPEED)
                velY=MAX_SPEED;
        }
        Collision(object);
    }
    
    private void Collision(ArrayList<GameObject>object)
    {
        for(int i=0;i<handler.object.size();i++)
        {
           GameObject tempObject=handler.object.get(i);
           
           if(tempObject.getId()==ObjectId.Block)
           {
               // Top Collision
                if(getBoundsTop().intersects(tempObject.getBounds()))
               {
                   y=tempObject.getY()+32;
                   velY=0;                  
               }
                //Bottom Collision
               if(getBounds().intersects(tempObject.getBounds()))
               {
                   y=tempObject.getY()-height;
                   velY=0;
                   falling=false;
                   jumping=false;
               }
               else
               {
                   falling=true;
               }
               // Right Collision
               if(getBoundsRight().intersects(tempObject.getBounds()))
               {
                   x=tempObject.getX()-width;
               }
               // Left Collision
               if(getBoundsLeft().intersects(tempObject.getBounds()))
               {
                   x=tempObject.getX()+35;
               }
               
           }
           if(tempObject.getId()==ObjectId.Spike)
           {
               if(getBounds().intersects(tempObject.getBounds()))
               {
                    String filename = "sounds\\Evil Laugh.wav";
                    InputStream in = null;
                    try 
                    {
                        in = new FileInputStream(filename);
                    }
                    catch (FileNotFoundException ex) 
                    {
                        System.out.println("File not found");
                    }
                    try 
                    {
                        AudioStream s = new AudioStream(in);
                        AudioData MD;
                        AudioPlayer.player.start(s);
                    } 
                    catch (IOException ex) 
                    {
                        System.out.println(ex.getMessage());
                    }
                    JOptionPane.showMessageDialog(null,"You Are Dead!!!");
                    System.exit(0);
               }
           }
                      
        }
    }

    //movement animation
    @Override
    public void render(Graphics g) 
    {
        if(jumping)
        {
          if(velX>0)
                g.drawImage(JumpingRight,(int)x,(int)y,48,96,null);
          if(velX<0)
                g.drawImage(JumpingLeft,(int)x,(int)y,48,96,null);
          else
                g.drawImage(JumpingRight,(int)x,(int)y,48,96,null);

        }
         else
        {
            if(velX==0)
                g.drawImage(hero,(int)x,(int)y,48,96,null);

            else if(velX>0)
            {            
                g.drawImage(this.Hero_Running[(int)imageCount],(int)x,(int)y,48,96,null);
                imageCount = imageCount + 0.03f;
                if(imageCount > Hero_Running.length-1)
                    imageCount = 0;                
            }
            else if(velX<0)
            {            
                g.drawImage(this.ReverseHero_Running[(int)imageCount],(int)x,(int)y,48,96,null);
                imageCount = imageCount + 0.03f;
                if(imageCount > ReverseHero_Running.length-1)
                    imageCount = 0;                
            }
        }
        
    }

    @Override
    public Rectangle getBounds() 
    {
        return new Rectangle((int) ((int)x+(width/2)-((width/2)/2)), (int) ((int)y+(height/2)),(int)width/2,(int)height/2);
    }
    
     public Rectangle getBoundsRight() 
    {
        return new Rectangle((int) ((int)x+width-5),(int)y+5,(int)5,(int)height-10);
    }
    
      public Rectangle getBoundsLeft() 
    {
        return new Rectangle((int)x,(int)y+5,(int)5,(int)height-10);
    }
    
       public Rectangle getBoundsTop() 
    {
        return new Rectangle((int) ((int)x+(width/2)-((width/2)/2)),(int)y,(int)width/2,(int)height/2);
    }
    
   
}