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
import com.victorpereira.mymarketplace.domain.enums.Profile;
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

		Product p12 = new Product(null, "Product 12", 10.00);
		Product p13 = new Product(null, "Product 13", 10.00);
		Product p14 = new Product(null, "Product 14", 10.00);
		Product p15 = new Product(null, "Product 15", 10.00);
		Product p16 = new Product(null, "Product 16", 10.00);
		Product p17 = new Product(null, "Product 17", 10.00);
		Product p18 = new Product(null, "Product 18", 10.00);
		Product p19 = new Product(null, "Product 19", 10.00);
		Product p20 = new Product(null, "Product 20", 10.00);
		Product p21 = new Product(null, "Product 21", 10.00);
		Product p22 = new Product(null, "Product 22", 10.00);
		Product p23 = new Product(null, "Product 23", 10.00);
		Product p24 = new Product(null, "Product 24", 10.00);
		Product p25 = new Product(null, "Product 25", 10.00);
		Product p26 = new Product(null, "Product 26", 10.00);
		Product p27 = new Product(null, "Product 27", 10.00);
		Product p28 = new Product(null, "Product 28", 10.00);
		Product p29 = new Product(null, "Product 29", 10.00);
		Product p30 = new Product(null, "Product 30", 10.00);
		Product p31 = new Product(null, "Product 31", 10.00);
		Product p32 = new Product(null, "Product 32", 10.00);
		Product p33 = new Product(null, "Product 33", 10.00);
		Product p34 = new Product(null, "Product 34", 10.00);
		Product p35 = new Product(null, "Product 35", 10.00);
		Product p36 = new Product(null, "Product 36", 10.00);
		Product p37 = new Product(null, "Product 37", 10.00);
		Product p38 = new Product(null, "Product 38", 10.00);
		Product p39 = new Product(null, "Product 39", 10.00);
		Product p40 = new Product(null, "Product 40", 10.00);
		Product p41 = new Product(null, "Product 41", 10.00);
		Product p42 = new Product(null, "Product 42", 10.00);
		Product p43 = new Product(null, "Product 43", 10.00);
		Product p44 = new Product(null, "Product 44", 10.00);
		Product p45 = new Product(null, "Product 45", 10.00);
		Product p46 = new Product(null, "Product 46", 10.00);
		Product p47 = new Product(null, "Product 47", 10.00);
		Product p48 = new Product(null, "Product 48", 10.00);
		Product p49 = new Product(null, "Product 49", 10.00);
		Product p50 = new Product(null, "Product 50", 10.00);

		c1.getProducts()
				.addAll(Arrays.asList(p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26, p27,
						p28, p29, p30, p31, p32, p34, p35, p36, p37, p38, p39, p40, p41, p42, p43, p44, p45, p46, p47,
						p48, p49, p50));

		p12.getCategories().add(c1);
		p13.getCategories().add(c1);
		p14.getCategories().add(c1);
		p15.getCategories().add(c1);
		p16.getCategories().add(c1);
		p17.getCategories().add(c1);
		p18.getCategories().add(c1);
		p19.getCategories().add(c1);
		p20.getCategories().add(c1);
		p21.getCategories().add(c1);
		p22.getCategories().add(c1);
		p23.getCategories().add(c1);
		p24.getCategories().add(c1);
		p25.getCategories().add(c1);
		p26.getCategories().add(c1);
		p27.getCategories().add(c1);
		p28.getCategories().add(c1);
		p29.getCategories().add(c1);
		p30.getCategories().add(c1);
		p31.getCategories().add(c1);
		p32.getCategories().add(c1);
		p33.getCategories().add(c1);
		p34.getCategories().add(c1);
		p35.getCategories().add(c1);
		p36.getCategories().add(c1);
		p37.getCategories().add(c1);
		p38.getCategories().add(c1);
		p39.getCategories().add(c1);
		p40.getCategories().add(c1);
		p41.getCategories().add(c1);
		p42.getCategories().add(c1);
		p43.getCategories().add(c1);
		p44.getCategories().add(c1);
		p45.getCategories().add(c1);
		p46.getCategories().add(c1);
		p47.getCategories().add(c1);
		p48.getCategories().add(c1);
		p49.getCategories().add(c1);
		p50.getCategories().add(c1);

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

		productRepo.saveAll(Arrays.asList(p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26,
				p27, p28, p29, p30, p31, p32, p34, p35, p36, p37, p38, p39, p40, p41, p42, p43, p44, p45, p46, p47, p48,
				p49, p50));

		State s1 = new State(null, "Minas Gerais");
		State s2 = new State(null, "São Paulo");

		City ct1 = new City(null, "Uberlândia", s1);
		City ct2 = new City(null, "São Paulo", s2);
		City ct3 = new City(null, "Campinas", s2);

		s1.getCities().addAll(Arrays.asList(ct1));
		s2.getCities().addAll(Arrays.asList(ct2, ct3));

		stateRepo.saveAll(Arrays.asList(s1, s2));
		cityRepo.saveAll(Arrays.asList(ct1, ct2, ct3));

		Client cl1 = new Client(null, "Maria Silva", "maria@gmail.com", "3123712378", ClientType.PHYSICALPERSON,
				encoder.encode("123"));
		cl1.getTelephones().addAll(Arrays.asList("123123133", "99981923"));

		Client cl2 = new Client(null, "Ana Costa", "ana@gmail.com", "02206218020", ClientType.PHYSICALPERSON,
				encoder.encode("123"));
		cl2.addProfile(Profile.ADMIN);
		cl1.getTelephones().addAll(Arrays.asList("9999999", "11223344"));

		Address ad1 = new Address(null, "Rua Flores", "300", "Apto 303", "Jardim", "38 220834", cl1, ct1);
		Address ad2 = new Address(null, "Avenida Matos", "105", "Sala 800", "Centro", "11222084", cl1, ct2);
		Address ad3 = new Address(null, "Avenida Floriano", "2005", null, "Centro", "13122084", cl2, ct2);

		cl1.getAdresses().addAll(Arrays.asList(ad1, ad2));
		cl1.getAdresses().addAll(Arrays.asList(ad3));

		clientRepo.saveAll(Arrays.asList(cl1, cl2));
		addressRepo.saveAll(Arrays.asList(ad1, ad2, ad3));

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
