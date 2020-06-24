package com.victorpereira.mymarketplace.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victorpereira.mymarketplace.domain.City;
import com.victorpereira.mymarketplace.domain.State;
import com.victorpereira.mymarketplace.dto.CityDTO;
import com.victorpereira.mymarketplace.dto.StateDTO;
import com.victorpereira.mymarketplace.services.CityService;
import com.victorpereira.mymarketplace.services.StateService;

@RestController
@RequestMapping(value="/states")
public class StateResource {
	
	@Autowired
	private StateService service;
	
	@Autowired
	private CityService cityService;
	
	@GetMapping
	public ResponseEntity<List<StateDTO>> findAll(){
		List<State> list = service.findAll();
		List<StateDTO> listDto = list.stream().map(obj -> new StateDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@GetMapping(value = "/{stateId}/cities")
	public ResponseEntity<List<CityDTO>> findCities(@PathVariable Integer stateId){
		List<City> list = cityService.findByState(stateId);
		List<CityDTO> listDto = list.stream().map(obj -> new CityDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
}
