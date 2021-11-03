package ppPackage;

import acm.graphics.GPoint;
import acm.program.GraphicsProgram;

import java.awt.*;

import static ppPackage.ppSimParams.TICK;
import static ppPackage.ppSimParams.TSCALE;

public class ppPaddleAgent extends ppPaddle{
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
            int AgentLag = 5;
        while(true){
            if (ballSkip++ >= AgentLag){
                ballSkip=0;
            }
            double PaddleAgentX = this.getP().getX();
            double PaddleAgentY = myBall.getP().getY();
            this.setP(new GPoint(PaddleAgentX, PaddleAgentY));

        }
    }

    public void attachBall(ppBall myBall){
            this.myBall = myBall;
    };

}
