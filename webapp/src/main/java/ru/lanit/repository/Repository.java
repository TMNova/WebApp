package ru.lanit.repository;

import ru.lanit.repository.dto.Address;
import ru.lanit.repository.dto.Person;
import ru.lanit.repository.dto.SummaryPerson;
import ru.lanit.repository.entity.AddressEntity;

import java.util.List;

public interface Repository {
    public void save(SummaryPerson person);
    public List<Person> getAllPersons();
    public List<Address> getAllAddresses();
}
