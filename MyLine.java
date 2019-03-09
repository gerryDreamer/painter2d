package drawing2d;

import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

/**
 * this class provides an line object, whose methods can be manipulated to draw an line(s)
 * @author gerryDreamer
 */
public class MyLine extends MyShape implements Serializable{
    private int count=0;
    /**
     * @see 
     * MyShape method declarations
     */
    public MyLine(){
        super(0,0,0,0,Color.BLACK,new BasicStroke(2.0f),Color.GREEN,Color.BLUE,false);
    }
    public MyLine(int x1, int y1, int x2, int y2,Paint paint,Stroke stroke,Color color1, Color color2,boolean primitive){
        super(x1,y1,x2,y2,paint,stroke,color1,color2,primitive);
    }
    public void draw(Graphics2D g2d){
		g2d.setStroke(getStroke());
         g2d.setPaint(getPaint());
        g2d.drawLine(getOriginX(), getOriginY(), getFinalX(), getFinalY());
    }
    /**
     *  
     * @param g the current graphics object
     */
    public void draw(Graphics g){
        g.setColor(getColor1());
        g.drawLine(getOriginX(), getOriginY(), getFinalX(), getFinalY());
    }
    /**
     * 
     * @return the current count of drawn objects of this type
     */
    public int getCount(){
        return ++count;
    }
     public String toString(){
         return String.format("MyLine: %s\n", super.toString());
     }
}
