package com.auk.order.service.domain.ports.output.message.publisher.restaurantapproval;

import com.auk.domain.events.publisher.DomainEventPublisher;
import com.auk.order.service.domain.event.OrderPaidEvent;

public interface OrderPaidRestaurantMessagePublisher extends DomainEventPublisher<OrderPaidEvent> {
}
