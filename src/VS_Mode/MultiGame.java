package VS_Mode;

import mega.man.*;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MultiGame extends Canvas implements Runnable {

    private boolean running = false;
    private Thread thread;
    
    public static int WIDTH, HEIGHT;
    BufferedImageLoader loader = new BufferedImageLoader();
    BufferedImage[] Hero_Running;
    //Object
    Handler handler;
    Camera cam=new Camera();

    Random rand = new Random();
    public static int LEVEL=1;

    /**
     * 
     * @param lvl
     * Takes the level input, which is incremented when player switches level.
     *  Starts the music as well
     */
    public void init(int lvl) {
        WIDTH = getWidth();
        HEIGHT = getHeight();

        handler = new Handler();
         if(lvl==1)
         {
      String filename = "sounds\\Castlevania SOTN.wav";
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File(filename));
            Clip c = AudioSystem.getClip();
            c.open(ais);
            c.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException ex) {
            
        } catch (IOException ex) {
            System.out.println("Can't load audio file");
        } catch (LineUnavailableException ex) {
            Logger.getLogger(MultiGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        LoadImageLevel(loader.getLevel1());
         }
        
         else if(lvl==2)
        {
            LoadImageLevel(loader.getLevel2());
        }
        
        else if (lvl==3)
        {
            LoadImageLevel(loader.getLevel3());
        }

        this.addKeyListener(new KeyInput(handler));
    }

    public synchronized void start() {
        if (running) {
            return;
        }
        running = true;
        thread = new Thread(this);
        
        thread.start();

    }

    @Override
    public void run() {
        init(LEVEL);
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames + " TICKS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    
    private void tick() {
        handler.tick();
        for (int i = 0; i < handler.object.size(); i++) {
            if (handler.object.get(i).getId() == ObjectId.Player) {
                cam.tick(handler.object.get(i));
            }
        }

    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;
        ////////////////////////////////
        //Draw here
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());
        g2d.translate(cam.getX(), cam.getY());//begin of cam
        for (int yy = 0; yy < loader.getUnderground().getHeight() * 3; yy += loader.getUnderground().getHeight()) {
            for (int xx = 0; xx < loader.getUnderground().getWidth() * 3; xx += loader.getUnderground().getWidth()) {
                g.drawImage(loader.getUnderground(), xx, yy, this);
            }
        }
        handler.render(g);
        g2d.translate(-cam.getX(), -cam.getY());//end of cam
        ////////////////////////////////
        g.dispose();
        bs.show();
    }

    /**
     * 
     * @param image which is the level with colored pixels to be rendered
     * @param Description renders the whole level in pixels, and scales it x32, meaning
     * scaled to the preffered size size
     */
    public void LoadImageLevel(BufferedImage image) {
        int w = image.getWidth();
        int h = image.getHeight();
        System.out.println("width " + w + ", height " + h);

        for (int xx = 0; xx < h; xx++) {
            for (int yy = 0; yy < w; yy++) {
                int pixel = image.getRGB(xx, yy);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;
                if (red == 255 && green == 255 && blue == 255) {
                    handler.addObject(new Block(loader.getGround(), xx * 32, yy * 32, ObjectId.Block));
                }
                if (red == 255 && green == 106 && blue == 0) {
                    handler.addObject(new Spike(loader.getSpike(), xx * 32, yy * 32, ObjectId.Spike));
                }
                //if (red == 0 && green == 0 && blue == 255) {
                //    handler.addObject(new Player(loader.getHero(), loader.getJumpingRight(), loader.getJumpingLeft(), loader.getHero_Running(), loader.getReverseHero_Running(), xx * 32, yy * 32, handler,cam, ObjectId.Player));
                //}
                if (red == 128 && green == 128 && blue == 128) {
                    handler.addObject(new Block(loader.getIce(), xx * 32, yy * 32, ObjectId.Block));
                }
                if (red == 255 && green == 0 && blue == 0) {
                    handler.addObject(new Enemy(handler, loader.getEnemy_Right(), loader.getEnemy_Left(), xx * 32, yy * 32, ObjectId.Enemy));
                }
                 if (red == 127 && green == 63 && blue == 63) {
                    handler.addObject(new Block(loader.getDarkBlock(), xx * 32, yy * 32, ObjectId.Block));
                }
                 if (red == 255 && green == 0 && blue == 110) {
                    handler.addObject(new Boss(handler,loader.getDracula(), xx * 32, yy * 32, ObjectId.Boss));
                }
            }
        }
    }   
}
