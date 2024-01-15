package com.library.library.Users;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addCustomer")
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

//    @DeleteMapping(path = "customerId")
//    public void deleteCustomer(@PathVariable("customerId") Long customerId){
//        customerService.deleteCustomerById(customerId);
//    }

    @PutMapping(path = "customerId")
    public void updateCustomer(@PathVariable("customerId") Long customerId,
                               @RequestParam(required = false) String name,
                               @RequestParam(required = false) String email,
                               @RequestParam(required = false) String password)
{
        customerService.updateCustomer(customerId, name, email, password);
    }

}
