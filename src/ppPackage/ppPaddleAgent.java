package ppPackage;

import acm.graphics.GPoint;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

import java.awt.*;

import static ppPackage.ppSimParams.*;
import static ppPackage.ppSimParams.EMAX;

public class ppPaddleAgent extends ppPaddle {
    ppBall myBall;

    /**
     * @param X        X coordinate
     * @param Y        Y coordinate
     * @param myColor
     * @param myTable  ppTable instance from which to import W2S and S2W
     * @param GProgram the GraphicsProgram instance to add the ppPaddle to
     */
    public ppPaddleAgent(double X, double Y, Color myColor, ppTable myTable, GraphicsProgram GProgram) {
        super(X, Y, myColor, myTable, GProgram);
    }

    public void run() {

        int ballSkip = 0;
        while (true) {
            GProgram.pause(TICK * 10000 / speedFactor);
            if (ballSkip++ >= agentLag) {
                double PaddleAgentX = this.getP().getX();
                double PaddleAgentY = myBall.getP().getY();
                this.setP(new GPoint(PaddleAgentX, PaddleAgentY));
                ballSkip = 0;

            }

        }
    }

    public void attachBall(ppBall myBall) {
        this.myBall = myBall;
    }

    ;

}
