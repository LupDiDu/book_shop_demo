package com.library.library.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addUser")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getCustomer(){
        return customerService.getCustomer();
    }
    @PostMapping
    public void registryNewCustomer(@RequestBody Customer customer){
        customerService.addNewCustomer(customer);
    }

}
