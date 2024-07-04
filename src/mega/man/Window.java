package mega.man;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class Window
{
    /**
     * 
     * @param w 800
     * @param h 600
     * @param mode 1 for single player,
     *             2 for VS mode, 
     *             3 for About 
     * @param Description sets the window size according to input parameters(800*600 by default)
     * it initializes the  window, adds components, adjusts the sizes and starts the game
     */
    
    public Window(int w,int h,String title,Game game,int mode)
    {
        
        JFrame frame=new JFrame(title);
        JMenuBar menuBar = new JMenuBar();
        JMenu menu= new JMenu("menu");
        JMenuItem save, load;
       
        switch(mode)
        {
            case 1:
                frame.add(menuBar,BorderLayout.NORTH);
                menuBar.add(menu);
                //Preparing the GUI for saving and loading
                save= new JMenuItem("save");
                save.addActionListener(
                    new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent ae) 
                        {
                            //Saving Code
                            JFileChooser s= new JFileChooser();
                            s.showSaveDialog(s);
                            File f= s.getSelectedFile();
                            if(f!=null)
                            {
                                String filePath= f.getPath();
                                Handler.saveGame(filePath);
                            }
                        }
                    }
                );
                load= new JMenuItem("load");
                load.addActionListener(
                    new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent ae) 
                        {
                            //Loading Code
                            JFileChooser l= new JFileChooser();
                            l.showOpenDialog(l);
                            File f = l.getSelectedFile();
                            if(f!=null)
                            {
                                String FilePath= f.getPath();
                                Handler.loadGame(FilePath);
                            }
                        }
                    }
                );
                menu.add(save);
                menu.add(load);
                
                //setting the Game window Dimentions
                game.setPreferredSize(new Dimension(w,h));
                game.setMaximumSize(new Dimension(w,h));
                game.setMinimumSize(new Dimension(w,h));

                frame.add(game);
                frame.pack();
                frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

                game.start();
            break;

                //For Multiplayer Mode
                //not implemented yet
            case 2: break;

            case 3:
                About about= new About();
                about.setVisible(true);
            break;
        }
    }
}
