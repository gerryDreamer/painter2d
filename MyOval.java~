package drawing2d;

import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

/**
 * this class provides an oval object, whose methods can be manipulated to draw an ovals(s)
 * @author gerryDreamer
 */
public class MyOval extends MyBoundedShape implements Serializable
{
  private int count=0;
  /**
     * @see 
     * MyBoundedShape method declarations
     */
    public MyOval(){
          super(0,0,0,0,Color.BLACK,new BasicStroke(2.0f),Color.GREEN,Color.BLUE,false,false);
    }
    public MyOval(int x1, int y1, int x2, int y2,Paint paint,Stroke stroke,Color color1, Color color2,boolean primitive,boolean b){
         super(x1,y1,x2,y2,paint,stroke,color1,color2,primitive,b);
    }
    /**
     * 
     * @param g the current graphics object
     */
     public void draw(Graphics g){
         g.setColor(getColor1());
       if(getFilled()==false){
            g.fillOval(getOriginX(), getOriginY(), getFinalX(), getFinalY());
       }
       else
            g.drawOval(getOriginX(), getOriginY(), getFinalX(), getFinalY());
    }
     public void draw(Graphics2D g2d){
        g2d.setPaint(getPaint());
	    g2d.setStroke(getStroke());
        if(getFilled()==true){
           g2d.fillOval(getOriginX(), getOriginY(), getFinalX(), getFinalY());  
        }
        else{
            g2d.drawOval(getOriginX(), getOriginY(), getFinalX(), getFinalY()); 
        }       
    }
     /**
     *    
     */
    public int getCount(){
        return ++count;
    }
    public String toString(){
        return String.format("\nMyOval: %s\n", super.toString());
    }
}
