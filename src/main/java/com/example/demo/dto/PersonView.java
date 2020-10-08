package com.example.demo.dto;

public interface PersonView {

    Long getId();

    String getFirstName();

    String getLastName();

    Integer getAge();

    Long getAddressId();

    String getStreetName();

    Integer getAddressNumber();

    String getAddressLetter();

    Integer getAddressZipCode();

    String getAddressCity();

    AddressViewNested getAddress();

    interface AddressViewNested {

        Long getId();

        String getAddress();

        Integer getNumber();

        String getLetter();

        Integer getZipCode();

        String getCity();

        // Get the inhabitant?

    }


}
