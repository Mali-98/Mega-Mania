package mega.man;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

import javax.imageio.ImageIO;

import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;



public class KeyInput extends KeyAdapter
{
    public static int power=0;
    public static int BulletShieldAllower=1;
    public static int BulletShieldMagic=100;
    public static int TimeStopAllower=1;
    public static int StopTimeMagic=100;
    int currentCooldown = 0;
    public static int playerHomingBulletNumber;
    Handler handler;
    public KeyInput(Handler handler)
    {
        this.handler=handler;
    }

    @Override
    public void keyPressed(KeyEvent ke) 
    {
       int key=ke.getKeyCode();
       for(int i=0;i<handler.object.size();i++)
       {
           GameObject tempObject=handler.object.get(i);
           if(tempObject.getId()==ObjectId.Player)
           {
                if(Game.lives>0&&Game.powerup2==0)
                {
                        //buttons for jumping and movement (which adjusts velocity)
                        if(key==KeyEvent.VK_RIGHT) 
                        {
                            tempObject.setvelX(5);
                        }
                        if(key==KeyEvent.VK_LEFT) 
                        {
                            tempObject.setvelX(-5);
                        }
                        if(key==KeyEvent.VK_UP&&!tempObject.isJumping())
                        {
                            tempObject.setJumping(true);
                            tempObject.setvelY(-14);
                        }
                    
                        // Code for firing weapon
                        // playes sound effect and creats a new object of the class bullet with 
                        //coordinates starting close to the player
                        if(key==KeyEvent.VK_SPACE && power==0 && BulletShieldAllower==1)
                        {
                            String filename = "sounds\\MegaBuster.wav";
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
                            if(tempObject.stance == 1)
                            {
                                handler.addObject(new Bullet(tempObject.getX()+30,tempObject.getY()+48 , (int) (tempObject.getvelX()*3),ObjectId.Bullet));
                            }
                            else if (tempObject.stance == -1)
                            {
                                handler.addObject(new Bullet(tempObject.getX()-10,tempObject.getY()+48 , -15,ObjectId.Bullet));
                            }
                            break;
                        }
                        if (key == KeyEvent.VK_SPACE && power == 1 && BulletShieldAllower==1 && BulletShieldMagic>0) 
                        {
                            BulletShieldAllower=0;
                            String filename = "sounds\\MegaBuster.wav";
                            BulletShieldMagic-=25;
                            InputStream in = null;
                            try {
                                in = new FileInputStream(filename);
                            } catch (FileNotFoundException ex) {
                                System.out.println("File not found");
                            }
                            try {
                                AudioStream s = new AudioStream(in);
                                AudioData MD;
                                AudioPlayer.player.start(s);
                            } catch (IOException ex) {
                                System.out.println(ex.getMessage());
                            }
                            if (tempObject.stance == 1 || tempObject.stance == -1) {
                                handler.addObject(new BulletShield((Player) tempObject, 300, 4, 15, 50, ObjectId.BulletShield));
                            }
                        }
                        if (key == KeyEvent.VK_SPACE && power == 2 && TimeStopAllower==1 && StopTimeMagic>0) 
                        {
                            TimeStopAllower=0;
                            StopTimeMagic-=50;
                            String filename = "sounds\\MegaBuster.wav";
                            InputStream in = null;
                            try {
                                in = new FileInputStream(filename);
                            } catch (FileNotFoundException ex) {
                                System.out.println("File not found");
                            }
                            try {
                                AudioStream s = new AudioStream(in);
                                AudioData MD;
                                AudioPlayer.player.start(s);
                            } catch (IOException ex) {
                                System.out.println(ex.getMessage());
                            }
                            if (tempObject.stance == 1 || tempObject.stance == -1) {
                                handler.addObject(new TimeStop(tempObject.getX()-10,tempObject.getY()+48 , 300,ObjectId.TimeStop));
                            }
                        }
                        /*if(key==KeyEvent.VK_SPACE && power==1) //Failed Attempt at homing bullet
                        {   
                            String filename = "sounds\\MegaBuster.wav";
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
                            for (int j = 0; j < handler.object.size(); j++) 
                            {
                                GameObject otherObject = handler.object.get(j);
                                if (otherObject.getId() == ObjectId.Death) 
                                {
                                    if (playerHomingBulletNumber<100)
                                    {
                                        if(tempObject.stance == 1)
                                        {
                                                handler.addObject(new PlayerHomingBullet(tempObject.getX(), tempObject.getY(), (float) 15.0, otherObject.getX(), otherObject.getY(), ObjectId.PlayerHomingBullet));
                                                playerHomingBulletNumber++;
                                        }
                                        else if (tempObject.stance == -1)
                                        {
                                                handler.addObject(new PlayerHomingBullet(tempObject.getX(), tempObject.getY(), (float) 15.0, otherObject.getX(), otherObject.getY(), ObjectId.PlayerHomingBullet));
                                                playerHomingBulletNumber++;
                                        }
                                    }
                                    if (otherObject.getId() == ObjectId.EnemyHomingBullet) 
                                    {
                                        if(playerHomingBulletNumber<100)
                                        {
                                            if(tempObject.stance == 1)
                                            {
                                                    handler.addObject(new PlayerHomingBullet(tempObject.getX(), tempObject.getY(), (float) 15.0, otherObject.getX(), otherObject.getY(), ObjectId.PlayerHomingBullet));
                                                    playerHomingBulletNumber++;
                                            }
                                            else if (tempObject.stance == -1)
                                            {
                                                    handler.addObject(new PlayerHomingBullet(tempObject.getX(), tempObject.getY(), (float) 15.0, otherObject.getX(), otherObject.getY(), ObjectId.PlayerHomingBullet));
                                                    playerHomingBulletNumber++;
                                            }
                                        }
                                    }
                                    if (otherObject.getId() == ObjectId.Enemy) 
                                    {
                                        if(playerHomingBulletNumber<100)
                                        {
                                            if(tempObject.stance == 1)
                                            {
                                                    handler.addObject(new PlayerHomingBullet(tempObject.getX(), tempObject.getY(), (float) 15.0, otherObject.getX(), otherObject.getY(), ObjectId.PlayerHomingBullet));
                                                    playerHomingBulletNumber++;
                                            }
                                            else if (tempObject.stance == -1)
                                            {
                                                    handler.addObject(new PlayerHomingBullet(tempObject.getX(), tempObject.getY(), (float) 15.0, otherObject.getX(), otherObject.getY(), ObjectId.PlayerHomingBullet));
                                                    playerHomingBulletNumber++;
                                            }
                                        }
                                    }
                                }
                            }
                        }*/
                }
                else
                {
                    if(key==KeyEvent.VK_R)
                    {
                        //Game.LEVEL=1;
                        Game.lives=3;
                        handler.clearLevel();
                        Game gg=new Game();
                        gg.init(Game.LEVEL);
                    }
                }
        }
        }
       
       if(key==KeyEvent.VK_ESCAPE)
       {
           System.exit(1);
       }
       if(key==KeyEvent.VK_C)
       {
            Game.LEVEL=12;
           handler.clearLevel();
            Game gg=new Game();
            gg.init(Game.LEVEL);
       }
        if(key==KeyEvent.VK_2&&Game.LEVEL>=4)
        {
            power=1;
            Game.powerup2=0;
            Game.powerup2confirm=1;
            //handler.clearLevel();
            //Game gg=new Game();
            //gg.init(Game.LEVEL);
        }
        if(key==KeyEvent.VK_3&&Game.LEVEL>=7)
        {
            power=2;
            Game.powerup3=0;
            Game.powerup3confirm=1;
            //handler.clearLevel();
            //Game gg=new Game();
            //gg.init(Game.LEVEL);
        }
        if(key==KeyEvent.VK_1)
        {
            power=0;
        }
    }

    // returns velocity to zero on arrow key release
    @Override
    public void keyReleased(KeyEvent ke) 
    {
        int key=ke.getKeyCode();
       
       for(int i=0;i<handler.object.size();i++)
       {
           GameObject tempObject=handler.object.get(i);
           if(tempObject.getId()==ObjectId.Player)
           {
                if(key==KeyEvent.VK_RIGHT) 
               {
                   tempObject.setvelX(0);
                   
               }
               if(key==KeyEvent.VK_LEFT) 
               {
                   tempObject.setvelX(0);
               }
           }
       }
        
    }
}
