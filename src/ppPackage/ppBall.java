package ppPackage;

import acm.graphics.GOval;
import acm.graphics.GPoint;
import acm.program.GraphicsProgram;

import java.awt.*;

import static ppPackage.ppSimParams.*;

/**
 * Wrapper for a GOval instance and handles physics simulation
 *
 * @author Martin Nguyen, Professor Frank Ferrie (Assignment 1 handout, Assignment 2 handout)
 */
public class ppBall extends Thread {


    private double Xinit;// Initial position of ball -X
    private double Yinit;// Initial position of ball -Y
    private double Vo;// Initial velocity (Magnitude)
    private double theta;// Initial direction
    private double loss;// Energy loss on collision
    private Color color;// Color of ball
    private GraphicsProgram GProgram;//Instance of ppSim class (this)
    ppTable myTable;
    GOval myBall; // Graphics object representing ball
    ppPaddle RPaddle;

    /**
     * The constructor for the ppBall class copies parameters to instance variables, creates an
     * instance of a GOval to represent the ping-pong ball, and adds it to the display.
     *
     * @param Xinit    - starting position of the ball X (meters)
     * @param Yinit    - starting position of the ball Y (meters)
     * @param Vo       - initial velocity (meters/second)
     * @param theta    - initial angle to the horizontal (degrees)
     * @param loss     - loss on collision ([0,1])
     * @param color    - ball color (Color)
     * @param myTable  - ppTable instance from which to get W2S and S2W methods
     * @param GProgram - a reference to the ppSim class used to manage the display
     */
    public ppBall(double Xinit, double Yinit, double Vo, double theta, double loss, Color color, ppTable myTable, GraphicsProgram GProgram) {
        this.Xinit = Xinit;// Copy constructor parameters to instance variables
        this.Yinit = Yinit;
        this.Vo = Vo;
        this.theta = theta;
        this.loss = loss;
        this.color = color;
        this.myTable = myTable;
        this.GProgram = GProgram;

        GPoint p = W2S(new GPoint(Xinit - bSize, Yinit - bSize));
        double ScrX = p.getX();
        double ScrY = p.getY();
        myBall = new GOval(ScrX, ScrY, 2 * bSize * Xs, 2 * bSize * Ys);
        myBall.setColor(color);
        myBall.setFilled(true);
        GProgram.add(myBall);

    }

