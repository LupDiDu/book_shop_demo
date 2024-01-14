package com.library.library;

import com.library.library.Books.Author;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Calendar;
import java.util.GregorianCalendar;


@SpringBootApplication
public class LibraryApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}



//	@GetMapping("/home")
//	public String hello(){
//		Calendar dateOfBirth = new GregorianCalendar(1926, Calendar.JANUARY , 25);
//		Calendar dateOfDeath = new GregorianCalendar(1998, Calendar.NOVEMBER  , 25);
//
//		Author author = new Author("Anton", "Chekhov", dateOfBirth, dateOfDeath);
//
//		return "home";
//	}

}
