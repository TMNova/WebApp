package ru.lanit.repository.dto;

import java.util.List;

public class Address {

    private int id;
    private String city;
    private String street;
    private List<Person> personList;

    public Address(String city, String street) {
        this.city = city;
        this.street = street;
    }

    public Address(int id, String city, String street, List<Person> personList) {
        this.id = id;
        this.city = city;
        this.street = street;
        this.personList = personList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

}
