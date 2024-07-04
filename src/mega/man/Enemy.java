package mega.man;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 * 
 * @author 0
 * Contains all info, variables, movement animations, and description for the enemies
 */
public class Enemy extends GameObject implements Serializable
{
    private float width=100,height=100;
    private Handler handler;
    private BufferedImage Enemy_Right;
    private BufferedImage Enemy_Left;
    private int facing=-1;

    public Enemy(Handler handler, BufferedImage Enemy_Right,BufferedImage Enemy_Left, float x, float y, ObjectId id) 
    {
        super(x, y, id);
        this.handler = handler;
        this.Enemy_Right = Enemy_Right;
        this.Enemy_Left = Enemy_Left;
    }

    @Override
    public void tick(ArrayList<GameObject> object) 
    {
        // Right Collision
        if(Game.lives>0 && KeyInput.TimeStopAllower==1)
        {
        if(facing==1)
            moveRight();
        else
            moveLeft();
        }
        else
        {
            
        }
                          
       Collision(object);
    }
    
    public void moveRight()
    {
        x+=2.5;
    }
    public void moveLeft()
    {
        x-=2.5;
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
        //changes pic of enemy monster facing right or facing left
        if(facing==1)
        {
            g.drawImage(Enemy_Right,(int)x,(int)y,100,100,null);
        }
         if(facing==-1)
        {
            g.drawImage(Enemy_Left,(int)x,(int)y,100,100,null);
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
}
