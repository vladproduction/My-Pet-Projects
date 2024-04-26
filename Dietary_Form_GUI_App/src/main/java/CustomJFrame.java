import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * CustomJFrame class inherit JFrame class, containing constructor method and inner class InnerActionListener.
 * In constructor initializing all class variable components, configure them and adding to layout manager.
 * As the layout manager was chosen GridBagLayout.
 * InnerActionListener class implement the ActionListener interface and contain 2 methods:
 * -actionPerformed() -  submitting data to csv file and clear form to default values;
 * -clearForm() - reset all fields to their default values;
 * */
public class CustomJFrame extends JFrame {

    //class constants
    private static final int INSERT_SIZE = 5;//indicating the size of the indentation
    private static final int MEAL_SLIDER_DEFAULT_VALUE = 3;//indicating the default value of mealSlider
    private static final int WATER_INTAKE_DEFAULT_VALUE = 15;//indicating the default value of waterIntakeSpinner
    private static final String SUBMIT_ACTION_COMMAND = "submitAction";//action command property for submit button
    private static final String CLEAR_ACTION_COMMAND = "clearAction";//action command property for clear button
    private static final String DATE_TIME_PATTERN = "MM/dd/yyyy HH:mm:ss"; //indicating pattern for Date and Time format

    //class variable components
    private JTextField firstNameTextField;
    private JTextField lastNameTextField;
    private JTextField phoneNumberTextField;
    private JTextField emailTextField;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private JRadioButton preferRadioButton;
    private ButtonGroup radioButtonGroup;
    private JSpinner waterIntakeSpinner;
    private JSlider mealSlider;
    private JCheckBox wheatCheckBox;
    private JCheckBox sugarCheckBox;
    private JCheckBox diaryCheckBox;
    private JComboBox<String> walkComboBox;
    private JFormattedTextField weightFormattedTextField;
    private String[] walkOptions;

    private FileHandler fileHandler;//FileHandler class object

    private DateFormat dateFormat;// API to format date according to our pattern


    /**
     * Constructor of the CustomJFrame class
     * */
    public CustomJFrame() {

        dateFormat = new SimpleDateFormat(DATE_TIME_PATTERN);//initializing SimpleDateFormat class instance
        fileHandler = new FileHandler();//initializing FileHandler object

        // Configure the frame layout and visibility:
        this.setSize(400, 700); //setting size of the frame
        this.setDefaultCloseOperation(EXIT_ON_CLOSE); //setting close operation
        this.setTitle("Dietary Survey"); //title of the frame
        this.setLocationRelativeTo(null); //center of the screen

        //initialization all the class variable components:
        JLabel headingLabel = new JLabel("Personal Information");
        JLabel firstNameLabel = new JLabel("First Name:");
        JLabel lastNameLabel = new JLabel("Last Name:");
        JLabel phoneNumberLabel = new JLabel("Phone Number:");
        JLabel emailLabel = new JLabel("Email:");
        JLabel dietaryLabel = new JLabel("Dietary Questions");
        JLabel genderLabel = new JLabel("Sex");
        JLabel waterLabel = new JLabel("How many cups of water on average do you drink a day?");
        JLabel mealsLabel = new JLabel("How many meals on average do you eat a day?");
        JLabel checkBoxLabel = new JLabel("Do any of this meals regularly contain:");
        JLabel walkLabel = new JLabel("On average how many miles do you walk in a day?");
        JLabel weightLabel = new JLabel("How much do you weigh?");
        firstNameTextField = new JTextField();
        lastNameTextField = new JTextField();
        phoneNumberTextField = new JTextField();
        emailTextField = new JTextField();
        maleRadioButton = new JRadioButton("Male");
        femaleRadioButton = new JRadioButton("Female");
        preferRadioButton = new JRadioButton("Prefer not to say");
        radioButtonGroup = new ButtonGroup();
        waterIntakeSpinner = new JSpinner();
        mealSlider = new JSlider();
        wheatCheckBox = new JCheckBox("Wheat");
        sugarCheckBox = new JCheckBox("Sugar");
        diaryCheckBox = new JCheckBox("Diary");
        walkOptions = new String[]{ //DefaultValue = Less than 1 Mile
                "Less than 1 Mile",
                "More than 1 mile but less than 2 miles",
                "More than 2 miles but less than 3 miles",
                "More than 3 miles"};
        walkComboBox = new JComboBox<>(walkOptions);
        weightFormattedTextField = new JFormattedTextField();

        JButton submitButton = new JButton("Submit");
        submitButton.setBackground(Color.GREEN);
        submitButton.setActionCommand(SUBMIT_ACTION_COMMAND);//it will be useful inside action listener to define the source of event

        JButton clearButton = new JButton("Clear");
        clearButton.setBackground(Color.YELLOW);
        clearButton.setActionCommand(CLEAR_ACTION_COMMAND);//it will be useful inside action listener to define the source of event

        //configuration components default values :
        //1-Only one radio button should be selectable at a time
        //adding radio buttons to group
        radioButtonGroup.add(maleRadioButton);
        radioButtonGroup.add(femaleRadioButton);
        radioButtonGroup.add(preferRadioButton);

        //2-JSpinner configuration: min = 0 , max = 50, step = 1, default = 15
        waterIntakeSpinner.setModel(new SpinnerNumberModel(WATER_INTAKE_DEFAULT_VALUE, 0, 50, 1));

        //3-JSlider configuration: min = 0, max = 10, default = 3
        mealSlider.setMinimum(0);
        mealSlider.setMaximum(10);
        mealSlider.setValue(MEAL_SLIDER_DEFAULT_VALUE);
        mealSlider.setPaintTicks(true);
        mealSlider.setPaintLabels(true);
        mealSlider.setMinorTickSpacing(1);
        mealSlider.setMajorTickSpacing(1);

        //initialising GridBagLayout object as layout manager
        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);//setting layout for our frame
        //initialization constraints object for our GridBagLayout manager (set position for particular component)
        GridBagConstraints constraints = new GridBagConstraints();

