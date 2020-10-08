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

    String address;
    Integer number;
    String letter;
    Integer zipCode;
    String city;

    @ManyToMany(mappedBy = "address")
    Set<Person> inhabitant = new HashSet<>();

    public Address(){}

    public Address(String address, Integer zipCode, String city){
        this(address, null, null, zipCode,city);
    }

    public Address(String address, Integer number, String letter, Integer zipCode, String city){
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
                ", address='" + address + '\'' +
                ", number=" + number +
                ", letter='" + letter + '\'' +
                ", zipCode=" + zipCode +
                ", city='" + city + '\'' +
                '}';
    }

}
