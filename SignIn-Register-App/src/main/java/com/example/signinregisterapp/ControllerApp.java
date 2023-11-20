package com.example.signinregisterapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Screen;

public class ControllerApp {

    ValidatorRegister validatorRegister = new ValidatorRegisterImpl();
    RegisterRepository registerRepository = new RegisterRepositoryImpl();
    RegisterService registerService = new RegisterServiceImpl(validatorRegister, registerRepository);
    LoginRepository loginRepository = new LoginRepositoryImpl();
    LoginService loginService = new LoginServiceImpl(loginRepository);


    @FXML
    private TextField TFLogin;
    @FXML
    private PasswordField PFPass;
    @FXML
    private TextField TFEmail;
    @FXML
    private Button BtnSignin;
    @FXML
    private Button BtnRegister;

    public void onActionBtnSignin() {
        try {
            System.out.println("onActionBtnSignin");
            boolean isExist = loginService.isExist(TFLogin.getText(), PFPass.getText());
            System.out.println("isExist in DB: " + isExist);
            if (!isExist) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Login and Password incorrect");
                alert.show();
            }else{
                Person person = new Person(
                        TFLogin.getText(),PFPass.getText(), TFEmail.getText()
                );
                FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("home-page.fxml"));

                Utils.currentPerson = person;
                Scene scene = new Scene(loader.load());
                Utils.stageHomePage.setScene(scene);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Utils.showErrorShortAlert(e);
        }
    }

    public void onActionBtnRegister(ActionEvent event) {
        System.out.println("onActionBtnRegister");
        boolean isRegistered = registerService.register(new Person(
                TFLogin.getText(),
                PFPass.getText(),
                TFEmail.getText()));
        System.out.println(isRegistered);
        if (!isRegistered) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            //alert.initModality(Modality.NONE); //useful or not another window during alert
            alert.setContentText("Login and Password already EXIST");
            alert.show();
        }
    }
}