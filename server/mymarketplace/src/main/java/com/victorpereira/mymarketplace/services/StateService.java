package com.victorpereira.mymarketplace.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victorpereira.mymarketplace.domain.State;
import com.victorpereira.mymarketplace.repositories.StateRepository;

@Service
public class StateService {

	@Autowired
	private StateRepository repo;
	
	public List<State> findAll(){
		return repo.findAllByOrderByName();
	}
}
