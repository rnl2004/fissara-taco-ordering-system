package com.fissara.taco.ordering.system.repository;

import com.fissara.taco.ordering.system.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findOrdersByCustomerId(Long id);
}
