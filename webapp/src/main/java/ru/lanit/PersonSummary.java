package ru.lanit;

import org.springframework.stereotype.Component;
import ru.lanit.repository.dto.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonSummary {
    private String surname;
    private String name;
    private String patronymic;
    private String city;
    private String street;

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
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

    public static List<PersonSummary> toPersonSummary(List<Person> persons) {
        List<PersonSummary> personSummaries = new ArrayList<>();

        for (Person person : persons) {
            PersonSummary personSummary = new PersonSummary();
            personSummary.setSurname(person.getSurname());
            personSummary.setName(person.getName());
            personSummary.setPatronymic(person.getPatronymic());
            personSummary.setCity(person.getAddress().getCity());
            personSummary.setStreet(person.getAddress().getStreet());
            personSummaries.add(personSummary);
        }

        return personSummaries;
    }
}
