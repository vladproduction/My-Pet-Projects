package com.example.myapp;

import com.example.io.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HelloController {

    @FXML
    private Label nameLabel;
    @FXML
    private Label phoneNumberLabel;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField phoneNumberTextField;
    @FXML
    private Label phoneBookLabel;
    @FXML
    private Label informationLabel;
    @FXML
    private Button createButton;
    @FXML
    private Button editButton;
    @FXML
    private Button removeButton;
    @FXML
    private ListView<InformationList> listView;
    @FXML
    private Button saveButton;
    @FXML
    private Button showContactsButton;
    @FXML
    private TextArea CurrentContactTextArea;
    @FXML
    private Button currentContactButton;


    //private DataProcess dataProcess = new DataStreamDataProcess();
    //private DataProcess dataProcess = new ObjectStreamDataProcess();
    //private DataProcess dataProcess = new PropertiesDataProcess();
    //typeStreamData:   0-->Data; 1-->Object;  2-->Properties;
    private DataProcess dataProcess = DataProcessFactory.getDataProcess(4);
    //why it works only by same o/i Stream-->потому что разные способы на ввод и вывод данных!
    // Possible to combine -->no! it`s rule


    @FXML
    public void onCreateAction() {
        System.out.println("Create Button on action");
        InformationList inform = this.createInformationList();//to call private method
        // for economy and reusing for the future

        //InformValidator informValidator = new InformValidator();
        boolean valid = InformValidator.isValid(inform);
        /*if (valid) {
            List<InformationList> items = listView.getItems();
            items.add(inform);
        }*/
        List<InformationList> items = listView.getItems();
        boolean unique = InformValidator.isUnique(items, inform);
        if (unique && valid) {
            items.add(inform);
        }
    }

    @FXML
    public void onEditAction() {
        System.out.println("Edit Button on action");
        InformationList inform = this.createInformationList();//to call private method
        // for economy and reuse for future
        boolean valid = InformValidator.isValid(inform);
        if (valid) {
            int selectedIndex = listView.getSelectionModel().getSelectedIndex();
            if (selectedIndex != -1) {
                listView.getItems().set(selectedIndex, inform);//get - куда я буду вставлять данные,
                // set - установить или замена данных по селектед индексу из инфлиста
            }
        }
    }

    @FXML
    public void onRemoveAction() {
        System.out.println("Remove Button on action");

        int selectedIndex = listView.getSelectionModel().getSelectedIndex();
        System.out.println(selectedIndex);
        if (selectedIndex != -1) {
            listView.getItems().remove(selectedIndex);
        }
    }

    private InformationList createInformationList() {
        String name = nameTextField.getText();
        String phone = phoneNumberTextField.getText();

        InformationList informationList = new InformationList();
        informationList.setName(name);
        informationList.setPhone(phone);

        System.out.println(informationList);// to logg

        nameTextField.setText("");
        phoneNumberTextField.setText("");// to make empty textField
        return informationList;
    }

    //новая кнопка-->Save, М для реализации, запись в файл дериктории через OutputStream
    public void onSaveAction() {
        System.out.println("save button");
        List<InformationList> phoneBook = listView.getItems();
        dataProcess.save(phoneBook);
    }

    //Show Contacts --> кнопка для демонстрации всех контактов в листе из файла
    public void onShowAction() {
        System.out.println("show contacts button");

        List<InformationList> listOfContacts = dataProcess.load();// читает инфо из файла
        List<InformationList> phoneBook = listView.getItems();   //добавляет текущие
        phoneBook.clear();                                   //удаляет из пользовательского интерфейса
        phoneBook.addAll(listOfContacts);      //добавляет из файла в польз интерфейс (file+current)
    }

    public void onShowCurrent() {
        System.out.println("onShowCurrent(); ---> currentContactButton");
        int selectedIndex = listView.getSelectionModel().getSelectedIndex();
        List<InformationList> allItems = listView.getItems();
        InformationList currentContact = allItems.get(selectedIndex);
        String currentContactText = currentContact.toString();
        CurrentContactTextArea.setText(currentContactText);


    }


}