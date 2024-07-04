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
public class Demon extends GameObject
{
    private float width=103,height=128;
    private int health;
    private static int demonsKilled=0;
    private Handler handler;
    private int facing=-1;
    private int bump = -1;
    private BufferedImage Demon;
    private BufferedImage DemonRight;
    private boolean collide;

    public Demon(Handler handler,BufferedImage Demon,BufferedImage DemonRight, float x, float y, ObjectId id) {
        super(x, y, id);
        health=100;
        this.handler = handler;
        this.Demon=Demon;
        this.DemonRight=DemonRight;
    }
    @Override
    public void tick(ArrayList<GameObject> object) 
    {
        if(health>0 && Game.lives>0 && KeyInput.TimeStopAllower==1)
        {
        // Right Collision
        if(facing==1 && bump==-1)
        {
            moveUp();
            moveRight();

        }
        else if (facing==-1 && bump==-1)
        {
            moveUp();
            moveLeft();
        }
        else if (facing==1 && bump==1)
        {
            moveDown();
            moveRight();
        }
        else if (facing==-1 && bump==1)
        {
            moveDown();
            moveLeft();
        }
        }
        else
        {
            moveDown();
        }
                          
       Collision(object);
    }
    
    public void moveRight()
    {
        x+=10;
    }
    public void moveLeft()
    {
        x-=10;
    }
    public void moveUp()
    {
        y-=5;
    }
    public void moveDown()
    {
        y+=5;
    }
    
    private void Collision(ArrayList<GameObject>object)
    {   
        for(int i=0;i<handler.object.size();i++)
        {
            GameObject tempObject=handler.object.get(i);
            if(tempObject.getId()==ObjectId.Block)
            {
                if(getBoundsTop().intersects(tempObject.getBounds()))
               {
                   y=tempObject.getY()+32;
                   velY=0;         
                   bump=1;         
               }
                //for bottom collision
                if(getBounds().intersects(tempObject.getBounds()))
               {
                   y=tempObject.getY()-height;
                   bump=-1;
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
                    setHealth(health-20);
                }
            }
               
            //collision with player code, plays a sound and ends the game with a loss
            if(tempObject.getId()==ObjectId.Player)
            {
                if(getBounds().intersects(tempObject.getBounds()))
                {
                    if(getHealth()>0)
                    {
                    ((Player) tempObject).setHealth(((Player) tempObject).getHealth() - 25);
                    if(((Player) tempObject).getHealth()<0)
                    {
                    KeyInput.BulletShieldAllower=1;
                    KeyInput.TimeStopAllower=1;
                    KeyInput.power=0;
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
                        object.remove(this);
                        demonsKilled++;
                        System.out.println(demonsKilled);
                        if(demonsKilled>=2)
                        {
                        handler.clearLevel();
                        Game gg=new Game();
                        gg.init(++Game.LEVEL);
                        }
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
            g.drawImage(DemonRight,(int)x,(int)y,103,128,null);
        }
         if(facing==-1)
        {
            g.drawImage(Demon,(int)x,(int)y,103,128,null);
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