package com.example.demo.service;

import com.example.demo.dto.PersonView;
import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public List<Person> findAll(){
        List<Person> p = personRepository.findAll();
        return p;
    }

    public List<Person> findAllCustom(){
        List<Person> p = personRepository.findAllCustom();
        return p;
    }

    public List<Person> findAllNativeCusom(){
        List<Person> p = personRepository.findAllNativeCustom();
        return p;
    }

    public List<PersonView> findAllCustomDtoInterface(){
        List<PersonView> p = personRepository.findAllCustomDtoInterface();
        return p;
    }


    public List<Person> findByFirstName(){
        List<Person> p = personRepository.findByFirstName("firstName");
        return p;
    }


    public List<Person> findByFirstNameCustom(){
        List<Person> p = personRepository.findByFirstNameCustom("firstName");
        return p;
    }

    public List<Person> findByFirstNameNativeCustom(){
        List<Person> p = personRepository.findByFirstNameNativeCustom("firstName");
        return p;
    }

    public List<PersonView> findByFirstNameDtoInterface(){
        List<PersonView> p = personRepository.findByFirstNameDto("firstName");
        return p;
    }


}
