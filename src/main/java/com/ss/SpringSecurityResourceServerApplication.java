package com.ss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories("com.ss.data")
public class SpringSecurityResourceServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityResourceServerApplication.class, args);
	}
}
