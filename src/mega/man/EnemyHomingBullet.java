package mega.man;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;

public class EnemyHomingBullet extends GameObject implements Serializable
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
    private BufferedImage enemyHomingBullet;
    private float speed;
    private float playerX;
    private float playerY;

    public EnemyHomingBullet(BufferedImage enemyHomingBullet, float x, float y, float speed, float playerX, float playerY, ObjectId id) {
        super(x, y, id);
        this.speed = speed;
        this.playerX = playerX;
        this.playerY = playerY;
        this.enemyHomingBullet=enemyHomingBullet;
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
        g.drawImage(enemyHomingBullet,(int)x,(int)y,50,50,null);
    }
    
    @Override
    public void tick(ArrayList<GameObject> object) 
    {
            float deltaX = playerX - x;
            float deltaY = playerY - y;
            double angle = Math.atan2(deltaY, deltaX);
            float newX = x + speed * (float) Math.cos(angle);
            float newY = y + speed * (float) Math.sin(angle);
            x = newX;
            y = newY;
    }

    @Override
    public Rectangle getBounds() {
    return new Rectangle((int)x, (int)y, 50, 50);
    }
}
