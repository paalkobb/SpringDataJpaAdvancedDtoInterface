package com.example.demo.repository;

import com.example.demo.module.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long>{

    List<Address> findAll();

}
