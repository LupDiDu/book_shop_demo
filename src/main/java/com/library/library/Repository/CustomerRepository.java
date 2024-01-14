package com.library.library.Repository;

import com.library.library.Users.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("SELECT s FROM Customer s WHERE s.email = ?1")
    List<Customer> findCustomerByEmail(String email);

//    @Query("SELECT s FROM Customer s WHERE s.email = ?1")
//    Optional<Customer> findCustomerByEmail(String email);
}
