package production.helloFX;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Hello extends Application {
    private Button hello;
    private Stage stage;
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        hello = new Button();
        hello.setText("Hello Java FX");
        hello.setOnAction(event-> {
            System.out.println("Congratulation with your first program \n in JavaFX");
        });
        StackPane pane = new StackPane();
        pane.getChildren().add(hello);
        Scene scene = new Scene(pane,200,300);
        scene.setFill(Color.BROWN);
        stage.setScene(scene);
        stage.setTitle("JavaFX");
        Image icon = new Image("file:src/JavaFX-logo.png");
        stage.getIcons().add(icon);
        stage.show();


    }
}
