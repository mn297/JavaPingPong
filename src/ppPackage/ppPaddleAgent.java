package ppPackage;

import acm.graphics.GPoint;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

import java.awt.*;

import static ppPackage.ppSimParams.*;
import static ppPackage.ppSimParams.EMAX;

/**
 * Bot opponent for game
 */
public class ppPaddleAgent extends ppPaddle {
    ppBall myBall;

    /**
     * @param X        X coordinate
     * @param Y        Y coordinate
     * @param myColor   Paddle color
     * @param myTable  ppTable instance from which to import W2S and S2W
     * @param GProgram the GraphicsProgram instance to add the ppPaddle to
     */
    public ppPaddleAgent(double X, double Y, Color myColor, ppTable myTable, GraphicsProgram GProgram) {
        super(X, Y, myColor, myTable, GProgram);
    }

    /**
     * The LPaddle will follow the ball Y coordinates with a lag
     */
    public void run() {
        int ballSkip = 0;
        while (true) {
            GProgram.pause(TICK * 1000 / speedFactor);
            if (ballSkip++ >= agentLag) {
                double PaddleAgentX = this.getP().getX();
                double PaddleAgentY = myBall.getP().getY();
                this.setP(new GPoint(PaddleAgentX, PaddleAgentY));
                ballSkip = 0;

            }

        }
    }

    /**
     * Setter for a ppBall instance variable
     * @param myBall ppBall instance to set to
     */
    public void attachBall(ppBall myBall) {
        this.myBall = myBall;
    }

}
