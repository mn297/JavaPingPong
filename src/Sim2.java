/*
    The code below contains lines from ECSE 202 assigment 1 handout by Professor Frank Ferrie.
 */

import acm.graphics.*;
import acm.program.GraphicsProgram;

import java.awt.Color;


public class Sim2 extends GraphicsProgram {

    //screen
    public static final int HEIGHT = 400;
    public static final int WIDTH = 200;
    public static final int OFFSET = 200;

    //coordinates
    public static final double Xmin = 0;
    public static final double Xmax = 2.74;
    public static final double Ymin = 0.0;
    public static final double Ymax = 1.52;
    public static final int xmin = 0;
    public static final int xmax = WIDTH;
    public static final int ymin = 0;
    public static final int ymax = HEIGHT;
    public static final double Xs = (xmax - xmin) / (Xmax - Xmin);
    public static final double Ys = (ymax - ymin) / (Ymax - Ymin);
    public static final double PD = 1;

    //simulation parameters
    public static final double g = 9.8;
    public static final double k = 0.1316;
    public static final double Pi = 3.1416;
    public static final double bSize = 0.02; //0.02 default
    public static final double bMass = 0.0027;
    public static final double Xinit = 0.0;
    public static final double Yinit = Ymax / 2; // or HEIGHT/2

    public static final double Vdef = 3.0;
    public static final double Tdef = 30.0;

    public static final int SLEEP = 100;
    public static final double TICK = SLEEP / 1000.0;

    // entry point
    public void run() {
        //window size
//        this.resize(WIDTH, HEIGHT + OFFSET);

        //plane
        GRect gPlane = new GRect(0, HEIGHT, WIDTH + OFFSET, 3);
        gPlane.setColor(Color.BLUE);
        gPlane.setFilled(true);
        add(gPlane);

        //Ball
        GPoint p = W2S(new GPoint(Xinit, Yinit));
        double ScrX = p.getX();
        double ScrY = p.getY();
        GOval myBall = new GOval(ScrX, ScrY, 2 * bSize*Xs, 2 * bSize *Xs);
//        GOval myBall = new GOval(10,10);
        myBall.setColor(Color.RED);
        myBall.setFilled(true);
        add(myBall);
        pause(1000);




        //input parameters
        double Vo = Vdef;
        double theta = Tdef;

        double Xo = Xinit;
        double Yo = Yinit;
        double time = 0;

        double Vt = bMass * g / (4 * Pi * bSize * bSize * k);
        double Vox = Vo * Math.cos(theta * Pi / 180);
        double Voy = Vo * Math.sin(theta * Pi / 180);

        boolean falling = true;
        System.out.println("\t\t\t Ball Position and Velocity\n");


        while (falling) {
            double X = Vox * Vt / g * (1 - Math.exp(-g * time / Vt));
            double Y = Vt / g * (Voy + Vt) * (1 - Math.exp(-g * time / Vt)) - Vt * time;
            double Vx = Vox * Math.exp(-g * time / Vt);
            double Vy = (Voy + Vt) * Math.exp(-g * time / Vt) - Vt;


            System.out.printf("t: %.2f\t\t X: %.2f\t Y:%.2f\t Vx: %.2f\t Vy: %.2f\n",
                    time,
                    X + Xo,
                    Y + Yo,
                    Vx,
                    Vy);
            pause(SLEEP);

//            if (Y + Yo < bSize) falling = false;

            p = W2S(new GPoint(Xo + X - bSize, Yo + Y + bSize));
            ScrX = p.getX();
            ScrY = p.getY();
            myBall.setLocation(p);
            trace(ScrX, ScrY);


//            pause(100);
            time += TICK;


        }
    }

    GPoint W2S(GPoint P) {

        double X = P.getX();
        double Y = P.getY();

        double x = (X - Xmin) * Xs;
        double y = ymax - ((Y - Ymin) * Ys);

        return new GPoint(x, y);

    }

    public void trace(double ScrX, double ScrY) {
        GOval dot = new GOval(ScrX + bSize, ScrY + bSize, PD, PD);
        add(dot);
    }
}
//Credits to Professor Frank Ferrie, Assignment 1 handout