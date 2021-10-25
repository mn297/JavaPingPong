package ppPackage;

import acm.graphics.GPoint;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

import java.awt.*;

import static ppPackage.ppSimParams.*;

/**
 * Walls for ball physics simulation
 *
 * @author Martin Nguyen, Professor Frank Ferrie (Assignment 1 handout, Assignment 2 handout)
 */
public class ppTable {
    /**
     * Construct the walls for ball physics simulation
     *
     * @param GProgram the GraphicsProgram in which to draw the walls
     */
    public ppTable(GraphicsProgram GProgram) {
        //plane
        GRect gPlane = new GRect(0, HEIGHT, WIDTH, 0);
        gPlane.setColor(Color.BLACK);
        gPlane.setFilled(true);
        GProgram.add(gPlane);

        //left wall
        GRect leftWall = new GRect(XwallL * Xs, 0, 0, HEIGHT);//p1 = 0
        leftWall.setColor(Color.BLUE);
        leftWall.setFilled(true);
        GProgram.add(leftWall);

        //right wall
//        GRect rightWall = new GRect(XwallR * Xs, 0, 0, HEIGHT);
//        rightWall.setColor(Color.RED);
//        rightWall.setFilled(true);
//        GProgram.add(rightWall);
    }

    public GPoint W2S(GPoint P) {

        return new GPoint((P.getX() - Xmin) * Xs, ymax - (P.getY() - Ymin) * Ys);
    }

    public GPoint S2W(GPoint P) {
        double x_s = P.getX();
        double y_s = P.getY();
        double X_w = (x_s/Xs) + Xmin;
        double Y_w = ((ymax-y_s)/Ys) + Ymin;

        return new GPoint(X_w, Y_w);

    }
}
