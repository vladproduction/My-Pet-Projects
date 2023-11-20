package com.example.managementwindow;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class HelloController implements Initializable {

    @FXML
    private TextField searchTF;
    @FXML
    private TreeTableColumn<Model, String> nameCol;
    @FXML
    private TreeTableColumn<Model, String> jobCol;
    @FXML
    private TreeTableColumn<Model, String> ageCol;
    @FXML
    private TreeTableColumn<Model, String> genderCol;
    @FXML
    private TextField nameTF;
    @FXML
    private TextField jobTF;
    @FXML
    private TextField ageTF;
    @FXML
    private JFXComboBox<String> genderCombo;
    @FXML
    private Label nameLB;
    @FXML
    private Label jobLB;
    @FXML
    private Label ageLB;
    @FXML
    private Label genderLB;
    @FXML
    private JFXTreeTableView<Model> treeTableView;

    ObservableList<Model> list;

    @FXML
    private JFXButton addButton;
    @FXML
    private JFXButton deleteButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        genderCombo.getItems().addAll("Male", "Female");

        nameCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model, String> modelStringCellDataFeatures) {
                return modelStringCellDataFeatures.getValue().getValue().name;
            }
        });
        jobCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model, String> modelStringCellDataFeatures) {
                return modelStringCellDataFeatures.getValue().getValue().job;
            }
        });
        ageCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model, String> modelStringCellDataFeatures) {
                return modelStringCellDataFeatures.getValue().getValue().age;
            }
        });
        genderCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model, String> modelStringCellDataFeatures) {
                return modelStringCellDataFeatures.getValue().getValue().gender;
            }
        });

        list = FXCollections.observableArrayList();
        TreeItem<Model> root = new RecursiveTreeItem<Model>(list, RecursiveTreeObject::getChildren);
        treeTableView.setRoot(root);
        treeTableView.setShowRoot(false);

        list.add(new Model("Valle","engineer-developer","40","Male"));
        list.add(new Model("Nikolas","software-engineer","42","Male"));
        list.add(new Model("Salma","administrator","32","Female"));
        list.add(new Model("Joi Jr.","driver (delivery service)","29","Male"));
        list.add(new Model("Laila","office-manager","26","Female"));

        searchTF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                treeTableView.setPredicate(new Predicate<TreeItem<Model>>() {
                    @Override
                    public boolean test(TreeItem<Model> modelTreeItem) {
                        return  modelTreeItem.getValue().name.getValue().contains(t1) |
                                modelTreeItem.getValue().age.getValue().contains(t1) |
                                modelTreeItem.getValue().job.getValue().contains(t1) |
                                modelTreeItem.getValue().gender.getValue().contains(t1);
                    }
                });
            }
        });

        treeTableView.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) ->{
            showDetails(t1);
        });

    }

    @FXML
    void addAction(ActionEvent event){
        list.add(new Model(nameTF.getText(),jobTF.getText(),ageTF.getText(),
                genderCombo.getSelectionModel().getSelectedItem()));
    }

    @FXML
    void deleteAction(ActionEvent event){
        int index = treeTableView.getSelectionModel().getSelectedIndex();
        list.remove(index);
        clearFields();
    }

    @FXML
    void clearAction(ActionEvent event){
        clearFields();
    }

    @FXML
    void updateAction(ActionEvent event){
        TreeItem<Model> treeItem = treeTableView.getSelectionModel().getSelectedItem();
        Model model = new Model(
                nameTF.getText(),
                jobTF.getText(),
                ageTF.getText(),
                genderCombo.getSelectionModel().getSelectedItem());
        treeItem.setValue(model);
    }

    public void showDetails(TreeItem<Model> treeItem){
        nameTF.setText(treeItem.getValue().getName());
        nameLB.setText(treeItem.getValue().getName());

        jobTF.setText(treeItem.getValue().getJob());
        jobLB.setText(treeItem.getValue().getJob());

        ageTF.setText(treeItem.getValue().getAge());
        ageLB.setText(treeItem.getValue().getAge());

        genderCombo.getSelectionModel().select(treeItem.getValue().getGender());
        genderLB.setText(treeItem.getValue().getGender());

    }

    public void clearFields(){
        nameTF.setText("");
        nameLB.setText("");

        jobTF.setText("");
        jobLB.setText("");

        ageTF.setText("");
        ageLB.setText("");

        genderLB.setText("");
        genderCombo.getSelectionModel().select("");
    }

}