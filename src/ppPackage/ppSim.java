package ppPackage;

import acm.program.GraphicsProgram;

import java.awt.*;

import static ppPackage.ppSimParams.*;


public class ppSim extends GraphicsProgram {
    public static void main(String[] args) {
        new ppSim().start(args);
    }

    public void run() {
        this.resize(ppSimParams.WIDTH + OFFSET, ppSimParams.HEIGHT + OFFSET);
        ppTable myTable = new ppTable(this);

        // get data from user here
//        double Vo = readDouble("Enter initial velocity: "); // default Vdef
//        double theta = readDouble("Enter launch angle: "); //default Tdef
//        double loss = readDouble("Enter loss: ");
        double Vo = 5; // default Vdef
        double theta = 5; //default Tdef
        double loss = 0.21;

        ppBall myBall = new ppBall(Xinit, Yinit, Vo, theta, loss, Color.RED, this);
        myBall.start();


    }
}