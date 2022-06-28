package com.auk.order.service.domain.event;

import com.auk.domain.events.DomainEvent;
import com.auk.order.service.domain.entity.Order;

import java.time.ZonedDateTime;

public class OrderCancelledEvent extends OrderEvent {
    public OrderCancelledEvent(Order order, ZonedDateTime zonedDateTime) {
        super(order, zonedDateTime);
    }
}
