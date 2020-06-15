package com.victorpereira.mymarketplace.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.victorpereira.mymarketplace.domain.Category;
import com.victorpereira.mymarketplace.repositories.CategoryRepository;
import com.victorpereira.mymarketplace.services.exceptions.DataIntegrityException;
import com.victorpereira.mymarketplace.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repo;

	public List<Category> findAll() {
		return repo.findAll();
	}

	public Category findById(Integer id) {
		Optional<Category> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + ", Tipo: " + Category.class.getName()));
	}
	
	public Category insert(Category category) {
		category.setId(null);
		return repo.save(category);
	}
	
	public Category update(Category category) {
		findById(category.getId());
		return repo.save(category);
	}

	public void delete(Integer id) {
		findById(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Can't delete a category that has products");
		}
	}
}
