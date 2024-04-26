package com.app;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by vladproduction on 24-Apr-24
 */

public class Project4ExampleFileHandler {
    public static void main(String[] args) {


        CustomJFrame frame = new CustomJFrame();


        //action command is just a property of JButton.
        //it is useful to use inside ActionListener to define which button was clicked
        JButton createBtn = new JButton("Create");
        createBtn.setActionCommand("createAction");
        JButton writeBtn = new JButton("Write");
        writeBtn.setActionCommand("writeAction");
        JButton writeWithErrorBtn = new JButton("WriteWithError");
        writeWithErrorBtn.setActionCommand("writeWithErrorAction");

        frame.getContentPane().add(createBtn);
        frame.getContentPane().add(writeBtn);
        frame.getContentPane().add(writeWithErrorBtn);

        ActionListener actionListener = new ActionListener() {
            FileHandler fileHandler = null;

            @Override
            public void actionPerformed(ActionEvent e) {
                String actionCommand = e.getActionCommand();
                if (createBtn.getActionCommand().equals(actionCommand)) {
                    fileHandler = new FileHandler();
                } else if (writeBtn.getActionCommand().equals(actionCommand)) {
                    if (fileHandler != null) {
                        String testData = "11/13/2020 10:26:50,Jim,Smith,123-123-1234,jsmith@email.com,Male,10,3,true,true,true,Less than 1 Mile,215";
                        fileHandler.writeResults(testData);
                    }
                } else if(writeWithErrorBtn.getActionCommand().equals(actionCommand)){
                    if (fileHandler != null) {
                        //missing first "," --> 50Jim --> will not pass validation and error Dialog wil be shown
                        String testData = "11/13/2020 10:26:50Jim,Smith,123-123-1234,jsmith@email.com,Male,10,3,true,true,true,Less than 1 Mile,215";
                        fileHandler.writeResults(testData);
                    }
                }
            }
        };

        createBtn.addActionListener(actionListener);
        writeBtn.addActionListener(actionListener);
        writeWithErrorBtn.addActionListener(actionListener);


    }
}
