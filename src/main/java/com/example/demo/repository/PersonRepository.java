package com.example.demo.repository;

import com.example.demo.module.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findAll();

    @Query(value = "select * FROM PERSON p", nativeQuery = true)
    List<Person> getIds();

    @Query("SELECT p.id as id, a as address FROM Person p left join p.address as a")
    List<PersonAddressDtoInterface> getIdAndAddress();
}
