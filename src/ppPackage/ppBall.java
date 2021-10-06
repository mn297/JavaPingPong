package ppPackage;

import acm.graphics.GOval;
import acm.program.GraphicsProgram;

import java.awt.*;
import static ppPackage.ppSimParams.*;
public class ppBall extends Thread {


    private double Xinit;// Initial position of ball -X
    private double Yinit;// Initial position of ball -Y
    private double Vo;// Initial velocity (Magnitude)
    private double theta;// Initial direction
    private double loss;// Energy loss on collision
    private Color color;// Color of ball
    private GraphicsProgram GProgram;//Instance of ppSim class (this)
    GOval myBall; // Graphics object representing ball

    public ppBall(double Xinit, double Yinit, double Vo, double theta, double loss, Color color, GraphicsProgram GProgram) {
        this.Xinit=Xinit;// Copy constructor parameters to instance variables
        this.Yinit=Yinit;
        this.Vo=Vo;
        this.theta=theta;
        this.loss=loss;
        this.color=color;
        this.GProgram=GProgram;
        GOval myBall = new GOval(Xinit, Yinit, 2 * bSize * Xs, 2 * bSize * Ys);
        myBall.setColor(color);
        myBall.setFilled(true);
        GProgram.add(myBall);

    }
}
