package com.example.io;

import com.example.myapp.InformationList;
//import org.xml.sax.helpers.DefaultHandler;
//
//import javax.xml.parsers.SAXParser;
//import javax.xml.parsers.SAXParserFactory;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

public class XMLSAXDataProcess implements DataProcess {
    private File file = new File("contact.xml");

    @Override
    public void save(List<InformationList> phoneBook) {
        StringBuilder sb = new StringBuilder();
        sb.append("<phoneBook>");
        for(int i = 0; i< phoneBook.size(); i++){
            InformationList infList = phoneBook.get(i);
            String xmlText = toXml(infList);
            sb.append(xmlText);
        }
        sb.append("</phoneBook>");
        String xml = sb.toString();
        System.out.println(xml);
        writeToFile(xml);


    }

    @Override
    public List<InformationList> load() {
//        SAXParserFactory factory = SAXParserFactory.newInstance();
//        SAXParser parser = factory.newSAXParser();
//        parser.parse(file,new DefaultHandler());

        return null;
    }

    private String toXml(InformationList infList){
        String xmlTemplate = "<informationList><name>%s</name><phone>%s</phone></informationList>";
        String xmlTextValue = String.format(xmlTemplate,infList.getName(),infList.getPhone());


        return xmlTextValue;
    }
    private void writeToFile(String text){
        try {
            FileOutputStream out = new FileOutputStream((file));
            byte[] content = text.getBytes();
            out.write(content);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
