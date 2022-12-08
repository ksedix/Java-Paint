import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Stores all the data of our application, such as the current color and shape
 * Stores an ArrayList of all the Shapes that have been drawn
 * Contains methods for saving to a file/loading from a file and undoing
 * Independent of View and Controller.
 */
public class PaintModel {

    private String color;
    private String shape;
    private ArrayList<Figure> shapes;

    public PaintModel(){
        //Initialize to default values
        this.color = "black";
        this.shape = "Pencil";
        shapes = new ArrayList<>();
    }

    //GETTER METHODS

    public String getColor() {
        return color;
    }

    public String getShape() {
        return shape;
    }

    public ArrayList<Figure> getShapes() {
        return shapes;
    }

    //SETTER METHODS

    public void setColor(String color) {
        this.color = color;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public void setShapes(ArrayList<Figure> shapes) {
        this.shapes = shapes;
    }

    //LOGIC METHODS - undo/save/load
    public void undo(){
        if (!shapes.isEmpty()){
            int group = shapes.get(shapes.size()-1).getGroup();
            shapes.removeIf(shape -> shape.getGroup() == group);
        }
    }

    public void save() throws IOException {
        JFileChooser jFileChooser = new JFileChooser();

        if (jFileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
            File file = jFileChooser.getSelectedFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(shapes);
            objectOutputStream.flush();
            objectOutputStream.close();
        }
    }

    public void load() throws IOException, ClassNotFoundException {
        JFileChooser jFileChooser = new JFileChooser();

        if (jFileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
            File file = jFileChooser.getSelectedFile();
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            shapes = (ArrayList<Figure>) objectInputStream.readObject();
            objectInputStream.close();
        }
    }

    public void addShape(Figure shape) {
        shapes.add(shape);
    }

    public void mouseMoved(int x, int y) {
        if (!shapes.isEmpty()){
            Figure shape = shapes.get(shapes.size()-1);
            shape.mouseMoved(x,y);
        }
    }
}
