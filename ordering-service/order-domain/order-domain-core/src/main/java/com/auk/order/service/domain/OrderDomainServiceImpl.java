package com.auk.order.service.domain;

import com.auk.order.service.domain.entity.Order;
import com.auk.order.service.domain.entity.Product;
import com.auk.order.service.domain.entity.Restaurant;
import com.auk.order.service.domain.event.OrderCancelledEvent;
import com.auk.order.service.domain.event.OrderCreatedEvent;
import com.auk.order.service.domain.event.OrderPaidEvent;
import com.auk.order.service.domain.exception.OrderDomainException;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Slf4j
public class OrderDomainServiceImpl implements OrderDomainService {
    private static String UTC = "UTC";
    @Override
    public OrderCreatedEvent validateAndIntiateOrder(Order order, Restaurant restaurant) {
        validateRestaurant(restaurant);
        setOrderProductInformation(order, restaurant);
        order.validateOrder();
        order.initializeOrder();
        log.info("Order with id: {} is intiated",order.getId().getValue());
        return new OrderCreatedEvent(order, ZonedDateTime.now(ZoneId.of(UTC)));
    }


    @Override
    public OrderPaidEvent payOrder(Order order) {
        order.pay();
        log.info("Order with id: {} is paid",order.getId().getValue());
        return new OrderPaidEvent(order, ZonedDateTime.now(ZoneId.of(UTC)));
    }

    @Override
    public void approveOrder(Order order) {
        order.approve();
        log.info("Order with id: {} is approved",order.getId().getValue());
    }

    @Override
    public OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages) {
        order.initCancel(failureMessages);
        log.info("Order payment is cancelling for order with id: {}",order.getId().getValue());
        return new OrderCancelledEvent(order, ZonedDateTime.now(ZoneId.of(UTC)));
    }

    @Override
    public void cancelOrder(Order order, List<String> failureMessages) {
        order.cancel(failureMessages);
        log.info("Order with id: {} is cancelled",order.getId().getValue());
    }

    private void validateRestaurant(Restaurant restaurant) {
        if (!restaurant.isActive()) {
            throw new OrderDomainException("Restaurant with ID " + restaurant.getId().getValue() + " is currently not active!");
        }
    }

    private void setOrderProductInformation(Order order, Restaurant restaurant) {
        order.getItems().forEach(orderItem -> {
            Product restaurantProduct = restaurant.getProducts().get(orderItem.getProduct().getId());
            orderItem.getProduct().updateWithConfirmedNameAndPrice(restaurantProduct.getName(),restaurantProduct.getPrice());
        });

    }
}
