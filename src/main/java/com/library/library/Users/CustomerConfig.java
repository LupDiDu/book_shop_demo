package com.library.library.Users;

import com.library.library.Repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CustomerConfig {
    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository repository){
        return args -> {
            Customer Alex = new Customer("Alex", "alex123@google.com", "alex");
            Customer Max = new Customer("Max", "max222@google.com", "max");
            repository.saveAll(List.of(Alex, Max));
        };
    }
}
