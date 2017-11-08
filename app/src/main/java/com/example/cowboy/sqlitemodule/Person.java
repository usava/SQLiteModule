package com.example.cowboy.sqlitemodule;

/**
 * Created by Cowboy on 30.10.2017.
 */

public class Person {
    public String name = null;
    public String surname = null;
    public String phone = null;
    public String email = null;

    public String getName() {
        return name;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String skype = null;

    public Person(String name, String surname, String phone, String email, String skype) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.skype = skype;
        this.surname = surname;
    }

    public Person(){};

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", skype='" + skype + '\'' +
                '}';
    }
}
