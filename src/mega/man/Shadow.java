package mega.man;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author 10
 */
public class Shadow extends GameObject
{
    private float width=103,height=128;
    private int health;
    private Handler handler;
    private int facing=-1;
    private int bump = -1;
    private BufferedImage Shadow;
    private BufferedImage ShadowRight;
    private boolean collide;

    public Shadow(Handler handler,BufferedImage Shadow,BufferedImage ShadowRight, float x, float y, ObjectId id) {
        super(x, y, id);
        health=100;
        this.handler = handler;
        this.Shadow=Shadow;
        this.ShadowRight=ShadowRight;
    }

    @Override
    public void tick(ArrayList<GameObject> object) 
    {
        if(health>0 && Game.lives>0)
        {
        // Right Collision
        if(facing==1)
        {
            moveRight();

        }
        else
        {
            moveLeft();
        }
        }                 
       Collision(object);
    }
    
    public void moveRight()
    {
        x+=5;
    }
    public void moveLeft()
    {
        x-=5;
    }
    
    private void Collision(ArrayList<GameObject>object)
    {   
        for(int i=0;i<handler.object.size();i++)
        {
            GameObject tempObject=handler.object.get(i);
            if(tempObject.getId()==ObjectId.Block || tempObject.getId()==ObjectId.DoorRock)
            {
                //for bottom collision
                if(getBounds().intersects(tempObject.getBounds()))
               {
                   y=tempObject.getY()-height;
               }
               else
               {
                   falling=true;
               }
                if(getBoundsRight().intersects(tempObject.getBounds()))
               {
                   facing=-1;
               }
               // Left Collision
               if(getBoundsLeft().intersects(tempObject.getBounds()))
               {
                   facing=1;
               }
            }
            
            if(tempObject.getId()==ObjectId.Bullet)
            {                
                if(getBounds().intersects(tempObject.getBounds()))
                {
                    handler.removeObject(tempObject);
                    handler.removeObject(this);
                }
            }

            if(tempObject.getId()==ObjectId.BulletShield)
            {                
                if(getBounds().intersects(tempObject.getBounds()))
                {
                    handler.removeObject(this);
                }
            }
               
            //collision with player code, plays a sound and ends the game with a loss
            if(tempObject.getId()==ObjectId.Player)
            {
                if(getBounds().intersects(tempObject.getBounds()))
                {
                    ((Player) tempObject).setHealth(((Player) tempObject).getHealth() - 5);
                    if(((Player) tempObject).getHealth()==0)
                    {
                    /*String filename = "sounds\\Evil Laugh.wav";
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
                    JOptionPane.showMessageDialog(null,"You Are Dead!!!!");
                    System.exit(0);*/
                    handler.clearLevel();
                    Game gg=new Game();
                    gg.init(Game.LEVEL);
                    Game.lives--;
                }
                }
            }          
        }    
    }
        
    @Override
    public void render(Graphics g) 
    {   
        if(getHealth()>0)
        {
        g.setColor(Color.RED);
        g.fillRect((int) x - 20, (int) y - 15, 100, 10); // draw the background of the health bar
        g.setColor(Color.GREEN);
        g.fillRect((int) x - 20, (int) y - 15, health, 10);
        }     
        //changes pic of enemy monster facing right or facing left
        if(facing==1)
        {
            g.drawImage(ShadowRight,(int)x,(int)y,103,128,null);
        }
         if(facing==-1)
        {
            g.drawImage(Shadow,(int)x,(int)y,103,128,null);
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
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
    
}