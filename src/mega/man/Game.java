package mega.man;

import java.awt.Canvas;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
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

public class Game extends Canvas implements Runnable {

    private boolean running = false;
    private Thread thread;
    
    BufferedImageLoader loader = new BufferedImageLoader();
    BufferedImage[] Hero_Running;
    //Object
    Handler handler;
    Camera cam=new Camera();
    AudioInputStream ais;
    Clip c1;
    AudioInputStream audioIn1;
    Random rand = new Random();
    public static int LEVEL=1;
    public static int lives=3;
    public static int powerup2=0;
    public static int powerup2confirm=0;
    public static int powerup3=0;
    public static int powerup3confirm=0;
    /**
     * 
     * @param lvl
     * Takes the level input, which is incremented when player switches level.
     *  Starts the music as well
     */
    public void init(int lvl) 
    {
        handler = new Handler();
        if(lvl == 1) {
            LoadImageLevel(loader.getLevel1());
        } else if(lvl == 2) {
            LoadImageLevel(loader.getLevel2());
        } else if (lvl == 3) {
            LoadImageLevel(loader.getLevel3());
        } else if (lvl == 4) {
            LoadImageLevel(loader.getLevel4());
        }
        else if (lvl == 5) {
            LoadImageLevel(loader.getLevel5());
        }
        else if (lvl == 6) {
            LoadImageLevel(loader.getLevel6());
        }
        else if (lvl == 7) {
            LoadImageLevel(loader.getLevel7());
        }
        else if (lvl == 8) {
            LoadImageLevel(loader.getLevel8());
        }
        else if (lvl == 9) {
            LoadImageLevel(loader.getLevel9());
        }
        else if (lvl == 10) {
            LoadImageLevel(loader.getLevel10());
        }
        else if (lvl == 11) {
            LoadImageLevel(loader.getLevel11());
        }
        else if (lvl == 12) {
            LoadImageLevel(loader.getLevel12());
        }
        else if (lvl == 13) {
            LoadImageLevel(loader.getLevel13());
        }
        else if (lvl == 14) {
            LoadImageLevel(loader.getLevel14());
        }
        this.addKeyListener(new KeyInput(handler));
    }

    public synchronized void start() 
    {
        if (running) {
            return;
        }
        running = true;
        thread = new Thread(this);
        
        thread.start();

    }

