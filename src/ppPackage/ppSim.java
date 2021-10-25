package ppPackage;

import acm.graphics.GOval;
import acm.graphics.GPoint;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

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
        addMouseListeners();

        myTable = new ppTable(this);
        RandomGenerator rgen = RandomGenerator.getInstance();
        rgen.setSeed(RSEED);

        // GENERATE PARAMETERS
        Color iColor = Color.RED;
        double iYinit = rgen.nextDouble(YinitMIN, YinitMAX);
        double iLoss = rgen.nextDouble(EMIN, EMAX);
        double iVel = rgen.nextDouble(VoMIN, VoMAX);
        double iTheta = rgen.nextDouble(ThetaMIN, ThetaMAX);


        myPaddle = new ppPaddle(ppPaddleXinit, ppPaddleYinit, myTable, this);
        myPaddle.setP(new GPoint(ppPaddleXinit, ppPaddleYinit));
        myBall = new ppBall(Xinit + bSize, iYinit, iVel, iTheta, iLoss, iColor, myTable, this);
        myBall.setRightPaddle(myPaddle);

        pause(STARTDELAY);
        myBall.start();
        myPaddle.start();


        //DEBUG
//        add(new GOval(100, 100, 1, 1)); //all screen coordinates
//        add(new GOval(200, 200, 1, 1));
//        add(new GOval(300, 300, 1, 1));
//        println(myTable.S2W(new GPoint(500, 500)).getX());
//        println(myTable.S2W(new GPoint(500, 500)).getY());
//        println(myTable.W2S(new GPoint(myTable.S2W(new GPoint(500, 500)).getX(), 0)).getX());
    }

    public void mouseMoved(MouseEvent e) {
        GPoint Pm = myTable.S2W(new GPoint(e.getX(), e.getY()));
        double PaddleX = myPaddle.getP().getX();
        double PaddleY = Pm.getY();
        myPaddle.setP(new GPoint(PaddleX, PaddleY));
    }
}
