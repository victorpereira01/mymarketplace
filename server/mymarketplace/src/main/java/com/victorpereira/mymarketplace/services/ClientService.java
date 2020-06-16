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

import com.victorpereira.mymarketplace.domain.Client;
import com.victorpereira.mymarketplace.dto.ClientDTO;
import com.victorpereira.mymarketplace.repositories.ClientRepository;
import com.victorpereira.mymarketplace.services.exceptions.DataIntegrityException;
import com.victorpereira.mymarketplace.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repo;

	public List<Client> findAll() {
		return repo.findAll();
	}

	public Client findById(Integer id) {
		Optional<Client> obj = repo.findById(id);
		return obj.orElseThrow(
				() -> new ObjectNotFoundException("Object not found! Id: " + id + ", Tipo: " + Client.class.getName()));
	}

	public Client update(Client client) {
		Client newClient = findById(client.getId());
		updateData(newClient, client);
		return repo.save(client);
	}

	public void delete(Integer id) {
		findById(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Can't delete because it has related entities");
		}
	}

	public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Client fromDTO(@Valid ClientDTO clientDto) {
		return new Client(clientDto.getId(), clientDto.getName(), clientDto.getEmail(), null, null);
	}
	
	private void updateData(Client newClient, Client client) {
		newClient.setName(client.getName());
		newClient.setEmail(client.getEmail());
	}

}
