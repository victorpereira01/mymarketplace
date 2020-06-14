package com.victorpereira.mymarketplace.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.victorpereira.mymarketplace.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
