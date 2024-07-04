package mega.man;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author 10
 */
public class LordSkull extends GameObject
{
    private float width=71,height=91;
    private int health;
    private Handler handler;
    private int facing=-1;
    private BufferedImage LordSkull;
    private BufferedImage LordSkullRight;
    private boolean collide;
    private float playerX;
    private float playerY;
    private double angle;
    private float speed; 

    public LordSkull(Handler handler,BufferedImage LordSkull,BufferedImage LordSkullRight, float x, float y, ObjectId id) {
        super(x, y, id);
        health=100;
        this.handler = handler;
        this.LordSkull=LordSkull;
        this.LordSkullRight=LordSkullRight;
    }

    @Override
    public void tick(ArrayList<GameObject> object) 
    {                  
       Collision(object);
    }
    
    private void Collision(ArrayList<GameObject>object)
    {   
        for(int i=0;i<handler.object.size();i++)
        {
            GameObject tempObject=handler.object.get(i);
            if(tempObject.getId()==ObjectId.Block)
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
            }
            
            if(tempObject.getId()==ObjectId.Bullet)
            {                
                if(getBounds().intersects(tempObject.getBounds()))
                {
                    handler.removeObject(tempObject);
                    setHealth(health-2);
                }
            }
               
            //collision with player code, plays a sound and ends the game with a loss
            if(tempObject.getId()==ObjectId.Player)
            {
                if(health>0 && Game.lives>0)
                {
                playerX = tempObject.getX();
                playerY = tempObject.getY();
                float deltaX = playerX - x;
                float deltaY = playerY - y;
                double angle = Math.atan2(deltaY, deltaX);
                float speed = 3.2f; // Adjust this value to control the speed
                float newX = x + speed * (float) Math.cos(angle);
                float newY = y + speed * (float) Math.sin(angle);
                x = newX;
                y = newY;
                if(x<playerX)
                {
                    facing=1;
                }
                else
                {
                    facing=-1;
                }
                }
                else
                {

                }
                if(getBounds().intersects(tempObject.getBounds()))
                {
                    if(getHealth()>0)
                    {
                    ((Player) tempObject).setHealth(((Player) tempObject).getHealth() - 25);
                    if(((Player) tempObject).getHealth()==0)
                    {
                    handler.clearLevel();
                    Game gg=new Game();
                    gg.init(Game.LEVEL);
                    if(Game.lives>0)
                    {
                        Game.lives--;
                    }
                    else
                    {
                        Game.lives=0;
                    }
                }
                }
                else
                {
                    handler.clearLevel();
                   Game gg=new Game();
                   gg.init(++Game.LEVEL);
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
            g.drawImage(LordSkullRight,(int)x,(int)y,71,91,null);
        }
         if(facing==-1)
        {
            g.drawImage(LordSkull,(int)x,(int)y,71,91,null);
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
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
    
}