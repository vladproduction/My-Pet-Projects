package com.example.addressbook;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.List;

public class HelloController {
    @FXML
    private TextField nameField;
    @FXML
    private TextField numberField;
    @FXML
    private ListView<MyContact> listView;

    @FXML
    public void createAction(){
        System.out.println("Create");
        String name = nameField.getText();
        String phone = numberField.getText();

        MyContact myContact = new MyContact();
        myContact.setName(name);
        myContact.setNumber(phone);

        System.out.println(myContact);

        nameField.setText("");
        numberField.setText("");

        List<MyContact> items = listView.getItems();
        items.add(myContact);

    }
    @FXML
    public void editAction(){

        System.out.println("Edit");
        String name = nameField.getText();
        String phone = numberField.getText();

        MyContact myContact = new MyContact();
        myContact.setName(name);
        myContact.setNumber(phone);

        System.out.println(myContact);

        nameField.setText("");
        numberField.setText("");

        int selectedIndex = listView.getSelectionModel().getSelectedIndex();
        if (selectedIndex!=-1){
            listView.getItems().set(selectedIndex, myContact);
        }



    }
    @FXML
    public void removeAction(){

        System.out.println("Remove");

        int selectedIndex = listView.getSelectionModel().getSelectedIndex();
        System.out.println(selectedIndex);
        if (selectedIndex!=-1){
            listView.getItems().remove(selectedIndex);
        }


    }
}