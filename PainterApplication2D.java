package drawing2d;

import java.awt.BasicStroke;
import java.awt.GradientPaint;
import javax.swing.JColorChooser;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * this class provides the GUI capabilities of this application.
 * @author gerryDreamer
 */
public class PainterApplication2D {

    /**
     * @param args the command line arguments
     */
    //create buttons for file handling
    private static JButton saveButton = new JButton("Save");
    private static JButton loadButton = new JButton("Load");
    private static  JLabel[] arcLabels = new JLabel[3];
    private static String arcValues[] = {"Reset_Arc_","startAngle: ","FinalAngle"};
    private static JTextField[] arcText = new JTextField[2];
    private static JCheckBox arcBox = new JCheckBox();
    private static JCheckBox gradientBox = new JCheckBox();
    private static JLabel gradientLabel = new JLabel("Use Gradient");
    private static JButton firstColorButton = new JButton("First Color");
    private static JButton secondColorButton = new JButton("Second Color");
    private static JLabel lineWidthLabel = new JLabel("Line Width");  
    private static JTextField lineText = new JTextField(10);
    private static  JLabel dashLengthLabel = new JLabel("Dash Length");
       private static JTextField dashText = new JTextField(10);
       private static JCheckBox dashBox = new JCheckBox();
       private static JLabel dashedLabel = new JLabel("Dashed");
       private static JLabel statusLabel = new JLabel();
       private static JPanel inputsPanel= new JPanel();
       private static JPanel mainPanel =new JPanel();
       private static JPanel topPanel = new JPanel();
       private static JButton undoButton = new JButton("Undo");
       private static  JButton clearButton = new JButton("Clear");
       private static JComboBox colorBox;
       private static JComboBox shapeBox;
       private static JCheckBox checkBox = new JCheckBox();
       private static JLabel filledLabel = new JLabel("Filled");
       private static final String shapeNames[] = {"Line","rectangle","Oval"};
       private static final String colorNames[] = {"Red","Green","Black"};   
       private static Color colors[] = {Color.RED, Color.GREEN, Color.BLACK}; 
       private static DrawPanel drawPanel = new DrawPanel(statusLabel);
       private static JPanel lowerPanel = new JPanel();
       private static JCheckBox primitiveBox = new JCheckBox();
       private static JLabel primitiveLabel = new JLabel("Primitive ");
      
    //create main constructor to initialize components
	
