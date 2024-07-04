package mega.man;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * @author 0
 * this is the finish line that transports player to next , the collision is in the player class
 */
public class Flag extends GameObject implements Serializable
{
    private BufferedImage Flag;
    public Flag(BufferedImage Flag,float x,float y,ObjectId id)
    {
        super(x,y,id);
        this.Flag=Flag;
    }

    @Override
    public void tick(ArrayList<GameObject> object) {
       
    }

    @Override
    public void render(Graphics g) {
       g.drawImage(Flag,(int)x,(int)y,80,90,null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,55,64);
    }
    
}

