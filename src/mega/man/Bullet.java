package mega.man;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.ArrayList;

public class Bullet extends GameObject implements Serializable
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

    public Bullet(float x, float y, int velX, ObjectId id) {
        super(x, y, id);
        this.velX=velX;
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

        g.setColor(Color.yellow);
        g.fillOval((int)x, (int)y, 20, 20);
    }
    
    @Override
    public void tick(ArrayList<GameObject> object) 
    {
    if(velX==0)
    {
        velX=15;
    }
    else
    {
        x += velX;
    }
    }

    @Override
    public Rectangle getBounds() {
    return new Rectangle((int)x, (int)y, 16, 16);
    }
}
