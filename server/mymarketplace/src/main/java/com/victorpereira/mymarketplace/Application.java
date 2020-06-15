package com.victorpereira.mymarketplace;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.victorpereira.mymarketplace.domain.Address;
import com.victorpereira.mymarketplace.domain.Category;
import com.victorpereira.mymarketplace.domain.City;
import com.victorpereira.mymarketplace.domain.Client;
import com.victorpereira.mymarketplace.domain.Product;
import com.victorpereira.mymarketplace.domain.State;
import com.victorpereira.mymarketplace.domain.enums.ClientType;
import com.victorpereira.mymarketplace.repositories.AddressRepository;
import com.victorpereira.mymarketplace.repositories.CategoryRepository;
import com.victorpereira.mymarketplace.repositories.CityRepository;
import com.victorpereira.mymarketplace.repositories.ClientRepository;
import com.victorpereira.mymarketplace.repositories.ProductRepository;
import com.victorpereira.mymarketplace.repositories.StateRepository;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepo;

	@Autowired
	private ProductRepository productRepo;

	@Autowired
	private StateRepository stateRepo;

	@Autowired
	private CityRepository cityRepo;

	@Autowired
	private ClientRepository clientRepo;

	@Autowired
	private AddressRepository addressRepo;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Category c1 = new Category(null, "Informática");
		Category c2 = new Category(null, "Escritório");

		Product p1 = new Product(null, "Computador", 2000.00);
		Product p2 = new Product(null, "Impressora", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);

		c1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		c2.getProducts().addAll(Arrays.asList(p2));

		p1.getCategories().addAll(Arrays.asList(c1));
		p2.getCategories().addAll(Arrays.asList(c1, c2));
		p3.getCategories().addAll(Arrays.asList(c1));

		categoryRepo.saveAll(Arrays.asList(c1, c2));
		productRepo.saveAll(Arrays.asList(p1, p2, p3));

		State s1 = new State(null, "Minas Gerais");
		State s2 = new State(null, "São Paulo");

		City ct1 = new City(null, "Uberlândia", s1);
		City ct2 = new City(null, "São Paulo", s2);
		City ct3 = new City(null, "Campinas", s2);

		s1.getCities().addAll(Arrays.asList(ct1));
		s2.getCities().addAll(Arrays.asList(ct2, ct3));

		stateRepo.saveAll(Arrays.asList(s1, s2));
		cityRepo.saveAll(Arrays.asList(ct1, ct2, ct3));

		Client cl1 = new Client(null, "Maria Silva", "maria@gmail.com", "3123712378", ClientType.PHYSICALPERSON);
		cl1.getTelephones().addAll(Arrays.asList("123123133", "99981923"));

		Address ad1 = new Address(null, "Rua Flores", "300", "Apto 303", "Jardim", "38 220834", cl1, ct1);
		Address ad2 = new Address(null, "Avenida Matos", "105", "Sala 800", "Centro", "11222084", cl1, ct2);

		cl1.getAdresses().addAll(Arrays.asList(ad1, ad2));

		clientRepo.saveAll(Arrays.asList(cl1));
		addressRepo.saveAll(Arrays.asList(ad1, ad2));

	}

}
