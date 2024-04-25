package demo_gridlayout;

import javax.swing.*;
import java.awt.*;

/**
 * Created by vladproduction on 25-Apr-24
 */

public class MyJFrame extends JFrame {

    public MyJFrame() {

        //1-create JPanel:
        JPanel panel = new JPanel();

        //2-create LayoutManager and set it for our panel:
        GridLayout gridLayout = new GridLayout();
        panel.setLayout(gridLayout);

        //3-create components:
        JLabel label1 = new JLabel("Number 1:");
        JTextField field1 = new JTextField(10);
        JLabel label2 = new JLabel("Number 2:");
        JTextField field2 = new JTextField(10);
        JLabel label3 = new JLabel("Sum:");
        JTextField result = new JTextField(10);
        JButton go = new JButton("Add");

        //4-add components to the panel:
        panel.add(label1);
        panel.add(field1);
        panel.add(label2);
        panel.add(field2);
        panel.add(label3);
        panel.add(result);
        panel.add(go);

        //5-create frame and add the panel to it:
        //but class MyJFrame extends JFrame, and in constructor creates new frame during instantiating in main class
//        JFrame frame = new JFrame();
        this.setContentPane(panel);

        //6-setting size and display window...
        this.setSize(500,800);
        this.setTitle("My Frame");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setVisible(true);

    }
}
