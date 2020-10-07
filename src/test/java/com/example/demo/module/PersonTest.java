package com.example.demo.module;

import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.PersonAddressDtoInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PersonTest {

    private static final Logger log = LogManager.getLogger(PersonTest.class);

    @Autowired
    PersonRepository pRepo;
    @Autowired
    AddressRepository aRepo;


    private void init(){
        for (int i = 0; i < 10; i++){
            Address a = new Address("gøteborggata", 51, null, 0566, "Oslo");
            Person p = new Person("paal", "kobbeltvedt", 26);
            aRepo.save(a);
            pRepo.save(p);
            a.addInhabitant(p);
        }
    }

    @Test
    void injectedComponentsAreNotNull(){
        Assert.assertNotNull(pRepo);
        Assert.assertNotNull(aRepo);
    }


    @Test
    void findAll(){
        this.init();
        List<Person> p = pRepo.findAll();
        for (Person pp : p ){
            log.info(pp.toString());
        }
    }


    @Test
    void manyToManyCheck(){
        this.init();
        List<Person> p = pRepo.getIds();
        for(Person pp : p){
            log.info(pp.toString());
            log.info(pp.getAddress());
        }
    }


    @Test
    void getIdAndAddress(){
        this.init();
        List<PersonAddressDtoInterface> p = pRepo.getIdAndAddress();
        for (PersonAddressDtoInterface pp : p){
            log.info(pp.getId());
            log.info(pp.getAddress());
            log.info(pp.getAddress().getAddress());
        }
    }
}