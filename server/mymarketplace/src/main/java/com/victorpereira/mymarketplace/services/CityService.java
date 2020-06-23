package com.victorpereira.mymarketplace.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victorpereira.mymarketplace.domain.City;
import com.victorpereira.mymarketplace.repositories.CityRepository;

@Service
public class CityService {

	@Autowired
	private CityRepository repo;
	
	public List<City> findByState(Integer stateId){
		return repo.findCities(stateId);
	}
}
