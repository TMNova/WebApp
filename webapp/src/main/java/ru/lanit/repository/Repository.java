package ru.lanit.repository;

import ru.lanit.Abstract.Address;
import ru.lanit.Abstract.Person;
import ru.lanit.entity.AddressEntity;

import java.util.List;

public interface Repository {
    public void save(Person person, Address address);
    public List<Person> getAllPersons();
    public List<AddressEntity> getAllAddresses();
}
