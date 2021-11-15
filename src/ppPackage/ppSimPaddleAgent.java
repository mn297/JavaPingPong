package ppPackage;

import acm.graphics.GPoint;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import static ppPackage.ppSimParams.*;

/**
 * Entry point for ball simulation program
 *
 * @author Martin Nguyen, Professor Frank Ferrie (Assignment 3 handout), Katrina Poulin's tutorial
 */
public class ppSimPaddleAgent extends GraphicsProgram {
    ppTable myTable;
    ppPaddle RPaddle;
    ppPaddleAgent LPaddle;

    ppBall myBall;
    RandomGenerator rgen;



    public static void main(String[] args) {
        new ppSimPaddleAgent().start(args);
    }

    /**
     * The run method for ppSim will create the walls using ppTable class and the balls by ppBall class.
     * It also reads user inputs for simulation parameters.
     */
    public void run() {
        this.resize(ppSimParams.WIDTH + OFFSET, ppSimParams.HEIGHT + OFFSET);
        //BUTTON
        traceButton = new JToggleButton("Trace");
        JButton newServeButton = new JButton("New Serve");
        JButton quitButton = new JButton("Quit");

        //SCOREBOARD
        scoreBoard = new JLabel("player: " + playerScore + " | agent: "+ agentScore);


        add(newServeButton, SOUTH);
        add(traceButton, SOUTH);
        add(quitButton, SOUTH);
        add(scoreBoard, NORTH);
        addMouseListeners();
        addActionListeners();

        rgen = RandomGenerator.getInstance();
        rgen.setSeed(RSEED);

        myTable = new ppTable(this);
        newGame();
    }


    public void newGame() {
        if (myBall != null) myBall.kill();// stop current game in play
        myTable.newScreen();
        myBall = newBall();

        RPaddle = new ppPaddle(ppPaddleXinit, ppPaddleYinit, Color.GREEN, myTable, this);
        LPaddle = new ppPaddleAgent(ppPaddleAgentXinit, ppPaddleAgentYinit, Color.RED, myTable, this);
        LPaddle.attachBall(myBall); // to track ball position

        myBall.setRightPaddle(RPaddle);
        myBall.setLeftPaddle(LPaddle);
        pause(STARTDELAY);

        myBall.start();
        LPaddle.start();
        RPaddle.start();
    }

    public ppBall newBall() {
        // GENERATE PARAMETERS
        Color iColor = Color.RED;
        double iYinit = rgen.nextDouble(YinitMIN, YinitMAX);
        double iLoss = rgen.nextDouble(EMIN, EMAX);
        double iVel = rgen.nextDouble(VoMIN, VoMAX);
        double iTheta = rgen.nextDouble(ThetaMIN, ThetaMAX);
        ppBall myBall = new ppBall(Xinit + bSize, iYinit, iVel, iTheta, iLoss, iColor, myTable, this);
        myBall.setRightPaddle(RPaddle);
        myBall.setLeftPaddle(LPaddle);
        return myBall;
    }

    public void mouseMoved(MouseEvent e) {
        if(myTable == null || RPaddle == null) return;
        GPoint Pm = myTable.S2W(new GPoint(e.getX(), e.getY()));
        double PaddleX = RPaddle.getP().getX();
        double PaddleY = Pm.getY();
        RPaddle.setP(new GPoint(PaddleX, PaddleY));
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("New Serve")) {
            newGame();
        } else if (command.equals("Quit")) {
            System.exit(0);
        }
    }
    public static void updateText(JLabel scoreboard){
        scoreboard.setText("player: " + playerScore + " | agent: "+ agentScore);;
    }
}
