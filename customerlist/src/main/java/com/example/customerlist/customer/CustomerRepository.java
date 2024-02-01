package com.example.customerlist.customer;

//import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer,Long>{

    Long countById(Long id);


    Page<Customer> searchCustomers(String search, PageRequest pageable);

    Page<Customer> findAll(PageRequest pageable);
    
}
