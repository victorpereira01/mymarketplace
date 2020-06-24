package com.victorpereira.mymarketplace.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.victorpereira.mymarketplace.domain.Category;
import com.victorpereira.mymarketplace.domain.Product;
import com.victorpereira.mymarketplace.repositories.CategoryRepository;
import com.victorpereira.mymarketplace.repositories.ProductRepository;
import com.victorpereira.mymarketplace.services.exceptions.ObjectNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repo;

	@Autowired
	private CategoryRepository categoryRepo;
	
	public List<Product> findAll() {
		return repo.findAll();
	}

	public Product findById(Integer id) {
		Optional<Product> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + ", Tipo: " + Product.class.getName()));
	}
	
	public Page<Product> search(String name, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, 
				Direction.valueOf(direction), orderBy);
		List<Category> categories = categoryRepo.findAllById(ids);
		return repo.findDistinctByNameContainingAndCategoriesIn(name, categories, pageRequest);
		
	}
}