        //configuration components separately one-by-one and adding to the frame by using manager

        //headingLabel
        constraints.insets = new Insets(0, INSERT_SIZE, INSERT_SIZE, INSERT_SIZE);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.anchor = GridBagConstraints.LINE_START;
        layout.setConstraints(headingLabel, constraints);
        add(headingLabel);

        //firstNameLabel
        constraints = new GridBagConstraints();
        constraints.insets = new Insets(INSERT_SIZE, INSERT_SIZE, INSERT_SIZE, INSERT_SIZE);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weightx = 0.3;
        constraints.anchor = GridBagConstraints.CENTER;
        layout.setConstraints(firstNameLabel, constraints);
        add(firstNameLabel);

        //firstNameTextField
        constraints = new GridBagConstraints();
        constraints.insets = new Insets(INSERT_SIZE, INSERT_SIZE, INSERT_SIZE, INSERT_SIZE);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weightx = 0.7;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.LINE_START;
        layout.setConstraints(firstNameTextField, constraints);
        add(firstNameTextField);

        //lastNameLabel
        constraints = new GridBagConstraints();
        constraints.insets = new Insets(INSERT_SIZE, INSERT_SIZE, INSERT_SIZE, INSERT_SIZE);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weightx = 0.3;
        constraints.anchor = GridBagConstraints.CENTER;
        layout.setConstraints(lastNameLabel, constraints);
        add(lastNameLabel);

        //lastNameTextField
        constraints = new GridBagConstraints();
        constraints.insets = new Insets(INSERT_SIZE, INSERT_SIZE, INSERT_SIZE, INSERT_SIZE);
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weightx = 0.7;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.LINE_START;
        layout.setConstraints(lastNameTextField, constraints);
        add(lastNameTextField);

        //phoneNumberLabel
        constraints = new GridBagConstraints();
        constraints.insets = new Insets(INSERT_SIZE, INSERT_SIZE, INSERT_SIZE, INSERT_SIZE);
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weightx = 0.3;
        constraints.anchor = GridBagConstraints.CENTER;
        layout.setConstraints(phoneNumberLabel, constraints);
        add(phoneNumberLabel);

        //phoneNumberTextField
        constraints = new GridBagConstraints();
        constraints.insets = new Insets(INSERT_SIZE, INSERT_SIZE, INSERT_SIZE, INSERT_SIZE);
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weightx = 0.7;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.LINE_START;
        layout.setConstraints(phoneNumberTextField, constraints);
        add(phoneNumberTextField);

