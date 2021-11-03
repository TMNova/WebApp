package ru.lanit.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.lanit.Abstract.Address;
import ru.lanit.Abstract.Person;
import ru.lanit.entity.EntityAddress;
import ru.lanit.entity.EntityPerson;
import ru.lanit.entity.HibernateUtil;
import ru.lanit.util.GeneralUtils;


import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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

        EntityPerson entityPerson = new EntityPerson();
        EntityAddress entityAddress = new EntityAddress();

        int addressId = getAddressId(address);

        if (addressId != 0) {
            entityAddress.setId(addressId);
        }
        entityAddress.setCity(address.getCity());
        entityAddress.setStreet(address.getStreet());

        entityPerson.setSurname(person.getSurname());
        entityPerson.setName(person.getName());
        entityPerson.setPatronymic(person.getPatronymic());
        entityPerson.setAddress(entityAddress);

        session.save(entityPerson);

        session.getTransaction().commit();
    }

    @Override
    public List<EntityAddress> getAllAddresses() {
        Session session = sessionFactory.openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(EntityAddress.class);
        Root<EntityAddress> root = cq.from(EntityAddress.class);

        cq.select(root);

        Query query = session.createQuery(cq);

        List<EntityAddress> entityAddresses = query.getResultList();

        session.close();

        return entityAddresses;
    }

    @Override
    public List<Person> getAllPersons() {
        Session session = sessionFactory.openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(EntityPerson.class);
        Root<EntityPerson> root = cq.from(EntityPerson.class);

        cq.select(root);

        Query query = session.createQuery(cq);

        List<EntityPerson> entityPersons = query.getResultList();

        session.close();

        List<Person> persons = GeneralUtils.toPersonList(entityPersons);

        return persons;

    }

    public int getAddressId(Address address) {
        Session session = sessionFactory.openSession();
        int addressId = 0;

        Query queryCity = session.createQuery("from EntityAddress" +
                " where city = :paramName");
        queryCity.setParameter("paramName", address.getCity());
        List<EntityAddress> list = queryCity.getResultList();

        if (!(list.isEmpty())) {
            for(EntityAddress entity : list) {
                if (entity.getStreet().equals(address.getStreet())) addressId = entity.getId();
            }
        }

        return addressId;
    }

}
