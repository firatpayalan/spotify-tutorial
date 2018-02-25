package com.firat.test.questiontwo;

import com.google.gson.Gson;

public class Source {
    private String name;
    private String surname;
    private String gsm;

    public String getName() {
        return name;
    }
    public Source()
    {
        this.name = new String();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGsm() {
        return gsm;
    }

    public void setGsm(String gsm) {
        this.gsm = gsm;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
