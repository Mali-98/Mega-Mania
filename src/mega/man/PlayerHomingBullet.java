package mega.man;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;

public class PlayerHomingBullet extends GameObject implements Serializable
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
    private float speed;
    private float enemyX;
    private float enemyY;

    public PlayerHomingBullet(float x, float y, float speed, float enemyX, float enemyY, ObjectId id) {
        super(x, y, id);
        this.speed = speed;
        this.enemyX = enemyX;
        this.enemyY = enemyY;
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
            float deltaX = enemyX - x;
            float deltaY = enemyY - y;
            double angle = Math.atan2(deltaY, deltaX);
            float newX = x + speed * (float) Math.cos(angle);
            float newY = y + speed * (float) Math.sin(angle);
            x = newX;
            y = newY;
    }

    @Override
    public Rectangle getBounds() {
    return new Rectangle((int)x, (int)y, 16, 16);
    }
}
