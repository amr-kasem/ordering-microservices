package com.auk.order.service.domain.entity;

import com.auk.domain.entity.BaseEntity;
import com.auk.domain.valueobject.Money;
import com.auk.domain.valueobject.ProductId;

public class Product extends BaseEntity<ProductId> {
  private String name;
  private Money price;

    public Product(ProductId productId,String name, Money price) {
        this.name = name;
        this.price = price;
        super.setId(productId);
    }

    public Product(ProductId productId) {
        super.setId(productId);
    }

    public void updateWithConfirmedNameAndPrice(String name, Money price) {
        this.name = name;
        this.price = price;
    }
    public String getName() {
        return name;
    }

    public Money getPrice() {
        return price;
    }


}
