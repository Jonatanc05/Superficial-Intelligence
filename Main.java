package ai;
import javax.swing.*;
import java.awt.event.*;

public class Main {
    private static final int WID = 1000, HEI = 500;
    private static final int SQRSIZE = 6, //Size of the moving square, in pixels
                             RATE = 75, //Time between every move of the squares, in milisseconds
                             DEADLINE = 36, //Margin between screen border and safe area border
                             AMOUNT = 300; //How many squares there will be in each generation
    private static final double MUTRATE = 0.02; //Mutation rate: chance of each square movement be slightly changed
    
    public static Screen s;
    
    public static Timer updater = new Timer(RATE, (ActionEvent e) -> {
        s.repaint();
    });

    public static void main(String[] args){
        
        JFrame f = new JFrame("Superficial Intelligence");
        f.setSize(WID, HEI);
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
        
        s = new Screen(WID, HEI, SQRSIZE, DEADLINE, AMOUNT, MUTRATE);
        f.add(s);
        updater.start();
        
        f.setVisible(true);
    }
    
}