    /**
     * In a thread, the run method is NOT started automatically (like in Assignment 1).
     * Instead, a start message must be sent to each instance of the ppBall class, e.g.,
     * ppBall myBall = new ppBall (--parameters--);
     * myBall.start();
     * The body of the run method is essentially the simulator code you wrote for A1.
     */
    public void run() {
        GPoint p;
        double ScrX;
        double ScrY;

        double Xo = Xinit;
        double Yo = Yinit;
        double time = 0;

        double Vt = bMass * g / (4 * Pi * bSize * bSize * k);
        double Vox = Vo * Math.cos(theta * Pi / 180);
        double Voy = Vo * Math.sin(theta * Pi / 180);

        //console title
        System.out.println("\t\t\t Ball Position and Velocity\n");

        //initialize energy as class variables
        double PE = bMass * g * (Yo + (Vt / g * (Voy + Vt) * (1 - Math.exp(-g * time / Vt)) - Vt * time)); //bMass * g *(Y+Yo)
        double KEx = 0.5 * bMass * Math.pow(Vox * Math.exp(-g * time / Vt), 2);
        double KEy = 0.5 * bMass * Math.pow((Voy + Vt) * Math.exp(-g * time / Vt) - Vt, 2);

        boolean RUNNING = true;
        while (RUNNING) {
//
            //speed and displacement
            double X = Vox * Vt / g * (1 - Math.exp(-g * time / Vt));
            double Y = Vt / g * (Voy + Vt) * (1 - Math.exp(-g * time / Vt)) - Vt * time;
            double Vx = Vox * Math.exp(-g * time / Vt);
            double Vy = (Voy + Vt) * Math.exp(-g * time / Vt) - Vt;


            //ground collision handler
            if (Vy < 0 && Y + Yo <= bSize) {
                PE = 0; //no potential energy on ground default 0
                KEx = 0.5 * bMass * Vx * Vx * (1 - loss);
                KEy = 0.5 * bMass * Vy * Vy * (1 - loss);
                if (Vx < 0) {
                    Vox = (-1) * Math.sqrt(2 * KEx / bMass);
                } else {
                    Vox = Math.sqrt(2 * KEx / bMass);
                }


                Voy = Math.sqrt(2 * KEy / bMass);

                //reset state
                Xo += X;
                Yo = bSize; //??why not Yo+=Y, bSize because offset from ground to midball
                X = 0; //??why need to be 0? its recalculated everytime
                Y = 0;
                time = 0;
            }

            //paddle collision handler TODO
            if ((X + Xo) >= (RPaddle.getP().getX() - ppPaddleW / 2 - bSize) && Vx > 0) {
                if (RPaddle.contact(Xo + X, Yo + Y) && Vx > 0) { //default XwallR - bSize
                    PE = bMass * g * (Y + Yo);
                    KEx = 0.5 * bMass * Vx * Vx * (1 - loss);
                    KEy = 0.5 * bMass * Vy * Vy * (1 - loss);
                    Vox = (-1) * Math.sqrt(2 * KEx / bMass);
                    Voy = Math.sqrt(2 * KEy / bMass);

                    Vox = Vox * ppPaddleXgain;
                    Voy = Voy * ppPaddleYgain * RPaddle.getSgnVy(); //maintain Vy direction
                    //reset state
                    Xo = (RPaddle.getP().getX() - ppPaddleW / 2 - bSize); // default XwallR - bSize, same as if condition
                    Yo += Y;
                    X = 0;
                    Y = 0;
                    time = 0;
                } else { // make ball appear at center X of paddle when program stops
                    p = W2S(new GPoint(RPaddle.getP().getX() - bSize, Yo + Y + bSize)); //??offset; default:Xo + X - bSize, Yo + Y + bSize
                    ScrX = p.getX();
                    ScrY = p.getY();
                    myBall.setLocation(p);
                    trace(ScrX, ScrY);
                    break;

                };
            }
            //left wall collision handler
            if (Vx < 0 && (X + Xo) <= (XwallL + bSize)) { //default: XwallL+bSize
                System.out.println("COLLIDE!!!!");
                PE = bMass * g * (Y + Yo);
                KEx = 0.5 * bMass * Vx * Vx * (1 - loss);
                KEy = 0.5 * bMass * Vy * Vy * (1 - loss);
                Vox = Math.abs(Math.sqrt(2 * KEx / bMass));
                Voy = Math.sqrt(2 * KEy / bMass);
                if (Vy < 0) Voy = -Voy; //maintain Vy direction

                //reset state
                Xo = (XwallL + bSize); //default XwallL+bSize
                Yo += Y;
                X = 0;
                Y = 0;
                time = 0;
            }

            if (MESG)
                System.out.printf("t: %.2f\t\t X: %.2f\t Y:%.2f\t Vx: %.2f\t Vy: %.2f \n ",
                        time,
                        X + Xo,
                        Y + Yo,
                        Vx,
                        Vy
                );

            GProgram.pause(TICK * TSCALE);


            p = W2S(new GPoint(Xo + X - bSize, Yo + Y + bSize)); //??offset; default:Xo + X - bSize, Yo + Y + bSize
            ScrX = p.getX();
            ScrY = p.getY();
            myBall.setLocation(p);
            if (time > 0) trace(ScrX, ScrY); // fix first trace (optional)

            if ((KEx + KEy + PE) < ETHR) RUNNING = false;
            time += TICK;


        }


    }

    /***
     * Method to convert from world to screen coordinates.
     * @param P a point object in world coordinates
     * @return p the corresponding point object in screen coordinates
     */
    GPoint W2S(GPoint P) {
        return new GPoint((P.getX() - Xmin) * Xs, ymax - (P.getY() - Ymin) * Ys);
    }

    /***
     * A simple method to plot a dot at the current location in screen coordinates
     * @param ScrX horizontal screen coordinate
     * @param ScrY vertical screen coordinate
     */
    private void trace(double ScrX, double ScrY) {
        GOval pt = new GOval(ScrX + bSize * Xs, ScrY + bSize * Ys, PD, PD);
        pt.setColor(Color.BLACK);
        pt.setFilled(true);
        GProgram.add(pt);
    }

    public void setRightPaddle(ppPaddle myPaddle) {
        this.RPaddle = myPaddle;
    }

}
