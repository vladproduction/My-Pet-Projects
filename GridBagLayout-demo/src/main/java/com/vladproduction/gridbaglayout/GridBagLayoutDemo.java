package com.vladproduction.gridbaglayout;

import javax.swing.*;
import java.awt.*;

/**
 * Created by vladproduction on 24-Apr-24
 */

public class GridBagLayoutDemo extends JFrame {

    public GridBagLayoutDemo() {

        GridBagConstraints gbc = new GridBagConstraints(); // for set position for particular component


        this.setSize(400, 700);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("My GridBagLayout");
        this.setLocationRelativeTo(null); //center of the screen
        this.setLayout(new GridBagLayout());

//        // Also center the window on the screen
//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        int x = (screenSize.width - this.getWidth()) / 2;
//        int y = (screenSize.height - this.getHeight()) / 2;
//        this.setLocation(x, y);

        JButton button1 = new JButton("1");
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(button1, gbc);

        JButton button2 = new JButton("2");
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        this.add(button2, gbc);

        JButton button3 = new JButton("3");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 20;
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(button3, gbc);

        JButton button4 = new JButton("4");
        gbc.gridx = 1;
        gbc.gridy = 1;
        this.add(button4, gbc);

        JButton button5 = new JButton("5");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        this.add(button5, gbc);


        this.setVisible(true);
    }
}
