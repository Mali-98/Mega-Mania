package mega.man;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/*
*Class for loading all images
*/
public class BufferedImageLoader 
{

    private BufferedImage level1 = null;
    private BufferedImage level2 = null;
    private BufferedImage level3 = null;  //Lord Skull boss
    private BufferedImage level4 = null; 
    private BufferedImage level5 = null;
    private BufferedImage level6 = null;  //Death boss
    private BufferedImage level7 = null;
    private BufferedImage level8 = null;
    private BufferedImage level9 = null;  //Two demons boss
    private BufferedImage level10 = null;
    private BufferedImage level11 = null; 
    private BufferedImage level12 = null; //Shadow boss 
    private BufferedImage level13 = null; //Dragon boss
    private BufferedImage level14 = null; //Dracula boss
    private BufferedImage ground = null;
    private BufferedImage stoneBlock = null;
    private BufferedImage blackBlock = null;
    private BufferedImage stonePathBlock = null;
    private BufferedImage stonePathBlockVertical = null;
    private BufferedImage stoneBlockHorizontal = null;
    private BufferedImage ice = null;
    private BufferedImage hero = null;
    private BufferedImage reversehero = null;
    private BufferedImage loader;
    private BufferedImage doorRock = null;
    private BufferedImage key = null;
    private BufferedImage[] Hero_Running;
    private BufferedImage[] ReverseHero_Running;
    private BufferedImage JumpingRight=null;
    private BufferedImage JumpingLeft=null;
    private BufferedImage Underground=null;
    private BufferedImage Shoot=null;
    private BufferedImage Portal=null; //this is the flag
    private BufferedImage Open_Gate=null;
    private BufferedImage DarkBlock=null;
    private BufferedImage blueOrb=null;
    private BufferedImage PurpleBlock=null;
    private BufferedImage SkullBlock=null;
    private BufferedImage Enemy_Right=null;
    private BufferedImage Enemy_Left=null;
    private BufferedImage Enemy2_Right=null;
    private BufferedImage Enemy2_Left=null;
    private BufferedImage Enemy3_Left=null;
    private BufferedImage Enemy3_Right=null;
    private BufferedImage Enemy4_Left=null;
    private BufferedImage Enemy4_Right=null;
    private BufferedImage Enemy5_Left=null;
    private BufferedImage Enemy5_Right=null;
    private BufferedImage Spike = null;
    private BufferedImage Heart = null;
    private BufferedImage SpikePointingRight = null;
    private BufferedImage SpikePointingLeft = null;
    private BufferedImage LordSkull = null;
    private BufferedImage LordSkullRight = null;
    private BufferedImage Shadow = null;
    private BufferedImage ShadowRight = null;
    private BufferedImage Death = null;
    private BufferedImage DeathRight = null;
    private BufferedImage Demon = null;
    private BufferedImage DemonRight = null;
    private BufferedImage Dragon = null;
    private BufferedImage Dracula = null;
    
    public BufferedImage[] getHero_Running() {
        return Hero_Running;
    }

    public BufferedImage[] getReverseHero_Running() {
        return ReverseHero_Running;
    }

    public BufferedImage getDracula() {
        return Dracula;
    }

    public BufferedImage getDragon() {
        return Dragon;
    }

    public BufferedImage getLordSkull() {
        return LordSkull;
    }

    public BufferedImage getLordSkullRight() {
        return LordSkullRight;
    }

    public BufferedImage getDeath() {
        return Death;
    }

    public BufferedImage getDeathRight() {
        return DeathRight;
    }

    public BufferedImage getShadow() {
        return Shadow;
    }

    public BufferedImage getShadowRight() {
        return ShadowRight;
    }

    public BufferedImage getDemon() {
        return Demon;
    }

    public BufferedImage getDemonRight() {
        return DemonRight;
    }

    public BufferedImage getGround() {
        return ground;
    }
    public BufferedImage getStoneBlock() {
        return stoneBlock;
    }
    public BufferedImage getStonePathBlock() {
        return stonePathBlock;
    }
    public BufferedImage getStoneBlockHorizontal() {
        return stoneBlockHorizontal;
    }
    public BufferedImage getStonePathBlockVertical() {
        return stonePathBlockVertical;
    }
    public BufferedImage getHeart() {
        return Heart;
    }

    public BufferedImage getUnderground() {
        return Underground;
    }

    public BufferedImage getDarkBlock() {
        return DarkBlock;
    }

    public BufferedImage getBlackBlock() {
        return blackBlock;
    }

    public BufferedImage getSkullBlock() {
        return SkullBlock;
    }