        //emailLabel
        constraints = new GridBagConstraints();
        constraints.insets = new Insets(INSERT_SIZE, INSERT_SIZE, INSERT_SIZE, INSERT_SIZE);
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weightx = 0.3;
        constraints.anchor = GridBagConstraints.CENTER;
        layout.setConstraints(emailLabel, constraints);
        add(emailLabel);

        //emailTextField
        constraints = new GridBagConstraints();
        constraints.insets = new Insets(INSERT_SIZE, INSERT_SIZE, INSERT_SIZE, INSERT_SIZE);
        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weightx = 0.7;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.LINE_START;
        layout.setConstraints(emailTextField, constraints);
        add(emailTextField);

        //genderLabel
        constraints = new GridBagConstraints();
        constraints.insets = new Insets(INSERT_SIZE, INSERT_SIZE, INSERT_SIZE, INSERT_SIZE);
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weightx = 0.5;
        constraints.anchor = GridBagConstraints.CENTER;
        layout.setConstraints(genderLabel, constraints);
        add(genderLabel);

        //maleRadioButton
        constraints = new GridBagConstraints();
        constraints.insets = new Insets(INSERT_SIZE, INSERT_SIZE, INSERT_SIZE, INSERT_SIZE);
        constraints.gridx = 1;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weightx = 0.5;
        constraints.anchor = GridBagConstraints.LINE_START;
        layout.setConstraints(maleRadioButton, constraints);
        add(maleRadioButton);

        //femaleRadioButton
        constraints = new GridBagConstraints();
        constraints.insets = new Insets(INSERT_SIZE, INSERT_SIZE, INSERT_SIZE, INSERT_SIZE);
        constraints.gridx = 1;
        constraints.gridy = 6;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weightx = 0.5;
        constraints.anchor = GridBagConstraints.LINE_START;
        layout.setConstraints(femaleRadioButton, constraints);
        add(femaleRadioButton);

        //preferRadioButton
        constraints = new GridBagConstraints();
        constraints.insets = new Insets(INSERT_SIZE, INSERT_SIZE, INSERT_SIZE, INSERT_SIZE);
        constraints.gridx = 1;
        constraints.gridy = 7;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weightx = 0.5;
        constraints.anchor = GridBagConstraints.LINE_START;
        layout.setConstraints(preferRadioButton, constraints);
        add(preferRadioButton);

        //dietaryLabel
        constraints = new GridBagConstraints();
        constraints.insets = new Insets(INSERT_SIZE, INSERT_SIZE, INSERT_SIZE, INSERT_SIZE);
        constraints.gridx = 0;
        constraints.gridy = 8;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weightx = 0.5;
        constraints.anchor = GridBagConstraints.LINE_START;
        layout.setConstraints(dietaryLabel, constraints);
        add(dietaryLabel);

        //waterLabel
        constraints = new GridBagConstraints();
        constraints.insets = new Insets(INSERT_SIZE, INSERT_SIZE, INSERT_SIZE, INSERT_SIZE);
        constraints.gridx = 0;
        constraints.gridy = 9;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.weightx = 1;
        constraints.anchor = GridBagConstraints.LINE_START;
        layout.setConstraints(waterLabel, constraints);
        add(waterLabel);

        //waterIntakeSpinner
        constraints = new GridBagConstraints();
        constraints.insets = new Insets(INSERT_SIZE, INSERT_SIZE, INSERT_SIZE, INSERT_SIZE);
        constraints.gridx = 0;
        constraints.gridy = 10;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.weightx = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        layout.setConstraints(waterIntakeSpinner, constraints);
        add(waterIntakeSpinner);

        //mealsLabel
        constraints = new GridBagConstraints();
        constraints.insets = new Insets(INSERT_SIZE, INSERT_SIZE, INSERT_SIZE, INSERT_SIZE);
        constraints.gridx = 0;
        constraints.gridy = 11;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.weightx = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        layout.setConstraints(mealsLabel, constraints);
        add(mealsLabel);

        //mealSlider
        constraints = new GridBagConstraints();
        constraints.insets = new Insets(INSERT_SIZE, INSERT_SIZE, INSERT_SIZE, INSERT_SIZE);
        constraints.gridx = 0;
        constraints.gridy = 12;
        constraints.gridwidth = 2;
        constraints.gridheight = 2;
        constraints.weightx = 1;
        constraints.weighty = 2;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        layout.setConstraints(mealSlider, constraints);
        add(mealSlider);

