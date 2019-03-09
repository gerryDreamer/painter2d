package drawing2d;

import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Paint;
import java.awt.Color;
import java.io.Serializable;

/**
 * this is an abstract superclass for all shapes that fall within bounds, i.e ovals, rectangles, arc ...
 * it encapsulates all the methods inherited from the superclass, Shape and adds methods of its own.
 * @author gerryDreamer
 */
public abstract class MyBoundedShape extends MyShape implements Serializable{
     private boolean isFilled;
     /**
      *  
      * see the MyShape documentation
      */
    public MyBoundedShape(){
        super(0,0,0,0,Color.BLACK,new BasicStroke(2.0f),Color.GREEN,Color.BLUE,false);
         setFilled(false);
    }
    public MyBoundedShape(int x1, int y1, int x2, int y2,Paint paint,Stroke stroke,Color color1, Color color2,boolean primitive,boolean b){
        super(x1,y1,x2,y2,paint,stroke,color1,color2,primitive);
        setFilled(b);
    }
    /**
     * 
     * @param b 
     * sets the value of the boolean is Filled to b
     */
     public void setFilled(boolean b){
       isFilled = b; 
    }
     /**
      * 
      * @return returns the new value of the isFilled instance
      */
    public boolean getFilled(){
        return isFilled;
    }
    /**     * 
     * @return 
     * a string representation of this object
     */
     public String toString(){
         return String.format("%s, %s", super.toString(),getFilled() == true ? "filled":"not filled");
     }
}
