package ru.lanit.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.lanit.repository.dto.Address;
import ru.lanit.repository.dto.Person;
import ru.lanit.PersonSummary;
import ru.lanit.repository.entity.AddressEntity;
import ru.lanit.repository.entity.PersonEntity;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Component
public class HibernatePostgresRepository implements Repository {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(PersonSummary person) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        PersonEntity personEntity = new PersonEntity();
        AddressEntity addressEntity = new AddressEntity();

        int addressId = getAddressId(new Address(person.getCity(), person.getStreet()));

        if (addressId != 0) {
            addressEntity.setId(addressId);
        }
        addressEntity.setCity(person.getCity());
        addressEntity.setStreet(person.getStreet());

        personEntity.setSurname(person.getSurname());
        personEntity.setName(person.getName());
        personEntity.setPatronymic(person.getPatronymic());
        personEntity.setAddress(addressEntity);

        session.save(personEntity);

        session.getTransaction().commit();
    }

    @Override
    public List<Address> getAllAddresses() {
        Session session = sessionFactory.openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(AddressEntity.class);
        Root<AddressEntity> root = cq.from(AddressEntity.class);
        root.fetch("personList", JoinType.LEFT);

        cq.select(root).distinct(true);

        Query query = session.createQuery(cq);

        List<AddressEntity> addressEntities = query.getResultList();

        session.close();

        List<Address> addresses = toAddressList(addressEntities);

        return addresses;
    }

    @Override
    public List<Person> getAllPersons() {
        Session session = sessionFactory.openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(PersonEntity.class);
        Root<PersonEntity> root = cq.from(PersonEntity.class);
        root.fetch("address", JoinType.LEFT);

        cq.select(root);

        Query query = session.createQuery(cq);

        List<PersonEntity> personEntities = query.getResultList();

        session.close();

        List<Person> persons = toPersonList(personEntities);

        return persons;

    }

    public int getAddressId(Address address) {
        Session session = sessionFactory.openSession();
        int addressId = 0;

        Query queryCity = session.createQuery("from AddressEntity" +
                " where city = :paramCity AND street = :paramStreet");
        queryCity.setParameter("paramCity", address.getCity());
        queryCity.setParameter("paramStreet", address.getStreet());
        List<AddressEntity> list = queryCity.getResultList();

        if (!(list.isEmpty())) {
            for(AddressEntity entity : list) {
                if (entity.getStreet().equals(address.getStreet())) addressId = entity.getId();
            }
        }

        return addressId;
    }

    private List<Person> toPersonList(List<PersonEntity> entityList) {
        List<Person> personList = new ArrayList<>();
        for (PersonEntity personEntity : entityList) {
            Address address = new Address();

            String surname = personEntity.getSurname();
            String name = personEntity.getName();
            String patronymic = personEntity.getPatronymic();
            address.setCity(personEntity.getAddress().getCity());
            address.setStreet(personEntity.getAddress().getStreet());

            Person person = new Person(surname, name, patronymic, address);

            personList.add(person);
        }

        return personList;
    }

    private List<Address> toAddressList(List<AddressEntity> entityList) {
        List<Address> addressList = new ArrayList<>();
        for(AddressEntity addressEntity : entityList) {
            int id = addressEntity.getId();
            String city = addressEntity.getCity();
            String street = addressEntity.getStreet();
            List<Person> personList = toPersonList(addressEntity.getPersonList());

            Address address = new Address(id, city, street, personList);

            addressList.add(address);
        }

        return addressList;
    }

}
