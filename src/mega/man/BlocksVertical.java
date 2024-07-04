package mega.man;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


public class BlocksVertical extends GameObject implements Serializable
{
    /**
 * 
 * @author 0 
 * @param Descritption The class for the walls and ground, 
 * it draws the image sent to its the render method 
 * and returns the bounds of the object using the getBounds method
 */
    private BufferedImage BlocksVertical;
    private boolean isMovingDown = true;

    public BlocksVertical(BufferedImage BlocksVertical,float x,float y,ObjectId id)
    {
        super(x,y,id);
        this.BlocksVertical=BlocksVertical;
    }

    @Override
    public void tick(ArrayList<GameObject> object)
    {   
       if (isMovingDown) {
        // Move down for 1 second
        moveDown();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Switch to moving up after 1 second
                isMovingDown = false;
            }
        }, 4000);
    } else {
        // Move up for 1 second
        moveUp();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Switch to moving down after 1 second
                isMovingDown = true;
            }
        }, 4000);
    }
    }

    public void moveUp()
    {
        y-=1;
    }
    public void moveDown()
    {
        y+=1;
    }

    @Override
    public void render(Graphics g) 
    {
        g.drawImage(BlocksVertical,(int)x,(int)y,null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,32,32);
    }
   
}