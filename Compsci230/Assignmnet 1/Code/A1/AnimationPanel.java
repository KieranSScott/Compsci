/*
 *    ======================================================================
 *    AnimationPanel.java : Moves shapes around on the screen according to different paths.
 *    It is the main drawing area where shapes are added and manipulated.
 *    It also contains a popup menu to clear all shapes.
 *    ======================================================================
 */

 /* Kieran Sean Scott
    UPI: ksco491
    ID: 439868967
    This is the AnimationPanel class for A1
 */

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class AnimationPanel extends JComponent implements Runnable {
    private Thread animationThread = null;    // the thread for animation
    private ArrayList<MovingShape> shapes;        // the ArrayList which stores a list of shapes
    private int currentXPos=10, currentYPos=20,
        currentShapeType=0, // the current shape type
        currentPath=0;        // the current path type
    private Color currentBorderColor = Color.black;  // the current border colour of a shape
    private Color currentFillColor = Color.blue;  // the current fill colour of a shape
    private int delay = 30;         // the current animation speed
    JPopupMenu popup;                // popup menu
    private int currentHeight;
    private int currentWidth;

     /** Constructor of the AnimationPanel
        */
    public AnimationPanel() {
        shapes = new ArrayList<MovingShape>(); //create the ArrayList to store shapes
        Insets insets = getInsets();
        currentWidth = 50;
        currentHeight = 20;
        int marginWidth = getWidth() - insets.left - insets.right;
        int marginHeight = getHeight() - insets.top - insets.bottom;
        popup = new JPopupMenu(); //create the popup menu
        makePopupMenu();
        // add the mouse event to handle popup menu
        addMouseListener( new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                maybeShowPopup(e);
            }

            public void mouseReleased(MouseEvent e) {
                maybeShowPopup(e);
            }

            private void maybeShowPopup(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    popup.show(e.getComponent(), e.getX(), e.getY());
                }
            }
            public void mouseClicked( MouseEvent e ) {
                if (animationThread != null)   // if the animation has started, then
                    for (MovingShape currentShape: shapes)
                        if ( currentShape.contains( e.getPoint()) )  // if the mousepoint is within a shape, then set the shape to be selected/deselected
                            currentShape.setSelected( ! currentShape.isSelected() );
            }
        });
    }

    /** create a new shape
     */
    protected void createNewShape() {
        // get the margin of the frame
        Insets insets = getInsets();
        int marginWidth = getWidth() - insets.left - insets.right;
        int marginHeight = getHeight() - insets.top - insets.bottom;
        // create a new shape dependent on all current properties and the mouse position
        switch (currentShapeType) {
            case 0: {
              MovingRectangle s0 = new MovingRectangle(currentXPos, currentYPos, marginWidth, marginHeight, currentBorderColor, currentFillColor, currentPath, currentWidth, currentHeight);
              shapes.add(s0);
                break;
            }
            case 1: {
              if (currentWidth < currentHeight) {
                int tempCurrentHeight = currentWidth;
                MovingSquare s1 = new MovingSquare(currentXPos, currentYPos, marginWidth, marginHeight, currentBorderColor, currentFillColor, currentPath, currentWidth, tempCurrentHeight);
                shapes.add(s1);
              }
              else if (currentHeight < currentWidth) {
                int tempCurrentWidth = currentHeight;
                MovingSquare s1 = new MovingSquare(currentXPos, currentYPos, marginWidth, marginHeight, currentBorderColor, currentFillColor, currentPath, tempCurrentWidth, currentHeight);
                shapes.add(s1);
              }
              else{
                MovingSquare s1 = new MovingSquare(currentXPos, currentYPos, marginWidth, marginHeight, currentBorderColor, currentFillColor, currentPath, currentWidth, currentHeight);
                shapes.add(s1);
              }
            }
        }
    }

    /** set the current shape type
     * @param s    the new shape type
     */
    public void setCurrentShapeType(int s) {
        currentShapeType = s;
    }

    /** reset the margin size of all shapes in the ArrayList
     */
    public void resetMarginSize() {
        Insets insets = getInsets();
        int marginWidth = getWidth() - insets.left - insets.right;
        int marginHeight = getHeight() - insets.top - insets.bottom ;
        for (MovingShape currentShape: shapes) {
            currentShape.setMarginSize(marginWidth, marginHeight);

        }
    }

    /** set the current path type and the path type for all currently selected shapes
     * @param t    the new path type
     */
    public void setCurrentPathType(int t) {
        currentPath = t;
        for (MovingShape currentShape: shapes) {
            if (currentShape.isSelected()) {
                currentShape.setPath(t);
            }
        }
    }

    /** set the current x and the x for all currently selected shapes
     * @param s    the new x value
     */
    public void setCurrentXPos(int x) {
        currentXPos = x;
        for (MovingShape currentShape: shapes) {
            if (currentShape.isSelected()) {
                currentShape.setX(x);
            }
        }
    }

    /** set the current y and the y for all currently selected shapes
     * @param y    the new y value
     */
    public void setCurrentYPos(int y) {
        currentYPos = y;
        for (MovingShape currentShape: shapes) {
            if (currentShape.isSelected()) {
                currentShape.setY(y);
            }
        }
    }

    /** set the current border colour and the border colour for all currently selected shapes
     * @param bc    the new border colour value
     */
    public void setCurrentBorderColor(Color bc) {
        currentBorderColor = bc;
        for (MovingShape currentShape: shapes) {
            if (currentShape.isSelected()) {
                currentShape.setBorderColor(bc);
            }
        }
    }

    /** set the current fill colour and the border colour for all currently selected shapes
     * @param bc    the new fill colour value
     */
    public void setCurrentFillColor(Color fc) {
        currentFillColor = fc;
        for (MovingShape currentShape: shapes) {
            if (currentShape.isSelected()) {
                currentShape.setFillColor(fc);
            }
        }
    }

    public void setCurrentHeight(int h) {
      currentHeight = h;
      for (MovingShape currentShape: shapes) {
          if (currentShape.isSelected()) {
              currentShape.setHeight(h);
          }
      }
    }

    public void setCurrentWidth(int w) {
      currentWidth = w;
      for (MovingShape currentShape: shapes) {
          if (currentShape.isSelected()) {
              currentShape.setWidth(w);
          }
      }
    }


    /** get the current x position in the top left corner
     * @return x - the x value
     */
    public int getCurrentXPos() {
        return currentXPos;
    }

    /** get the current y position in the top left corner
     * @return y - the y value
     */
    public int getCurrentYPos() {
        return currentYPos;
    }

    public int getCurrentWidth() {
      return currentWidth;
    }

    public int getCurrentHeight() {
      return currentHeight;
    }

    /** get the current fill colour
     * @return currentFillColor - the fill colour value
     */
    public Color getCurrentFillColor() {
        return currentFillColor;
    }

    /** get the current border colour
     * @return currentBorderColor - the border colour value
     */
    public Color getCurrentBorderColor() {
        return currentBorderColor;
    }

    public void getTotalArea() {
      calculateArea(shapes);
    }

    public static <T extends MovingShape> void calculateArea(ArrayList<MovingShape> shapes) {
      double area = 0;
      for (MovingShape currentShape : shapes) {
        area += currentShape.area();
      }
      System.out.println("Area: " + area);
    }

   // you don't need to make any changes after this line ______________

    /** remove all shapes from the ArrayList
     */
    public void clearAllShapes() {
        shapes.clear();
    }

    /**    update the painting area
     * @param g    the graphics control
     */
    public void update(Graphics g){
        paint(g);
    }

    /**    move and paint all shapes within the animation area
     * @param g    the Graphics control
     */
    public void paintComponent(Graphics g) {
        for (MovingShape currentShape: shapes) {
            currentShape.move();
            currentShape.draw(g);
        }
    }

    /** create the popup menu for our animation program
     */
    protected void makePopupMenu() {
        JMenuItem menuItem;
     // clear all
        menuItem = new JMenuItem("Clear All");
        menuItem.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearAllShapes();
            }
        });
        popup.add(menuItem);
     }

    /** change the speed of the animation
     * @param newValue     the speed of the animation in ms
     */
    public void adjustSpeed(int newValue) {
        if (animationThread != null) {
            stop();
            delay = newValue;
            start();
        }
    }

    /**    When the "start" button is pressed, start the thread
     */
    public void start() {
        animationThread = new Thread(this);
        animationThread.start();
    }

    /**    When the "stop" button is pressed, stop the thread
     */
    public void stop() {
        if (animationThread != null) {
            animationThread = null;
        }
    }

    /** run the animation
     */
    public void run() {
        Thread myThread = Thread.currentThread();
        while(animationThread==myThread) {
            repaint();
            pause(delay);
        }
    }

    /** Sleep for the specified amount of time
     */
    private void pause(int milliseconds) {
        try {
            Thread.sleep((long)milliseconds);
        } catch(InterruptedException ie) {}
    }
}
