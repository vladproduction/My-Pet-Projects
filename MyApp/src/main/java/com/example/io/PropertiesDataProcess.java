package com.example.io;

import com.example.myapp.InformationList;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PropertiesDataProcess implements DataProcess {


    @Override
    public void save(List<InformationList> phoneBook) {
        File file = new File("phoneContacts.txt");
        Properties phoneBookProperties = new Properties();
        int size = phoneBook.size();
        phoneBookProperties.setProperty("length",""+size);

        try (FileOutputStream out = new FileOutputStream(file)) {
            for(int i =0;i<size;i++){
                InformationList contact = phoneBook.get(i);
                phoneBookProperties.setProperty("name"+i,contact.getName());
                phoneBookProperties.setProperty("phone"+i,contact.getPhone());
            }
            phoneBookProperties.store(out, "contacts from phone book");
        } catch (Exception e) {
        e.printStackTrace();
        }

    }

    @Override
    public List<InformationList> load() {
        File file = new File("phoneContacts.txt");
        List<InformationList> listOfContacts = new ArrayList();
        Properties phoneBookProperties = new Properties();
        try (FileInputStream in = new FileInputStream(file)) {
            phoneBookProperties.load(in);
            String lengthValue = phoneBookProperties.getProperty("length","0");
            int size = Integer.parseInt(lengthValue);
            for(int i=0;i<size;i++){
                String name = phoneBookProperties.getProperty("name"+i);
                String phone = phoneBookProperties.getProperty("phone"+i);
                InformationList list = new InformationList();
                list.name = name;
                list.phone = phone;
                listOfContacts.add(list);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return listOfContacts;
    }
}
