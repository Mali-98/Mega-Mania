package mega.man;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class BeatGame extends GameObject
{
    private BufferedImage BeatGame;
    public BeatGame(BufferedImage BeatGame,float x,float y,ObjectId id)
    {
        super(x,y,id);
        this.BeatGame=BeatGame;
    }

    @Override
    public void tick(ArrayList<GameObject> object) {
       
    }

    @Override
    public void render(Graphics g) {
        
       g.drawImage(BeatGame,(int)x-118/2,(int)y-157/2+20,118,157,null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x-118/2,(int)y,118/4,157/4);
    }
    
}

