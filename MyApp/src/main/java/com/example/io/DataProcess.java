package com.example.io;

import com.example.myapp.InformationList;

import java.util.List;

public interface DataProcess {

    public void save(List<InformationList> phoneBook);//DATA  (0)=save(List<InformationList> phoneBook)
                                                      //OBJECT(1)=save(List<InformationList> phoneBook)
    public List<InformationList> load();//DATA  (0)=List<InformationList> load()
                                        //OBJECT(1)=List<InformationList> load()




}
