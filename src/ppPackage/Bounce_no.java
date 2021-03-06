package ppPackage;/*
    The code below contains lines from ECSE 202 assigment 1 handout by Professor Frank Ferrie.
 */

import acm.graphics.GOval;
import acm.graphics.GPoint;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

import java.awt.*;

//Credits to Professor Frank Ferrie, Assignment 1 handout
public class Bounce_no extends GraphicsProgram {
    //DEBUG purposes
    private static final boolean DEBUG = false;
    private static final boolean TEST = true;

    //screen
    public static final int HEIGHT = 600; //600 default
    public static final int WIDTH = 1280;// 1280 default
    public static final int OFFSET = 200;

    //coordinates transformation

    public static final double Xmin = 0;
    public static final double Xmax = 2.74; //2.74 default
    public static final double Ymin = 0.0;
    public static final double Ymax = 1.52; //1.52 default
    public static final int xmin = 0;
    public static final int xmax = WIDTH;
    public static final int ymin = 0;
    public static final int ymax = HEIGHT;
    public static final double Xs = (xmax - xmin) / (Xmax - Xmin);
    public static final double Ys = (ymax - ymin) / (Ymax - Ymin);
    public static final double PD = 1;

    public static final double wallThickness = 3; //useless
    public static final double XwallL = 0.05;
    public static final double XwallR = 2.69;


    //simulation parameters
    public static final double g = 9.8;
    public static final double k = 0.1316;
    public static final double Pi = 3.1416;
    public static final double bSize = 0.02; //0.02 default
    public static final double bMass = 0.0027;
    public static final double Xinit = XwallL; //0 looks better than XwallL
    public static final double Yinit = Ymax / 2; // or HEIGHT/2

    //energy
    private static final double ETHR = 0.001;

    public static final double Vdef = 3.0;
    public static final double Tdef = 10.0;

    public static final double SLEEP = 10;
    public static final double TICK = SLEEP / 1000.0;

    //Credits to Professor Frank Ferrie, Assignment 1 handout
    // entry point
    public void run() {

        boolean RUNNING = true;

        //window size
        this.resize(WIDTH, HEIGHT + OFFSET);

        //plane
        GRect gPlane = new GRect(0, HEIGHT, WIDTH, wallThickness);
        gPlane.setColor(Color.BLACK);
        gPlane.setFilled(true);
        add(gPlane);

        //left wall
        GRect leftWall = new GRect(XwallL * Xs, 0, 0, HEIGHT);//p1 = 0
        leftWall.setColor(Color.BLUE);
        leftWall.setFilled(true);
        add(leftWall);

        //right wall
        GRect rightWall = new GRect(XwallR * Xs, 0, 0, HEIGHT);
        rightWall.setColor(Color.RED);
        rightWall.setFilled(true);
        add(rightWall);


        //Ball
        GPoint p = W2S(new GPoint(Xinit, Yinit));
        double ScrX = p.getX();
        double ScrY = p.getY();
        GOval myBall = new GOval(ScrX, ScrY, 2 * bSize * Xs, 2 * bSize * Ys);
        myBall.setColor(Color.RED);
        myBall.setFilled(true);
        add(myBall);
        pause(1000);


        //input parameters, ask user
        double Vo = readDouble("Enter initial velocity: "); // default Vdef
        double theta = readDouble("Enter launch angle: "); //default Tdef
        double loss = readDouble("Enter loss: ");


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
//Credits to Professor Frank Ferrie, Assignment 1 handout
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

            //right wall collision handler
            if ((X + Xo) >= (XwallR - bSize) && Vx > 0) { //default XwallR - bSize
                PE = bMass * g * (Y + Yo);
                KEx = 0.5 * bMass * Vx * Vx * (1 - loss);
                KEy = 0.5 * bMass * Vy * Vy * (1 - loss);
                Vox = (-1) * Math.sqrt(2 * KEx / bMass);
                Voy = Math.sqrt(2 * KEy / bMass);
                if (Vy < 0) Voy = -Voy; //maintain Vy direction

                //reset state
                Xo = (XwallR - bSize); // default XwallR - bSize, same as if condition
                Yo += Y;
                X = 0;
                Y = 0;
                time = 0;
            }

            //left wall collision handler
            if ((X + Xo) <= (XwallL + bSize) && Vx < 0) { //default: XwallL+bSize
                PE = bMass * g * (Y + Yo);
                KEx = 0.5 * bMass * Vx * Vx * (1 - loss);
                KEy = 0.5 * bMass * Vx * Vx * (1 - loss);
                Vox = Math.abs(Math.sqrt(2 * KEx / bMass));
                Voy = Math.sqrt(2 * KEy / bMass);
                if (Vy < 0) Voy = -Voy; //maintain Vy direction

                //reset state
                Xo = XwallL + bSize; //default XwallL+bSize
                Yo += Y;
                X = 0;
                Y = 0;
                time = 0;
            }

            if (TEST)
                System.out.printf("t: %.2f\t\t X: %.2f\t Y:%.2f\t Vx: %.2f\t Vy: %.2f \n ",
                        time,
                        X + Xo,
                        Y + Yo,
                        Vx,
                        Vy
                );
//            pause(SLEEP);



            p = W2S(new GPoint(Xo + X - bSize, Yo + Y + bSize)); //??offset; default:Xo + X - bSize, Yo + Y + bSize
            ScrX = p.getX();
            ScrY = p.getY();
            myBall.setLocation(p);
            if (time > 0) trace(ScrX, ScrY); // fix first trace (optional)


            if (Vy < 0 && Y + Yo <= bSize) RUNNING = false;
            time += TICK;


        }
    }
    //Credits to Professor Frank Ferrie, Assignment 1 handout
    GPoint W2S(GPoint P) {

        double X = P.getX();
        double Y = P.getY();

        double x = (X - Xmin) * Xs;
        double y = (double) ymax - ((Y - Ymin) * Ys);

        return new GPoint(x, y);

    }

    public void trace(double ScrX, double ScrY) {
        GOval dot = new GOval(ScrX + bSize * Xs, ScrY + bSize * Ys, PD, PD); //offset in ScrX to be in middle of ball
        add(dot);
    }
}
//Credits to Professor Frank Ferrie, Assignment 1 handout