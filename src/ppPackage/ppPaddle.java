package ppPackage;/*
    The code below contains lines from ECSE 202 assigment 1 handout by Professor Frank Ferrie.
 */

import acm.graphics.GPoint;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

import java.awt.*;

import static ppPackage.ppSimParams.*;
/**
 * Wrapper class for the paddle in game
 *
 * @author Martin Nguyen, Professor Frank Ferrie (Assignment 3 handout), Katrina Poulin's tutorial
 */
public class ppPaddle extends Thread {
    double X;
    double Y;
    double Vx;
    double Vy;
    double lastX;
    double lastY;
    GRect myPaddle;
    GraphicsProgram GProgram;
    ppTable myTable;

    public ppPaddle(double X, double Y, ppTable myTable, GraphicsProgram GProgram) {
        this.X = X; //center of paddle
        this.Y = Y;
        this.Vx = 0;
        this.Vy = 0;
        this.myTable = myTable;
        this.GProgram = GProgram;

        this.lastX = X;
        this.lastY = Y;

        double upperLeftX = X - ppPaddleW / 2;
        double upperLeftY = Y + ppPaddleH / 2;
        GPoint p = myTable.W2S(new GPoint(upperLeftX, upperLeftY));

        double ScrX = p.getX();
        double ScrY = p.getX();

        this.myPaddle = new GRect(ScrX, ScrY, ppPaddleW * Xs, ppPaddleH * Ys);

        myPaddle.setFilled(true);
        myPaddle.setColor(Color.BLACK);
        this.GProgram.add(this.myPaddle);

    }

    public void run() {
        while (true) {
            Vx = (X - lastX) / TICK;
            Vy = (Y - lastY) / TICK;
            lastX = X;
            lastY = Y;
            GProgram.pause(TICK*TSCALE);
        }
    }
    public GPoint getV(){
        return new GPoint(Vx,Vy);
    }
    public GPoint getP() {
        return new GPoint(this.X, this.Y);
    }

    public void setP(GPoint P) { //P in world, center
        this.X = P.getX();
        this.Y = P.getY();
        double upperLeftX = X - ppPaddleW / 2; //world, top left
        double upperLeftY = Y + ppPaddleH / 2;

        //Screen
        GPoint p = myTable.W2S(new GPoint(upperLeftX, upperLeftY));

        //Screen
        double ScrX = p.getX();
        double ScrY = p.getY();

        //move GRect instance
        this.myPaddle.setLocation(ScrX, ScrY);
    }
    /***
     * Get the sign of Vy of the current ppPaddle instance
     * @return 1 if moving up, -1 if moving down
     */
    public double getSgnVy(){
        if (Vy < 0) {
            return -1;
        } else return 1;
    }

    /***
     * check whether a point is in contact with the current ppPaddle instance
     * @param Sx x in world coordinates
     * @param Sy y in world coordinates
     * @return true if in contact, false otherwise
     */
    public boolean contact(double Sx, double Sy){         //true when X+Xo >= myPaddle.X -2*bSize or not
        boolean Xcontact =  (Sx >= this.getP().getX() - ppPaddleW / 2 - bSize);
        boolean Ycontact = (Sy <= Y + ppPaddleH / 2) && (Sy >= Y - ppPaddleH / 2);
        if ( Xcontact && Ycontact ) {
            return true; // X is center of paddle so offset half width + half ball
        }        else return false;
    }
}