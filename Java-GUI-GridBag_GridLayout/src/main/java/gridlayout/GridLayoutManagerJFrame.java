package gridlayout;

import javax.swing.*;

/**
 * Created by vladproduction on 25-Apr-24
 */

public class GridLayoutManagerJFrame extends JFrame{
    private JPanel gridLMPanel;
    private JTextField firstNameTextField;
    private JTextField lastNameTextField;
    private JTextField phoneNumberTextField;
    private JTextField emailTextField;
    private JLabel headingLabel;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private JRadioButton preferRadioButton;
    private JLabel dietaryLabel;
    private JLabel waterLabel;
    private JSpinner waterIntakeSpinner;
    private JLabel mealsLabel;
    private JSlider mealSlider;
    private JLabel checkBoxLabel;
    private JCheckBox wheatCheckBox;
    private JCheckBox sugarCheckBox;
    private JCheckBox diaryCheckBox;
    private JLabel walkLabel;
    private JComboBox comboBox1;
    private JLabel weightLabel;
    private JFormattedTextField weightFormattedTextField;
    private JButton clearButton;
    private JButton submitButton;
    private JLabel firstNameLabel;
    private JLabel lastNameLabel;
    private JLabel phoneNumberLabel;
    private JLabel emailLabel;
    private JLabel genderLabel;

    public GridLayoutManagerJFrame()  {
        this.setContentPane(gridLMPanel);
        this.setTitle("Dietary Survey");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(500, 800);
        this.setLocationRelativeTo(null); //center of the screen

        this.setVisible(true);
    }
}
