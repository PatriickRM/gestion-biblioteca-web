package com.webapp.biblioteca.springboot_webapp;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SpringbootWebappApplication implements CommandLineRunner{

	@Autowired

	public static void main(String[] args) {
		SpringApplication.run(SpringbootWebappApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}

}
