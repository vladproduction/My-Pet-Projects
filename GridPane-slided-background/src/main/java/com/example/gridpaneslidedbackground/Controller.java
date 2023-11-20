package com.example.gridpaneslidedbackground;


import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {



    @FXML
    private AnchorPane pane1, pane2, pane3, pane4, opacityPane, drawerPane;

    @FXML
    private ImageView drawerImage;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        opacityPane.setVisible(false);

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5),opacityPane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();

        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5),drawerPane);
        translateTransition.setByX(-600);
        translateTransition.play();

        pane1.setStyle("-fx-background-image: url(\"/1.jpg\")");
        pane2.setStyle("-fx-background-image: url(\"/2.jpg\")");
        pane3.setStyle("-fx-background-image: url(\"/3.jpg\")");
        pane4.setStyle("-fx-background-image: url(\"/4.jpg\")");

        animation();

        drawerImage.setOnMouseClicked(mouseEvent -> {

            opacityPane.setVisible(true);

            FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.5),opacityPane);
            fadeTransition1.setFromValue(0);
            fadeTransition1.setToValue(0.15);
            fadeTransition1.play();

            TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5),drawerPane);
            translateTransition1.setByX(+600);
            translateTransition1.play();
        });

        opacityPane.setOnMouseClicked(mouseEvent -> {


            FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.5),opacityPane);
            fadeTransition1.setFromValue(0.15);
            fadeTransition1.setToValue(0);
            fadeTransition1.play();

            fadeTransition1.setOnFinished(event -> {
                opacityPane.setVisible(false);
            });

            TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5),drawerPane);
            translateTransition1.setByX(-600);
            translateTransition1.play();
        });
    }

    public void animation(){
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(5),pane4);
        fadeTransition.setFromValue(2);
        fadeTransition.setToValue(0);
        fadeTransition.play();

        fadeTransition.setOnFinished(event -> {
            FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(5),pane3);
            fadeTransition1.setFromValue(2);
            fadeTransition1.setToValue(0);
            fadeTransition1.play();

            fadeTransition1.setOnFinished(event1 -> {
                FadeTransition fadeTransition2 = new FadeTransition(Duration.seconds(5),pane2);
                fadeTransition2.setFromValue(2);
                fadeTransition2.setToValue(0);
                fadeTransition2.play();

                fadeTransition2.setOnFinished(event2 -> {
                    FadeTransition fadeTransition00 = new FadeTransition(Duration.seconds(5),pane2);
                    fadeTransition00.setToValue(2);
                    fadeTransition00.setFromValue(0);
                    fadeTransition00.play();

                    fadeTransition00.setOnFinished(event3 -> {
                        FadeTransition fadeTransition11 = new FadeTransition(Duration.seconds(5),pane3);
                        fadeTransition11.setToValue(2);
                        fadeTransition11.setFromValue(0);
                        fadeTransition11.play();

                        fadeTransition11.setOnFinished(event4 -> {
                            FadeTransition fadeTransition22 = new FadeTransition(Duration.seconds(5),pane4);
                            fadeTransition22.setToValue(2);
                            fadeTransition22.setFromValue(0);
                            fadeTransition22.play();

                            fadeTransition22.setOnFinished(event5 -> {
                                animation();
                            });
                        });
                    });
                });
            });
        });
    }
}