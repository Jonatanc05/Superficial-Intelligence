package ai;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Population {
    
    public static int fittest = -1;
    public int gen;
    
    private boolean wait = false; //little time between two generations
    private double mutationRate;
    private int amount;
    private Square[] dots;
    
    public Population(int amount, double mutationRate, int w, int h){
        gen = 0;
        this.mutationRate = mutationRate;
        this.amount = amount;
        dots = new Square[amount];
        for(int i = 0; i < dots.length; i++){
            dots[i] = new Square(i, null, 0);
        }
    }

    
    //Called every "RATE" milisseconds
    public void update(Graphics g){
        boolean end = true;
        for(Square dot : dots){
            dot.move();
            dot.draw(g);
            if(!dot.isDead())
                end = false;
        }
        
        if(wait) return;
        
        if(end){
            if(fittest == -1)
                fittest = getFittest();
            else if( dots[fittest].step < Screen.fastestPathMoves )
                Screen.fastestPathMoves = dots[fittest].step;
            gen++;
            dots[fittest].color = Color.BLUE;
            Timer restart = new Timer(0, (ActionEvent e) -> {
                wait = false;
                reproduceFittest();
            });
            restart.setInitialDelay(500);
            restart.setRepeats(false);
            restart.start();
            wait = true;
        }
    }
    
    
    /*
     * Select the best square from a generation so he is the parent 
     * of the next one. Isn't called case any square has reached 
     * the goal, in this case, the first who did it is the fittest.
     */
    public int getFittest(){
        double min = 999999;
        int minIdx = -1;
        for(int i = 0; i < dots.length; i++){
            int xDist = 895 - dots[i].x, yDist = 43 - dots[i].y;
            double dist = Math.sqrt(xDist*xDist+yDist*yDist);
            if(dist < min){
                minIdx = i;
                min = dist;
            }
        }
        return minIdx;
    }
    
    
    //Reproduce the best square so all of them are similar to the fittest
    public void reproduceFittest(){
        Square aux = dots[fittest];
        aux.x = Screen.w/2;
        aux.y = Screen.h*9/10;
        aux.step = 0;
        aux.dead = false;
        aux.veloc = new Vector(0);
        
        dots = new Square[amount];
        //Making him the last one to be drawn and, therefore, always visible
        dots[amount-1] = aux;
        aux.id = amount-1;
        
        for(int i = 0; i < dots.length-1; i++){
            dots[i] = new Square(i, dots[amount-1].brain, mutationRate);
        }
        fittest = -1;
    }
    
}