        //checkBoxLabel
        constraints = new GridBagConstraints();
        constraints.insets = new Insets(INSERT_SIZE, INSERT_SIZE, INSERT_SIZE, INSERT_SIZE);
        constraints.gridx = 0;
        constraints.gridy = 14;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.weightx = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        layout.setConstraints(checkBoxLabel, constraints);
        add(checkBoxLabel);

        //decided to organized check boxes into separate JPanel and used flow layout
        JPanel mealsContainPanel = new JPanel();
        mealsContainPanel.setLayout(new FlowLayout());//add components one-by-one
        mealsContainPanel.add(diaryCheckBox);
        mealsContainPanel.add(wheatCheckBox);
        mealsContainPanel.add(sugarCheckBox);
        constraints = new GridBagConstraints();
        constraints.insets = new Insets(INSERT_SIZE, INSERT_SIZE, INSERT_SIZE, INSERT_SIZE);
        constraints.gridx = 0;
        constraints.gridy = 15;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.weightx = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.BOTH;
        layout.setConstraints(mealsContainPanel, constraints);
        add(mealsContainPanel); //add panel that contains 3 check boxes

        //walkLabel
        constraints = new GridBagConstraints();
        constraints.insets = new Insets(INSERT_SIZE, INSERT_SIZE, INSERT_SIZE, INSERT_SIZE);
        constraints.gridx = 0;
        constraints.gridy = 16;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.weightx = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        layout.setConstraints(walkLabel, constraints);
        add(walkLabel);

        //walkComboBox
        constraints = new GridBagConstraints();
        constraints.insets = new Insets(INSERT_SIZE, INSERT_SIZE, INSERT_SIZE, INSERT_SIZE);
        constraints.gridx = 0;
        constraints.gridy = 17;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.weightx = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.BOTH;
        layout.setConstraints(walkComboBox, constraints);
        add(walkComboBox);

        //weightLabel
        constraints = new GridBagConstraints();
        constraints.insets = new Insets(INSERT_SIZE, INSERT_SIZE, INSERT_SIZE, INSERT_SIZE);
        constraints.gridx = 0;
        constraints.gridy = 18;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.weightx = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        layout.setConstraints(weightLabel, constraints);
        add(weightLabel);

        //weightFormattedTextField
        constraints = new GridBagConstraints();
        constraints.insets = new Insets(INSERT_SIZE, INSERT_SIZE, INSERT_SIZE, INSERT_SIZE);
        constraints.gridx = 0;
        constraints.gridy = 19;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.weightx = 0.5;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.BOTH;
        layout.setConstraints(weightFormattedTextField, constraints);
        add(weightFormattedTextField);

        //clearButton
        constraints = new GridBagConstraints();
        constraints.insets = new Insets(INSERT_SIZE, INSERT_SIZE, INSERT_SIZE, INSERT_SIZE);
        constraints.gridx = 0;
        constraints.gridy = 20;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weightx = 1;
        constraints.anchor = GridBagConstraints.WEST;
        layout.setConstraints(clearButton, constraints);
        add(clearButton);

        //submitButton
        constraints = new GridBagConstraints();
        constraints.insets = new Insets(INSERT_SIZE, INSERT_SIZE, INSERT_SIZE, INSERT_SIZE);
        constraints.gridx = 1;
        constraints.gridy = 20;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weightx = 1;
        constraints.anchor = GridBagConstraints.EAST;
        layout.setConstraints(submitButton, constraints);
        add(submitButton);

        //initialization an inner class to handle actions:
        ActionListener actionListener = new InnerActionListener();
        //submit and clear buttons both registered this action listener
        clearButton.addActionListener(actionListener); //setting listener for clear button
        submitButton.addActionListener(actionListener);//setting listener for submit button

