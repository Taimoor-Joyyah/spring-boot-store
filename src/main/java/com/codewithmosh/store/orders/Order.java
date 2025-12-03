package com.codewithmosh.store.orders;

import com.codewithmosh.store.carts.Cart;
import com.codewithmosh.store.carts.CartItem;
import com.codewithmosh.store.payments.PaymentStatus;
import com.codewithmosh.store.users.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private User customer;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private PaymentStatus status;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @OneToMany(mappedBy = "order", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private Set<OrderItem> items = new LinkedHashSet<>();

    public static Order from(User customer, Cart cart) {
        Order order = new Order();
        order.customer = customer;
        for (CartItem cartItem : cart.getItems())
            order.items.add(OrderItem.from(order, cartItem));
        order.totalPrice = cart.getTotalPrice();
        return order;
    }

    public boolean isPlacedBy(User user) {
        return customer.equals(user);
    }
}
