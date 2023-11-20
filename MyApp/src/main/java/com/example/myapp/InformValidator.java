package com.example.myapp;

import java.util.List;

public class InformValidator {

    public static boolean isValid(InformationList info) { //проверка имени номера тел на валидность
        boolean result = true;
        if (info.getName() == null || info.getName().trim().equals("")) {//проверяем что имя не пустое
            result = false;
        }
        if (info.getPhone()==null || info.getPhone().trim().equals("")) {//проверяем что number не пустое
            result = false;
        }
        return result;

    }
    public static boolean isSame(InformationList a, InformationList b){
        boolean isSameName = a.getName().equals(b.getName());
        boolean isSameNumber = a.getPhone().equals(b.getPhone());
        return isSameName&&isSameNumber;
    }

    public static boolean isUnique(List<InformationList> allItems, InformationList current) {

        boolean result = true;

        for(int i = 0;i< allItems.size();i++){
           InformationList infList = allItems.get(i);
           boolean uniqueContact = isSame(infList, current);
            if(uniqueContact){
                result = false;
            }
        }
        return result;
    }
}




