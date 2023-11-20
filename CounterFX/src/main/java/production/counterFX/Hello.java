package production.counterFX;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Hello extends Application {
    private TextField textFieldCount;
    private Button buttonCount;
    private int counter = 0;

    private Stage stage;

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        textFieldCount = new TextField("0");
        textFieldCount.setEditable(false);
        buttonCount = new Button("Click and count");
        buttonCount.setOnAction(event-> {
            textFieldCount.setText(++counter +"");
        });

        FlowPane flow = new FlowPane();
        flow.setPadding(new Insets(15,12,15,12));  // top, right, bottom, left
        flow.setVgap(10);  // Vertical gap between nodes in pixels
        flow.setHgap(10);  // Horizontal gap between nodes in pixels
        flow.setAlignment(Pos.CENTER);  // Alignment
        flow.getChildren().addAll(new Label("Count Clicks:"),textFieldCount,buttonCount);

        stage.setScene(new Scene(flow,400,80));
        stage.setTitle("CounterFX");
        Image icon = new Image("file:src/JavaFX-logo.png");
        stage.getIcons().add(icon);
        stage.show();

    }
}
