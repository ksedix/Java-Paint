import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The Controller handles user input
 * When the user clicks on a button, on the canvas or drags his mouse, the Controller
 * will change the model
 * When the Controller has changed the model, it will notify the PaintView that the model
 * was changed and tell it to redraw itself
 * The Controller needs a reference to both the PaintModel and the PaintView
 */
public class PaintController extends JFrame implements ActionListener {

    private PaintModel paintModel;
    private PaintView paintView;
    private int group = 0;

    public PaintController(){
        paintModel = new PaintModel();
        paintView = new PaintView(paintModel);

        //Adding listeners to all of the buttons we created in PaintView
        paintView.black.addActionListener(this);
        paintView.red.addActionListener(this);
        paintView.green.addActionListener(this);
        paintView.blue.addActionListener(this);

        paintView.pencil.addActionListener(this);
        paintView.dot.addActionListener(this);
        paintView.oval.addActionListener(this);
        paintView.rect.addActionListener(this);

        paintView.save.addActionListener(this);
        paintView.undo.addActionListener(this);
        paintView.load.addActionListener(this);
        paintView.reset.addActionListener(this);


        paintView.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                String color = paintModel.getColor();
                String form = paintModel.getShape();
                int x = e.getX();
                int y = e.getY();
                int size = paintView.getSliderValue();
                Figure shape = new Figure(color, form, x, y, size, group);
                paintModel.addShape(shape);
                paintView.repaint();
                group++;
            }
        });

        paintView.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                //This function gets called everytime the mouse cursor is moved
                //we will need to update the xMoved and yMoved variable everytime
                //this happens
                int x = e.getX();
                int y = e.getY();
                String color = paintModel.getColor();
                String form = paintModel.getShape();
                int size = paintView.getSliderValue();

                paintModel.mouseMoved(x, y);
                paintView.repaint();

                if (paintModel.getShape().equals("Pencil")){
                    Figure shape = new Figure(color,form,x,y,size,group);
                    paintModel.addShape(shape);
                }

            }
        });


        //JFRAME METHODS - necessary to display the window
        setVisible(true);
        add(paintView);
        pack();
        setLocationRelativeTo(null);
        //Will terminate the program when the window is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "Black":
                paintModel.setColor("black");
                System.out.println(paintModel.getColor());
                break;
            case "Red":
                paintModel.setColor("red");
                System.out.println(paintModel.getColor());
                break;
            case "Green":
                paintModel.setColor("green");
                System.out.println(paintModel.getColor());
                break;
            case "Blue":
                paintModel.setColor("blue");
                System.out.println(paintModel.getColor());
                break;
            case "Pencil":
                paintModel.setShape("Pencil");
                System.out.println(paintModel.getShape());
                break;
            case "Dot":
                paintModel.setShape("dot");
                System.out.println(paintModel.getShape());
                break;
            case "Oval":
                paintModel.setShape("oval");
                System.out.println(paintModel.getShape());
                break;
            case "Rectangle":
                paintModel.setShape("rectangle");
                System.out.println(paintModel.getShape());
                break;
            case "Save":
                try {
                    paintModel.save();
                    System.out.println("Save was successful");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
            case "Undo":
                paintModel.undo();
                paintView.repaint();
                System.out.println("Undid successfully");
                break;
            case "Load":
                try {
                    paintModel.load();
                    paintView.repaint();
                    System.out.println("Load was successful");
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                break;
            case "Reset":
                paintModel.setShapes(new ArrayList<>());
                paintView.repaint();
        }
        paintView.setMode();
    }

}