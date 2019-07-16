package ai;

public class Vector implements Cloneable{
    private double angle, module;
    
    public Vector(double angle, double module){
        this.angle = angle;
        this.module = module;
    }
    public Vector(){
        this(360 * Math.random(), 5*Math.random());
    }
    
    public void add(Vector vect){
        double xresult = getX() + vect.getX();
        double yresult = getY() + vect.getY();
        
        module = Math.sqrt(xresult*xresult + yresult*yresult);
        angle = Math.toDegrees(Math.atan2(yresult, xresult));
    }
    
    public void mutate(){
        //module += 2*Math.random() - 1;
        angle += 20*Math.random() - 10;
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
