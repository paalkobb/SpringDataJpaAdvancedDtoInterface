package com.example.demo.service;

import com.example.demo.dto.PersonView;
import com.example.demo.model.Address;
import com.example.demo.model.Person;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.PersonRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.collection.internal.PersistentSet;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PersonServiceTest {

    private static final Logger log = LogManager.getLogger(PersonServiceTest.class);
    private static boolean setUpIsDone = false;

    @Autowired
    PersonRepository personRepository;
    @Autowired
    AddressRepository addressRepository;

    List<String> firstName = Arrays.asList("Paal","Per", "Knut", "Xin", "Jan", "Dan", "Manus", "Ove", "Dag", "John");
    List<String> lastName = Arrays.asList("Angeltveit", "Berntson", "Carlson", "Davidson", "Eson", "Fredrikson", "Geirson", "Person", "Knutson", "Oveson");

    @BeforeEach
    private void setUp(){
//      Only call init once
        if (setUpIsDone){
            return;
        }
        log.info("Startup...");
        this.populateDatabase();
        setUpIsDone = true;
    }

    @AfterEach
    private void tearDown(){
        log.info("Teardown...");
    }


//  Finn bedre måter å kjøre tester opp mot database på
    private void populateDatabase(){
        log.info("Populating the database...");
        for (int j = 0; j < 1; j++) {
            for (int i = 0; i < this.firstName.size(); i++) {
                Address a = new Address("Innspurten", 1, "C", 1234, "Oslo");
                Address a2 = new Address("Rolfsgate", 2, "A", 5000, "Oslo");
                Address a3 = new Address("Persgate", 3, "D", 4325, "Bergen");
                Person p = new Person(firstName.get(i), lastName.get(i), 10 + i);
                addressRepository.save(a);
                addressRepository.save(a2);
                addressRepository.save(a3);
                personRepository.save(p);
                a.addInhabitant(p);
                a2.addInhabitant(p);
                a3.addInhabitant(p);
            }
        }
    }

    private void assertPersonDataTypes(List<Person> p){
        for (Person pp : p){
//      Assert the each value is not null
            Assert.assertNotNull(pp.getAge());
            Assert.assertNotNull(pp.getAddress());
            Assert.assertNotNull(pp.getFirstName());
            Assert.assertNotNull(pp.getLastName());
            Assert.assertNotNull(pp.getId());
//      Assert the correct datatype for each value
            Assert.assertEquals(Integer.class, pp.getAge().getClass());
//      Notice that the return value is a PersistentSet, and not a Set
            Assert.assertEquals(PersistentSet.class, pp.getAddress().getClass());
            Assert.assertEquals(String.class, pp.getFirstName().getClass());
            Assert.assertEquals(String.class, pp.getLastName().getClass());
            Assert.assertEquals(Long.class, pp.getId().getClass());
        }
    }

    void logPersonResult(List<Person> p){
//   Log the result
        for (Person pp : p){
            log.info(pp.toString());
        }
    }


/*******************************/
/** Different findAll queries */
/*******************************/
    @Test
    @Rollback(false)
    @Order(1)
//  Derived query
//  Will lazyload the @manytomany address from Person
    void findAll() {
        log.info("Testing method findAll()...");
//      Call the repository method
        List<Person> p = personRepository.findAll();
//      Assert that the method does not return null value
//      when there is data in the database
        Assert.assertNotNull(p);
//      Assert that the size is bigger than 0
        Assert.assertTrue(p.size() > 0);
//      Assert that a List is returned
        Assert.assertEquals(ArrayList.class, p.getClass());
//      Assert the each value is not null
        this.assertPersonDataTypes(p);
        this.logPersonResult(p);
    }

    @Test
    @Rollback(false)
    @Order(2)
    void findAllCustom() {
        log.info("Testing method findAllCustom()...");
//      Call the repository method
        List<Person> p = personRepository.findAllCustom();
//      Assert that the method does not return null value
//      when there is data in the database
        Assert.assertNotNull(p);
//      Assert that the size is bigger than 0
        Assert.assertTrue(p.size() > 0);
//      Assert that a List is returned
        Assert.assertEquals(ArrayList.class, p.getClass());
        this.assertPersonDataTypes(p);
        this.logPersonResult(p);
    }

    @Test
    @Rollback(false)
    @Order(3)
    void findAllNativeCusom() {
        log.info("Testing method findAllNativeCustom()...");
//      Call the repository method
        List<Person> p = personRepository.findAllNativeCustom();
//      Assert that the method does not return null value
//      when there is data in the database
        Assert.assertNotNull(p);
//      Assert that the size is bigger than 0
        Assert.assertTrue(p.size() > 0);
//      Assert that a List is returned
        Assert.assertEquals(ArrayList.class, p.getClass());
        this.assertPersonDataTypes(p);
        this.logPersonResult(p);
    }


    @Test
    @Rollback(false)
    @Order(4)
    void findAllCustomDtoInterface() {
        log.info("Testing method findAllCustomDtoInterface()...");
//      Call the repository method
        List<PersonView> p = personRepository.findAllCustomDtoInterface();
//      Assert that the method does not return null value
//      when there is data in the database
        Assert.assertNotNull(p);
//      Assert that the size is bigger than 0
        Assert.assertTrue(p.size() > 0);
//      Assert that a List is returned
        Assert.assertEquals(ArrayList.class, p.getClass());
        for (PersonView pp : p){
            log.info(
                    "[id: '" + pp.getId() + "'\'' " +
                            "firstName: '" + pp.getFirstName() + "'\'' " +
                            "lastName: '" + pp.getLastName() + "'\'' " +
                            "age: '" + pp.getAge() + "'\'' " +
                            "addressId: '" + pp.getAddress().getId() + "'\'' " +
                            "streetName: '" + pp.getAddress().getStreetName() + "'\'' " +
                            "addressNumber: '" + pp.getAddress().getNumber() + "'\'' " +
                            "addressLetter: '" + pp.getAddress().getLetter() + "'\'' " +
                            "addressZipCode: '" + pp.getAddress().getZipCode() + "'\'' " +
                            "addressCity: '" + pp.getAddress().getCity() + "'\'' "
            );
        }
    }

/**************************************/
/** Different findByFirstName queries */
/**************************************/

    @Test
    @Rollback(false)
    @Order(5)
//  Derived query
//  Will lazyload the @manytomany address from Person
    void findByFirstName() {
        log.info("Testing method findByFirstName()...");
//      Call the repository method
        List<Person> p = personRepository.findByFirstName("Paal");
//      Assert that the method does not return null value
//      when there is data in the database
        Assert.assertNotNull(p);
//      Assert that the size is bigger than 0
        Assert.assertTrue(p.size() > 0);
//      Assert that a List is returned
        Assert.assertEquals(ArrayList.class, p.getClass());
//      Assert the each value is not null
        this.assertPersonDataTypes(p);
//      Log the result
        this.logPersonResult(p);
    }

    @Test
    @Rollback(false)
    @Order(6)
//  Custom query
//  Will fetch the @manytomany address from Person
    void findByFirstNameCustom() {
        log.info("Testing method findByFirstNameCustom()...");
//      Call the repository method
        List<Person> p = personRepository.findByFirstNameCustom("Paal");
//      Assert that the method does not return null value
//      when there is data in the database
        Assert.assertNotNull(p);
//      Assert that the size is bigger than 0
        Assert.assertTrue(p.size() > 0);
//      Assert that a List is returned
        Assert.assertEquals(ArrayList.class, p.getClass());
//      Assert the each value is not null
        this.assertPersonDataTypes(p);
//      Log the result
        this.logPersonResult(p);
    }

    @Test
    @Rollback(false)
    @Order(7)
    void findByFirstNameNativeCustom() {
        log.info("Testing method findByFirstNameNativeCustom()...");
//      Call the repository method
        List<Person> p = personRepository.findByFirstNameNativeCustom("Paal");
//      Assert that the method does not return null value
//      when there is data in the database
        Assert.assertNotNull(p);
//      Assert that the size is bigger than 0
        Assert.assertTrue(p.size() > 0);
//      Assert that a List is returned
        Assert.assertEquals(ArrayList.class, p.getClass());
//      Assert the each value is not null
        this.assertPersonDataTypes(p);
//      Log the result
        this.logPersonResult(p);
    }


    @Test
    @Rollback(false)
    @Order(8)
    void findByFirstNameDto() {
        log.info("Testing method findByFirstNameDto()...");
//      Call the repository method
        List<PersonView> p = personRepository.findByFirstNameDto("Paal");
//      Assert that the method does not return null value
//      when there is data in the database
        Assert.assertNotNull(p);
//      Assert that the size is bigger than 0
        Assert.assertTrue(p.size() > 0);
//      Assert that a List is returned
        Assert.assertEquals(ArrayList.class, p.getClass());
        for (PersonView pp : p){
            log.info(
                    "[id: '" + pp.getId() + "'\'' " +
                            "firstName: '" + pp.getFirstName() + "'\'' " +
                            "lastName: '" + pp.getLastName() + "'\'' " +
                            "age: '" + pp.getAge() + "'\'' " +
                            "addressId: '" + pp.getAddress().getId() + "'\'' " +
                            "streetName: '" + pp.getAddress().getStreetName() + "'\'' " +
                            "addressNumber: '" + pp.getAddress().getNumber() + "'\'' " +
                            "addressLetter: '" + pp.getAddress().getLetter() + "'\'' " +
                            "addressZipCode: '" + pp.getAddress().getZipCode() + "'\'' " +
                            "addressCity: '" + pp.getAddress().getCity() + "'\'' "
            );
        }

    }

    @Test
    @Order(9)
    void findByFirstNameDtoWithInhabitants() {
        log.info("Testing method findByFirstNameDtoWithInhabitants()...");
//      Call the repository method
        List<PersonView> p = personRepository.findByFirstNameDtoWithInhabitants("Paal");
//      Assert that the method does not return null value
//      when there is data in the database
        Assert.assertNotNull(p);
//      Assert that the size is bigger than 0
        Assert.assertTrue(p.size() > 0);
//      Assert that a List is returned
        Assert.assertEquals(ArrayList.class, p.getClass());
        for (PersonView pp : p){
            log.info(
                    "[id: '" + pp.getId() + "'\'' " +
                            "firstName: '" + pp.getFirstName() + "'\'' " +
                            "lastName: '" + pp.getLastName() + "'\'' " +
                            "age: '" + pp.getAge() + "'\'' " +
                            "addressId: '" + pp.getAddress().getId() + "'\'' " +
                            "streetName: '" + pp.getAddress().getStreetName() + "'\'' " +
                            "addressNumber: '" + pp.getAddress().getNumber() + "'\'' " +
                            "addressLetter: '" + pp.getAddress().getLetter() + "'\'' " +
                            "addressZipCode: '" + pp.getAddress().getZipCode() + "'\'' " +
                            "addressCity: '" + pp.getAddress().getCity() + "'\'' " +
                            "addressInhabitans: '" + pp.getInhabitant().getId() + "'\''"
            );
        }
    }
}