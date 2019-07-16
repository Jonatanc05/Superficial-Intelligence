package ai;
import javax.swing.*;
import java.awt.*;

public class Screen extends JPanel{
    
    public Population pop;
    public static int w, h, deadline, sqrSize;
    
    public Screen(int w, int h, int sqrSize, int deadline, int amount, double mutationRate ){
        this.w = w;
        this.h = h;
        this.deadline = deadline;
        this.sqrSize = sqrSize;
        pop = new Population(amount, mutationRate, w, h);
    }
    
    @Override
    public void paintComponent( Graphics g ){
        super.paintComponent(g);
        this.setBackground(Color.BLACK);

        g.setColor(Color.RED);
        g.drawRect(deadline/2, deadline/2, w-(2*deadline), h-(2*deadline));
        
        g.setColor(Color.GREEN);
        g.drawRect(880, 28, 30, 30);
        
        pop.update(g);
        
        g.setColor(Color.WHITE);
        g.drawString(("Generation: "+pop.gen), 25, h-40);
        
    }
    
}
