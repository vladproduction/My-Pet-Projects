package com.example.io;

import com.example.myapp.InformationList;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ObjectStreamDataProcess implements DataProcess {
    @Override
    public void save(List<InformationList> phoneBook) {
        //write ObjectOutputStream
        File file = new File("phoneContacts.txt");
        ArrayList<InformationList> dataList = new ArrayList(); //phoneBook который приходит из юай(списка), это javaFX ObservableList, который нельзя использовать для сериализации
        //поэтому мы создаем пустой аррей лист(который работает с сериализацией)
        dataList.addAll(phoneBook);// в который мы добавляем все контакты которые хотим сохранить

        try (FileOutputStream fileOutputStream = new FileOutputStream(file);
             ObjectOutputStream out = new ObjectOutputStream(fileOutputStream)) {
            out.writeObject(dataList);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<InformationList> load() {
        //read ObjectInputStream

        File file = new File("phoneContacts.txt");      //from where to take data
        try (FileInputStream fileInputStream = new FileInputStream(file);  //how to read data
             ObjectInputStream in = new ObjectInputStream(fileInputStream)) {
            Object result = in.readObject();
            List<InformationList> listOfContacts = (List<InformationList>)result;//where to put data(which has been read)
            //Child c = new Parent();-->not allowed
            //Child x = new Child();
            //Parent p = x;
            //Child y = p; -->not allowed
            //Child y = x; -->could be, child=child
            //Child y = (Child)p;
            return listOfContacts;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }


    }



}

