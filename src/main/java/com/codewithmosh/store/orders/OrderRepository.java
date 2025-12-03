package com.codewithmosh.store.orders;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends CrudRepository<Order, Long> {
    @EntityGraph(attributePaths = "items.product")
    List<Order> getOrdersByCustomerId(Long customerId);

    @EntityGraph(attributePaths = "items.product")
    Optional<Order> getOrderById(Long id);
}
