package com.library.library.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    public final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getCustomer() {
        return customerRepository.findAll();
    }

    public void addNewCustomer(Customer customer) {
        Optional<Customer> customerByEmail = customerRepository.findCustomerByEmail(customer.getEmail());
        if (customerByEmail.isPresent()) {
            throw new IllegalStateException("email taken");
        } else {
            customerRepository.save(customer);
        }
    }
}
