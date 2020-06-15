package com.victorpereira.mymarketplace.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.victorpereira.mymarketplace.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{

}
