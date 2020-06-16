package com.victorpereira.mymarketplace.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.victorpereira.mymarketplace.domain.Address;
import com.victorpereira.mymarketplace.domain.City;
import com.victorpereira.mymarketplace.domain.Client;
import com.victorpereira.mymarketplace.domain.enums.ClientType;
import com.victorpereira.mymarketplace.dto.ClientDTO;
import com.victorpereira.mymarketplace.dto.ClientNewDTO;
import com.victorpereira.mymarketplace.repositories.AddressRepository;
import com.victorpereira.mymarketplace.repositories.ClientRepository;
import com.victorpereira.mymarketplace.services.exceptions.DataIntegrityException;
import com.victorpereira.mymarketplace.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepo;

	@Autowired
	private AddressRepository addressRepo;
	
	public List<Client> findAll() {
		return clientRepo.findAll();
	}

	public Client findById(Integer id) {
		Optional<Client> obj = clientRepo.findById(id);
		return obj.orElseThrow(
				() -> new ObjectNotFoundException("Object not found! Id: " + id + ", Tipo: " + Client.class.getName()));
	}

	@Transactional
	public Client insert(Client obj) {
		obj.setId(null);
		obj = clientRepo.save(obj);
		addressRepo.saveAll(obj.getAdresses());
		return obj;
	}
	
	public Client update(Client obj) {
		Client newObj = findById(obj.getId());
		updateData(newObj, obj);
		return clientRepo.save(obj);
	}

	public void delete(Integer id) {
		findById(id);
		try {
			clientRepo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Can't delete because it has related entities");
		}
	}

	public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clientRepo.findAll(pageRequest);
	}

	public Client fromDTO(@Valid ClientDTO objDto) {
		return new Client(objDto.getId(), objDto.getName(), objDto.getEmail(), null, null);
	}
	
	public Client fromDTO(@Valid ClientNewDTO objDto) {
			Client cli = new Client(null, objDto.getName(), objDto.getEmail(), objDto.getCpfOrCnpj(), ClientType.toEnum(objDto.getType()));
			City city = new City(objDto.getCityId(), null, null);
			Address ad = new Address(null, objDto.getPublicPlace(), objDto.getNumber(), objDto.getComplement(), objDto.getNeighborhood(), objDto.getCep(), cli, city);
					
			cli.getAdresses().add(ad);
			cli.getTelephones().add(objDto.getTel1());
			if (objDto.getTel2()!=null) {
				cli.getTelephones().add(objDto.getTel2());
			}
			if (objDto.getTel3()!=null) {
				cli.getTelephones().add(objDto.getTel3());
			}
			return cli;
	
	}
	
	private void updateData(Client newObj, Client obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}

}
