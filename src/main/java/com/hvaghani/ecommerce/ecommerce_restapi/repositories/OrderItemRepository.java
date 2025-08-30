package com.hvaghani.ecommerce.ecommerce_restapi.repositories;

import com.hvaghani.ecommerce.ecommerce_restapi.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{
}
