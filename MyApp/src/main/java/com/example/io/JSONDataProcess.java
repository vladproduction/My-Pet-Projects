package com.example.io;

import com.example.myapp.InformationList;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSONDataProcess implements DataProcess{

    private ObjectMapper objectMapper = new ObjectMapper();
    private File contactsJson = new File("phoneBook.json");
    @Override
    public void save(List<InformationList> phoneBook)  {
        try {
            objectMapper.writeValue(contactsJson,phoneBook);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<InformationList> load() {
        try {
            ArrayList<InformationList> arrayList = objectMapper.readValue(contactsJson, ArrayList.class);
            return arrayList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
