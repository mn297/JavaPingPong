package ecse202;

import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GPoint;
import acm.program.GraphicsProgram;
import ppPackage.ppSim;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

public class drag_demo extends GraphicsProgram {
    public static void main(String[] args) {
        new drag_demo().start(args);
    }

    public void init() {
        GOval redBall = new GOval(100, 100, 50, 50);
        GOval redBall2 = new GOval(100, 100, 1, 1);
        redBall2.setColor(Color.RED);

        this.add(redBall2);
        this.add(redBall);

        redButton = new JButton("Red");
        add(new JLabel("red"), SOUTH);
        add(redButton, SOUTH);
        redButton.addActionListener(this); // unnecessary?

        greenButton = new JButton("Green");
        add(new JLabel("green"), SOUTH);
        add(greenButton, SOUTH);

        nameField = new JTextField(10);
        add(new JLabel("name"), SOUTH);
        add(nameField, SOUTH);
        nameField.addActionListener(this);

        addActionListeners();
        addMouseListeners();
    }
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.greenButton) {
            println("green pressed");
        } else if (e.getSource() == this.redButton) {
            println("red pressed");
        }else if (e.getSource() == this.nameField) {
            println("name is "+nameField.getText());
        }


    }
    public void mousePressed(MouseEvent e) {
        last = new GPoint(e.getX(), e.getY());
        gobj = getElementAt(last);
    }



    GObject gobj;
    GPoint last;
    JButton redButton;
    JButton greenButton;
    JTextField nameField;
}
