package drawing2d;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Stroke;
import java.io.Serializable;
/**
 * this is the default superclass for drawing any shape, it encapsulates methods for drawing,
 * primitive and 2D graphics. It is abstract and therefore cannot be instantiated.
 * @author gerryDreamer
 */
public abstract class MyShape implements Serializable{
    /**
     *  
     * these values are initialized in main and default constructors
     */
    private int x1,y1,x2,y2;
   // private Color color;
   private Paint paint;
   private Stroke stroke;
   private Color color1;
   private Color color2;
   private Color color;
   private boolean isPrimitive;
   /**
    *
    * see the method declarations
    */
   public MyShape(){
       
               setOriginX(0);
               setOriginY(0);
               setFinalX(0);
               setFinalY(0);
               setPaint(Color.BLACK);
               setStroke(new BasicStroke(2.0f));
               setColor1(Color.GREEN);
               setColor2(Color.BLUE);
               setPrimitive(false);              
   } 
   public MyShape(int x1, int y1, int x2, int y2,Paint paint,Stroke stroke,Color color1,Color color2,boolean primitive){
        /** @Param
        * the following method declarations initialize default values of the class
        * incase the user does input values
        */
      setOriginX(x1);
      setOriginY(y1);
      setFinalX(x2);
      setFinalY(y2);
      setPaint(paint);
      setStroke(stroke);
      setColor1(color1);
     setColor2(color2);
     setPrimitive(false);
   }
   /**
    * 
    * @param stroke 
    * this variable sets the value of the main stroke instance variable
    * to be a value as received from the application that uses this object
    */
   public void setStroke(Stroke stroke){
	   this.stroke = stroke;
   }
   /**
    * 
    * @return 
    * returns a Value from the Stroke object instance variable
    * the value is received as is and cannot be modified
    */
   public Stroke getStroke(){
	   return stroke;
   }
   /**
    * 
    * @param x1 
    * sets the first origin coordinate to this value
    */
   public void setOriginX(int x1){
       this.x1 = (x1 < 0) ? 0:x1;
   }
   /**
    * 
    * @return 
    * returns the x coordinate of the origin
    */
   public int getOriginX(){
       return x1;
   }
   /**
    * 
    * @param y1 
    * sets the  the y coordinate of the origin
    */
   public void setOriginY(int y1){
       this.y1 = (y1 < 0)? 0:y1;
   }
   /**
    * 
    * @return 
    * returns the y origin coordinate
    */
   public int getOriginY(){
       return y1;
   }
   /**
    * 
    * @param x2 
    * final x coordinate is set
    */
   public void setFinalX(int x2){
       this.x2 = (x2 <0)? 0:x2;
   }
   /**
    * 
    * @return final x coordinate is returned
    */
   public int getFinalX(){
       return x2;
   }
   /**
    * 
    * @param y2 
    * final y coordinate is set
    */
   public void setFinalY(int y2){
       this.y2 = (y2 <0)? 0:y2;
   }
   /**
    * 
    * @return final y coordinate is returned
    */
   public int getFinalY(){
       return y2;
   }
   /**
    * 
    * @param paint sets the paint object
    */
   public void setPaint(Paint paint){
       this.paint = paint;
   }
   /**
    * 
    * @return returns an object representation of Paint
    */
   public Paint getPaint(){
       return paint;
   }
   /**
    * 
    * @param color sets the first color of the gradient
    */
   public void setColor1(Color color){
	   color1 = color;
   }
   /**
    * 
    * @return returns the first gradient color
    */
   public Color getColor1(){
	   return color1;
   }
   /**
    * 
    * @param color sets the 2nd gradient color
    */
   public void setColor2(Color color){
	   color2 = color;
   }
   /**
    * 
    * @return returns the second gradient color
    */
   public Color getColor2(){
	   return color2;
   }
   /**
    * 
    * @param g the current graphics object
    */
   public abstract void draw(Graphics g);
   //create abstract method draw with Graphics 2d
   /**
    * 
    * @param g2d an abstract representation of drawing shapes with Graphics2D
    */
   public abstract void draw(Graphics2D g2d);
   /**
    * returns the number of times this object is called
    * @return 
    */
   /**
    * 
    * a more enhanced version is used on draw panel class
    * other wise the superclass implementation of this class is redundant
    *@deprecated  
    * @return the current count of shapes
    */
   public abstract int getCount();
   /**
    * @deprecated    
    * previously used in the first version to set the graphics color
    * for primitives
    * @param color sets the color instance  variable as this value
    */
   public void setColor(Color color){
       this.color = color;
   }
   /**
    * 
    * @deprecated
    * @return returns the current graphics color
    */
   public Color getColor(){
       return this.color;
   }
   /**
    * 
    * @return a string representation of this object is returned
    */
   public String toString(){
       return String.format("x1: %d, y1: %d , x2: %d, y2: %d\n"
               , getOriginX(),getOriginY(),getFinalX(),
               getFinalY());
   }
   /**
    * 
    * @param b sets the value of isPrimitive to boolean b
    * 
    */
   public void setPrimitive(boolean b){
       isPrimitive = b;
   }
   /**
    * 
    * @return returns the current value of isPrimitive
    * 
    */
   public boolean getPrimitive(){
       return isPrimitive;
   }
}
