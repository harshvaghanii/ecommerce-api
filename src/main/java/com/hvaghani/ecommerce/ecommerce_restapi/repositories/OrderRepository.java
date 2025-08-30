package com.hvaghani.ecommerce.ecommerce_restapi.repositories;

import com.hvaghani.ecommerce.ecommerce_restapi.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
