package com.library.library.Users;

import com.library.library.Repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

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

    public String addNewCustomer(Customer customer) {
        List<Customer> customerByEmail = customerRepository.findCustomerByEmail(customer.getEmail());
        try {
            if (customer.getName().isEmpty()) {
                return "Name cannot be empty.";
            }
            else if (!Pattern.matches("^[a-zA-Z]+(?:[\\\\s-][a-zA-Z]+)*$", customer.getName())) {
                return "Name is not valid.";
            }
            else if (customer.getEmail().isEmpty()) {
                return "Email cannot be empty.";
            }
            else if (!Pattern.matches("^(?=.*[a-zA-Z0-9])([a-zA-Z0-9_.+-])+@([a-zA-Z0-9-])+\\.([a-zA-Z0-9-.])+$",
                    customer.getEmail())){
                return "Email is not valid.";
            }
            else if (!customerByEmail.isEmpty() && Objects.equals(customer.getEmail(),
                    customerByEmail.getFirst().getEmail())) {
                return "This email has already been taken.";
            }
            else if  (customer.getPassword().isEmpty() || customer.getPassword() == null){
                return "Password cannot be empty.";
            }
            //Password must contain at least one number and one letter
            else if ( !Pattern.matches("^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{6,}$", customer.getPassword())){
                return "Password is too short or/and must contain at least one number and one letter.";
            }
            } catch (Exception e) {
                return e.toString();
            }
            return "Success";
        }

    public boolean deleteCustomerById(Customer customer) {
        boolean exists = customerRepository.existsById(customer.getId());
        if (exists){
            customerRepository.deleteById(customer.getId());
            return true;
        }
        return false;
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
