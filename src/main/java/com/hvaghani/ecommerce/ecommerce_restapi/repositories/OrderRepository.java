package com.hvaghani.ecommerce.ecommerce_restapi.repositories;

import com.hvaghani.ecommerce.ecommerce_restapi.entities.Order;
import com.hvaghani.ecommerce.ecommerce_restapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
}
