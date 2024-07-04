package mega.man;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


public class Key extends GameObject implements Serializable
{
    /**
 * 
 * @author 0 
 * @param Descritption The class for the walls and ground, 
 * it draws the image sent to its the render method 
 * and returns the bounds of the object using the getBounds method
 */
    private BufferedImage Key;

    public Key(BufferedImage Key,float x,float y,ObjectId id)
    {
        super(x,y,id);
        this.Key=Key;
    }

    @Override
    public void tick(ArrayList<GameObject> object)
    { 
    }

    @Override
    public void render(Graphics g) 
    {
        g.drawImage(Key,(int)x,(int)y,null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,32,32);
    }
   
}