    /**
     *
     * @param args the string representation used as a command line argument
     * @exception Exception is thrown in the main thread.
     */
    public static void main(String ...args) throws Exception{
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
              try{
                  try
                  {
                      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                       createGUI(); 
                  }
                  catch(Exception e)
                  {
                    e.printStackTrace();
                  }
              }
              catch(Exception e){
                  e.printStackTrace();
              }
            }
        });
        //System.out.printf("Modifiers: %s\nMethods: %s\n",drawPanel.getClass().getModifiers(),drawPanel.getClass().getMethods());
    }

    /**
     *
     */
    public static void createGUI(){
        JFrame f = new JFrame("Assignment");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(500, 500);
        f.setVisible(true);		
        f.setLayout(new BorderLayout());
        f.add(drawPanel,BorderLayout.CENTER);
        f.add(lowerPanel,BorderLayout.SOUTH);
        f.add(mainPanel,BorderLayout.NORTH);
        mainPanel.setLayout(new GridLayout(2,1));
        mainPanel.add(topPanel);		
        mainPanel.add(inputsPanel);	
        lowerPanel.add(statusLabel);
        lowerPanel.setBackground(Color.WHITE);
        statusLabel.setForeground(Color.RED);
        //add components to JPanel
                     
         //set colorBox
         colorBox = new JComboBox(colorNames);
        shapeBox = new JComboBox(shapeNames);
        topPanel.setLayout(new FlowLayout());
        topPanel.add(undoButton);
        topPanel.add(clearButton);
        topPanel.add(colorBox);
        topPanel.add(shapeBox);
        topPanel.add(checkBox);
        topPanel.add(filledLabel);
        topPanel.add(primitiveBox);
        topPanel.add(primitiveLabel);   
          //populate top panel with aditional elements
          //initialize components
          for(int i=0; i<arcLabels.length;++i){
              arcLabels[i] = new JLabel(arcValues[i]);
           if(i >= 0 && i <=1)
                  arcText[i] = new JTextField(5);
          }//end for loop
          topPanel.add(arcBox);
           topPanel.add(arcLabels[0]);
            topPanel.add(arcLabels[1]);
             topPanel.add(arcText[0]);
              topPanel.add(arcLabels[2]);
               topPanel.add(arcText[1]);  
                 //add file processing buttons to the top panel
               topPanel.add(saveButton);//add save button
               topPanel.add(loadButton);//and load button
            
                //add components to inputsPanel
		inputsPanel.setBackground(Color.WHITE);
		inputsPanel.setLayout(new FlowLayout());
		inputsPanel.add(gradientBox);
		inputsPanel.add(gradientLabel);
		inputsPanel.add(firstColorButton);
		inputsPanel.add(secondColorButton);
		inputsPanel.add(lineWidthLabel);
		inputsPanel.add(lineText);
		inputsPanel.add(dashLengthLabel);
		inputsPanel.add(dashText);
		inputsPanel.add(dashBox);
		inputsPanel.add(dashedLabel);
                topPanel.setBackground(Color.WHITE);
                colorBox.addItemListener(new ItemHandler());
                shapeBox.addItemListener(new ShapeHandler());
                undoButton.addActionListener(new ButtonHandler());
                clearButton.addActionListener(new ButtonHandler());
		firstColorButton.addActionListener(new ButtonHandler());
		secondColorButton.addActionListener(new ButtonHandler());
                //create event handlers for file processing buttons
                saveButton.addActionListener(new ButtonHandler());
                loadButton.addActionListener(new ButtonHandler());
                //create events for for the check box 
                checkBox.addItemListener(new CheckHandler());
		//add listeners for inputsPanel items
		//dashes[0] = floatVal;
		gradientBox.addItemListener(new GradientHandler());
		dashBox.addItemListener(new DashListener());
                //add a handler for primitiveBox
                primitiveBox.addItemListener(new PrimitiveHandler());
                //create an item listener for arcBox
                 arcBox.addItemListener(new ArcBoxHandler());
   }
    private static class ArcBoxHandler implements ItemListener{
        public void itemStateChanged(ItemEvent evt) throws NumberFormatException{
           try{
                if(evt.getStateChange()==ItemEvent.SELECTED){
                drawPanel.setCurrentStartAngle(Integer.parseInt(arcText[0].getText()));
                drawPanel.getCurrentStartAngle();
                drawPanel.setCurrentFinalAngle(Integer.parseInt(arcText[1].getText()));
                 drawPanel.getCurrentFinalAngle();
            }//end if
            else if(!(evt.getStateChange()==ItemEvent.SELECTED)){
                drawPanel.getCurrentStartAngle();
                drawPanel.getCurrentFinalAngle();
            }//end else if
           }
           catch(NumberFormatException numberFormatException){
               arcText[0].setText("");
               arcText[1].setText("");
             StackTraceElement[] traceElements = numberFormatException.getStackTrace();
                        // System.out.println("Class\t\tFile\t\tLine\tMethod");
                       for(StackTraceElement element: traceElements){
                           System.out.printf("%s\t",element.getClassName());
                            System.out.printf("%s\t",element.getFileName());
                             System.out.printf("%s\t",element.getLineNumber());
                              System.out.printf("%s\n",element.getLineNumber());
                       }   
           }
        }
    }//end ArcBoxHandler inner class 
    private static class PrimitiveHandler implements ItemListener{
        public void itemStateChanged(ItemEvent evt){
            if(evt.getStateChange()==ItemEvent.SELECTED){
                drawPanel.setPrimitive(primitiveBox.isSelected());
                drawPanel.getPrimitive();
            }//end if
            else if(!(evt.getStateChange()==ItemEvent.SELECTED)){
                drawPanel.setPrimitive(false);
                drawPanel.getPrimitive();
            }//end else if
        }
    }//end PrimitiveHandler
   private static class DashListener implements ItemListener {
	   public void itemStateChanged(ItemEvent evt) throws NumberFormatException{				   
		   try{
                        if(evt.getStateChange()==ItemEvent.SELECTED)
		   {
			 drawPanel.setStrokeWidth(Integer.parseInt(lineText.getText()));
				drawPanel.setDashVal(Float.parseFloat(dashText.getText()));
                                float dashes[] = {drawPanel.getDashVal()};//set as a local variable
			   drawPanel.setCurrentStroke(new BasicStroke(drawPanel.getStrokeWidth(),BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,10,dashes,0));
		   }//end if
		   else if(!(evt.getStateChange()==ItemEvent.SELECTED)){
			 drawPanel.setStrokeWidth(Integer.parseInt(lineText.getText()));
			    drawPanel.setCurrentStroke(new BasicStroke(drawPanel.getStrokeWidth(),BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
		   }//end if
                   }//end try
                   catch(NumberFormatException numberFormatException){
                       lineText.setText("");
                       dashText.setText("");
                       StackTraceElement[] traceElements = numberFormatException.getStackTrace();
                        // System.out.println("Class\t\tFile\t\tLine\tMethod");
                       for(StackTraceElement element: traceElements){
                           System.out.printf("%s\t",element.getClassName());
                            System.out.printf("%s\t",element.getFileName());
                             System.out.printf("%s\t",element.getLineNumber());
                              System.out.printf("%s\n",element.getLineNumber());
                       }
                   }
	   }
   }//end dash listeners
	private static class GradientHandler implements ItemListener{
		public void itemStateChanged(ItemEvent evt){
			if(evt.getStateChange()==ItemEvent.SELECTED){
                            drawPanel.isTriggerGradient();
                            drawPanel.getCurrentColor1();
                            drawPanel.getCurrentColor2();
				drawPanel.setCurrentPaint(new GradientPaint(0,0,drawPanel.getCurrentColor1(),50,50,drawPanel.getCurrentColor2(),true));
			}//end if conditional
			else
				drawPanel.setCurrentPaint(drawPanel.getCurrentPaint());
		}//end itemStateChanged
	}//end GradientHandler
    private static class CheckHandler implements ItemListener{
    public void itemStateChanged(ItemEvent evt){
        if(evt.getStateChange()==ItemEvent.SELECTED){
            drawPanel.setFilledShape(checkBox.isSelected());
            drawPanel.getFilledShape();
        }//end if
        else if (!(evt.getStateChange()==ItemEvent.SELECTED))
        {
             drawPanel.setFilledShape(false);
            drawPanel.getFilledShape();
        }
    }
}
    private static class ButtonHandler implements ActionListener{
        public void actionPerformed(ActionEvent evt){
            if(evt.getSource()==undoButton){
                drawPanel.clearLastShape();
            }//end if
            else if(evt.getSource()==clearButton){
                drawPanel.clearDrawing();
            }//end else if
            else if(evt.getSource()==firstColorButton){
                    if(drawPanel.isTriggerGradient()){
                        drawPanel.setCurrentColor1(JColorChooser.showDialog(mainPanel,"Choose a color",drawPanel.getCurrentColor1()));
                    if(drawPanel.getCurrentColor1()==null)
                            drawPanel.setCurrentColor1(Color.GREEN);
                    }
            }//end else if
            else if(evt.getSource()==secondColorButton){
                    if(drawPanel.isTriggerGradient()){
                    drawPanel.setCurrentColor2(JColorChooser.showDialog(mainPanel,"Choose a color",drawPanel.getCurrentColor2()));
                    if(drawPanel.getCurrentColor2()==null)
                            drawPanel.setCurrentColor2(Color.BLUE);
                    }
            }//end else if conditional
            /*
            *the following buttons allow for the program to handle file processing capabilities.
            */
            else if(evt.getSource()==saveButton){
              try{
                    drawPanel.openCurrentSaveFile();
                drawPanel.writeCurrentshapes();  
              } 
              catch(NullPointerException e){
                  e.printStackTrace();
              }
            }//end else if
            else if(evt.getSource()==loadButton){               
              try{
                 drawPanel.openCurrentReadFile();
                drawPanel.clearDrawing();
                drawPanel.readCurrentShapes();
              }
              catch(ClassCastException e){
                  e.printStackTrace();
              }//end catch
              catch(NullPointerException e){
                  e.printStackTrace();
              }
            }//end else if
         }
    }
    private static class ItemHandler implements ItemListener{
        public void itemStateChanged(ItemEvent evt){
           if(evt.getStateChange()==ItemEvent.SELECTED){
              if(drawPanel.getPrimitive()==false){
                  drawPanel.setCurrentPaint(colors[colorBox.getSelectedIndex()]);
              drawPanel.getCurrentPaint();  
              }//end if
              else if(drawPanel.getPrimitive()==true){
                  drawPanel.setCurrentColor1(colors[colorBox.getSelectedIndex()]);
              drawPanel.getCurrentColor1();
              }
           } //end if
        }
    }
    private static class ShapeHandler implements ItemListener{
        public void itemStateChanged(ItemEvent evt){
           if(evt.getStateChange()==ItemEvent.SELECTED){
              drawPanel.setShapeType(shapeBox.getSelectedIndex());
              drawPanel.getShapeType();
           } //end if
        }//
        }//end shape handler
   }//end main class
