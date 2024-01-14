package com.library.library;

import com.library.library.Books.Author;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Calendar;
import java.util.GregorianCalendar;




// TODO: В докере не работает PostgresSQL



@SpringBootApplication
@RestController
public class LibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}
	@GetMapping
	public String hello(){
		Calendar dateOfBirth = new GregorianCalendar(1926, Calendar.JANUARY , 25);
		Calendar dateOfDeath = new GregorianCalendar(1998, Calendar.NOVEMBER  , 25);

		Author author = new Author("Anton", "Chekhov", dateOfBirth, dateOfDeath);

//		CustomerService bca = new CustomerService();
//		CustomerController abc = new CustomerController(bca);

//		return abc.getCustomer().toString();
		return "GENRE";
	}

}
