package com.victorpereira.mymarketplace.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.victorpereira.mymarketplace.domain.City;

@Repository
public interface CityRepository extends JpaRepository<City, Integer>{

}
