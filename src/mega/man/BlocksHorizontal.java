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


public class BlocksHorizontal extends GameObject implements Serializable
{
    /**
 * 
 * @author 0 
 * @param Descritption The class for the walls and ground, 
 * it draws the image sent to its the render method 
 * and returns the bounds of the object using the getBounds method
 */
    private BufferedImage BlocksHorizontal;
    private boolean isMovingRight = true;

    public BlocksHorizontal(BufferedImage BlocksHorizontal,float x,float y,ObjectId id)
    {
        super(x,y,id);
        this.BlocksHorizontal=BlocksHorizontal;
    }

    @Override
    public void tick(ArrayList<GameObject> object)
    {   
        if(Game.lives>0)
        {
       if (isMovingRight) {
        // Move down for 1 second
        moveRight();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Switch to moving up after 1 second
                isMovingRight = false;
            }
        }, 6000);
    } else {
        // Move up for 1 second
        moveLeft();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Switch to moving down after 1 second
                isMovingRight = true;
            }
        }, 6000);
    }
    }
    else
    {
        
    }
    }

    public void moveRight()
    {
        x+=1.75;
    }
    public void moveLeft()
    {
        x-=1.75;
    }

    @Override
    public void render(Graphics g) 
    {
        g.drawImage(BlocksHorizontal,(int)x,(int)y,null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,32,32);
    }
   
}