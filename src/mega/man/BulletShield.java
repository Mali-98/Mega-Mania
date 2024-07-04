package mega.man;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.ArrayList;

public class BulletShield extends GameObject implements Serializable {
    private Player player;  // Reference to the player
    private int duration;   // Duration of shield's presence in ticks
    private int timer;      // Current timer value
    private int numBullets; // Number of bullets
    private float rotationSpeed; // Speed of rotation
    private float bulletRadius; // Radius of the circular arrangement

    public BulletShield(Player player, int duration, int numBullets, float rotationSpeed, float bulletRadius, ObjectId id) {
        super(player.getX(), player.getY(), id);
        this.player = player;
        this.duration = duration;
        this.timer = 0;
        this.numBullets = numBullets;
        this.rotationSpeed = rotationSpeed;
        this.bulletRadius = bulletRadius;
    }

    @Override
    public void render(Graphics g) {
        // Calculate shield positions relative to the player
        float playerX = player.getX();
        float playerY = player.getY();

        for (int i = 0; i < numBullets; i++) {
            float angle = (float) (2 * Math.PI * i / numBullets + rotationSpeed * timer);
            float bulletX = playerX + bulletRadius * (float) Math.cos(angle);
            float bulletY = playerY + bulletRadius * (float) Math.sin(angle);

            g.setColor(Color.yellow);
            g.fillOval((int) (bulletX+10), (int) (bulletY+40), 20, 20);
        }
    }

    @Override
    public void tick(ArrayList<GameObject> object) {
        // Update the shield's position based on the player's position
        x = player.getX();
        y = player.getY();

        // Increment the timer
        timer++;

        // If the timer reaches the specified duration, remove the shield
        if (timer >= duration) {
            object.remove(this);
            KeyInput.BulletShieldAllower=1;
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x - 30, (int) y + 15, 100, 100);
    }
}
