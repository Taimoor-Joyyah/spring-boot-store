package com.codewithmosh.store.carts;

import com.codewithmosh.store.products.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "date_created", insertable = false, updatable = false)
    private LocalDate dateCreated;

    @OneToMany(
            mappedBy = "cart",
            fetch = FetchType.EAGER,
            cascade = CascadeType.MERGE,
            orphanRemoval = true
    )
    private Set<CartItem> items = new LinkedHashSet<>();

    public BigDecimal getTotalPrice() {
        return items.stream().map(CartItem::getTotalPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public CartItem addCartItem(Product product) {
        Optional<CartItem> OptionalCartItem = getCartItem(product.getId());
        if (OptionalCartItem.isPresent()) {
            CartItem cartItem = OptionalCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + 1);
            return cartItem;
        }

        CartItem cartItem = new CartItem();
        cartItem.setCart(this);
        cartItem.setProduct(product);
        cartItem.setQuantity(1);
        items.add(cartItem);
        return cartItem;
    }

    public Optional<CartItem> getCartItem(Long productId) {
        return items.stream()
                .filter(cartItem -> cartItem.getProduct().getId().equals(productId))
                .findFirst();
    }

    public boolean removeCartItem(Long productId) {
        return items.removeIf(cartItem -> cartItem.getProduct().getId().equals(productId));
    }

    public void clear() {
        items.clear();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}
