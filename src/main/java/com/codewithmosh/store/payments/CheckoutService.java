package com.codewithmosh.store.payments;

import com.codewithmosh.store.carts.Cart;
import com.codewithmosh.store.orders.Order;
import com.codewithmosh.store.users.User;
import com.codewithmosh.store.carts.CartEmptyException;
import com.codewithmosh.store.carts.CartNotFoundException;
import com.codewithmosh.store.carts.CartRepository;
import com.codewithmosh.store.orders.OrderRepository;
import com.codewithmosh.store.users.UserRepository;
import com.codewithmosh.store.auth.AuthService;
import com.codewithmosh.store.carts.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CheckoutService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final AuthService authService;
    private final CartService cartService;
    private final PaymentGateway paymentGateway;

    @Transactional(rollbackFor = PaymentException.class)
    public CheckoutResponse checkout(UUID cartId) {
        Long userId = authService.currentUserId();
        if (userId == null) {
            throw new AuthenticationCredentialsNotFoundException("User not logged in");
        }

        Cart cart = cartRepository.findByCartWithItems(cartId).orElse(null);
        if (cart == null) {
            throw new CartNotFoundException("Cart not found", HttpStatus.BAD_REQUEST);
        } else if (cart.isEmpty()) {
            throw new CartEmptyException("Cart is empty");
        }

        User user = userRepository.findById(userId).orElseThrow();
        Order order = Order.from(user, cart);
        order.setStatus(PaymentStatus.PENDING);
        orderRepository.save(order);

        var session = paymentGateway.createCheckoutSession(order);

        cartService.clearCart(cartId);

        return new CheckoutResponse(order.getId(), session.getCheckoutUrl());
    }

    public void handleWebhook(WebhookRequest request) {
        paymentGateway.parseWebhookRequest(request).ifPresent(paymentResult -> {
            Order order = orderRepository.findById(paymentResult.getOrderId()).orElseThrow();
            order.setStatus(paymentResult.getPaymentStatus());
            orderRepository.save(order);
        });
    }
}
