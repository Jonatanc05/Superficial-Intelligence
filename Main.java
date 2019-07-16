package ai;
import javax.swing.*;
import java.awt.event.*;

public class Main {
    private static final int WID = 1280, HEI = 700;
    private static final int SQRSIZE = 12, RATE = 75, DEADLINE = 36, AMOUNT = 64;
    private static final double MUTRATE = 0.04;
    
    public static Screen s;
    
    public static Timer updater = new Timer(RATE, (ActionEvent e) -> {
        s.repaint();
    });

    public static void main(String[] args){
        
        JFrame f = new JFrame("AI testing");
        f.setSize(WID, HEI);
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
        
        s = new Screen(WID, HEI, SQRSIZE, DEADLINE, AMOUNT, MUTRATE);
        f.add(s);
        updater.start();
        
        f.setVisible(true);
    }
    
}
