package com.fdmgroup.project_gamesdatabase_spring.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "developer_gen")
    @SequenceGenerator(name = "developer_gen", sequenceName = "DEVELOPER_SEQ", allocationSize = 1)
    private long id;

    @Column
    private String name;

    @Column
    private String address;

    public Developer(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Developer() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Developer developer = (Developer) o;
        return id == developer.id && name.equals(developer.name) && address.equals(developer.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address);
    }


    @Override
    public String toString() {
        return "Developer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
