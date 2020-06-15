package com.victorpereira.mymarketplace.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.victorpereira.mymarketplace.domain.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>{

}
