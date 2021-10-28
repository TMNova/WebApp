package ru.lanit.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.lanit.Person;
import ru.lanit.entity.EntityPerson;
import ru.lanit.entity.HibernateUtil;


import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class HibernatePostgresRepository implements Repository {
    private SessionFactory sessionFactory;

    public HibernatePostgresRepository() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public void save(Person person) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        EntityPerson entityPerson = new EntityPerson();
        entityPerson.setSurname(person.getSurname());
        entityPerson.setName(person.getName());
        entityPerson.setPatronymic(person.getPatronymic());

        session.save(entityPerson);

        session.getTransaction().commit();
    }

    @Override
    public List<Person> getAll() {
        Session session = sessionFactory.openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(EntityPerson.class);
        Root<EntityPerson> root = cq.from(EntityPerson.class);

        cq.select(root);

        Query query = session.createQuery(cq);

        List<EntityPerson> entityPersons = query.getResultList();

        session.close();

        List<Person> personList = new ArrayList<>();
        for (EntityPerson entityPerson: entityPersons) {
            String surname = entityPerson.getSurname();
            String name = entityPerson.getName();
            String patronymic = entityPerson.getPatronymic();

            Person person = new Person(surname, name, patronymic);

            personList.add(person);
        }

        return personList;
    }
}
