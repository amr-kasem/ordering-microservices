package com.auk.order.service.domain.mapper;

import com.auk.domain.valueobject.CustomerId;
import com.auk.domain.valueobject.Money;
import com.auk.domain.valueobject.ProductId;
import com.auk.domain.valueobject.RestaurantId;
import com.auk.order.service.domain.dto.create.CreateOrderCommand;
import com.auk.order.service.domain.dto.create.CreateOrderResponse;
import com.auk.order.service.domain.dto.create.OrderAddress;
import com.auk.order.service.domain.entity.Order;
import com.auk.order.service.domain.entity.OrderItem;
import com.auk.order.service.domain.entity.Product;
import com.auk.order.service.domain.entity.Restaurant;
import com.auk.order.service.domain.valueobject.StreetAddress;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class OrderDataMapper {
    public Restaurant createOrderCommandToRestaurant(CreateOrderCommand createOrderCommand){
        return Restaurant.builder()
                .restaurantId(new RestaurantId(createOrderCommand.getRestaurantId()))
                .products(createOrderCommand.getItems().stream().map(orderItem ->
                        new Product(new ProductId(orderItem.getProductId())))
                        .collect(Collectors.toList())
                )
                .build();
    }

    public Order createOrderCommandToOrder(CreateOrderCommand createOrderCommand){
        return Order.builder()
                .customerId(new CustomerId(createOrderCommand.getCustomerId()))
                .restaurantId(new RestaurantId(createOrderCommand.getRestaurantId()))
                .deliveryAddress(orderAddressToStreetAddress(createOrderCommand.getAddress()))
                .price(new Money(createOrderCommand.getPrice()))
                .items(orderItemsToOrderItemsEntities(createOrderCommand.getItems()))
                .build();
    }
    public CreateOrderResponse orderToCreateOrderResponse(Order order, String message){
        return CreateOrderResponse.builder()
                .orderTrackingId(order.getTrackingId().getValue())
                .orderStatus(order.getOrderStatus())
                .message(message)
                .build();
    }
    private List<OrderItem> orderItemsToOrderItemsEntities(List<com.auk.order.service.domain.dto.create.OrderItem> orderItems) {
        return orderItems.stream().map(orderitem->
                    OrderItem.builder()
                            .product(new Product(new ProductId(orderitem.getProductId())))
                            .price(new Money(orderitem.getPrice()))
                            .quantity(orderitem.getQuantity())
                            .subTotal(new Money(orderitem.getSubtotal()))
                            .build())
                    .collect(Collectors.toList()
                );
    }

    public StreetAddress orderAddressToStreetAddress(OrderAddress orderAddress){
        return new StreetAddress(
                UUID.randomUUID(),
                orderAddress.getStreet(),
                orderAddress.getPostalCode(),
                orderAddress.getCity()
        );
    }
}
