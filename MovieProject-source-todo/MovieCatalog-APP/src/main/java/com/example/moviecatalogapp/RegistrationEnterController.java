package com.example.moviecatalogapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.solution2.administration.adminValidator.AdminRegValidator;
import org.solution2.administration.adminValidator.AdminRegValidatorImpl;
import org.solution2.administration.enter.AdminEnterInDB;
import org.solution2.administration.enter.AdminEnterInDBImpl;
import org.solution2.administration.enter.AdminEnterService;
import org.solution2.administration.enter.AdminEnterServiceImpl;
import org.solution2.administration.model.Admin;
import org.solution2.administration.registration.AdminRegInDB;
import org.solution2.administration.registration.AdminRegInDBImpl;
import org.solution2.administration.registration.AdminRegService;
import org.solution2.administration.registration.AdminRegServiceImpl;

public class RegistrationEnterController {

    AdminRegValidator adminRegValidator = new AdminRegValidatorImpl();
    AdminRegInDB adminRegInDB = new AdminRegInDBImpl();
    AdminRegService adminRegService = new AdminRegServiceImpl(adminRegInDB, adminRegValidator);
    AdminEnterInDB adminEnterInDB = new AdminEnterInDBImpl();
    AdminEnterService adminEnterService = new AdminEnterServiceImpl(adminEnterInDB);

    @FXML
    private TextField LoginTF;
    @FXML
    private PasswordField PassPF;

    

    public void onRegistrationAction() {
        System.out.println("onRegistrationAction");
        Admin admin = new Admin(LoginTF.getText(), PassPF.getText());
        boolean isInDB = adminRegService.register(admin);
        System.out.println(isInDB);
        if(!isInDB){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("admin with: ("+LoginTF.getText()+") Login already exist!");
            alert.show();
        }
    }

    public void onEnterAction() {
        try{

            boolean isIn = adminEnterService.isExist(LoginTF.getText(),PassPF.getText());
            if(!isIn){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Your Login or Password incorrect");
                alert.show();
            }else {
                Admin admin = new Admin(LoginTF.getText(),PassPF.getText());
                FXMLLoader loader = new FXMLLoader(MovieCatalogApp.class.getResource("Admin-page.fxml"));
                UtilsAttention.currentAdmin = admin;
                Scene scene = new Scene(loader.load(), 750, 550);
                UtilsAttention.stage.setScene(scene);
            }
        }catch (Exception e){
            e.printStackTrace();
            UtilsAttention.showErrorShortAlert(e);

        }

    }

    public void onAsAdminAction() {
    }
}