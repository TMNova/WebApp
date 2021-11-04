package ru.lanit.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.lanit.Abstract.Address;
import ru.lanit.Abstract.Person;
import ru.lanit.entity.AddressEntity;
import ru.lanit.entity.PersonEntity;
import ru.lanit.entity.HibernateUtil;


import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class HibernatePostgresRepository implements Repository {
    private SessionFactory sessionFactory;

    public HibernatePostgresRepository() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public void save(Person person, Address address) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        PersonEntity personEntity = new PersonEntity();
        AddressEntity addressEntity = new AddressEntity();

        int addressId = getAddressId(address);

        if (addressId != 0) {
            addressEntity.setId(addressId);
        }
        addressEntity.setCity(address.getCity());
        addressEntity.setStreet(address.getStreet());

        personEntity.setSurname(person.getSurname());
        personEntity.setName(person.getName());
        personEntity.setPatronymic(person.getPatronymic());
        personEntity.setAddress(addressEntity);

        session.save(personEntity);

        session.getTransaction().commit();
    }

    @Override
    public List<AddressEntity> getAllAddresses() {
        Session session = sessionFactory.openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(AddressEntity.class);
        Root<AddressEntity> root = cq.from(AddressEntity.class);

        cq.select(root);

        Query query = session.createQuery(cq);

        List<AddressEntity> addressEntities = query.getResultList();

        session.close();

        return addressEntities;
    }

    @Override
    public List<Person> getAllPersons() {
        Session session = sessionFactory.openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(PersonEntity.class);
        Root<PersonEntity> root = cq.from(PersonEntity.class);
        root.fetch("personList", JoinType.LEFT);

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

    public static List<Person> toPersonList(List<PersonEntity> entityList) {
        List<Person> personList = new ArrayList<>();
        for (PersonEntity personEntity : entityList) {
            String surname = personEntity.getSurname();
            String name = personEntity.getName();
            String patronymic = personEntity.getPatronymic();

            Person person = new Person(surname, name, patronymic);

            personList.add(person);
        }

        return personList;
    }

}
