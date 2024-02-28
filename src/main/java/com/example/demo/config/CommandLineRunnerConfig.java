package com.example.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.demo.DemoApplication;

import com.example.demo.repository.TodoRepository;
import com.example.demo.entity.TodoEntity;

@Configuration
public class CommandLineRunnerConfig {
    private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

    @Bean
	public CommandLineRunner demo(TodoRepository repository) {
		return (args) -> {
			// save a few users
			// repository.save(new TodoEntity("Todo1", "Description1"));
			// repository.save(new TodoEntity("Todo2", "Description2"));
			// repository.save(new TodoEntity("Todo3", "Description3"));
			// repository.save(new TodoEntity("Todo4", "Description4"));
			// repository.save(new TodoEntity("Todo5", "Description5"));

			// fetch all users
			log.info("Todos found with findAll():");
			log.info("-------------------------------");

			repository.findAll().forEach(todo -> {
				log.info(todo.toString());
			});

			log.info("");

			// fetch an individual customer by ID
			// Todo user = repository.findById("1");

			// log.info("User found with findById(1L):");
			// log.info("--------------------------------");
			// log.info(user.toString());
			// log.info("");

			// fetch customers by first name
			// log.info("Customer found with findByFirstName('Bauer'):");
			// log.info("--------------------------------------------");

			// repository.findByTitle("Bauer").forEach(bauer -> {
			// 	log.info(bauer.toString());
			// });

			// log.info("");
		};
	}
}
