package ru.lanit.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "address")
public class EntityAddress implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @OneToMany(mappedBy = "address",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<EntityPerson> personList;

    public EntityAddress() {

    }

    public List<EntityPerson> getPersonList() {
        return personList;
    }

    public void setPersonList(List<EntityPerson> personList) {
        this.personList = personList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
