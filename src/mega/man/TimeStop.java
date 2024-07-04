package mega.man;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.ArrayList;

public class TimeStop extends GameObject implements Serializable
{
    /**
 *
 * @author 0
 * This is the class responsible for drawing bullets and setting their velocity 
     * @param x 
     * @param y 
     * @param velX 
     * @param id 
 */
private int duration;   // Duration of shield's presence in ticks
private int timer;      // Current timer value

    public TimeStop(float x, float y, int duration,  ObjectId id) {
        super(x, y, id);
        this.duration = duration;
        this.timer = 0;
    }

    @Override
    public float getY() {
        return this.y;
    }

    @Override
    public float getX() {
        return this.x; 
    }

    @Override
    public void render(Graphics g) {

    }
    
    @Override
    public void tick(ArrayList<GameObject> object) 
    {
        timer++;

        // If the timer reaches the specified duration, remove the shield
        if (timer >= duration) {
            object.remove(this);
            KeyInput.TimeStopAllower=1;
        }
    }

    @Override
    public Rectangle getBounds() {
    return new Rectangle((int)x, (int)y, 16, 16);
    }
}
