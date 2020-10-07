package com.example.demo.module;


import javax.persistence.*;

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

}
