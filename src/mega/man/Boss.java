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
public class Boss extends GameObject
{
    private float width=466,height=228;
    private Handler handler;
    private BufferedImage Dracula;
    private boolean collide;

    public Boss(Handler handler,BufferedImage Dracula, float x, float y, ObjectId id) {
        super(x, y, id);
        this.handler = handler;
        this.Dracula=Dracula;
    }

    @Override
    public void tick(ArrayList<GameObject> object) 
    {   
        if(Game.lives>0)
        {
            moveRight();
        }
        else
        {
            
        }
        
                          
       Collision(object);

    }
    
    public void moveRight()
    {
        x+=4.3;
    }
    
    private void Collision(ArrayList<GameObject>object)
    {   
        for(int i=0;i<handler.object.size();i++)
        {
            GameObject tempObject=handler.object.get(i);   
                 if(tempObject.getId()==ObjectId.Player)
               {
                
                    if(getBounds().intersects(tempObject.getBounds()))
                    {
                        /*String filename = "sounds\\Evil Laugh.wav";
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
                        JOptionPane.showMessageDialog(null,"Your Soul Shall Wonder Aimlessly In Dracula's Castle");
                        System.exit(0);*/
                        handler.clearLevel();
                        Game gg=new Game();
                        gg.init(Game.LEVEL);
                        Game.lives--;
                    }
                }
                 
                 
                 
                 if(tempObject.getId()==ObjectId.BeatGame)
                 {
                     if(getBounds().intersects(tempObject.getBounds()))
                     {
                        /*String filename = "sounds\\Evil Laugh.wav";
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
                        JOptionPane.showMessageDialog(null,"Your Soul Shall Wonder Aimlessly In Dracula's Castle");
                        System.exit(0);*/
                        handler.clearLevel();
                        Game gg=new Game();
                        gg.init(Game.LEVEL);
                        Game.lives--;
                     }
                 }
            
        }
    
    }
    @Override
    public void render(Graphics g) 
    {
        g.drawImage(Dracula,(int)x,(int)y,466,228,null);
        
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