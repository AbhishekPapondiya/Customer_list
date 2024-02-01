package com.example.customerlist;

import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.example.customerlist.customer.Customer;
import com.example.customerlist.customer.CustomerRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class CostomerRepositoryTest {
   @Autowired private CustomerRepository repo;
   
   @Test
   public void testAddNew() {
       Customer customer = new Customer();
       customer.setFirstName("Abhisehk");
       customer.setLastName("Papondiya");
       customer.setAddress("Noida");
       customer.setCity("Noida");
       customer.setState("u.p");
       customer.setEmail("abhishek@gmail.com");
       customer.setPhone("9923475852");

       Customer savedUser = repo.save(customer);

       Assertions.assertThat(savedUser).isNotNull();
       Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
   }
}
