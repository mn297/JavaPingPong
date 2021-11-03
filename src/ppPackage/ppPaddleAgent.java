package ppPackage;

import acm.program.GraphicsProgram;

import java.awt.*;

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
    public void attachBall(ppBall myBall){
        this.myBall = myBall;
    };

}