    @Override
    public void run() {
        try {
                    audioIn1 = AudioSystem.getAudioInputStream(new File("sounds\\Castlevania SOTN.wav"));
                    c1=AudioSystem.getClip();
                    c1.open(audioIn1);
                    c1.start();
                    c1.loop(Clip.LOOP_CONTINUOUSLY);
                } catch (Exception e) {
                    e.printStackTrace();
                }
        init(LEVEL);
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
       
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
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;                
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
        for (int yy = 0; yy < loader.getUnderground().getHeight() * 12; yy += loader.getUnderground().getHeight()) {
            for (int xx = 0; xx < loader.getUnderground().getWidth() * 12; xx += loader.getUnderground().getWidth()) {
                g.drawImage(loader.getUnderground(), xx, yy, this);
            }
        }
        handler.render(g);
        g2d.translate(-cam.getX(), -cam.getY());//end of cam
        g.setColor(Color.white);
        g.drawString("Lives: " + lives, 10, 25);
        if(lives<=0)
            {   
                g.setColor(Color.white);
                g.drawString("Out of lives! Press R to continue", 10, 40);
            }
            
        if(LEVEL==4 && powerup2confirm==0)
        {
            powerup2=1;
            g.setColor(Color.white);
            g.drawString("Gained a shield ability, press 2 and fire", 350, 280);
        }
        if(LEVEL==7 && powerup3confirm==0)
        {
            powerup3=1;
            g.setColor(Color.white);
            g.drawString("Gained a time stop ability, press 3 and fire", 350, 280);
        }
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
                if (red == 30 && green == 30 && blue == 30) {
                    handler.addObject(new Block(loader.getBlackBlock(), xx * 32, yy * 32, ObjectId.Block));
                }
                if (red == 0 && green == 0 && blue == 70) {
                    handler.addObject(new Block(loader.getStoneBlock(), xx * 32, yy * 32, ObjectId.Block));
                }
                if (red == 0 && green == 0 && blue == 71) {
                    handler.addObject(new BlocksHorizontal(loader.getStoneBlockHorizontal(), xx * 32, yy * 32, ObjectId.BlocksHorizontal));
                }
                if (red == 119 && green == 15 && blue == 27) {
                    handler.addObject(new Block(loader.getSkullBlock(), xx * 32, yy * 32, ObjectId.Block));
                }
                if (red == 169 && green == 69 && blue == 0) {
                    handler.addObject(new Block(loader.getStonePathBlock(), xx * 32, yy * 32, ObjectId.Block));
                }
                if (red == 169 && green == 70 && blue == 0) {
                    handler.addObject(new BlocksVertical(loader.getStonePathBlockVertical(), xx * 32, yy * 32, ObjectId.BlocksVertical));
                }
                if (red == 255 && green == 106 && blue == 0) {
                    handler.addObject(new Spike(loader.getSpike(), xx * 32, yy * 32, ObjectId.Spike));
                }
                if (red == 255 && green == 105 && blue == 0) {
                    handler.addObject(new Heart(handler, loader.getHeart(), xx * 32, yy * 32, ObjectId.Heart));
                }
                if (red == 0 && green == 148 && blue == 255) {
                    handler.addObject(new Spike(loader.getSpikePointingRight(), xx * 32, yy * 32, ObjectId.SpikeRight));
                }
                if (red == 0 && green == 150 && blue == 240) {
                    handler.addObject(new Spike(loader.getSpikePointingLeft(), xx * 32, yy * 32, ObjectId.SpikeLeft));
                }
                
                if (red == 128 && green == 128 && blue == 128) {
                    handler.addObject(new Block(loader.getIce(), xx * 32, yy * 32, ObjectId.Block));
                }
                if (red == 255 && green == 0 && blue == 0) {
                    handler.addObject(new Enemy(handler, loader.getEnemy_Right(), loader.getEnemy_Left(), xx * 32, yy * 32, ObjectId.Enemy));
                }
                 if (red == 0 && green == 255 && blue == 255) {
                    handler.addObject(new Enemy(handler, loader.getEnemy2_Right(), loader.getEnemy2_Left(), xx * 32, yy * 32, ObjectId.Enemy));
                }
                  if (red == 165 && green == 255 && blue == 127) {
                    handler.addObject(new Enemy(handler, loader.getEnemy3_Right(), loader.getEnemy3_Left(), xx * 32, yy * 32, ObjectId.Enemy));
                }
                if (red == 250 && green == 250 && blue == 250) {
                    handler.addObject(new Enemy(handler, loader.getEnemy4_Right(), loader.getEnemy4_Left(), xx * 32, yy * 32, ObjectId.Enemy));
                }
                if (red == 250 && green == 250 && blue == 245) {
                    handler.addObject(new Enemy(handler, loader.getEnemy5_Right(), loader.getEnemy5_Left(), xx * 32, yy * 32, ObjectId.Enemy));
                }
                 if (red == 255 && green == 216 && blue == 0) {
                    handler.addObject(new Flag(loader.getPortal(),xx * 32, yy * 32, ObjectId.Flag));
                }
                  if (red == 255 && green == 127 && blue == 182) {
                    handler.addObject(new BeatGame(loader.getOpen_Gate(),xx * 32, yy * 32, ObjectId.BeatGame));
                }
                 if (red == 127 && green == 63 && blue == 63) {
                    handler.addObject(new Block(loader.getDarkBlock(), xx * 32, yy * 32, ObjectId.Block));
                }
                if (red == 255 && green == 0 && blue == 220) {
                    handler.addObject(new Block(loader.getPurpleBlock(), xx * 32, yy * 32, ObjectId.Block));
                }
                if (red == 100 && green == 20 && blue == 120) {
                    handler.addObject(new Block(loader.getblueOrb(), xx * 32, yy * 32, ObjectId.Block));
                }
                if (red == 255 && green == 39 && blue == 0) {
                    handler.addObject(new DoorRock(loader.getdoorRock(), xx * 32, yy * 32, ObjectId.DoorRock));
                }
                if (red == 255 && green == 255 && blue == 0) {
                    handler.addObject(new Key(loader.getKey(), xx * 32, yy * 32, ObjectId.Key));
                }
                 if (red == 0 && green == 0 && blue == 255) {
                    handler.addObject(new Player(loader.getHero(), loader.getreverseHero(), loader.getJumpingRight(), loader.getJumpingLeft(), loader.getHero_Running(), loader.getReverseHero_Running(), xx * 32, yy * 32, handler,cam, ObjectId.Player));
                }
                 if (red == 255 && green == 0 && blue == 110) {
                    handler.addObject(new Boss(handler,loader.getDracula(), xx * 32, yy * 32, ObjectId.Boss));
                }
                 if (red == 250 && green == 0 && blue == 110) {
                    handler.addObject(new LordSkull(handler,loader.getLordSkull(),loader.getLordSkullRight(), xx * 32, yy * 32, ObjectId.LordSkull));
                }
                if (red == 245 && green == 0 && blue == 110) {
                    handler.addObject(new Death(handler,loader.getDeath(),loader.getDeathRight(), xx * 32, yy * 32, ObjectId.Death));
                }
                if (red == 240 && green == 0 && blue == 110) {
                    handler.addObject(new Demon(handler,loader.getDemon(),loader.getDemonRight(), xx * 32, yy * 32, ObjectId.Demon));
                }
                if (red == 0 && green == 255 && blue == 33) {
                    handler.addObject(new Dragon(handler,loader.getDragon(), xx * 32, yy * 32, ObjectId.Dragon));
                }
            }
        }
    }   
}
