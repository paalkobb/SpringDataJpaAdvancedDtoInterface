package com.example.demo.repository;

import com.example.demo.dto.PersonView;
import com.example.demo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

/*******************************/
/** Different findAll queries */
/*******************************/
//  Derived query
//  Finds all Person entities and associated entities
//  Lazyloads the @ManyToMany associations
    List<Person> findAll();

//  Custom Query
//  Find all Person entities and associated entities
//  Fetches the @ManyToMany associations, instead of lazy loading
//  In my opinion, this would be the preferred choice for this scenario, where you want the whole entity
    @Query("FROM Person p join fetch p.address a")
    List<Person> findAllCustom();

//  Custom native query
//  Find all Person entities and associated entities
//  LazyLoads the @ManyToMany associations
    @Query(value = "SELECT * FROM PERSON p", nativeQuery = true)
    List<Person> findAllNativeCustom();

//  Custom JPQL DTO Interface projection
//  Find all Person entities and associated entities
//  Must use 'join' instead of 'join fetch'
//  Joins the @ManyToMany associations, instead og lazy loading
//  Will create a proxy at runtime
    @Query(value = "SELECT p.id as id, p.firstName as firstName, p.lastName as lastName, p.age as age, " +
            "address.id as addressId, address.address as streetName, address.letter as addressLetter, " +
            "address.number as addressNumber, address.zipCode as addressZipCode, address.city as addressCity " +
            "FROM Person p " +
            "join p.address address")
    List<PersonView> findAllCustomDtoInterface();


/**************************************/
/** Different findByFirstName queries */
/**************************************/

//  Derived query
//  Finds all Person entities and associated entities where firstName equals the firstName argument
//  Lazyloads the @ManyToMany associations
    List<Person> findByFirstName(String firstName);

//  Custom Query
//  Find all Person entities and associated entities where firstName equals the firstName argument
//  Fetches the @ManyToMany associations, instead of lazy loading
//  In my opinion, this would be the preferred choice for this scenario, where you want the whole entity
    @Query("FROM Person p join fetch p.address a WHERE p.firstName = :firstName")
    List<Person> findByFirstNameCustom(@Param("firstName") String firstName);

//  Custom native query
//  Find all Person entities and associated entities
//  LazyLoads the @ManyToMany associations
    @Query(value = "SELECT * FROM PERSON p WHERE p.firstName = :firstName", nativeQuery = true)
    List<Person> findByFirstNameNativeCustom(@Param("firstName") String firstName);


//  Custom JPQL DTO Interface Projection
//  Find all Person entities and associated entities where firstName equals the firstName argument
//  Must use 'join' instead of 'join fetch'
//  Joins the @ManyToMany associations, instead og lazy loading
//  Will create a proxy at runtime
    @Query(value = "SELECT p.id as id, p.firstName as firstName, p.lastName as lastName, p.age as age, " +
            "address.id as addressId, address.address as streetName, address.letter as addressLetter, " +
            "address.number as addressNumber, address.zipCode as addressZipCode, address.city as addressCity " +
            "FROM Person p " +
            "join p.address address " +
            "WHERE p.firstName = :firstName")
    List<PersonView> findByFirstNameDto(@Param("firstName") String firstName);

}
