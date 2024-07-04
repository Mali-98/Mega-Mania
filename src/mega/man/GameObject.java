package mega.man;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public abstract class GameObject 
{
    protected float x,y;
    protected ObjectId id;
    protected float velX=0,velY=0;
    protected boolean falling=true;
    protected boolean jumping=false;
    protected int stance = 1;
//    protected int facing = 1;
    
    /**
     * 
     * @param x
     * @param y
     * @param id 
     * @param Description takes the coordinates of the game object, and the ID of the game object(block, player, enemy, etc.) as parameters
     * but the info is stored in an arraylist in the handler  class
     */
    public GameObject(float x,float y,ObjectId id)
    {
        this.x=x;
        this.y=y;
        this.id=id;
    }
    
    public abstract void tick (ArrayList<GameObject>object);
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();
    
    public float getX()
    {
        return x;
    }
    public float getY()
    {
        return y;
    }
    public void setX(float x)
    {
        this.x=x;
    }
    public void setY(float y)
    {
        this.y=y;
    }
    
    public float getvelX()
    {
        return velX;
    }
    public float getvelY()
    {
        return velY;
    }
    public void setvelX(float velX)
    {
        this.velX=velX;
    }
    public void setvelY(float velY)
    {
        this.velY=velY;
    }
    public boolean isFalling()
    {
        return falling;
    }
    public void setFalling(boolean falling)
    {
        this.falling=falling;
    }
    public boolean isJumping()
    {
        return jumping;
    }
    public void setJumping(boolean jumping)
    {
        this.jumping=jumping;
    }
    public ObjectId getId()
    {
        return id;
    }
}
