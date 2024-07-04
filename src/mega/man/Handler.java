package mega.man;

import java.awt.Graphics;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * 
 * @author 0
 * This is where all objects that appear in the game are stored (in an array list named "object",
 * it also loops over all list with the tick method and renders(draws)
 * each object while looping to redraw them in the render method. it has other needed methods like add/removeObject
 * and save/loadGame(which is not fully working yet, missing saving the images, 
 * the class implements serializable for the bitwise storing of game objects in saving.)
 */
public class Handler implements Serializable
{
    public static ArrayList<GameObject> object=new ArrayList<GameObject>();
    
    private GameObject tempObject;
    
    /**
     * Loops over all of the different objects contained in the arraylist object
     */
    public void tick()
    {
        for(int i=0;i<object.size();i++)
        {
            tempObject=object.get(i);
            tempObject.tick(object);
        }
    }
    public void render(Graphics g)
    {
        for(int i=0;i<object.size();i++)
        {
            tempObject=object.get(i);
            tempObject.render(g);
        }
    }
    
    public void addObject(GameObject object)
    {
        this.object.add(object);
    }
    
    public void removeObject(GameObject object)
    {
        this.object.remove(object);
    }
    public void clearLevel()
    {
        object.clear();
    }
   
    public static void saveGame(String filePath)
    {
        try
        {
        FileOutputStream fos= new FileOutputStream(filePath);
        ObjectOutputStream oos= new ObjectOutputStream(fos);
        
        //oos.writeInt(object.size());
        oos.writeObject(object);
        oos.close();
        fos.close();
        }
        catch(IOException e)
        {
            JOptionPane.showMessageDialog(null, "Problem while saving objects: "+e.getMessage());
        }
    }
    public static void loadGame(String filePath)
    {
        try
        {
        FileInputStream fis= new FileInputStream(filePath);
        ObjectInputStream ois= new ObjectInputStream(fis);        
        //ois.readInt();
        Handler.object= (ArrayList<GameObject>)ois.readObject();
        ois.close();
        fis.close();        
        }
        catch(IOException e)
        {
            JOptionPane.showMessageDialog(null, "Problem while laoading objects"+ e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
