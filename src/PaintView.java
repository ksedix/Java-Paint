import javax.swing.*;
import java.awt.*;

/**
 * Holds all graphical components of our app, such as buttons, labels and JPanels
 * Holds a reference to the PaintModel in order to draw/display it
 */
public class PaintView extends JPanel {


    private PaintModel paintModel;
    private JPanel topPanel;
    private JPanel paintPanel;

    /**
     * The slider controls the size of the dot shapes, but not the thickness of the pencil
     */
    private JSlider jSlider;

    //Buttons for selecting color
    JButton black;
    JButton red;
    JButton green;
    JButton blue;

    //Buttons for selecting shape
    JButton pencil;
    JButton dot;
    JButton oval;
    JButton rect;

    //Buttons for saving drawing to a file/loading drawing from a file and to undo
    JButton save;
    JButton undo;
    JButton load;
    JButton reset;

    //Adding the text that displays the current color and shape
    JLabel mode;

    //CONSTRUCTOR
    public PaintView(PaintModel paintModel){
        this.paintModel = paintModel;
        topPanel = new JPanel(new GridLayout(1,11));
        paintPanel = new JPanel(new BorderLayout());

        jSlider = new JSlider(SwingConstants.VERTICAL,5,50,10);
        jSlider.setMajorTickSpacing(5);
        jSlider.setMinorTickSpacing(1);
        jSlider.setPaintTicks(true);
        jSlider.setPaintLabels(true);
        jSlider.setFont(new Font("Default", Font.BOLD,16));
        jSlider.setBorder(BorderFactory.createEmptyBorder(0,0,0,10));

        //Initializing buttons
        black = new JButton(" ");
        red = new JButton(" ");
        green = new JButton(" ");
        blue = new JButton(" ");

        black.setBackground(Color.BLACK);
        red.setBackground(Color.RED);
        green.setBackground(Color.green);
        blue.setBackground(Color.blue);

        black.setActionCommand("Black");
        red.setActionCommand("Red");
        green.setActionCommand("Green");
        blue.setActionCommand("Blue");

        pencil = new JButton("Pencil",new ImageIcon(this.getClass().getResource("pencil.png")));
        dot = new JButton("Dot");
        oval = new JButton("Oval");
        rect = new JButton("Rectangle");

        save = new JButton("Save");
        undo = new JButton("Undo");
        load = new JButton("Load");
        reset = new JButton("Reset");

        mode = new JLabel("The current color is "+paintModel.getColor()+" "+
                "and the current mode is "+paintModel.getShape());
        mode.setFont(new Font("Default",Font.BOLD,16));


        //Adding the JButtons to the topPanel
        topPanel.add(black);
        topPanel.add(red);
        topPanel.add(green);
        topPanel.add(blue);

        topPanel.add(pencil);
        topPanel.add(dot);
        topPanel.add(oval);
        topPanel.add(rect);

        topPanel.add(save);
        topPanel.add(undo);
        topPanel.add(load);
        topPanel.add(reset);

        paintPanel.add(mode,BorderLayout.SOUTH);


        //Adding the topPanel and paintPanel to the main JPanel - the PaintView
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(1200,700));
        this.add(jSlider,BorderLayout.EAST);
        this.add(topPanel,BorderLayout.NORTH);
        this.add(paintPanel,BorderLayout.SOUTH);

    }

    //GETTER METHODS
    /**
     * Get the value from the JSlider. Used to set the size of the dot shapes
     * @return the value from the JSlider
     */
    public int getSliderValue(){
        return jSlider.getValue();
    }

    //SETTER METHODS
    public void setMode(){
        mode.setText("The current color is "+paintModel.getColor()+" "+
                "and the current mode is "+paintModel.getShape());
    }


    //DRAWING METHODS

    /**
     * Simple function for drawing free-form
     * @param g
     * @param shape
     */
    public void drawLine(Graphics g, Figure shape){
        g.drawLine(shape.getX(),shape.getY(),shape.getxMoved(),shape.getyMoved());
    }

    /**
     * Simple function for drawing a dot
     * @param g
     * @param shape
     */
    public void drawDot(Graphics g, Figure shape){
        g.fillOval(shape.getX()-shape.getSize()/2, shape.getY()-shape.getSize()/2, shape.getSize(), shape.getSize());
    }

    /**
     * Simple function for drawing an oval.
     * @param g
     * @param shape
     */
    public void drawOval(Graphics g, Figure shape){
        g.fillOval(shape.getOriginX(),shape.getOriginY(),shape.getWidth(),shape.getHeight());
    }

    /**
     * Simple function for drawing a rectangle.
     * @param g
     * @param shape
     */
    public void drawRect(Graphics g, Figure shape){
        g.fillRect(shape.getOriginX(),shape.getOriginY(),shape.getWidth(),shape.getHeight());
    }

    /**
     * Draws a Figure depending on the shape of the figure - oval,pencil,rectangle or dot
     * @param g
     * @param shape
     */
    public void drawShape(Graphics g, Figure shape){
        String color = shape.getColor();
        changeColor(g,color);
        String form = shape.getShape();
        if (form.equals("dot")) {
            drawDot(g,shape);
        } else if (form.equals("oval")){
            drawOval(g,shape);
        } else if (form.equals("rectangle")) {
            drawRect(g,shape);
        } else {
            drawLine(g,shape);
        }
    }

    public void changeColor(Graphics g, String color){
        switch (color){
            case "black":
                g.setColor(Color.black);
                break;
            case "red":
                g.setColor(Color.red);
                break;
            case "green":
                g.setColor(Color.green);
                break;
            case "blue":
                g.setColor(Color.blue);
                break;
        }
    }

    //The function needed to draw on the paintPanel
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Figure shape : paintModel.getShapes()){
            drawShape(g,shape);
        }

    }

}
