package ppPackage;

import acm.graphics.GPoint;
import acm.program.GraphicsProgram;

import java.awt.*;
import java.awt.event.MouseEvent;

import static ppPackage.ppSimParams.*;

/**
 * Entry point for ball simulation program
 *
 * @author Martin Nguyen, Professor Frank Ferrie (Assignment 1 handout, Assignment 2 handout)
 */
public class ppSim extends GraphicsProgram {
    ppTable myTable;
    ppPaddle myPaddle;

    public static void main(String[] args) {
        new ppSim().start(args);
    }

    /**
     * The run method for ppSim will create the walls using ppTable class and the balls by ppBall class.
     * It also reads user inputs for simulation parameters.
     */
    public void run() {
        this.resize(ppSimParams.WIDTH + OFFSET, ppSimParams.HEIGHT + OFFSET);
        myTable = new ppTable(this);
        myPaddle = new ppPaddle(ppPaddleXinit, ppPaddleYinit, myTable, this);
//        addMouseListeners();
        // get data from user here
//        double Vo = readDouble("Enter initial velocity: "); // default Vdef
//        double theta = readDouble("Enter launch angle: "); //default Tdef
//        double loss = readDouble("Enter loss: ");
        double Vo = 9; // default Vdef
        double theta = 15; //default Tdef
        double loss = 0.2;


//        ppBall myBall = new ppBall(XwallR-2*bSize, Yinit, Vo, theta, loss, Color.RED, this);
//        myBall.start();


//        ppBall myBall = new ppBall(Xinit, Yinit, Vo, theta, loss, Color.RED, this);
//        ppBall myBall2 = new ppBall(XwallR - 2 * bSize, Yinit, Vo, (180 - theta), loss, Color.BLUE, this);
//        pause(1500);
//        myBall.start();
//        myBall2.start();


    }

    public void mouseMoved(MouseEvent e) {
        GPoint Pm = myTable.S2W(new GPoint(e.getX(), e.getY()));
        double PaddleX = myPaddle.getP().getX();
        double PaddleY = Pm.getY();
        myPaddle.setP(new GPoint(PaddleX, PaddleY));
    }
}
