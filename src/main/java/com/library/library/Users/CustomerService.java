package com.library.library.Users;

import com.library.library.Repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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
        List<Customer> customerByEmail = customerRepository.findCustomerByEmail(customer.getEmail());
        if (Objects.equals(customerByEmail.getFirst().getEmail(), customer.getEmail())) {
            throw new IllegalStateException("email taken");
        } else {
            customerRepository.save(customer);
        }
    }

    public void deleteCustomerById(Long customerId) {
        boolean exists = customerRepository.existsById(customerId);
        if (exists){
            customerRepository.deleteById(customerId);
        }
        else {
            throw new IllegalStateException("Customer with id: " + customerId + " doesn't exists.");
        }
    }

    @Transactional
    public void updateCustomer(Long customerId, String name, String  email) {
       Customer customer = customerRepository.findById(customerId)
               .orElseThrow(()-> new IllegalStateException("Customer with id: " + customerId + " doesn't exists."));

       if(name != null && name.length() > 0 && Objects.equals(customer.getName(), name)){
           customer.setName(name);
       }

        if(email != null && email.length() > 0 && Objects.equals(customer.getEmail(), email)){
            Optional<Customer> customerOptional = customerRepository.findById(customerId);
            if (customerOptional.isPresent()){
                throw new IllegalStateException("This email has already taken.");
            }
            customer.setEmail(email);
        }

    }
}
