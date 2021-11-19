package ru.lanit.repository;

import ru.lanit.repository.dto.Address;
import ru.lanit.repository.dto.Person;
import ru.lanit.PersonSummary;

import java.util.List;

public interface Repository {
    public void save(PersonSummary person);
    public List<Person> getAllPersons();
    public List<Address> getAllAddresses();
}
