package com.auk.order.service.domain.entity;

import com.auk.domain.entity.AggregateRoot;
import com.auk.domain.valueobject.ProductId;
import com.auk.domain.valueobject.RestaurantId;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Restaurant extends AggregateRoot<RestaurantId> {
    private Restaurant(Builder builder) {
        super.setId(builder.restaurantId);
        products = builder.products;
        active = builder.active;
    }

    public static Builder builder() {
        return new Builder();
    }

    public IdentityHashMap<ProductId,Product> getProducts() {
        return products;
    }



    public boolean isActive() {
        return active;
    }

    private final IdentityHashMap<ProductId,Product> products;
    private boolean active;

    public static final class Builder {
        private RestaurantId restaurantId;
        private IdentityHashMap<ProductId,Product> products;
        private boolean active;

        private Builder() {
        }

        public Builder restaurantId(RestaurantId val) {
            restaurantId = val;
            return this;
        }

        public Builder products(List<Product> val) {
            products = new IdentityHashMap<>();
            products.putAll(val.stream().collect(Collectors.toMap(Product::getId, Function.identity())));
            return this;
        }

        public Builder active(boolean val) {
            active = val;
            return this;
        }

        public Restaurant build() {
            return new Restaurant(this);
        }
    }
}
