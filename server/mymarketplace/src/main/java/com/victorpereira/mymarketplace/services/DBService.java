package com.victorpereira.mymarketplace.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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

@Service
public class DBService {

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
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	public void instantiateTestDatabase() throws ParseException {
		Category c1 = new Category(null, "Informática");
		Category c2 = new Category(null, "Escritório");
		Category c3 = new Category(null, "Cama mesa e banho");
		Category c4 = new Category(null, "Eletrônicos");
		Category c5 = new Category(null, "Jardinagem");
		Category c6 = new Category(null, "Decoração");
		Category c7 = new Category(null, "Perfumaria");

		Product p1 = new Product(null, "Computador", 2000.00);
		Product p2 = new Product(null, "Impressora", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);
		Product p4 = new Product(null, "Mesa de escritório", 300.00);
		Product p5 = new Product(null, "Toalha", 50.00);
		Product p6 = new Product(null, "Colcha", 200.00);
		Product p7 = new Product(null, "TV true color", 1200.00);
		Product p8 = new Product(null, "Roçadeira", 800.00);
		Product p9 = new Product(null, "Abajour", 100.00);
		Product p10 = new Product(null, "Pendente", 180.00);
		Product p11 = new Product(null, "Shampoo", 90.00);

		c1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		c2.getProducts().addAll(Arrays.asList(p2));
		c2.getProducts().addAll(Arrays.asList(p2, p4));
		c3.getProducts().addAll(Arrays.asList(p5, p6));
		c4.getProducts().addAll(Arrays.asList(p1, p2, p3, p7));
		c5.getProducts().addAll(Arrays.asList(p8));
		c6.getProducts().addAll(Arrays.asList(p9, p10));
		c7.getProducts().addAll(Arrays.asList(p11));

		p1.getCategories().addAll(Arrays.asList(c1, c4));
		p2.getCategories().addAll(Arrays.asList(c1, c2, c4));
		p3.getCategories().addAll(Arrays.asList(c1, c4));
		p4.getCategories().addAll(Arrays.asList(c2));
		p5.getCategories().addAll(Arrays.asList(c3));
		p6.getCategories().addAll(Arrays.asList(c3));
		p7.getCategories().addAll(Arrays.asList(c4));
		p8.getCategories().addAll(Arrays.asList(c5));
		p9.getCategories().addAll(Arrays.asList(c6));
		p10.getCategories().addAll(Arrays.asList(c6));
		p11.getCategories().addAll(Arrays.asList(c7));

		categoryRepo.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6, c7));
		productRepo.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));

		State s1 = new State(null, "Minas Gerais");
		State s2 = new State(null, "São Paulo");

		City ct1 = new City(null, "Uberlândia", s1);
		City ct2 = new City(null, "São Paulo", s2);
		City ct3 = new City(null, "Campinas", s2);

		s1.getCities().addAll(Arrays.asList(ct1));
		s2.getCities().addAll(Arrays.asList(ct2, ct3));

		stateRepo.saveAll(Arrays.asList(s1, s2));
		cityRepo.saveAll(Arrays.asList(ct1, ct2, ct3));

		Client cl1 = new Client(null, "Maria Silva", "maria@gmail.com", "3123712378", ClientType.PHYSICALPERSON, encoder.encode("123") );
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
