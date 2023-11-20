package com.example.io;

import com.example.myapp.InformationList;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataStreamDataProcess implements DataProcess{
    @Override
    public void save(List<InformationList> phoneBook) {
         File file = new File("phoneContacts.txt");

        try (FileOutputStream fileOutputStream = new FileOutputStream(file);
             DataOutputStream out = new DataOutputStream(fileOutputStream)) {
            out.writeInt(phoneBook.size());
            for (int i = 0; i < phoneBook.size(); i++) {
                InformationList contacts = phoneBook.get(i);
                out.writeUTF(contacts.getName());
                out.writeUTF(contacts.getPhone());
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<InformationList> load() {
        List<InformationList> listOfContacts = new ArrayList();  //where to put data(which has been read)
        File file = new File("phoneContacts.txt");      //from where to take data
        try(FileInputStream fileInputStream = new FileInputStream(file);  //how to read data
            DataInputStream in = new DataInputStream(fileInputStream)){
            int size = in.readInt();

            for(int i=0;i<size;i++){
                String nameFromFile = in.readUTF();
                String phoneFromFile = in.readUTF();
                InformationList informationList = new InformationList();
                informationList.name=nameFromFile;
                informationList.phone=phoneFromFile;
                listOfContacts.add(informationList);
            }
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
        return listOfContacts;
    }



}
