package com.auk.order.service.domain.ports.output.message.publisher.payment;

import com.auk.domain.events.publisher.DomainEventPublisher;
import com.auk.order.service.domain.event.OrderCancelledEvent;
import com.auk.order.service.domain.event.OrderCreatedEvent;

public interface OrderCancelledPaymentRequestMessagePublisher extends DomainEventPublisher<OrderCancelledEvent> {
}
