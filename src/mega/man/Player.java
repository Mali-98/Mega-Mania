package mega.man;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import javafx.scene.text.Font;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class Player extends GameObject implements Serializable {
    private float width = 48, height = 96;
    private float gravity = 0.5f;
    private final float MAX_SPEED = 10;
    private Handler handler;
    private int health;
    private Camera cam;
    private BufferedImage[] Hero_Running;
    private BufferedImage[] ReverseHero_Running;
    private float imageCount;

    private BufferedImage JumpingRight;
    private BufferedImage JumpingLeft;
    private BufferedImage hero;
    private BufferedImage reversehero;
    private static int gotKey = 0;

    public Player(BufferedImage hero, BufferedImage reversehero, BufferedImage JumpingRight, BufferedImage JumpingLeft,
            BufferedImage[] Hero_Running, BufferedImage[] ReverseHero_Running, float x, float y, Handler handler,
            Camera cam, ObjectId id) {
        super(x, y, id);
        health = 100;
        this.handler = handler;
        this.cam = cam;
        this.hero = hero;
        this.reversehero = reversehero;
        this.JumpingRight = JumpingRight;
        this.JumpingLeft = JumpingLeft;
        this.Hero_Running = Hero_Running;
        this.ReverseHero_Running = ReverseHero_Running;
        imageCount = 0;
    }

    @Override
    public void tick(ArrayList<GameObject> object) {
        x += velX;
        y += velY;
        if (falling || jumping) {
            velY += gravity;
            if (velY > MAX_SPEED)
                velY = MAX_SPEED;
        }
        Collision(object);
    }

    private void Collision(ArrayList<GameObject> object) {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ObjectId.Block || tempObject.getId() == ObjectId.BlocksVertical
                    || tempObject.getId() == ObjectId.BlocksHorizontal) {
                // Top Collision
                if (getBoundsTop().intersects(tempObject.getBounds())) {
                    y = tempObject.getY() + 32;
                    velY = 0;
                }
                // Bottom Collision
                if (getBounds().intersects(tempObject.getBounds())) {
                    y = tempObject.getY() - height;
                    velY = 0;
                    falling = false;
                    jumping = false;
                } else {
                    falling = true;
                }
                // Right Collision
                if (getBoundsRight().intersects(tempObject.getBounds())) {
                    x = tempObject.getX() - width;
                }
                // Left Collision
                if (getBoundsLeft().intersects(tempObject.getBounds())) {
                    x = tempObject.getX() + 35;
                }

            }
            if (tempObject.getId() == ObjectId.Spike || tempObject.getId() == ObjectId.SpikeRight
                    || tempObject.getId() == ObjectId.SpikeLeft) {
                if (getBounds().intersects(tempObject.getBounds())
                        || getBoundsRight().intersects(tempObject.getBounds())
                        || getBoundsLeft().intersects(tempObject.getBounds())) {
                    /*
                     * String filename = "sounds\\Evil Laugh.wav";
                     * InputStream in = null;
                     * try
                     * {
                     * in = new FileInputStream(filename);
                     * }
                     * catch (FileNotFoundException ex)
                     * {
                     * System.out.println("File not found");
                     * }
                     * try
                     * {
                     * AudioStream s = new AudioStream(in);
                     * AudioData MD;
                     * AudioPlayer.player.start(s);
                     * }
                     * catch (IOException ex)
                     * {
                     * System.out.println(ex.getMessage());
                     * }
                     * JOptionPane.showMessageDialog(null,"You Are Dead!!!");
                     * System.exit(0);
                     */
                    KeyInput.BulletShieldMagic = 100;
                    KeyInput.BulletShieldAllower = 1;
                    KeyInput.TimeStopAllower = 1;
                    KeyInput.StopTimeMagic = 100;
                    KeyInput.power = 0;
                    handler.clearLevel();
                    Game gg = new Game();
                    gg.init(Game.LEVEL);
                    Game.lives--;
                    break;
                }
            } else if (tempObject.getId() == ObjectId.Flag) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    KeyInput.BulletShieldMagic = 100;
                    KeyInput.BulletShieldAllower = 1;
                    KeyInput.TimeStopAllower = 1;
                    KeyInput.StopTimeMagic = 100;
                    KeyInput.power = 0;
                    handler.clearLevel();
                    Game gg = new Game();
                    gg.init(++Game.LEVEL);
                }
            } else if (tempObject.getId() == ObjectId.EnemyBullet) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    setHealth(getHealth() - 5);
                    if (getHealth() <= 0) {
                        KeyInput.BulletShieldMagic = 100;
                        KeyInput.BulletShieldAllower = 1;
                        KeyInput.TimeStopAllower = 1;
                        KeyInput.StopTimeMagic = 100;
                        KeyInput.power = 0;
                        handler.clearLevel();
                        Game gg = new Game();
                        gg.init(Game.LEVEL);
                        Game.lives--;
                        break;
                    }
                }
            } else if (tempObject.getId() == ObjectId.EnemyHomingBullet) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    setHealth(getHealth() - 2);
                    if (getHealth() <= 0) {
                        KeyInput.BulletShieldMagic = 100;
                        KeyInput.BulletShieldAllower = 1;
                        KeyInput.TimeStopAllower = 1;
                        KeyInput.StopTimeMagic = 100;
                        KeyInput.power = 0;
                        handler.clearLevel();
                        Game gg = new Game();
                        gg.init(Game.LEVEL);
                        Game.lives--;
                        break;
                    }
                } else {
                    tempObject.tick(object);
                }
            } else if (tempObject.getId() == ObjectId.BeatGame) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    String filename = "sounds\\Victory Theme.wav";
                    InputStream in = null;
                    try {
                        in = new FileInputStream(filename);
                    } catch (FileNotFoundException ex) {
                        System.out.println("File not found");
                    }
                    try {
                        AudioStream s = new AudioStream(in);
                        AudioData MD;
                        AudioPlayer.player.start(s);
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                    JOptionPane.showMessageDialog(null, "Congratulations!!!"
                            + " You Have Escaped The Clutches Of Dracula, "
                            + "Thank You For Playing");
                    System.exit(0);

                }
            } else if (tempObject.getId() == ObjectId.Key) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    handler.removeObject(tempObject);
                    gotKey = 1;
                }
            }

            else if (tempObject.getId() == ObjectId.DoorRock) {
                if ((getBoundsRight().intersects(tempObject.getBounds())) && (gotKey == 1) && ((y - 40) < tempObject.getY())) {
                    handler.removeObject(tempObject);
                } else {
                    //right Collision
                    if ((getBoundsRight().intersects(tempObject.getBounds())) && ((y - 40) < tempObject.getY())) {
                        x = tempObject.getX() - 70;
                    }
                    // Left Collision
                    if ((getBoundsLeft().intersects(tempObject.getBounds())) && ((y - 40) < tempObject.getY())) {
                        x = tempObject.getX() + 50;
                    }
                }
            }
        }
    }

    // movement animation
    @Override
    public void render(Graphics g) {
        // Draw the health bar
        g.setColor(Color.RED);
        g.fillRect((int) x - 20, (int) y - 15, 100, 10); // draw the background of the health bar
        g.setColor(Color.GREEN);
        g.fillRect((int) x - 20, (int) y - 15, health, 10);

        if (KeyInput.power == 0) {
            // Draw nothing
        }

        else if (KeyInput.power == 1) {
            // Draw the bullet shield magic bar
            g.setColor(Color.GRAY);
            g.fillRect((int) x - 20, (int) y - 30, 100, 10); // draw the background of the magic bar
            g.setColor(Color.BLUE);
            g.fillRect((int) x - 20, (int) y - 30, KeyInput.BulletShieldMagic, 10);
        }

        else if (KeyInput.power == 2) {
            // Draw the stop time magic bar
            g.setColor(Color.GRAY);
            g.fillRect((int) x - 20, (int) y - 30, 100, 10); // draw the background of the magic bar
            g.setColor(Color.BLUE);
            g.fillRect((int) x - 20, (int) y - 30, KeyInput.StopTimeMagic, 10);
        }

        if (jumping) {
            if (velX > 0)
                g.drawImage(JumpingRight, (int) x, (int) y, 48, 96, null);
            if (velX < 0)
                g.drawImage(JumpingLeft, (int) x, (int) y, 48, 96, null);
            if (velX == 0 && stance == 1)
                g.drawImage(JumpingRight, (int) x, (int) y, 48, 96, null);
            if (velX == 0 && stance == -1)
                g.drawImage(JumpingLeft, (int) x, (int) y, 48, 96, null);

        } else {
            if (velX == 0 && stance == 1) {
                g.drawImage(hero, (int) x, (int) y, 48, 96, null);
            } else if (velX == 0 && stance == -1) {
                g.drawImage(reversehero, (int) x, (int) y, 48, 96, null);
            } else if (velX > 0) {
                stance = 1;
                g.drawImage(this.Hero_Running[(int) imageCount], (int) x, (int) y, 48, 96, null);
                imageCount = imageCount + 0.03f;
                if (imageCount > Hero_Running.length - 1)
                    imageCount = 0;
            } else if (velX < 0) {
                stance = -1;
                g.drawImage(this.ReverseHero_Running[(int) imageCount], (int) x, (int) y, 48, 96, null);
                imageCount = imageCount + 0.03f;
                if (imageCount > ReverseHero_Running.length - 1)
                    imageCount = 0;
            }
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) ((int) x + (width / 2) - ((width / 2) / 2)), (int) ((int) y + (height / 2)),
                (int) width / 2, (int) height / 2);
    }

    public Rectangle getBoundsRight() {
        return new Rectangle((int) ((int) x + width - 5), (int) y + 5, (int) 5, (int) height - 10);
    }

    public Rectangle getBoundsLeft() {
        return new Rectangle((int) x, (int) y + 5, (int) 5, (int) height - 10);
    }

    public Rectangle getBoundsTop() {
        return new Rectangle((int) ((int) x + (width / 2) - ((width / 2) / 2)), (int) y, (int) width / 2,
                (int) height / 2);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void drawHealthBar(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(20, 20, 100, 10);
        g.setColor(Color.GREEN);
        g.fillRect(20, 20, health, 10);
    }

}
