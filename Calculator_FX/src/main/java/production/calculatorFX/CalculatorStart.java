package production.calculatorFX;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class CalculatorStart extends Application {
    private TextField textFieldDisplay;
    private Button [] buttons;
    private String [] buttonLabels = {
            "7","8","9","+",
            "4","5","6","-",
            "1","2","3","*",
            "C","0","=","/"
    };
    private int result = 0;
    private String inputString = "0";// Input number as String
    // Previous operator: ' '(nothing), '+', '-', '*', '/', '='
    private char lastOperator = ' ';

    // Event handler for all the 16 Buttons
    EventHandler eventHandler = event -> {
        String currentButtonLabel = ((Button)event.getSource()).getText();
        switch (currentButtonLabel){
            // Number buttons
            case "0": case "1": case "2": case "3": case "4":
            case "5": case "6": case "7": case "8": case "9":
                if(inputString.equals("0")){  // no leading zero
                    inputString = currentButtonLabel;
                }else {
                    inputString += currentButtonLabel;  // append input digit
                }
                textFieldDisplay.setText(inputString);

                // Clear buffer if last operator is '='
                if (lastOperator == '=') {
                    result = 0;
                    lastOperator = ' ';
                }
                break;
            // Operator buttons: '+', '-', '*', '/' and '='
            case "+":
                compute();
                lastOperator = '+';
                break;
            case "-":
                compute();
                lastOperator = '-';
                break;
            case "*":
                compute();
                lastOperator = '*';
                break;
            case "/":
                compute();
                lastOperator = '/';
                break;
            case "=":
                compute();
                lastOperator = '=';
                break;
            // Clear button
            case "C":
                result = 0;
                inputString = "0";
                lastOperator = ' ';
                textFieldDisplay.setText("0");
                break;
        }
    };


    // User pushes '+', '-', '*', '/' or '=' button.
    // Perform computation on the previous result and the current input number,
    // based on the previous operator.
    private void compute() {
        int inNum = Integer.parseInt(inputString);
        inputString = "0";
        if (lastOperator == ' ') {
            result = inNum;
        } else if (lastOperator == '+') {
            result += inNum;
        } else if (lastOperator == '-') {
            result -= inNum;
        } else if (lastOperator == '*') {
            result *= inNum;
        } else if (lastOperator == '/') {
            result /= inNum;
        } else if (lastOperator == '=') {
            // Keep the result for the next operation
        }
        textFieldDisplay.setText(result + "");
    }

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        // Setup the Display TextField
        textFieldDisplay = new TextField("0");
        textFieldDisplay.setEditable(false);
        textFieldDisplay.setAlignment(Pos.CENTER_RIGHT);

        // Setup a GridPane for 4x4 Buttons
        int numCols = 4;
        int numRows = 4;
        GridPane paneButton = new GridPane();
        paneButton.setPadding(new Insets(15, 0, 15, 0));  // top, right, bottom, left
        paneButton.setVgap(5);  // Vertical gap between nodes
        paneButton.setHgap(5);  // Horizontal gap between nodes
        // Setup 4 columns of equal width, fill parent
        ColumnConstraints[] columns = new ColumnConstraints[numCols];
        for (int i = 0; i < numCols; ++i) {
            columns[i] = new ColumnConstraints();
            columns[i].setHgrow(Priority.ALWAYS) ;  // Allow column to grow
            columns[i].setFillWidth(true);  // Ask nodes to fill space for column
            paneButton.getColumnConstraints().add(columns[i]);
        }

        // Setup 16 Buttons and add to GridPane; and event handler
        buttons = new Button[16];
        for (int i = 0; i < buttons.length; ++i) {
            buttons[i] = new Button(buttonLabels[i]);
            buttons[i].setOnAction(eventHandler);  // Register event handler
            buttons[i].setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);  // full-width
            paneButton.add(buttons[i], i % numCols, i / numCols);  // control, col, row
        }

        // Setup up the scene graph rooted at a BorderPane (of 5 zones)
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(15, 15, 15, 15));  // top, right, bottom, left
        root.setTop(textFieldDisplay);     // Top zone contains the TextField
        root.setCenter(paneButton); // Center zone contains the GridPane of Buttons

        stage.setScene(new Scene(root,300,200));
        stage.setTitle("Calculator_FX");
        Image icon = new Image("file:src/CalculatorIcon.png");
        stage.getIcons().add(icon);
        stage.show();
    }
}
