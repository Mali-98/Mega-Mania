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


public class DoorRock extends GameObject implements Serializable
{
    /**
 * 
 * @author 0 
 * @param Descritption The class for the walls and ground, 
 * it draws the image sent to its the render method 
 * and returns the bounds of the object using the getBounds method
 */
    private BufferedImage DoorRock;

    public DoorRock(BufferedImage DoorRock,float x,float y,ObjectId id)
    {
        super(x,y,id);
        this.DoorRock=DoorRock;
    }

    @Override
    public void tick(ArrayList<GameObject> object)
    { 
    }

    @Override
    public void render(Graphics g) 
    {
        g.drawImage(DoorRock,(int)x,(int)y-75,null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x-23,(int)y-93,46,(int)y+93);
    }
   
}