package com.victorpereira.mymarketplace.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.victorpereira.mymarketplace.domain.OrderItem;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer>{

}
