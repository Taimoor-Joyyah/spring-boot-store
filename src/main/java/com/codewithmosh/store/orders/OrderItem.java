package com.codewithmosh.store.orders;

import com.codewithmosh.store.carts.CartItem;
import com.codewithmosh.store.products.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    public static OrderItem from(Order order, CartItem cartItem) {
        OrderItem orderItem = new OrderItem();
        orderItem.order = order;
        orderItem.product = cartItem.getProduct();
        orderItem.unitPrice = cartItem.getProduct().getPrice();
        orderItem.quantity = cartItem.getQuantity();
        orderItem.totalPrice = cartItem.getTotalPrice();
        return orderItem;
    }
}
