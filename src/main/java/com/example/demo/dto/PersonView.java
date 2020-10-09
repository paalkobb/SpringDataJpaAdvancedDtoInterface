package com.example.demo.dto;

import com.example.demo.model.Person;

import java.util.Set;

public interface PersonView {

    Long getId();

    String getFirstName();

    String getLastName();

    Integer getAge();

    AddressViewNested getAddress();

    InhabitantViewNested getInhabitant();

    interface AddressViewNested {

        Long getId();

        String getStreetName();

        Integer getNumber();

        String getLetter();

        Integer getZipCode();

        String getCity();

    }

    interface InhabitantViewNested{

        Long getId();

    }


}
