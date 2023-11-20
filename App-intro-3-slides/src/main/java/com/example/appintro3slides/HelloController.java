package com.example.appintro3slides;

import com.jfoenix.controls.JFXButton;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private JFXButton backBtn;

    @FXML
    private Label countLabel;

    @FXML
    private JFXButton nextBtn;

    @FXML
    private AnchorPane pane1;

    @FXML
    private AnchorPane pane2;

    @FXML
    private AnchorPane pane3;


    int showSlide = 0;
    @FXML
    void backAction(ActionEvent event) {

        if(showSlide==0){
            System.out.println("no more slides");
        } else if (showSlide==1) {
            translateAnimation(0.5,pane2,600);
            showSlide--; //showSlide=0
            countLabel.setText("1/3");
        }else if(showSlide==2){
            translateAnimation(0.5,pane3,600);
            showSlide--; //showSlide=1
            countLabel.setText("2/3");
        }

    }

    @FXML
    void nextAction(ActionEvent event) {

        if(showSlide==0){
            translateAnimation(0.5,pane2,-600);
            showSlide++; //showSlide=1
            countLabel.setText("2/3");
        } else if (showSlide==1) {
            translateAnimation(0.5,pane3,-600);
            showSlide++; //showSlide=2
            countLabel.setText("3/3");
        } else{
            System.out.println("No more slides");
        }
    }

    public void translateAnimation(double duration, Node node, double byX){

        TranslateTransition transition = new TranslateTransition(Duration.seconds(duration), node);
        transition.setByX(byX);
        transition.play();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        translateAnimation(0.5,pane2,600);
        translateAnimation(0.5,pane3,600);
    }
}