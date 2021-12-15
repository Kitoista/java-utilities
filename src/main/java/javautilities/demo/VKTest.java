package javautilities.demo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.*;

public class VKTest extends JFrame {

    private JPanel panel;
    private JTextField textField;
    private Robot robot;
    private int value = -1;
    private Class theClass;

    public static void main(String[] args) {
        VKTest r = new VKTest();
        r.runTest();
    }

    public VKTest() {
        try {
            robot = new Robot();
        } catch(Exception e) {
            e.printStackTrace();
        }

        textField = new JTextField(10);
        textField.addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {
                value = e.getKeyCode();
            }

            public void keyReleased(KeyEvent e) {}
            public void keyTyped(KeyEvent e) {}
        });

        panel = new JPanel();
        panel.add(textField);

        getContentPane().add(panel);

        pack();
        setVisible(true);
    }

    public void runTest() {
        try {
            Thread.sleep(2000);
        } catch(Exception e) {
            e.printStackTrace();
        }

        textField.requestFocus();

        try {
            theClass = Class.forName("java.awt.event.KeyEvent");
        } catch(Exception e) {
            e.printStackTrace();
        }

        Field[] fields = theClass.getFields();
        int integer = 0;
        
        for(int i = 0; i < fields.length; i++) {
            String name = fields[i].getName();

            if(name.indexOf("VK") != -1) {
                try {
                    integer = fields[i].getInt(fields[i]);
                } catch(Exception e) {
                    e.printStackTrace();
                }


                try {
                    robot.keyPress(integer);
                    robot.keyRelease(integer);
                } catch(Exception e) {
                    value = -2;
                }

                try {
                    Thread.sleep(500);
                } catch(Exception e) {
                    e.printStackTrace();
                }

                if(value != integer) {
                    System.out.println(name + " - " + "Actual: " + integer + " Fired: " + value);
                }

                value = -1;
            }
        }
    }
}