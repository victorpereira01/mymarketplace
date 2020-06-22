package com.victorpereira.mymarketplace.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.victorpereira.mymarketplace.domain.Client;
import com.victorpereira.mymarketplace.repositories.ClientRepository;
import com.victorpereira.mymarketplace.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private ClientRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Client cli = repo.findByEmail(email);
		if(cli == null)
			throw new UsernameNotFoundException(email);
		
		return new UserSS(cli.getId(), cli.getEmail(), cli.getPassword(), cli.getProfiles());
	}
}
