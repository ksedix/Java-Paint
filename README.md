# Java-Paint
A paint application created in Java using Swing and AWT libraries. The project follows the Model-View-Controller design pattern.

## Preview

![paint_preview](https://user-images.githubusercontent.com/76788207/206437570-76096dd0-3b53-4867-a89b-e016d523af2e.png)

## User Manual

The paint application features **4 colors** and **4 drawing modes**.    
The 4 colors are: black, red, green and blue.  
The 4 drawing modes are: pencil, dot, oval and rectangle. 

**Pencil**: Used to draw a free-hand figure.  
**Undo**: Undo the latest drawn figure.   
**Reset**: Clear all drawings from the canvas.  
**Save**: Save the state of the drawing to a file. (**Note**: Saving a drawing does not save it as a png. It simply saves the state of the drawing so that you can resume it at a later stage.)   
**Load**: Load the drawing from a saved file.  


**JSlider**: The JSlider is used to set the size of the dots. It does not work to set the line width.

## How To Run

There are 2 ways to run the program:
- Download **Paint.jar** in the project folder and run it on your computer
- Clone the project and run **Paint.java**
