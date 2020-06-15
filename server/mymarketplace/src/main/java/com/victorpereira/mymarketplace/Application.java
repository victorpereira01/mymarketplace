package com.victorpereira.mymarketplace;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.victorpereira.mymarketplace.domain.Address;
import com.victorpereira.mymarketplace.domain.BilletPayment;
import com.victorpereira.mymarketplace.domain.CardPayment;
import com.victorpereira.mymarketplace.domain.Category;
import com.victorpereira.mymarketplace.domain.City;
import com.victorpereira.mymarketplace.domain.Client;
import com.victorpereira.mymarketplace.domain.Order;
import com.victorpereira.mymarketplace.domain.OrderItem;
import com.victorpereira.mymarketplace.domain.Payment;
import com.victorpereira.mymarketplace.domain.Product;
import com.victorpereira.mymarketplace.domain.State;
import com.victorpereira.mymarketplace.domain.enums.ClientType;
import com.victorpereira.mymarketplace.domain.enums.PaymentState;
import com.victorpereira.mymarketplace.repositories.AddressRepository;
import com.victorpereira.mymarketplace.repositories.CategoryRepository;
import com.victorpereira.mymarketplace.repositories.CityRepository;
import com.victorpereira.mymarketplace.repositories.ClientRepository;
import com.victorpereira.mymarketplace.repositories.OrderItemRepository;
import com.victorpereira.mymarketplace.repositories.OrderRepository;
import com.victorpereira.mymarketplace.repositories.PaymentRepository;
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

	@Autowired
	private OrderRepository orderRepo;

	@Autowired
	private PaymentRepository paymentRepo;

	@Autowired
	private OrderItemRepository orderItemRepo;

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

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Order o1 = new Order(null, sdf.parse("30/09/2017 10:32"), cl1, ad1);
		Order o2 = new Order(null, sdf.parse("10/10/2017 19:35"), cl1, ad2);

		Payment pay1 = new CardPayment(null, PaymentState.PAID, o1, 6);
		o1.setPayment(pay1);

		Payment pay2 = new BilletPayment(null, PaymentState.PENDING, o2, sdf.parse("20/10/2017 00:00"), null);
		o2.setPayment(pay2);

		cl1.getOrders().addAll(Arrays.asList(o1, o2));

		orderRepo.saveAll(Arrays.asList(o1, o2));
		paymentRepo.saveAll(Arrays.asList(pay1, pay2));

		OrderItem oi1 = new OrderItem(o1, p1, 0.00, 1, 2000.00);
		OrderItem oi2 = new OrderItem(o1, p3, 0.00, 2, 80.00);
		OrderItem oi3 = new OrderItem(o2, p2, 100.00, 1, 800.00);

		o1.getItens().addAll(Arrays.asList(oi1, oi2));
		o2.getItens().addAll(Arrays.asList(oi3));

		p1.getItens().addAll(Arrays.asList(oi1));
		p2.getItens().addAll(Arrays.asList(oi3));
		p3.getItens().addAll(Arrays.asList(oi2));

		orderItemRepo.saveAll(Arrays.asList(oi1, oi2, oi3));
	}
}