        pack();//fit the preferred size and layouts of its subcomponents
        setVisible(true);//setting visibility of the frame

    }

    /**
     * InnerActionListener class implement the ActionListener interface and contain 2 methods:
     * -actionPerformed()
     * -clearForm() - reset all fields to their default values;
     * */
    private class InnerActionListener implements ActionListener {

        /**
         * Clear the data of the form and if the submit button was pressed the
         * data capturing and writing to the csv file using the FileHandler writeResults() method;
         * @param event is generated by Button (pressed), indicates defined action occurred
         * */
        public void actionPerformed(ActionEvent event) {
            //check the source of event.
            //because we have added one action listener to both buttons
            //so this if statement checks whether we have clicked submit button
            if (SUBMIT_ACTION_COMMAND.equals(event.getActionCommand())) {

                //Configuring data for String csvTemplate
                String dateTimeValue = dateFormat.format(new Date());//took current time and formatting it by our pattern
                String firstNameValue = firstNameTextField.getText();//took First Name data
                String lastNameValue = lastNameTextField.getText();//took Last Name data
                String phoneNumberTextValue = phoneNumberTextField.getText();//took Phone Number data
                String emailTextValue = emailTextField.getText();//took Email data

                //configuring statement for radio button (should be selected one)
                String sexValue = "";
                if (maleRadioButton.isSelected()) {
                    sexValue = maleRadioButton.getText();
                } else if (femaleRadioButton.isSelected()) {
                    sexValue = femaleRadioButton.getText();
                } else if (preferRadioButton.isSelected()) {
                    sexValue = preferRadioButton.getText();
                }
                if(sexValue.isEmpty()){
                    //in case is not selected showing MessageDialog (press OK and select one)
                    JOptionPane.showMessageDialog(null,
                            "Sex Value is not selected",
                            "Sex Value should be selected",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                String waterValue = waterIntakeSpinner.getValue() + "";//took Water data from (spinner) indicating
                int mealsValue = mealSlider.getValue();//took Meal data from (slider) indicating
                boolean wheatValue = wheatCheckBox.isSelected();//took data from Wheat (check box) if selected
                boolean sugarValue = sugarCheckBox.isSelected();//took data from Sugar (check box) if selected
                boolean diaryValue = diaryCheckBox.isSelected();//took data from Diary (check box) if selected
                String milesValue = walkComboBox.getSelectedItem() + "";//took data from milesValue (combo box) if selected

                //configuring format for weightFormattedTextField (should be numeric)
                String weightValue = weightFormattedTextField.getText();
                if(!weightValue.isEmpty()){
                    weightValue = weightValue.trim().replaceAll(",","."); //in case user print ','
                    try {
                        Double.parseDouble(weightValue.trim()); //parsing data to type Double
                    } catch (Exception ex) {
                        //in case is not numeric showing MessageDialog (press OK and use numbers for that FormattedTextField)
                        JOptionPane.showMessageDialog(null,
                                "Weight is not Numeric",
                                "Only numeric value is valid",
                                JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }

                //creating csvTemplate as String value based on GUI form data
                String csvTemplate = "%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s";
                //creating surveyData as String by standard method format
                String surveyData = String.format(csvTemplate, dateTimeValue, firstNameValue, lastNameValue,
                        phoneNumberTextValue, emailTextValue, sexValue, waterValue, mealsValue, wheatValue,
                        sugarValue, diaryValue, milesValue, weightValue);

                //invoke method writeResults from FileHandler class
                // and paste as parameter String value been created by format (surveyData)
                fileHandler.writeResults(surveyData);
            }
            clearForm(); //invoke clearForm() method for cleaning all data of the GUI form to default state
        }

        /**
         * Reset all fields to their default values.
         * */
        private void clearForm() {
            firstNameTextField.setText(""); //empty
            lastNameTextField.setText("");//empty
            phoneNumberTextField.setText("");//empty
            emailTextField.setText("");//empty
            radioButtonGroup.clearSelection();//method clear selected
            mealSlider.setValue(MEAL_SLIDER_DEFAULT_VALUE); //constant value
            wheatCheckBox.setSelected(false);//check box stands empty
            sugarCheckBox.setSelected(false);//check box stands empty
            diaryCheckBox.setSelected(false);//check box stands empty
            walkComboBox.setSelectedItem(walkOptions[0]);//default value as first item from String[] walkOptions
            weightFormattedTextField.setText("");//empty
            waterIntakeSpinner.setValue(WATER_INTAKE_DEFAULT_VALUE);//constant value
        }
    }
}
