package mega.man;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;

public class EnemyBullet extends GameObject implements Serializable
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
    private BufferedImage enemyBullet;

    public EnemyBullet(BufferedImage enemyBullet, float x, float y, int velX, int velY, ObjectId id) {
        super(x, y, id);
        this.velX=velX;
        this.velY = velY;
        this.enemyBullet=enemyBullet;
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
        g.drawImage(enemyBullet,(int)x,(int)y,50,50,null);
    }
    
    @Override
    public void tick(ArrayList<GameObject> object) 
    {
    if(velX==0)
    {
        velX=15;
        velY=15;
    }
    else
    {
        x += velX;
        y += velY;
    }
    }

    @Override
    public Rectangle getBounds() {
    return new Rectangle((int)x, (int)y, 16, 16);
    }
}
