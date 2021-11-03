package ppPackage;

import acm.graphics.GOval;
import acm.graphics.GPoint;
import acm.gui.TableLayout;
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

        //BUTTON
        JToggleButton toggleButton = new JToggleButton("Toggle Button");
        setLayout(new TableLayout(2, 3));
        add(toggleButton, SOUTH);


        myTable = new ppTable(this);
        RandomGenerator rgen = RandomGenerator.getInstance();
        rgen.setSeed(RSEED);

        // GENERATE PARAMETERS
        Color iColor = Color.RED;
        double iYinit = rgen.nextDouble(YinitMIN, YinitMAX);
        double iLoss = rgen.nextDouble(EMIN, EMAX);
        double iVel = rgen.nextDouble(VoMIN, VoMAX);
        double iTheta = rgen.nextDouble(ThetaMIN, ThetaMAX);


        myPaddle = new ppPaddle(ppPaddleXinit, ppPaddleYinit, Color.GREEN, myTable, this);
//        myPaddle.setP(new GPoint(ppPaddleXinit, ppPaddleYinit)); // fix paddle not appearing at start
        myBall = new ppBall(Xinit + bSize, iYinit, iVel, iTheta, iLoss, iColor, myTable, this);
        myBall.setRightPaddle(myPaddle);
        addMouseListeners();

        pause(STARTDELAY);
        myBall.start();
        myPaddle.start();


    }

    public void mouseMoved(MouseEvent e) {
        GPoint Pm = myTable.S2W(new GPoint(e.getX(), e.getY()));
        double PaddleX = myPaddle.getP().getX();
        double PaddleY = Pm.getY();
        myPaddle.setP(new GPoint(PaddleX, PaddleY));
    }
    private void addButton(String name,
                           GridBagLayout gridbag,
                           GridBagConstraints c) {
        Button button = new Button(name);
        gridbag.setConstraints(button, c);
        add(button);
    }
}
