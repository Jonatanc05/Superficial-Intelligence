package ai;

public class Vector implements Cloneable{
    public double angle, module;
    
    public Vector(double angle){
        this.angle = angle;
        this.module = 10;
    }
    public Vector(){
        this(360*Math.random());
    }
    
    public void add(Vector vect){
        double xresult = getX() + vect.getX();
        double yresult = getY() + vect.getY();
        
        //module = Math.sqrt(xresult*xresult + yresult*yresult);
        angle = Math.toDegrees(Math.atan2(yresult, xresult));
    }
    
    public void mutate(){
        //module += 2*Math.random() - 1;
        //angle += 180*Math.random() - 90;
        this.angle = 360*Math.random();
    }
    
    public int getX(){
        return (int) Math.round(module*Math.cos(Math.toRadians(angle)));
    }
    
    public int getY(){
        return (int) Math.round(module*Math.sin(Math.toRadians(angle)));
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException 
    { 
        return super.clone(); 
    } 
}
