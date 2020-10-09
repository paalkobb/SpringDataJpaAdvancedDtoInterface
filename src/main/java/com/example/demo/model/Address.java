package com.example.demo.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String streetName;
    Integer number;
    String letter;
    Integer zipCode;
    String city;

    @ManyToMany(mappedBy = "address")
    Set<Person> inhabitant = new HashSet<>();

    public Address(){}

    public Address(String streetName, Integer zipCode, String city){
        this(streetName, null, null, zipCode,city);
    }

    public Address(String streetName, Integer number, String letter, Integer zipCode, String city){
        this.streetName = streetName;
        this.number = number;
        this.letter = letter;
        this.zipCode = zipCode;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public Integer getZipCode() {
        return zipCode;
    }


    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Set<Person> getInhabitant() {
        return inhabitant;
    }

    public void setInhabitant(Set<Person> inhabitant) {
        this.inhabitant = inhabitant;
    }

    public void addInhabitant(Person inhabitant){
        this.inhabitant.add(inhabitant);
        inhabitant.getAddress().add(this);
    }

    public void removeInhabitant(Person inhabitant){
        this.inhabitant.remove(inhabitant);
        inhabitant.getAddress().remove(this);
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", address='" + streetName + '\'' +
                ", number=" + number +
                ", letter='" + letter + '\'' +
                ", zipCode=" + zipCode +
                ", city='" + city + '\'' +
                '}';
    }

}
