package ppPackage;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;
import static ppPackage.ppSimParams.*;
import java.awt.Color;



public class ppSim2 extends GraphicsProgram {
    boolean SINGLEBALLTEST = false;
    RandomGenerator rgen = new RandomGenerator();
    private static final int NUMBALLS = 100;
    private static final double MINSIZE = 1.0;
    private static final double MAXSIZE = 10.0;
    private static final double EMIN = 0.1;
    private static final double EMAX = 0.6;
    private static final double VoMIN = 40.0;
    private static final double VoMAX = 50.0;
    private static final double ThetaMIN = 80.0;
    private static final double ThetaMAX = 100.0;



    public static void main(String[] args) {
        new ppSim2().start(args);
    }

    public void run() {
        rgen.setSeed( (long) 0.12345 );
        this.resize(ppSimParams.WIDTH + OFFSET, ppSimParams.HEIGHT + OFFSET);
        ppTable myTable = new ppTable(this);

        // get data from user here
//        double Vo = readDouble("Enter initial velocity: "); // default Vdef
//        double theta = readDouble("Enter launch angle: "); //default Tdef
//        double loss = readDouble("Enter loss: ");
        double Vo = 5; // default Vdef
        double theta = 5; //default Tdef
        double loss = 0.21;
        if(SINGLEBALLTEST) {
            ppBall myBall = new ppBall(Xinit, Yinit, Vo, theta, loss, Color.RED, this);
            myBall.start();
        }
        else {
            for (int i=1;i<=10;i++) {
                ppBall ball = new ppBall(Xinit,Yinit,Vo,theta,loss,Color.RED,this);
                ball.start();
                pause(1000);
            }
        }
    }
}