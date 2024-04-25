package demo_gridbaglayout;

import javax.swing.*;
import java.awt.*;

/**
 * Created by vladproduction on 25-Apr-24
 */

public class MyGridBagLayout extends JFrame {

    public MyGridBagLayout() {

        //1-create JPanel:
        JPanel panel = new JPanel();

        //2-create LayoutManager based on GridBagLayout and set it for our panel:
        GridBagLayout gridBagLayout = new GridBagLayout();
        panel.setLayout(gridBagLayout);

        //3-create an instance of the GridBagConstraints
        GridBagConstraints constraints = new GridBagConstraints();

        //4-create components:
        JLabel headerLabel = new JLabel("Header");
        JLabel firstNameLabel = new JLabel("First Name:");
        JTextField firstNameTextField = new JTextField();
        JLabel lastNameLabel = new JLabel("Last Name:");
        JTextField lastNameTextField = new JTextField();
        JButton submitButton = new JButton("Submit");


        //5-setting constraints for each component separately:
        //===headerLabel===:
        // x coordinate in the grid
        constraints.gridx = 0;
        // y coordinate in the grid
        constraints.gridy = 0;
        //height for the cell
        constraints.gridheight = 1;
        //width for the cell
        constraints.gridwidth = 5;
        // fill all space in the cell
        constraints.fill = GridBagConstraints.BOTH;
        // proportion of horizontal space taken by this component
        constraints.weightx = 1.0;
        // proportion of vertical space taken by this component
        constraints.weighty = 1.0;
        // position of the component within the cell
        constraints.anchor = GridBagConstraints.CENTER;
        //setting constraints:
        gridBagLayout.setConstraints(headerLabel, constraints);
        //==============================

        //===firstNameLabel===:
        // x coordinate in the grid
        constraints.gridx = 0;
        // y coordinate in the grid
        constraints.gridy = 1;
        //height for the cell
        constraints.gridheight = 1;
        //width for the cell
        constraints.gridwidth = 5;
        // fill all space in the cell
        constraints.fill = GridBagConstraints.BOTH;
        // proportion of horizontal space taken by this component
        constraints.weightx = 1.0;
        // proportion of vertical space taken by this component
        constraints.weighty = 1.0;
        // position of the component within the cell
        constraints.anchor = GridBagConstraints.CENTER;
        //setting constraints:
        gridBagLayout.setConstraints(firstNameLabel, constraints);
        //==============================

        //===firstNameTextField===:
        // x coordinate in the grid
        constraints.gridx = 1;
        // y coordinate in the grid
        constraints.gridy = 1;
        //height for the cell
        constraints.gridheight = 1;
        //width for the cell
        constraints.gridwidth = 5;
        // fill all space in the cell
        constraints.fill = GridBagConstraints.BOTH;
        // proportion of horizontal space taken by this component
        constraints.weightx = 1.0;
        // proportion of vertical space taken by this component
        constraints.weighty = 1.0;
        // position of the component within the cell
        constraints.anchor = GridBagConstraints.CENTER;
        //setting constraints:
        gridBagLayout.setConstraints(firstNameTextField, constraints);
        //==============================

        //6-add component to the panel:
        panel.add(headerLabel);
        panel.add(firstNameLabel);
        panel.add(firstNameTextField);

        //7-add panel to the frame:
        this.setContentPane(panel);

        //8-setting size and display window...
        this.setSize(500,800);
        this.setTitle("My Frame");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setVisible(true);



    }
}
