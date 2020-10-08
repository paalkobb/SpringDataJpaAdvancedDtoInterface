package com.example.demo.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="Person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private Integer age;

    @ManyToMany
    @JoinTable(name="PersonAddress",
        joinColumns = {@JoinColumn(name = "fk_person")},
        inverseJoinColumns = {@JoinColumn(name = "fk_address")}
    )
    private Set<Address> address = new HashSet<>();

    public Person(){
    }

    public Person(String firstName, String lastName){
        this(firstName, lastName, null);

    }

    public Person(String firstName, String lastName, Integer age){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;

    }

    public Long getId(){
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Set<Address> getAddress() {
        return address;
    }

    public void setAddress(Set<Address> address) {
        this.address = address;
    }

    public void addAddress(Address address){
        this.address.add(address);
    }


    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                '}';
    }


}
