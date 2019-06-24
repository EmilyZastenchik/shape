/*
 * Emily Zastenchik
Java HW assignment 3
 */
package shape;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Shape extends JFrame {

    public Shape(){
        //make buttons to adjust size
        JButton jbtLessSides = new JButton ("-1");
        JButton jbtMoreSides = new JButton ("+1");
        //make panel
        JPanel panel = new JPanel();    
        setTitle("Polygon Sides");
        //add both buttons to panel
        panel.add(jbtLessSides);
        panel.add(jbtMoreSides);
        //create instance of MakeShapePanel
        MakeShapePanel canvas = new MakeShapePanel();  
        //add shape panel and buttons to frame using BorderLayout manager
        add(canvas, BorderLayout.CENTER);  
        add(panel, BorderLayout.SOUTH);
        
        //create and register event handlers
        //lambda expressions for ActionListeners
        jbtLessSides.addActionListener((ActionEvent e) -> {
            canvas.sub1();
        });

        jbtMoreSides.addActionListener((ActionEvent e) -> {
            canvas.add1();
        });

        canvas.addMouseListener(new MouseAdapter(){
            @Override//override method from MouseAdapter (abstract class)
            public void mouseClicked(MouseEvent e){
            //sub1 or add1 side count when mouse clicks button
                if(e.getButton()==MouseEvent.BUTTON1)
                    canvas.sub1();
                else if (e.getButton() == MouseEvent.BUTTON2)
                    canvas.add1();
            }
        });

    }
    public static void main(String[]args){

        Shape frame = new Shape();
        frame.setSize(500, 500);
        frame.setLocation(200, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
//Draw a polygon in the panel
class MakeShapePanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

    //Create a polygon object
        Polygon shape = new Polygon();
        for (int j = 0; j < 200; ++j ){
            for (int i = 0; i < numberOfSides; ++i){
                int x = getWidth()/ 2;
                int y = getHeight()/ 2;
                //changes size of polygon
                int radius = (int)(Math.min(getWidth(), getHeight()) * .5);
                double angle = 2 * Math.PI / numberOfSides;

                    int calcX = (int)(x + radius *  Math.cos(i * angle));
                    int calcY = (int)( y - radius * Math.sin(i * angle));
                    
                //adds new x and y coordinates to shape
                shape.addPoint(calcX, calcY);
            }
            g.drawPolygon(shape);
        }
    }
    private int numberOfSides = 3; //polygon has at least 3 sides
    //sets default sides to 3 (makes triangle first)
    public void add1() {
        //increment side count and repaint component
       numberOfSides++;
        repaint();
    }

    public void sub1() {
         //decrement side count and repaint component
        if(numberOfSides> 3) //polygon has a minimum of three sides
        {
            numberOfSides--;
            repaint(); 
        }
     }
}
