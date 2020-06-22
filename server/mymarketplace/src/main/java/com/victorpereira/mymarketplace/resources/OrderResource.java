package com.victorpereira.mymarketplace.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.victorpereira.mymarketplace.domain.Order;
import com.victorpereira.mymarketplace.services.OrderService;

@RestController
@RequestMapping(value = "/orders")
public class OrderResource {

	@Autowired
	private OrderService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Order> findById(@PathVariable Integer id) {
		Order obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody Order order) {
		order = service.insert(order);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(order.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@GetMapping
	public ResponseEntity<Page<Order>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "instant") String orderBy,
			@RequestParam(value = "direction", defaultValue = "DESC") String direction) {
		Page<Order> list = service.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}
}
