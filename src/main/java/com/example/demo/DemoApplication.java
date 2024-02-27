package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class DemoApplication {

	private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(UserRepository repository) {
		return (args) -> {
			// save a few users
			repository.save(new User("Jack", "Bauer", 42));
			repository.save(new User("Chloe", "O'Brian", 35));
			repository.save(new User("Kim", "Bauer", 37));
			repository.save(new User("David", "Palmer", 45));
			repository.save(new User("Michelle", "Dessler", 28));

			// fetch all users
			log.info("Users found with findAll():");
			log.info("-------------------------------");

			repository.findAll().forEach(user -> {
				log.info(user.toString());
			});

			log.info("");

			// fetch an individual customer by ID
			User user = repository.findById(1L);

			log.info("User found with findById(1L):");
			log.info("--------------------------------");
			log.info(user.toString());
			log.info("");

			// fetch customers by first name
			log.info("Customer found with findByFirstName('Bauer'):");
			log.info("--------------------------------------------");

			repository.findByFirstName("Bauer").forEach(bauer -> {
				log.info(bauer.toString());
			});

			log.info("");
		};
	}


}
