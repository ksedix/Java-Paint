import java.io.Serializable;

/**
 * A class that represents a single drawing
 * A Figure is a drawable object with a color, ex:"green", and a shape, ex: "oval"
 * A Figure always has an x and y coordinate
 */
public class Figure implements Serializable {

    /**
     * The group of a figure is necessary when we want to undo a free-hand drawing that is made up of multiple Line figures.
     * The Line figures with the same group will be removed simultaneously
     */
    private int group;
    //the size of a dot - only needed for DOT shape
    private int size;

    private String color;
    private String shape;

    //The x coordinate of the Shape - where the shape starts in the x direction
    private int x;
    //The y coordinate of the Shape - where the shape starts in the y direction
    private int y;

    //xMoved and yMoved are not needed for DOT
    //When we are drawing ovals,rectangles and lines, we need to keep track of where the
    //mouse cursor is moving
    private int xMoved;
    private int yMoved;

    //CONSTRUCTOR
    public Figure(String color, String shape, int x, int y, int size, int group){
        this.color = color;
        this.shape = shape;
        this.x = x;
        this.y = y;
        this.size = size;
        this.xMoved = x;
        this.yMoved = y;
        this.group = group;
    }


    //GETTER methods
    public String getColor() {
        return color;
    }

    public String getShape() {
        return shape;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    //THE 4 GETTER METHODS BELOWS ARE ONLY RELEVANT FOR OVALS/RECTANGLES
    /**
     * This method returns the X that should be used as origin for the rectangle or oval shape
     * @return
     */
    public int getOriginX(){
        return Math.min(x,xMoved);
    }

    /**
     * This method returns the Y that should be used as origin for the rectangle or oval shape
     * @return
     */
    public int getOriginY(){
        return Math.min(y,yMoved);
    }

    /**
     * This method finds the width of the oval/rectangle by using the absolute difference between x and xMoved
     * @return The width of the oval/rectangle
     */
    public int getWidth(){
        return Math.abs(x-xMoved);
    }

    /**
     * This methods finds the height of the oval/rectangle by using the absolute difference between y and yMoved
     * @return The height of the oval/rectangle
     */
    public int getHeight(){
        return Math.abs(y-yMoved);
    }


    /**
     * This method is used to return the size of a dot that should be drawn
     * @return The size of the dot(diameter)
     */
    public int getSize(){
        return size;
    }

    /**
     * Get the x coordinate of where the mouse has moved to
     * @return
     */
    public int getxMoved(){
        return xMoved;
    }

    /**
     * Get the y coordinate of where the mouse has moved to
     * @return
     */
    public int getyMoved(){
        return yMoved;
    }

    /**
     * Get the group of the figure. Lines in the same group will be removed simultaneously when undoing.
     * @return
     */
    public int getGroup() {
        return group;
    }

    /**
     * update xMoved and yMoved to where the mouse cursor has moved
     * @param x
     * @param y
     */
    public void mouseMoved(int x, int y) {
        xMoved = x;
        yMoved = y;
    }

}
