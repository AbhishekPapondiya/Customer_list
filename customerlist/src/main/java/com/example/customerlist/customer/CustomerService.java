package com.example.customerlist.customer;

import java.util.List;
import java.util.Optional;

//import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired private CustomerRepository repo;

    public List<Customer> listAll(){
        return (List<Customer>) repo.findAll();
    }

    public Customer get(Long id) throws UserNotFoundException{
        Optional<Customer> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new UserNotFoundException("Could not find any customer with ID " + id);
    }
    

    public void delete(Long id) throws UserNotFoundException{
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new UserNotFoundException("Could not find any customer with ID " + id);
        }
        repo.deleteById(id);
    }

    public void save(Customer customer) {
        repo.save(customer);
    }

   /*  @Override
    public List<Customer> getAllCustomers(int page, int size, String sort, String search)  {
        PageRequest pageable = PageRequest.of(page, size);

        if (sort != null && !sort.isEmpty()) {
            pageable = PageRequest.of(page, size, Sort.by(sort));
        }

        Page<Customer> customerPage;

        if (search != null && !search.isEmpty()) {
            // If search is provided, use a custom query method for searching
            customerPage = repo.searchCustomers(search, pageable);
        } else {
            // Otherwise, use the default findAll method with sorting
            customerPage = repo.findAll(pageable);
        }

        return customerPage.getContent();
    }*/

    public Customer getCustomerById(Long id) {
    
        return repo.findById(id).orElse(null);
    }

    public List<Customer> getAllCustomers(int page, int size, String sort, String search) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllCustomers'");
    }
}
