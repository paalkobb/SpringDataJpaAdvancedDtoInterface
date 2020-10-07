package com.example.demo.repository;

import com.example.demo.module.Address;

public interface PersonAddressDtoInterface {

    Long getId();

    String getFirstName();

    AddressView getAddress();

    interface AddressView{

        String getAddress();

    }

}
