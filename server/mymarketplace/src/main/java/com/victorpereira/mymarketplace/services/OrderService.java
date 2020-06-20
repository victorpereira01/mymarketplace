package com.victorpereira.mymarketplace.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.victorpereira.mymarketplace.domain.BilletPayment;
import com.victorpereira.mymarketplace.domain.Order;
import com.victorpereira.mymarketplace.domain.OrderItem;
import com.victorpereira.mymarketplace.domain.enums.PaymentState;
import com.victorpereira.mymarketplace.repositories.OrderItemRepository;
import com.victorpereira.mymarketplace.repositories.OrderRepository;
import com.victorpereira.mymarketplace.repositories.PaymentRepository;
import com.victorpereira.mymarketplace.services.exceptions.ObjectNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repo;

	@Autowired
	private BilletService billetService;
	
	@Autowired
	private PaymentRepository paymentRepo;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderItemRepository orderItemRepo;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private EmailService emailService;
	
	public List<Order> findAll() {
		return repo.findAll();
	}

	public Order findById(Integer id) {
		Optional<Order> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + ", Tipo: " + Order.class.getName()));
	}
	
	@Transactional
	public Order insert(Order obj) {
		obj.setId(null);
		obj.setInstant(new Date());
		obj.setClient(clientService.findById(obj.getClient().getId()));
		obj.getPayment().setState(PaymentState.PENDING);
		obj.getPayment().setOrder(obj);
		if(obj.getPayment() instanceof BilletPayment) {
			BilletPayment pay = (BilletPayment) obj.getPayment();
			billetService.fillBilletPayment(pay, obj.getInstant());
		}
		obj = repo.save(obj);
		paymentRepo.save(obj.getPayment());
		
		for(OrderItem i : obj.getItens()) {
			i.setDiscount(0.0);
			i.setProduct(productService.findById(i.getProduct().getId()));
			i.setPrice(i.getProduct().getPrice());
			i.setOrder(obj);
		}
		orderItemRepo.saveAll(obj.getItens());
		emailService.sendOrderConfirmationHtmlEmail(obj);
		return obj;
	}

}
