package ai;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Population {
    
    public static int fittest = -1;
    public int gen;
    
    private boolean wait = false;
    private double mutationRate;
    private int amount;
    private Square[] dots;
    
    public Population(int amount, double mutationRate, int w, int h){
        gen = 0;
        this.mutationRate = mutationRate;
        this.amount = amount;
        dots = new Square[amount];
        for(int i = 0; i < dots.length; i++){
            dots[i] = new Square(null, 0);
        }
    }
    
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
            if(fittest == -1) fittest = getFittest();
            gen++;
            System.out.println(fittest);
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
    
    public int getFittest(){
        double min = 999999;
        int minIdx = -1;
        for(int i = 0; i < dots.length; i++){
            if(dots[i].color == Color.ORANGE){
                minIdx = i;
                break;
            }
            
            int xDist = 895 - dots[i].x, yDist = 43 - dots[i].y;
            double dist = Math.sqrt(xDist*xDist+yDist*yDist);
            if(dist < min){
                minIdx = i;
                min = dist;
            }
        }
        return minIdx;
    }
    
    public void reproduceFittest(){
        Square aux = dots[fittest];
        aux.x = Screen.w/2;
        aux.y = Screen.h*9/10;
        aux.step = 0;
        aux.dead = false;
        aux.veloc = new Vector(0, 0);
        
        dots = new Square[amount];
        dots[0] = aux;
        
        for(int i = 1; i < dots.length; i++){
            /*if(i == fittest){
                dots[i].x = Screen.w/2;
                dots[i].y = Screen.h*9/10;
                dots[i].step = 0;
                dots[i].dead = false;
                dots[i].veloc = new Vector(0, 0);
                continue;
            }*/
            System.out.println("dot " + i);
            dots[i] = new Square(dots[0].brain, mutationRate);
        }
        fittest = -1;
    }
    
}
