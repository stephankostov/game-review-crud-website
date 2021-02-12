package com.fdmgroup.project_gamesdatabase.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "developer_gen")
    @SequenceGenerator(name = "developer_gen", sequenceName = "DEVELOPER_SEQ", allocationSize = 1)
    private long developerId;

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

    public long getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(long id) {
        this.developerId = id;
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
        return developerId == developer.developerId && name.equals(developer.name) && address.equals(developer.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(developerId, name, address);
    }


    @Override
    public String toString() {
        return "Developer{" +
                "id=" + developerId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
