package com.vladproduction.gridlayout;

import javax.swing.*;

import java.awt.*;

/**
 * Created by vladproduction on 24-Apr-24
 */

public class GridLayout_Main {
    public static void main(String[] args) {
        // Create the JFrame window
        JFrame frame = new JFrame("My Window");

        // Set the default close operation
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the size of the window (400 width, 700 height)
        frame.setSize(400, 700);

        // Center the window on the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - frame.getWidth()) / 2;
        int y = (screenSize.height - frame.getHeight()) / 2;
        frame.setLocation(x, y);

        //create GridLayout
        frame.setLayout(new GridLayout(3,1));


        frame.add(new JButton("1"));
        frame.add(new JButton("2"));
        frame.add(new JButton("3"));


        // Make the window visible
        frame.setVisible(true);

    }
}