    public BufferedImage getdoorRock() {
        return doorRock;
    }

    public BufferedImage getKey() {
        return key;
    }

    public BufferedImage getblueOrb() {
        return blueOrb;
    }

    public BufferedImage getPurpleBlock() {
        return PurpleBlock;
    }

    public BufferedImage getIce() {
        return ice;
    }

    public BufferedImage getPortal() {
        return Portal;
    }

    public BufferedImage getOpen_Gate() {
        return Open_Gate;
    }

    public BufferedImage getImage() {
        return loader;
    }

    public BufferedImage getHero() {
        return hero;
    }
    public BufferedImage getreverseHero() {
        return reversehero;
    }

    public BufferedImage getShoot() {
        return Shoot;
    }

    public BufferedImage getJumpingRight() {
        return JumpingRight;
    }

    public BufferedImage getJumpingLeft() {
        return JumpingLeft;
    }

    public BufferedImage getLevel1() {
        return level1;
    }

    public BufferedImage getLevel2() {
        return level2;
    }

    public BufferedImage getLevel3() {
        return level3;
    }
    public BufferedImage getLevel4() {
        return level4;
    }
    public BufferedImage getLevel5() {
        return level5;
    }
    
    public BufferedImage getLevel6() {
        return level6;
    }

    public BufferedImage getLevel7() {
        return level7;
    }
    public BufferedImage getLevel8() {
        return level8;
    }
    public BufferedImage getLevel9() {
        return level9;
    }
    public BufferedImage getLevel10() {
        return level10;
    }
    public BufferedImage getLevel11() {
        return level11;
    }
    public BufferedImage getLevel12() {
        return level12;
    }
    public BufferedImage getLevel13() {
        return level13;
    }
    public BufferedImage getLevel14() {
        return level14;
    }

    public BufferedImage getEnemy_Left() {
        return Enemy_Left;
    }

    public BufferedImage getEnemy_Right() {
        return Enemy_Right;
    }

    public BufferedImage getEnemy3_Right() {
        return Enemy3_Right;
    }

    public BufferedImage getEnemy3_Left() {
        return Enemy3_Left;
    }

    public BufferedImage getEnemy4_Right() {
        return Enemy4_Right;
    }

    public BufferedImage getEnemy4_Left() {
        return Enemy4_Left;
    }

    public BufferedImage getEnemy5_Right() {
        return Enemy5_Right;
    }

    public BufferedImage getEnemy5_Left() {
        return Enemy5_Left;
    }

    public BufferedImage getEnemy2_Right() {
        return Enemy2_Right;
    }

    public BufferedImage getEnemy2_Left() {
        return Enemy2_Left;
    }

    public BufferedImage getSpike() {
        return Spike;
    }
    public BufferedImage getSpikePointingRight() {
        return SpikePointingRight;
    }
    public BufferedImage getSpikePointingLeft() {
        return SpikePointingLeft;
    }

