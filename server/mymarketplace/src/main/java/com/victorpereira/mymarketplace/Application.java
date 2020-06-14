package com.victorpereira.mymarketplace;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.victorpereira.mymarketplace.domain.Category;
import com.victorpereira.mymarketplace.repositories.CategoryRepository;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Category c1 = new Category(null, "Informática");		
		Category c2 = new Category(null, "Escritório");
		
		categoryRepo.saveAll(Arrays.asList(c1,c2));
	}

}
