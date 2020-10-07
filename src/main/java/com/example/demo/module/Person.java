package com.example.demo.module;

import javax.persistence.*;
import java.util.Collection;


@Entity
@Table(name="Person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String firstName;
    String lastName;
    Integer age;

    Address address;

    Collection<Person> friends;

    public Person(){

    }

    public Person(String firstName, String lastName){
        this(firstName, lastName, null, null, null);

    }

    public Person(String firstName, String lastName, Integer age, Address address, Collection<Person> friends){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.address = address;
        this.friends = friends;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Collection<Person> getFriends() {
        return friends;
    }

    public void setFriends(Collection<Person> friends) {
        this.friends = friends;
    }


    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", address=" + address +
                ", friends=" + friends +
                '}';
    }


}
