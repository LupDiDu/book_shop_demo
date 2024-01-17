package com.library.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@ComponentScan
public class LibraryApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}
}
