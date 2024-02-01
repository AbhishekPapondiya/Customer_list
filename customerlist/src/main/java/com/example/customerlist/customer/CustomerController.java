package com.example.customerlist.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ch.qos.logback.core.model.Model;


@Controller
public class CustomerController {
    @Autowired private CustomerService service;
    
    @GetMapping("/customer")
    public String showCustomerList(Model model){
        List<Customer> customList=service.listAll();
        ((RedirectAttributes) model).addAttribute("customerList", customList);
        
        return "customer";
    }

    @GetMapping("/customer/new")
    public String showNewForm(Model model) {
        ((RedirectAttributes) model).addAttribute("user", new Customer());
        ((RedirectAttributes) model).addAttribute("pageTitle", "Add New customer");
        return "cutomer_add";
    }
    
    @PostMapping("/customer/save")
    public String saveUser(Customer customer, RedirectAttributes ra) {
        service.save(customer);
        ra.addFlashAttribute("message", "The customer has been saved successfully.");
        return "redirect:/customer";
    }

     @GetMapping("/customer/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model, RedirectAttributes ra) {
        try {
            Customer customer = service.get(id);
            ((RedirectAttributes) model).addAttribute("customer", customer);
            ((RedirectAttributes) model).addAttribute("pageTitle", "Edit customer (ID: " + id + ")");

            return "customer_add";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/costomer";
        }
    }
    @GetMapping("/customer/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id, RedirectAttributes ra) throws UserNotFoundException {
        service.delete(id);
        ra.addFlashAttribute("message", "The customer ID " + id + " has been deleted.");
        return "redirect:/customer";
    }
    @GetMapping("/customer/search")
    public ResponseEntity<List<Customer>> getAllCustomers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "") String search) {
        List<Customer> customers = service.getAllCustomers(page, size, sort, search);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Customer customer = service.getCustomerById(id);

        if (customer != null) {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
