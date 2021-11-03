package ppPackage;

import acm.graphics.GPoint;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

import static ppPackage.ppSimParams.*;

/**
 * Entry point for ball simulation program
 *
 * @author Martin Nguyen, Professor Frank Ferrie (Assignment 3 handout), Katrina Poulin's tutorial
 */
public class ppSimPaddleAgent extends GraphicsProgram {
    ppTable myTable;
    ppPaddle rPaddle;
    ppBall myBall;

    public static void main(String[] args) {
        new ppSim().start(args);
    }

    /**
     * The run method for ppSim will create the walls using ppTable class and the balls by ppBall class.
     * It also reads user inputs for simulation parameters.
     */
    public void run() {
        this.resize(ppSimParams.WIDTH + OFFSET, ppSimParams.HEIGHT + OFFSET);

        //BUTTON
        JToggleButton toggleButton = new JToggleButton("Toggle Button");

        myTable = new ppTable(this);
        RandomGenerator rgen = RandomGenerator.getInstance();
        rgen.setSeed(RSEED);

        // GENERATE PARAMETERS
        Color iColor = Color.RED;
        double iYinit = rgen.nextDouble(YinitMIN, YinitMAX);
        double iLoss = rgen.nextDouble(EMIN, EMAX);
        double iVel = rgen.nextDouble(VoMIN, VoMAX);
        double iTheta = rgen.nextDouble(ThetaMIN, ThetaMAX);


        rPaddle = new ppPaddle(ppPaddleXinit, ppPaddleYinit, Color.RED, myTable, this);
//        myPaddle.setP(new GPoint(ppPaddleXinit, ppPaddleYinit)); // fix paddle not appearing at start
        myBall = new ppBall(Xinit + bSize, iYinit, iVel, iTheta, iLoss, iColor, myTable, this);
        myBall.setRightPaddle(rPaddle);
        addMouseListeners();

        pause(STARTDELAY);
        myBall.start();
        rPaddle.start();


    }

    public void mouseMoved(MouseEvent e) {
        GPoint Pm = myTable.S2W(new GPoint(e.getX(), e.getY()));
        double PaddleX = rPaddle.getP().getX();
        double PaddleY = Pm.getY();
        rPaddle.setP(new GPoint(PaddleX, PaddleY));
    }
}
