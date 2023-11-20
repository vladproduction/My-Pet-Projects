package com.example.myapp;

import java.io.Serializable;

public class InformationList implements Serializable {

    public String name;
    public String phone;

    public void setName(String name) { //для установки, записи
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() { //для получения, чтения результата
        return name;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return name + " " + phone;
    }
}
