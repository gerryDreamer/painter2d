package drawing2d;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 * this class provides drawing capabilities for this application, it creates a full array memory of 
 * 500 shape objects that are either dynamically drawn by the application, or user defined.
 * this class houses all the major mechanisms of the overall library.
 * @author gerryDreamer
 * 
 */
public class DrawPanel extends JPanel{
    private float dashVal;
    private int width;//sets the stroke width
    private Stroke stroke;
    private int shapeNumber = 1000;
    private MyShape shapes[] = new MyShape[shapeNumber];//sets the number of shapes to be drawn by the user as shapeNumber
    private MyShape currentShape;//rep. the current shape being drawn
    private MyLine lines;
   // private MyBoundedShape bounded;
    private MyOval ovals;
    private MyRect rect;
    private int shapeCount = 0;//counts # shapes in the array 
    private int shapeType;//deter. tpe of shape to draw
    private Paint currentPaint;
    private boolean filledShape;
    private JLabel statusLabel = new JLabel();  
    private Color color1 ;
    private Color color2 ;
    private boolean isPrimitive;
    private int startAngle;
    private int finalAngle;
    //the following objects allow for file saving capabilities of the program
    private JFileChooser fileChooser = new JFileChooser();
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private int result;//shows the current JFileChooser dialog, either open,save, or a custom setting as in 
    //JFileChooser fileChooser;
    // result = fileChooser.showOpenDialog(this);
       
