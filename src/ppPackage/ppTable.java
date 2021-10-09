package ppPackage;

import acm.graphics.GRect;
import acm.program.GraphicsProgram;

import java.awt.*;
import static ppPackage.ppSimParams.*;//import all instead of writing ppSImParams at every constant


public class ppTable {
    public ppTable(GraphicsProgram GProgram) {
        //plane
        GRect gPlane = new GRect(0, HEIGHT, WIDTH, wallThickness);
        gPlane.setColor(Color.BLACK);
        gPlane.setFilled(true);
        GProgram.add(gPlane);

        //left wall
        GRect leftWall = new GRect(XwallL * Xs, 0, 0, HEIGHT);//p1 = 0
        leftWall.setColor(Color.BLUE);
        leftWall.setFilled(true);
        GProgram.add(leftWall);

        //right wall
        GRect rightWall = new GRect(XwallR * Xs, 0, 0, HEIGHT);
        rightWall.setColor(Color.RED);
        rightWall.setFilled(true);
        GProgram.add(rightWall);
    }



}
