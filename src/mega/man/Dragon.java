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
public class Dragon extends GameObject
{
    private float width=456,height=555;
    private int health;
    private int bulletCooldown;
    private float initialX;
    private Handler handler;
    private BufferedImage Dragon;
    private boolean collide;
    private boolean movingRight;

    public Dragon(Handler handler,BufferedImage Dragon, float x, float y, ObjectId id) {
        super(x, y, id);
        health=100;
        this.handler = handler;
        this.Dragon=Dragon;
        initialX = x;
    }

    @Override
    public void tick(ArrayList<GameObject> object) 
    {      
        if (health > 0 && Game.lives > 0 && KeyInput.TimeStopAllower==1) {
            // Right Collision
            /*if (movingRight) {
                moveRight();
                if (x >= initialX + 50) {
                    movingRight = false;
                }
            } else {
                moveLeft();
                if (x <= initialX) {
                    movingRight = true;
                }
            }*/
            moveRight();
            try {
                fireBullet(object);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }      
       Collision(object);
    }

    private void moveRight() {
        x += 2.7;
    }
    
    private void moveLeft() {
        x -= 5;
    }

    private void fireBullet(ArrayList<GameObject> object) throws IOException {
        // Fire a bullet at a certain interval
        if (bulletCooldown <= 0) { // Adjust the cooldown value as desired
            float bulletX = x + 400; // Adjust the bullet's initial X position as desired
            float bulletY = y + 150; // Adjust the bullet's initial Y position as desired
            //int bulletSpeedX = -10; // Adjust the bullet's speed as desired
            //int bulletSpeedY = 10; // Adjust the bullet's speed as desired
                    String filename = "sounds\\FireBall.wav";
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
    
            // Create a new bullet object for the dragon
            handler.addObject(new EnemyBullet(ImageIO.read(new File("image\\FireBall.png")), bulletX, bulletY, 10, -1, ObjectId.EnemyBullet));
            handler.addObject(new EnemyBullet(ImageIO.read(new File("image\\FireBall.png")), bulletX, bulletY, 10, 10, ObjectId.EnemyBullet));
            handler.addObject(new EnemyBullet(ImageIO.read(new File("image\\FireBall.png")), bulletX, bulletY, 10, -10, ObjectId.EnemyBullet));
            bulletCooldown = 70; // Adjust the cooldown value as desired
        } else {
            bulletCooldown--;
        }
    }
    
    private void Collision(ArrayList<GameObject>object)
    {   
        for(int i=0;i<handler.object.size();i++)
        {
            GameObject tempObject=handler.object.get(i);
            
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
                if(getBounds().intersects(tempObject.getBounds()))
                {
                    if(getHealth()>0)
                    {
                    ((Player) tempObject).setHealth(((Player) tempObject).getHealth() - 25);
                    if(((Player) tempObject).getHealth()<=0)
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
        g.drawImage(Dragon,(int)x,(int)y,456,555,null);
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