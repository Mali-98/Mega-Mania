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
public class Death extends GameObject
{
    private float width=103,height=128;
    private int health;
    private Handler handler;
    private int facing=-1;
    private int bump = -1;
    private int DeathBallNumber;
    private BufferedImage Death;
    private BufferedImage DeathRight;
    private boolean collide;
    private int bulletCooldown;

    public Death(Handler handler,BufferedImage Death,BufferedImage DeathRight, float x, float y, ObjectId id) {
        super(x, y, id);
        health=100;
        this.handler = handler;
        this.Death=Death;
        this.DeathRight=DeathRight;
    }

    @Override
    public void tick(ArrayList<GameObject> object) 
    {
        if(health>0 && Game.lives>0)
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
        x+=5;
    }
    public void moveLeft()
    {
        x-=5;
    }
    public void moveUp()
    {
        y-=2;
    }
    public void moveDown()
    {
        y+=2;
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
                    setHealth(health-7);
                }
            }

            if(tempObject.getId()==ObjectId.BulletShield)
            {                
                if(getBounds().intersects(tempObject.getBounds()))
                {
                    setHealth(health-2);
                }
            }

            /*if(tempObject.getId()==ObjectId.PlayerHomingBullet) //Failed Attempt in homing bullet
            {   
                for (int j = 0; j < handler.object.size(); j++) 
                {
                    GameObject otherObject = handler.object.get(j);
                    if (otherObject.getId() == ObjectId.Death) 
                    {
                        if(otherObject.x>tempObject.x)
                        {
                            tempObject.x+=6;
                        }
                        if(otherObject.x<tempObject.x)
                        {
                            tempObject.x-=6;
                        }
                        if(otherObject.y>tempObject.y)
                        {
                            tempObject.y+=6;
                        }
                        if(otherObject.y<tempObject.y)
                        {
                            tempObject.y-=6;
                        }
                    }
                }  
                if(getBounds().intersects(tempObject.getBounds()))
                {
                    handler.removeObject(tempObject);
                    setHealth(health-7);
                }
            }*/

            if (tempObject.getId() == ObjectId.Bullet) {
                // Check for collision with EnemyHomingBullet
                for (int j = 0; j < handler.object.size(); j++) {
                    GameObject otherObject = handler.object.get(j);
                    if (otherObject.getId() == ObjectId.EnemyHomingBullet) {
                        if (tempObject.getBounds().intersects(otherObject.getBounds())) {
                            // Remove the EnemyHomingBullet
                            handler.removeObject(otherObject);
                            DeathBallNumber--;
                        }
                    }
                }
            }

            if (tempObject.getId() == ObjectId.BulletShield) {
                // Check for collision with EnemyHomingBullet
                for (int j = 0; j < handler.object.size(); j++) {
                    GameObject otherObject = handler.object.get(j);
                    if (otherObject.getId() == ObjectId.EnemyHomingBullet) {
                        if (tempObject.getBounds().intersects(otherObject.getBounds())) {
                            // Remove the EnemyHomingBullet
                            handler.removeObject(otherObject);
                            DeathBallNumber--;
                        }
                    }
                }
            }

            /*if (tempObject.getId() == ObjectId.PlayerHomingBullet) //Failed Attempt in homing bullet
            {
                // Check for collision with EnemyHomingBullet
                for (int j = 0; j < handler.object.size(); j++) {
                    GameObject otherObject = handler.object.get(j);
                    if (otherObject.getId() == ObjectId.EnemyHomingBullet) 
                    {
                        if(otherObject.x>tempObject.x)
                        {
                            tempObject.x+=6;
                        }
                        if(otherObject.x<tempObject.x)
                        {
                            tempObject.x-=6;
                        }
                        if(otherObject.y>tempObject.y)
                        {
                            tempObject.y+=6;
                        }
                        if(otherObject.y<tempObject.y)
                        {
                            tempObject.y-=6;
                        }
                        if (tempObject.getBounds().intersects(otherObject.getBounds())) 
                        {
                            // Remove the EnemyHomingBullet
                            handler.removeObject(otherObject);
                            handler.removeObject(tempObject);
                            DeathBallNumber--;
                            KeyInput.playerHomingBulletNumber--;
                        }
                    }
                }
            }*/

            if (tempObject.getId() == ObjectId.EnemyHomingBullet) {
                // Check for collision with EnemyHomingBullet
                for (int j = 0; j < handler.object.size(); j++) {
                    GameObject otherObject = handler.object.get(j);
                    if (otherObject.getId() == ObjectId.Block) {
                        if (tempObject.getBounds().intersects(otherObject.getBounds())) {
                            // Remove the EnemyHomingBullet
                            tempObject.x = otherObject.x;
                            tempObject.y = otherObject.y;
                        }
                    }
                }
            }

            if (tempObject.getId() == ObjectId.EnemyHomingBullet) {
                // Check for collision with EnemyHomingBullet
                for (int j = 0; j < handler.object.size(); j++) {
                    GameObject otherObject = handler.object.get(j);
                    if (otherObject.getId() == ObjectId.Player) 
                    {
                        float playerX = otherObject.getX() + otherObject.getvelX();
                        float playerY = otherObject.getY() + otherObject.getvelY();
                        float deltaX = playerX - tempObject.x;
                        float deltaY = playerY - tempObject.y;
                        double angle = Math.atan2(deltaY, deltaX);
                        float speed = 2; // Adjust this value to control the speed
                        float newX = tempObject.x + speed * (float) Math.cos(angle);
                        float newY = tempObject.y + speed * (float) Math.sin(angle);
                        tempObject.x = newX;
                        tempObject.y = newY;
                        if(otherObject.x>tempObject.x)
                        {
                            tempObject.x+=4.2;
                        }
                        if(otherObject.x<tempObject.x)
                        {
                            tempObject.x-=4.2;
                        }
                        if(otherObject.y>tempObject.y)
                        {
                            tempObject.y+=2.6;
                        }
                        if(otherObject.y<tempObject.y)
                        {
                            tempObject.y-=2.6;
                        }
                    }
                }
            }
               
            //collision with player code, plays a sound and ends the game with a loss
            if(tempObject.getId()==ObjectId.Player)
            {
                if(health>0 && Game.lives>0&&Game.powerup2==0)
                {
                    if(DeathBallNumber<2)
                    {
                    if (bulletCooldown <= 0) 
                    {
                        try {
                            handler.addObject(new EnemyHomingBullet(ImageIO.read(new File("image\\DeathBall.png")), x, y, 2, tempObject.getX(), tempObject.getY(), ObjectId.EnemyHomingBullet));
                            DeathBallNumber++;
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        //handler.addObject(new EnemyHomingBullet(ImageIO.read(new File("image\\DeathBall.png")), bulletX, bulletY, 10, 10, ObjectId.EnemyBullet));
                        //handler.addObject(new EnemyHomingBullet(ImageIO.read(new File("image\\DeathBall.png")), bulletX, bulletY, 10, -10, ObjectId.EnemyBullet));
                        bulletCooldown = 70; // Adjust the cooldown value as desired
                    } else {
                        bulletCooldown--;
                    }
                    }
                    else{}
                }
                if(getBounds().intersects(tempObject.getBounds()))
                {
                    if(getHealth()>0)
                    {
                    ((Player) tempObject).setHealth(((Player) tempObject).getHealth() - 25);
                    if(((Player) tempObject).getHealth()<0)
                    {
                    KeyInput.BulletShieldAllower=1;
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
                    KeyInput.BulletShieldAllower=1;
                    handler.clearLevel();
                   Game gg=new Game();
                   gg.init(++Game.LEVEL);
                   break;
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
            g.drawImage(DeathRight,(int)x,(int)y,103,128,null);
        }
         if(facing==-1)
        {
            g.drawImage(Death,(int)x,(int)y,103,128,null);
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