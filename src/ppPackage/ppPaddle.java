package ppPackage;/*
    The code below contains lines from ECSE 202 assigment 1 handout by Professor Frank Ferrie.
 */

import acm.graphics.GPoint;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

import java.awt.*;

import static ppPackage.ppSimParams.*;

public class ppPaddle extends Thread {
    double X;
    double Y;
    double Vx;
    double Vy;
    GRect myPaddle;
    GraphicsProgram GProgram;
    ppTable myTable;

    public ppPaddle(double X, double Y, ppTable myTable, GraphicsProgram GProgram) {
        this.X = X;
        this.Y = Y;
        this.myTable = myTable;
        this.GProgram = GProgram;

        double upperLeftX = X - ppPaddleW / 2;
        double upperLeftY = Y + ppPaddleH / 2;
        GPoint p = myTable.W2S(new GPoint(upperLeftX, upperLeftY));

        double ScrX = p.getX();
        double ScrY = p.getX();
        this.myPaddle = new GRect(ScrX, ScrY, ppPaddleW * Xs, ppPaddleH * Ys);
        myPaddle.setFilled(true);
        myPaddle.setColor(Color.BLACK);
        GProgram.add(myPaddle);


    }

    public void run() {
        double lastX = X;
        double lastY = Y;
        while (true) {
            Vx = (X - lastX) / TICK;
            Vy = (Y - lastX) / TICK;
            lastX = X;
            lastY = Y;
            GProgram.pause(TICK*TSCALE);
        }
    }

    public GPoint getP() {
        return new GPoint(this.X, this.Y);
    }

    public void setP(GPoint P) {
        this.X = P.getX();
        this.Y = P.getY();
        double upperLeftX = X - ppPaddleW / 2; //world coordinates
        double upperLeftY = Y + ppPaddleH / 2;

        //Screen
        GPoint p = myTable.W2S(new GPoint(upperLeftX, upperLeftY));

        //Screen
        double ScrX = p.getX();
        double ScrY = p.getY();

        //move GRect instance

        this.myPaddle.setLocation(ScrX, ScrY);
    }
    public double getSgnY(){
        if (Vy < 0) {
            return -1;
        } else return 1;
    }
    public boolean contact(double Sx, double Sy){
        //called when X+Xo >= myPaddle.X -2*bSize or not
    }
}