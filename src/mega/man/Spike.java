package mega.man;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * @author 0
 * a class which draws spikes and returns their bounds, which kill the player on collision
 */
public class Spike extends GameObject implements Serializable
{
     private BufferedImage Spike;

    public Spike(BufferedImage Spike,float x,float y,ObjectId id)
    {
        super(x,y,id);
        this.Spike=Spike;
    }

    @Override
    public void tick(ArrayList<GameObject> object) {
       
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Spike,(int)x,(int)y,null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y+32,32,32);
    }
    
}
