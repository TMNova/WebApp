package ru.lanit.util;

import ru.lanit.Abstract.Address;
import ru.lanit.Abstract.Person;
import ru.lanit.entity.EntityAddress;
import ru.lanit.entity.EntityPerson;

import java.util.ArrayList;
import java.util.List;

public class GeneralUtils {
    public static List<Person> toPersonList(List<EntityPerson> entityList) {
        List<Person> personList = new ArrayList<>();
        for (EntityPerson entityPerson: entityList) {
            String surname = entityPerson.getSurname();
            String name = entityPerson.getName();
            String patronymic = entityPerson.getPatronymic();

            Person person = new Person(surname, name, patronymic);

            personList.add(person);
        }

        return personList;
    }
}
