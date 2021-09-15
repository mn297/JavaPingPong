import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class HelloProgram extends GraphicsProgram {
    public void run() {
        GLabel greeting = new GLabel("hello, world", 100, 75);
        add(greeting);
        GPen myPen = new GPen(150,75);
        GLine myLine = new GLine(200, 100, 250, 150);
//        add(myLine);
        add(myPen);
        pause(500);
        myPen.drawLine(20,20);

        add(new GLabel(String.valueOf(myPen.isPenVisible()), 200, 75));
        GRect myRect = new GRect(100, 50, 125, 60);
        myRect.setFilled(true);
        myRect.setColor(Color.RED);
        add(myRect);
        pause(500);
        myRect.setLocation(0, 0);

    }
}