package com.victorpereira.mymarketplace.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victorpereira.mymarketplace.domain.Category;
import com.victorpereira.mymarketplace.repositories.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repo;
	
	public List<Category> findAll(){
		return repo.findAll();
	}
	
	public Category findById(Integer id) {
		Optional<Category> obj = repo.findById(id);
		return obj.orElse(null); 
	}
}
