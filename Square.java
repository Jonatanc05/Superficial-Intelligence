package ai;
import java.awt.*;

public class Square {
    
    public int id;
    public int x, y; //Center coords
    public Vector[] brain;
    public Color color;
    public int step;
    public Vector veloc;
    public boolean dead;
    
    public Square(int id, Vector[] brain, double mutationRate){
        this.id = id;
        this.x = Screen.w/2;
        this.y = Screen.h*9/10;
        veloc = new Vector(0);
        if(brain == null){
            this.brain = new Vector[100];
            for(int i = 0; i < this.brain.length; i++)
                this.brain[i] = new Vector();
        }
        else{//Mutation
            this.brain = brain.clone();
            for(int i = 0; i < this.brain.length; i++){
                try{
                    this.brain[i] = (Vector)brain[i].clone();
                }
                catch(Exception e){System.out.println(e);}
                double r = (new java.util.Random()).nextDouble();
                if (r < mutationRate) {
                    this.brain[i].mutate();
                }
            }
        }
        step = 0;
        dead = false;
        color = Color.WHITE;
    }
    
    public void draw(Graphics g){
        int cornerX = x - Screen.sqrSize/2;
        int cornerY = y - Screen.sqrSize/2;
        
        g.setColor(color);
        g.fillRect(cornerX, cornerY, Screen.sqrSize, Screen.sqrSize);
        //g.setColor(Color.BLACK);
        //g.drawString(""+id, cornerX, cornerY+Screen.sqrSize);
    }
    
    public void move(){
        if(dead) return;
        
        veloc.add(brain[step]);
        
        x += veloc.getX() > 7 ? 7 : veloc.getX();
        y += veloc.getY() > 7 ? 7 : veloc.getY();
        if(++step == brain.length){
            dead = true;
            color = Color.RED;
        }
        
        //Check border
        if(x >= (Screen.w-Screen.deadline*1.5) || x <= Screen.deadline/2
                || y >= (Screen.h-Screen.deadline*1.5) || y <= Screen.deadline/2){
            dead = true;
            color = Color.RED;
        }
        
        //Check goal
        if(x > 885 && x < 905 && y > 28 && y < 48 ){
            dead = true;
            color = Color.GREEN;
            if(Population.fittest == -1){
                Population.fittest = id;
                color = Color.ORANGE;
            }
        }
        
    }
    
    public boolean isDead(){ return dead; }
    
}
