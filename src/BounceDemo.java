//from github

import java.awt.Color;

import acm.program.*;
import acm.graphics.*;

public class BounceDemo extends GraphicsProgram {

    public void run() {
        double x = (getWidth() - BALL_SIZE) / 2;
        double y = (getHeight() - BALL_SIZE) /2;
        GOval ball = new GOval(x, y, BALL_SIZE, BALL_SIZE);
        ball.setColor(Color.RED);
        ball.setFilled(true);
        ball.setFillColor(Color.RED);
        add(ball);
        double dx = 1;
        double dy = 1;
        while(true) {
            ball.move(dx, dy);
            pause(PAUSE_TIME);

            double abscisa = ball.getX();
            double ordinate = ball.getY();
            if(abscisa >= getWidth() - BALL_SIZE){
                dx = -dx;
            }
            if(ordinate >= getHeight() - BALL_SIZE) {
                dy = -dy;
            }
            if(abscisa == 0){
                dx = -dx;
            }
            if(ordinate == 0){
                dy = -dy;
            }
        }
    }
    private static final int BALL_SIZE = 100;
    private static final int PAUSE_TIME = 100;
}