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
 * a class which draws spikes and returns their bounds, which kill the player on collision
 */
public class Heart extends GameObject implements Serializable
{
     private BufferedImage Heart;
     private Handler handler;

    public Heart(Handler handler, BufferedImage Heart,float x,float y,ObjectId id)
    {
        super(x,y,id);
        this.handler = handler;
        this.Heart=Heart;
    }

    @Override
    public void tick(ArrayList<GameObject> object) {
       Collision(object);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Heart,(int)x,(int)y,null);
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
                    Game.lives++;
                    handler.removeObject(this);
                }
            }
        }          
    }
    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y+32,32,32);
    }    
}

