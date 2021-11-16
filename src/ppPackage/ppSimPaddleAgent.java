package ppPackage;

import acm.graphics.GPoint;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import static ppPackage.ppSimParams.*;

/**
 * Entry point for ball simulation program
 *
 * @author Martin Nguyen, Professor Frank Ferrie (Assignment 4 handout), Katrina Poulin's tutorial
 */
public class ppSimPaddleAgent extends GraphicsProgram {
    ppTable myTable;
    ppPaddle RPaddle;
    ppPaddleAgent LPaddle;

    ppBall myBall;
    RandomGenerator rgen;

    /**
     * Entry point to fix 64-bit compatibility issue
     */
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
        JButton clearButton = new JButton("Clear");

        //SCOREBOARD
        scoreBoard = new JLabel("PLAYER: " + playerScore + " | AGENT: " + agentScore);

        //slider
        JSlider lagSlider = new JSlider(JSlider.HORIZONTAL,
                0, 70, agentLag);
        JSlider timeSlider = new JSlider(JSlider.HORIZONTAL,
                1, 70, speedFactor*10);


        add(newServeButton, SOUTH);
        add(traceButton, SOUTH);
        add(quitButton, SOUTH);
        add(clearButton, SOUTH);

        add(new JLabel("\t-t"), SOUTH);
        add(timeSlider, SOUTH);
        add(new JLabel("+t\t"), SOUTH);

        add(new JLabel("\t-lag"), SOUTH);
        add(lagSlider, SOUTH);
        add(new JLabel("+lag\t"), SOUTH);


        add(scoreBoard, NORTH);

        addMouseListeners();
        addActionListeners();

        lagSlider.addChangeListener(new lagSliderListener());
        timeSlider.addChangeListener(new timeSliderListener());

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

    /**
     *
     * @return a new ppBall instance with random values
     */
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

    /**
     * Moves the RPaddle with accordingly with mouse movement
     * @param e Mouse event
     */
    public void mouseMoved(MouseEvent e) {
        if (myTable == null || RPaddle == null) return;
        GPoint Pm = myTable.S2W(new GPoint(e.getX(), e.getY()));
        double PaddleX = RPaddle.getP().getX();
        double PaddleY = Pm.getY();
        RPaddle.setP(new GPoint(PaddleX, PaddleY));
    }

    /**
     * Action listeners for buttons
     * @param e Action event
     */
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("New Serve")) {
            newGame();
        } else if (command.equals("Quit")) {
            System.exit(0);
        }else if (command.equals("Clear")) {
            playerScore = 0;
            agentScore = 0;
            updateText(scoreBoard);
        }
    }

    /**
     * Update JLabel scoreBoard with new score values
     * @param scoreboard JLabel instance that stores the score
     */
    public static void updateText(JLabel scoreboard) {
        scoreboard.setText("PLAYER: " + playerScore + " | AGENT: " + agentScore);
    }

    /**
     * Listener for lag slider
     */
    class lagSliderListener implements ChangeListener {
        public void stateChanged(ChangeEvent e) {
            JSlider source = (JSlider) e.getSource();
            if (!source.getValueIsAdjusting()) {
                int newAgentLag = source.getValue();
                println("agentLag changed to " + newAgentLag);
                agentLag = newAgentLag;
            }
        }
    }

    /**
     * Listener for time slider
     */
    class timeSliderListener implements ChangeListener {
        public void stateChanged(ChangeEvent e) {
            JSlider source = (JSlider) e.getSource();
            if (!source.getValueIsAdjusting()) {
                int newFactor = source.getValue() / 10;
                println("speedFactor changed to " + newFactor);
                speedFactor = newFactor;
            }
        }
    }
}
