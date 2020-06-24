package com.victorpereira.mymarketplace.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.victorpereira.mymarketplace.domain.Product;
import com.victorpereira.mymarketplace.dto.ProductDTO;
import com.victorpereira.mymarketplace.resources.utils.URL;
import com.victorpereira.mymarketplace.services.ProductService;



@RestController
@RequestMapping(value = "/products")
public class ProductResource {
	
	@Autowired
	private ProductService service;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Product> findById(@PathVariable Integer id) {
		Product obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}	
	
	@GetMapping
	public ResponseEntity<Page<ProductDTO>> findPage(
			@RequestParam(value="name", defaultValue = "") String name,
			@RequestParam(value="categories", defaultValue = "") String categories, 
			@RequestParam(value="page", defaultValue = "0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue = "name") String orderBy,
			@RequestParam(value="direction", defaultValue = "ASC") String direction) {
		String decodedName = URL.decodeParam(name);
		List<Integer> ids = URL.decodeIntList(categories);
		Page<Product> list = service.search(decodedName, ids, page, linesPerPage, orderBy, direction);
		Page<ProductDTO> listDto = list.map(obj -> new ProductDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
}
