package com.auk.order.service.domain.ports.output.repository;

import com.auk.order.service.domain.entity.Order;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository {
    Order save(Order order);

    Optional<Order> findByTrackingId(UUID trackingId);
}