    /**
     *
     * @param label the current JLabel used in the status bar of the application.
     */
        public DrawPanel(JLabel label){       
        statusLabel = label;
        setShapeType(0);
        setShapeCount(0);
        setCurrentShape(null);
        setCurrentPaint(Color.BLACK);
        setBackground(Color.WHITE);
        setCurrentStroke(new BasicStroke(2.0f));
        setCurrentColor1(Color.BLACK);
        setCurrentColor2(Color.BLUE);
        setPrimitive(false);
        setCurrentStartAngle(0);
        setCurrentFinalAngle(90);
        setStrokeWidth(2);
        setDashVal(10);
        addMouseListener(new MouseHandler());
        addMouseMotionListener(new MouseHandler());        
    }   
    private class MouseHandler extends MouseAdapter implements MouseMotionListener{
       @Override 
       /**
        * @param evt
        * an instance of the MouseEvent object
        */
       public void mousePressed(MouseEvent evt){
            //initialize both points of the current shape to the mouse position
           //assign current shape new shape specified by the shapeType
           int VAL = 5;
           if(getShapeType()==0){
               setCurrentShape(new MyLine(evt.getX(),evt.getY(),evt.getX(),evt.getY(),getCurrentPaint(),getCurrentStroke(),getCurrentColor1(),getCurrentColor2(),getPrimitive()));
            }//end if
            else if(getShapeType()==1){
                setCurrentShape(new MyRect(evt.getX(),evt.getY(),VAL,VAL,getCurrentPaint(),getCurrentStroke(),getCurrentColor1(),getCurrentColor2(),getPrimitive(),getFilledShape()));
            }//end else if
            else if(getShapeType()==2){
                setCurrentShape(new MyOval(evt.getX(),evt.getY(),VAL,VAL,getCurrentPaint(),getCurrentStroke(),getCurrentColor1(),getCurrentColor2(),getPrimitive(),getFilledShape()));
            }//end else if
         }
        @Override 
        /**
        * @param evt
        * an instance of the MouseEvent object
        */
        public void mouseReleased(MouseEvent evt){
            //finish drawing current shape
            //add current shape to the array
            /**
             * shape count determines the insertion index into the array
             */
            getCurrentShape().setFinalX(evt.getX());
            getCurrentShape().setFinalY(evt.getY());
            shapes[getShapeCount()] = getCurrentShape();
            setShapeCount(getShapeCount()+1);
            isReleased();
            repaint();           
           //test example
          /* for(int i=0; i<getShapeCount();++i){
            System.out.printf("%d: %s",getShapeCount(),shapes[i].toString());
        }//end for **/
        }
       @Override  
       /**
        * @param evt
        * an instance of the MouseEvent object
        */
       public void mouseMoved(MouseEvent evt){
           //update the status label to show the mouse coordinates
            statusLabel.setText(String.format("X: %d, Y: %d\n", evt.getX(),evt.getY()));
        }
       @Override  
       /**
        * @param evt
        * an instance of the MouseEvent object
        */
       public void mouseDragged(MouseEvent evt) throws NullPointerException{
            //sets the second point of the current shape
            //to the current mouse pstn
            //allows the user to see the shape while dragging the mouse
           try
           {
                 getCurrentShape().setFinalX(evt.getX());
            getCurrentShape().setFinalY(evt.getY());           
            repaint();
              statusLabel.setText(String.format("X: %d, Y: %d\n", evt.getX(),evt.getY()));
           }
           catch(NullPointerException nullPointerException){
               nullPointerException.printStackTrace();
           }
        }
    }
  @Override
  /**
        * @param g
        * an instance of the Graphics object
        */
  public void paintComponent(Graphics g){
        super.paintComponent(g);//shapeCount = 0 ; shapeCount <shapes.length;++shapeCount
		Graphics2D g2d = (Graphics2D)g;//casts the Graphics2D object to g, since Graphics is a 
                //superclass of Graphics2D
      if(getPrimitive()==false){
          drawShapes(g2d);
      }//end if
      else if(getPrimitive()==true){
          drawShapes(g);
      }//end else if
    }//end paint component 
  /**
   * returns a true value for this method
   * @return 
   * the main essence is to allow this object to save the buffered shapes in a MyShape array
   * once this value has been passed as true, all the stored shapes are drawn
   * @deprecated 
   */
   public boolean isReleased(){
       return true;
   }
   /**
    * 
    * @param b 
    * sets the value of isPrimitive to boolean b
    */
   public void setPrimitive(boolean b){
       isPrimitive = b;
   }
   /**
    * 
    * @return 
    * returns the current value of isPrimitive
    * a true value indicates to the paint manager to draw using java primitive Graphics capabilities
    * else, java Graphics2D features are invoked for drawing.
    */
   public boolean getPrimitive(){
       return isPrimitive;
   }
   /**
    * this method creates the capability of the main panel
    * to clear the shapes it has drawn once it is called
    */
    public void clearDrawing(){
       setShapeCount(0);
       getShapeCount(); 
       repaint();
    }
    /**
     * clears the last shape drawn by the main panel
     */
    public void clearLastShape(){       
     setShapeCount(getShapeCount()-1);
     getShapeCount();
     repaint();
    }
    /**
     * 
     * @param s 
     * sets the current shape count to be the value s
     */
    public void setShapeCount(int s){
        shapeCount = s < 0 ? 0:s;
    }
    /**
     * 
     * @return returns the current shapeCount instance variable
     */
    public int getShapeCount(){
        return shapeCount;
    }
    /**
     * 
     * @param f 
     * the value f is returned as either true or false depending 
     * on the value received from the client
     */
    public void setFilledShape(boolean f){
        filledShape = f;
    }
    /**
     * 
     * @return 
     * returns filledShape as either true or false
     */
    public boolean getFilledShape(){
        return filledShape;
    }
    /**
     * 
     * @param c 
     * the Paint object c is received from an external client
     * this sets the current paint value to c
     */
    public void setCurrentPaint(Paint c){
        currentPaint = c;
    }
    /**
     * 
     * @return 
     * returns the current paint object
     */
    public Paint getCurrentPaint(){
        return currentPaint;
    }
    /**
     * 
     * @param s 
     * sets the current shape value to s
     */
    public void setShapeType(int s){
        shapeType = s;
    }
    /**
     * 
     * @return 
     * returns the current shape type value
     */
    public int getShapeType(){
        return shapeType;
    }
    /**
     * 
     * @param c 
     * sets the current shape of type MyShape to c
     */
    public void setCurrentShape(MyShape c){
        currentShape = c;
    }
    /**
     * 
     * @return 
     * returns the current shape
     */
    public MyShape getCurrentShape(){
        return currentShape;
    }
    /**
     * 
     * @param stroke 
     * sets the value of the current stroke object to stroke
     */
    public void setCurrentStroke(Stroke stroke){
            this.stroke = stroke;
    }
    /**
     * 
     * @return 
     * returns the value of the current stroke object
     */
    public Stroke getCurrentStroke(){
            return this.stroke;
    }
    /**
     * 
     * @param color 
     * sets the 1st gradient color to color
     */
    public void setCurrentColor1(Color color){
        color1 = color;
    }
    /**
     * 
     * @return 
     * returns the first gradient color
     */
    public Color getCurrentColor1(){
        return color1;
    }
    /**
     * 
     * @param color 
     * sets the second gradient color to the value of color
     */
     public void setCurrentColor2(Color color){
        color2 = color;
    }
     /**
      * 
      * @return 
      * returns the second gradient color
      */
    public Color getCurrentColor2(){
        return color2;
    }
    /**
     * @deprecated 
     * @see PainterApplication2D implementation of the GradientHandler class
     * @return 
     * returns a true value which is manipulated by the client
     */
     public boolean isTriggerGradient(){
        return true;
    }
     /**
      * 
      * @param g2d sets the Graphics object 
      */
     public void drawShapes(Graphics2D g2d){
          for(int i=0; i<getShapeCount();++i){
           if(getCurrentShape() != null){
              shapes[i].draw(g2d);
           }//end if           
       }//end iterator
          
     }  //end drawShapes
     /**
      * 
      * @param g draws with primitive java objects
      */
      public void drawShapes(Graphics g){//for primitive shapes
          for(int i=0; i<getShapeCount();++i){
           if(getCurrentShape() != null){
              // getCurrentShape().draw(g);
               shapes[i].draw(g);
           }//end if           
       }
     }  //end drawShapes
/**
 * 
 * @param s 
 * setCurrentStartAngle to s
 */	
public void setCurrentStartAngle(int s){
	startAngle = s;
}
/**
 * 
 * @return 
 * startAngle
 */
public int getCurrentStartAngle(){
	return startAngle;
}
/**
 * 
 * @param f 
 * setCurrentFinalAngle to f
 */
public void setCurrentFinalAngle(int f){
	finalAngle = f;
}
/**
 * 
 * @return return finalAngle
 * 
 */
public int getCurrentFinalAngle(){
	return finalAngle;
}
/**
 * 
 * @param w sets the width to the value w 
 */
public void setStrokeWidth(int w){
    width = w;
}
/**
 * 
 * @return returns the current value of the width
 */
public int getStrokeWidth(){
    return width;
}
/**
 * 
 * @param d sets the value of the stroke dashed to d
 * 
 */
public void setDashVal(float d){
    dashVal =  d;
}
/**
 * 
 * @return returns the value of the stroke dashes
 * 
 */
public float getDashVal(){
    return dashVal;
}
/**
 * the following lines of code introduce file handling capabilities.
 * based on the logic that every shape object is serializable, we attempt to redraw a shape from memory,
 * and place it in secondary storage for future reference.
 * the default file system for the images produced here for now is the serializable format.
 */
 /**
     * 
     * @param result sets the current dialog for the file chooser
     */
    public void setFileChooserDialog(int result){
        this.result = result;
    }
    /**
     *
     * @return  returns the current dialog for the file chooser
     */
    public int getFileChooserDialog(){
        return result;
    }
    /**
     * 
     * @return returns the current file chosen by the user
     */
    public File getFile(){
       //display file dialog so user can choose the file to open       
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        //return the file chooser dialog.
        //if the user clicked cancel button on the dialog, return
        if(getFileChooserDialog()==JFileChooser.CANCEL_OPTION)
            System.exit(1);
        File fileName = fileChooser.getSelectedFile();//returns the selected file
        //dislay an error if invalid
        if(fileName== null || fileName.getName().equals(""))
        {
            JOptionPane.showMessageDialog(this, "Invalid file name", "invalid file name", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        return fileName;
    }
    /**
     * opens the current file saved by the user
     */
    public void openCurrentSaveFile() throws NullPointerException{
     setFileChooserDialog(fileChooser.showSaveDialog(DrawPanel.this));
        try{
          output = new ObjectOutputStream(new FileOutputStream(getFile()));  
        }
        catch(IOException e){
          JOptionPane.showMessageDialog(this, "Error writing to file.", "Error writing to file.", JOptionPane.ERROR_MESSAGE);
        }//end catch
    }//end method open file
    /**
     * opens the current file previously saved by the user
     */
    public void openCurrentReadFile()  throws NullPointerException{
         setFileChooserDialog(fileChooser.showDialog(DrawPanel.this, "Read_image_File"));
        try{
            input = new ObjectInputStream(new FileInputStream(getFile()));
        }
        catch(IOException e){
              JOptionPane.showMessageDialog(this, "Error reading from file.", "Error reading from file.", JOptionPane.ERROR_MESSAGE);
        }
    }//end method open file
    /**
     * closes the current file.
     */
    public void closeCurrentSaveFile(){
        try{
            if(output != null)
                output.close();
        }
        catch(IOException e){
               JOptionPane.showMessageDialog(this, "Error closing file.", "Error closin file.", JOptionPane.ERROR_MESSAGE);
        }
    }//end method close file
    /**
     * close current read file
     */
    public void closeCurrentReadFile(){
          try{
            if(input != null)
                input.close();
        }
        catch(IOException e){
               JOptionPane.showMessageDialog(this, "Error closing file.", "Error closin file.", JOptionPane.ERROR_MESSAGE);
        }
    }//end method close file
     /**
     * this method is used to manipulate the current shapes drawn on the canvas and save them on an external file.
     */
    public void writeCurrentshapes(){
      try{
          output = new ObjectOutputStream(new FileOutputStream(getFile()));//create a new output stream object, that receives as an argument
          //a file output stream, and a file object
          output.writeInt(getShapeCount());//an integer rep the current number of shapes drawn
       MyBoundedShape boundedShape;
       
       for(int i=0; i<getShapeCount();++i){
             //output.writeObject(shapes[i]);
            //output.writeObject(shapes);
             if(shapes[i] instanceof MyLine){
              MyLine line = (MyLine) shapes[i];//cast the current shape as a MyLine object
              output.writeObject(line);
             }//end if
             else if(shapes[i] instanceof MyBoundedShape){
                 //write bounded shape objects
                 boundedShape = (MyBoundedShape) shapes[i];
                    if(boundedShape instanceof MyOval){
                        MyOval oval = (MyOval) boundedShape;
                        output.writeObject(oval);
                        }//end if
                    else if(boundedShape instanceof MyRect){
                        MyRect rect = (MyRect) boundedShape;
                        output.writeObject(rect);
                    } //end inner else if
                 } //end outer else if for boundedshapes
         }//end for
          closeCurrentSaveFile();//close the current file saved by the user.
      }//end try
      catch(IOException e){
            JOptionPane.showMessageDialog(this, "Error in writing to file","Error in writing to file",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
      }
    }//end current write shapes method
    /**
     * this method attempts to read the previously saved files and loads them to memory
     * it attempts to read array objects of the type MyShape
     */
    public void readCurrentShapes() throws ClassCastException{         
           try{
          input = new ObjectInputStream(new FileInputStream(getFile()));
          int count = input.readInt();//read the previously saved number of shapes.
          setShapeCount(count);//set the current number of shapes to this value
         // MyShape currentShape[] = new MyShape[count];//create an instance of MyShape
          MyLine line = new MyLine();//create an instance of MyLine
          MyOval oval = new MyOval();//create an instance of MyOval
          MyRect rect  = new MyRect();//create an instance of MyRect
         // clearDrawing();//call this method to clear the canvas of any shapes that have been drawn  
           // currentShape =  (MyShape) input.readObject();
          MyShape[] newShape = new MyShape[count];
         // Object object[] = new Object[count];
          MyBoundedShape boundedShape;
          //create a curved shapes object
                //   Object object = input.readObject();          
          for(int i=0; i<count; ++i){
              newShape[i] =(MyShape) input.readObject();
              //  Object object = input.readObject();      
              setCurrentShape(newShape[i]);
           if(newShape[i] instanceof MyLine){
               // line = (MyLine) input.readObject();
               line = new MyLine(newShape[i].getOriginX(),newShape[i].getOriginY(),newShape[i].getFinalX(),newShape[i].getFinalY(),newShape[i].getPaint(),newShape[i].getStroke(),newShape[i].getColor1(),newShape[i].getColor2(),newShape[i].getPrimitive());
                 setCurrentShape(line);
            shapes[i] =   getCurrentShape(); 
           }//end if
           else if(newShape[i] instanceof MyBoundedShape){
               boundedShape = (MyBoundedShape) newShape[i];
               if(boundedShape instanceof MyOval){
                //  oval = (MyOval) input.readObject();
               oval = new MyOval(boundedShape.getOriginX(),boundedShape.getOriginY(),boundedShape.getFinalX(),boundedShape.getFinalY(),boundedShape.getPaint(),boundedShape.getStroke(),boundedShape.getColor1(),boundedShape.getColor2(),boundedShape.getPrimitive(),boundedShape.getFilled());
                   setCurrentShape(oval);
              shapes[i] =  getCurrentShape(); 
           }//end inner if
           else if(boundedShape instanceof MyRect){
               rect = new MyRect(boundedShape.getOriginX(),boundedShape.getOriginY(),boundedShape.getFinalX(),boundedShape.getFinalY(),boundedShape.getPaint(),boundedShape.getStroke(),boundedShape.getColor1(),boundedShape.getColor2(),boundedShape.getPrimitive(),boundedShape.getFilled());
                setCurrentShape(rect);
             shapes[i] =   getCurrentShape(); 
           }//end inner else if     
         }   //end  outer else if for the bounded shapes        
                setShapeCount(getShapeCount()+1); 
                getShapeCount();               
         }//end for
           repaint();//call the repaint method to draw each shape once it is read.
         closeCurrentReadFile();//call method close to clear any shape that has not been read
        }//end try
        catch(EOFException e){
            return;
        }//end catch
        catch(ClassNotFoundException e){
              JOptionPane.showMessageDialog(this, "Error in loading class object","Error in loading class object",JOptionPane.ERROR_MESSAGE);
        }//end catch
        catch(IOException e){
              JOptionPane.showMessageDialog(this, "Error in reading from file","Error in reading from file",JOptionPane.ERROR_MESSAGE);
              e.printStackTrace();
        }//end catch        
    }//end method read current shapes
/**
     * this method randomly displays a set of shapes on the screen, up to 16 shapes
     * sets the current shape type to randomly draw shapes of the range 1-4
     * also, it sets the current shape type using a switch statement.
     * in the iterator, it also initializes a random color of type (integer r, integer g, integer b);
     * here, the color is determined by the application system, and not the user.
     * this method draws four shapes in each generation.
     */
    public void generateShapes(){
       Random random = new Random();//initialize the random variable.
        int colorRed;
        int colorBlue;
        int colorGreen;
        Color randomColor;
        int shapeCounter = 0;
        boolean isFilled;//current identifier for MyBoundedShape subclasses
        //additional integers for the MyCurvedShape superclass
        int startAngle;
        int finalAngle;
      //  int shapeType = 4;  //redundant
        int x1, y1, x2, y2;
        int randomShapes = 1 + random.nextInt(15);//the number of shapes to be drawn is random (1-15);
        Paint paint;//current paint
        Color color1;//current color1
        Color color2;//current color2
        Stroke stroke;//current stroke
        boolean isPrimitive;//determines if the current shape is a primitive of a 2D vector
        while(shapeCounter < randomShapes ){
            //create the overall variables that are used by the shapes
            //create at least four copies for each shape
            colorRed = random.nextInt(256);//red
            colorBlue = random.nextInt(256);//green
            colorGreen = random.nextInt(256);//blue
            randomColor = new Color(colorRed, colorBlue, colorGreen);
            //initialize paint
            paint = randomColor;
            //initialize basic colors
            color2 = color1 = new Color(colorRed, colorBlue, colorGreen);
            //initialize stroke
            stroke = new BasicStroke((float)random.nextInt(10));
            x1 = random.nextInt(100);//origin
            y1 = random.nextInt(100);//origin
            x2 = random.nextInt(800);//final
            y2 = random.nextInt(800); //final
           isPrimitive = isFilled = getRandomBooleanForTheFilledShape(1 + random.nextInt(2));
            startAngle = random.nextInt(360);
            finalAngle  = random.nextInt(360);
            switch(shapeCounter)//
            {
                case 1:
                case 2:
                case 3:
                case 4:
                setCurrentShape(new MyLine(x1,y1,x2,y2,paint,stroke,color1,color2,isPrimitive));    //draw the current shape as a Line (4 shapes)
                    break;
                case 5:
                case 6:
                case 7:
                case 8:
              setCurrentShape(new MyOval(x1,y1,x2,y2,paint,stroke,color1,color2,isPrimitive,isFilled));  //draw a new oval shape. (4 shapes)
                    break;
                case 9:
                case 10:
                case 11:
                case 12:
                    setCurrentShape(new MyRect(x1,y1,x2,y2,paint,stroke,color1,color2,isPrimitive,isFilled));//draw a new rectangle shape (4 shapes)
                    break;
                    
                default:
                   //Add future shapes here 
                    break;                   
            }//end switch statement.
            ++shapeCounter;//the counter increases dynamically, until the loop continuation condition is reached (1)
             //as the counter increases, increase the current shape count of the class DrawPanel, to enable, clearing and undo capabilities
             // setShapeCount(getShapeCount()+1); 
            setShapeCount(shapeCounter);
            shapes[getShapeCount()] = getCurrentShape();//sets the current shape as the shape invoked in the switch statement
            //also draws the generated shapes on the canvas
        }//end while repetition statement
    }//end method generate shapes   
/**
     
     * @param val the current random integer
     * @return returns true the parameter is 1, else a false is returned if the parameter is 2.
     * this method allows for the random generation of  a boolean value, for use in drawing subclasses of MyBoundedShape.
     */
    public boolean getRandomBooleanForTheFilledShape(int val){
        if(val == 1){
            return true;
        }//end if
        else
        {
             return false;
        }//end else
    }//end boolean method    
}//end class draw panel
