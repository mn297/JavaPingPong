package ppPackage;
import acm.program.GraphicsProgram;

import static ppPackage.ppSimParams.*;

public class ppSim extends GraphicsProgram {
    public static void main(String[] args) {
        new ppSim().start(args);
    }

    public void run() {
        this.resize(WIDTH + OFFSET, HEIGHT + OFFSET);
        ppTable myTable = new ppTable(this);// get data from user here
        ppBall myBall = new ppBall(Xinit, Yinit, Vo, theta, loss, Color.RED, this);
        myBall.start();
    }
}