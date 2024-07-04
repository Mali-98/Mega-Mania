package mega.man;


public class Camera 
{
    private float x,y;
    public Camera()
    {
        this.x=x;
        this.y=y;
    }
    
    public void tick(GameObject Player)
    {
        x=-Player.getX()+400;
        y=-Player.getY()+300;
    }

    public void setX(float x) 
    {
        this.x = x;
    }

    public void setY(float y)
    {
        this.y = y;
    }

    public float getX()
    {
        return x;
    }

    public float getY()
    {
        return y;
    }
}