    public BufferedImageLoader() {
        Hero_Running = new BufferedImage[4];
        ReverseHero_Running = new BufferedImage[4];

        try {
            level1 = ImageIO.read(new File("image\\Level1.png"));
        } catch (IOException ex) {
            System.out.println("Can't load lvl1");
        }
         try {
            level2 = ImageIO.read(new File("image\\Level2.png"));
        } catch (IOException ex) {
            System.out.println("Can't load lvl2");
        }
         try {
            level3 = ImageIO.read(new File("image\\Level3.png"));
        } catch (IOException ex) {
            System.out.println("Can't load lvl3");
        }
        try {
            level4 = ImageIO.read(new File("image\\Level4.png"));
        } catch (IOException ex) {
            System.out.println("Can't load lvl4");
        }
        try {
            level5 = ImageIO.read(new File("image\\Level5.png"));
        } catch (IOException ex) {
            System.out.println("Can't load lvl5");
        }
        try {
            level6 = ImageIO.read(new File("image\\Level6.png"));
        } catch (IOException ex) {
            System.out.println("Can't load lvl6");
        }
        try {
            level7 = ImageIO.read(new File("image\\Level7.png"));
        } catch (IOException ex) {
            System.out.println("Can't load lvl7");
        }
        try {
            level8 = ImageIO.read(new File("image\\Level8.png"));
        } catch (IOException ex) {
            System.out.println("Can't load lvl8");
        }
        try {
            level9 = ImageIO.read(new File("image\\Level9.png"));
        } catch (IOException ex) {
            System.out.println("Can't load lvl9");
        }
        try {
            level10 = ImageIO.read(new File("image\\Level10.png"));
        } catch (IOException ex) {
            System.out.println("Can't load lvl10");
        }
        try {
            level11 = ImageIO.read(new File("image\\Level11.png"));
        } catch (IOException ex) {
            System.out.println("Can't load lvl11");
        }
        try {
            level12 = ImageIO.read(new File("image\\Level12.png"));
        } catch (IOException ex) {
            System.out.println("Can't load lvl12");
        }
        try {
            level13 = ImageIO.read(new File("image\\Level13.png"));
        } catch (IOException ex) {
            System.out.println("Can't load lvl13");
        }
        try {
            level14 = ImageIO.read(new File("image\\Level14.png"));
        } catch (IOException ex) {
            System.out.println("Can't load lvl14");
        }
         try {
            Underground = ImageIO.read(new File("image\\underground.png"));
        } catch (IOException ex) {
            System.out.println("Can't load Background");
        }
        try {
            Heart = ImageIO.read(new File("image\\heart.png"));
        } catch (IOException ex) {
            System.out.println("Can't load Heart");
        }
         try {
            Portal = ImageIO.read(new File("image\\Portal.png"));
        } catch (IOException ex) {
            System.out.println("Can't load Portal");
        }
        try {
            key = ImageIO.read(new File("image\\key.png"));
        } catch (IOException ex) {
            System.out.println("Can't load key");
        }
         try {
            Open_Gate = ImageIO.read(new File("image\\Open Gate.png"));
        } catch (IOException ex) {
            System.out.println("Can't load Gate");
        }
         try {
            DarkBlock = ImageIO.read(new File("image\\darkStoneBlock.png"));
        } catch (IOException ex) {
            System.out.println("Can't load darkStoneBlock");
        }
        try {
            blackBlock = ImageIO.read(new File("image\\blackBlock.png"));
        } catch (IOException ex) {
            System.out.println("Can't load blackBlock");
        }
        try {
            blueOrb = ImageIO.read(new File("image\\blueOrb.png"));
        } catch (IOException ex) {
            System.out.println("Can't load blueOrb");
        }
        try {
            SkullBlock = ImageIO.read(new File("image\\skull_block.png"));
        } catch (IOException ex) {
            System.out.println("Can't load skull_block");
        }
        try {
            doorRock = ImageIO.read(new File("image\\doorRock.png"));
        } catch (IOException ex) {
            System.out.println("Can't load doorRock");
        }
        try {
            PurpleBlock = ImageIO.read(new File("image\\purpleBlock.png"));
        } catch (IOException ex) {
            System.out.println("Can't load purpleBlock");
        }
        try {
            ground = ImageIO.read(new File("image\\grassDirtBlock.png"));
        } catch (IOException ex) {
            System.out.println("Can't load dirtblock");
        }
        try {
            stoneBlock = ImageIO.read(new File("image\\stoneBlock.png"));
        } catch (IOException ex) {
            System.out.println("Can't load stoneBlock");
        }
         try {
            stoneBlockHorizontal = ImageIO.read(new File("image\\stoneBlockHorizontal.png"));
        } catch (IOException ex) {
            System.out.println("Can't load stoneBlockHorizontal");
        }
        try {
            stonePathBlock = ImageIO.read(new File("image\\stonePathBlock.png"));
        } catch (IOException ex) {
            System.out.println("Can't load stonePathBlock");
        }
        try {
            stonePathBlockVertical = ImageIO.read(new File("image\\stonePathBlockVertical.png"));
        } catch (IOException ex) {
            System.out.println("Can't load stonePathBlockVertical");
        }
        try {
            ice = ImageIO.read(new File("image\\iceBlock.png"));
        } catch (IOException ex) {
            System.out.println("Can't load iceblock");
        }
        try {
            hero = ImageIO.read(new File("image\\Standing.png"));
        } catch (IOException ex) {
            System.out.println("Can't load standing");
        }
        try {
            reversehero = ImageIO.read(new File("image\\Standing Left.png"));
        } catch (IOException ex) {
            System.out.println("Can't load left standing");
        }
        try {
            Shoot = ImageIO.read(new File("image\\Shooting.png"));
        } catch (IOException ex) {
            System.out.println("Can't load Shooting");
        }
         try {
            JumpingRight = ImageIO.read(new File("image\\Jumping.png"));
        } catch (IOException ex) {
            System.out.println("Can't load Jumping Right");
        }
          try {
            JumpingLeft = ImageIO.read(new File("image\\Jumping Left.png"));
        } catch (IOException ex) {
            System.out.println("Can't load Jumping Left");
        }
        try {
            for (int i = 0; i < 4; i += 1) {

                File f = new File("image\\Running " + (int) i + " Left.png");
                Hero_Running[(int) i] = ImageIO.read(new File("image\\Running " + (int) i + ".png"));
                ReverseHero_Running[(int) i] = ImageIO.read(f);

            }
        } catch (IOException ex) {
            System.out.println("Can't load running");
        }
         try {
            Enemy_Right = ImageIO.read(new File("image\\Enemy Right.png"));
        } catch (IOException ex) {
            System.out.println("Can't load Enemy");
        }
          try {
            Enemy_Left = ImageIO.read(new File("image\\Enemy Left.png"));
        } catch (IOException ex) {
            System.out.println("Can't load Enemy");
        }
           try {
            Enemy2_Right = ImageIO.read(new File("image\\Ice Man2.png"));
        } catch (IOException ex) {
            System.out.println("Can't load Fish");
        }
          try {
            Enemy2_Left = ImageIO.read(new File("image\\Ice Man2 Left.png"));
        } catch (IOException ex) {
            System.out.println("Can't load Fish");
        }
           try {
            Enemy3_Right = ImageIO.read(new File("image\\Medusa Head Right.png"));
        } catch (IOException ex) {
            System.out.println("Can't load Medusa");
        }
            try {
            Enemy3_Left = ImageIO.read(new File("image\\Medusa Head.png"));
        } catch (IOException ex) {
            System.out.println("Can't load Medusa");
        }
         try {
            Enemy4_Right = ImageIO.read(new File("image\\Enemy4 Right.png"));
        } catch (IOException ex) {
            System.out.println("Can't load Devil");
        }
            try {
            Enemy4_Left = ImageIO.read(new File("image\\Enemy4.png"));
        } catch (IOException ex) {
            System.out.println("Can't load Devil");
        }
        try {
            Enemy5_Right = ImageIO.read(new File("image\\Enemy5.png"));
        } catch (IOException ex) {
            System.out.println("Can't load Ectoplasm");
        }
            try {
            Enemy5_Left = ImageIO.read(new File("image\\Enemy5.png"));
        } catch (IOException ex) {
            System.out.println("Can't load Ectoplasm");
        }
          try {
            Spike = ImageIO.read(new File("image\\spikes.png"));
        } catch (IOException ex) {
            System.out.println("Can't load spikes");
        }
        try {
            SpikePointingLeft = ImageIO.read(new File("image\\spikesPointingLeft.png"));
        } catch (IOException ex) {
            System.out.println("Can't load spikes pointing left");
        }
        try {
            SpikePointingRight = ImageIO.read(new File("image\\spikesPointingRight.png"));
        } catch (IOException ex) {
            System.out.println("Can't load spikes pointing right");
        }
        try {
            LordSkull = ImageIO.read(new File("image\\Lord Skull.png"));
        } catch (IOException ex) {
            System.out.println("Can't load Lord Skull");
        }
        try {
            LordSkullRight = ImageIO.read(new File("image\\Lord Skull Right.png"));
        } catch (IOException ex) {
            System.out.println("Can't load Lord Skull Right");
        }
        try {
            Death = ImageIO.read(new File("image\\Death.png"));
        } catch (IOException ex) {
            System.out.println("Can't load Death");
        }
        try {
            DeathRight = ImageIO.read(new File("image\\Death Right.png"));
        } catch (IOException ex) {
            System.out.println("Can't load Death Right");
        }
        try {
            Shadow = ImageIO.read(new File("image\\Shadow.png"));
        } catch (IOException ex) {
            System.out.println("Can't load Shadow");
        }
        try {
            ShadowRight = ImageIO.read(new File("image\\Shadow Right.png"));
        } catch (IOException ex) {
            System.out.println("Can't load Shadow Right");
        }
        try {
            Demon = ImageIO.read(new File("image\\Demon.png"));
        } catch (IOException ex) {
            System.out.println("Can't load Demon");
        }
        try {
            DemonRight = ImageIO.read(new File("image\\Demon Right.png"));
        } catch (IOException ex) {
            System.out.println("Can't load Demon Right");
        }
           try {
            Dracula = ImageIO.read(new File("image\\Dracula.png"));
        } catch (IOException ex) {
            System.out.println("Can't load Dracula");
        }
        try {
            Dragon = ImageIO.read(new File("image\\Dragon.png"));
        } catch (IOException ex) {
            System.out.println("Can't load Dragon");
        }
    }
}
