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
        if(!customerByEmail.isEmpty()) {
            throw new IllegalStateException("Email taken");
        }
         else {
            customerRepository.save(customer);
        }
    }

    public void deleteCustomerById(Customer customer) {
        boolean exists = customerRepository.existsById(customer.getId());
        if (exists){
            customerRepository.deleteById(customer.getId());
        }
        else {
            throw new IllegalStateException("This customer doesn't exists.");
        }
    }

    @Transactional
    public void updateCustomer(Long customerId, String name, String  email, String password) {
       Customer customer = customerRepository.findById(customerId)
               .orElseThrow(()-> new IllegalStateException("Customer with id: " + customerId + " doesn't exists."));

       if(name != null && !name.isEmpty()){
           if(!customer.getName().equals(name)){
               customer.setName(name);
           }
       }

       if(password != null && !password.isEmpty()){
           if(!customer.getPassword().equals(password)){
               customer.setPassword(password);
           }
       }

        if(email != null && !email.isEmpty() && Objects.equals(customer.getEmail(), email)){
            if(!customer.getEmail().equals(email)){
                Optional<Customer> customerOptional = customerRepository.findById(customerId);
                if (customerOptional.isPresent()){
                    throw new IllegalStateException("This email has already taken.");
                }
                customer.setEmail(email);
            }
        }

    }
}
