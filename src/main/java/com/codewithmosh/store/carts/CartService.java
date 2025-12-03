package com.codewithmosh.store.carts;

import com.codewithmosh.store.products.Product;
import com.codewithmosh.store.products.ProductNotFoundException;
import com.codewithmosh.store.products.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class CartService {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final CartItemMapper cartItemMapper;
    private final ProductRepository productRepository;

    public CartDto createCart() {
        Cart cart = new Cart();
        cartRepository.save(cart);
        return cartMapper.toDto(cart);
    }

    public CartItemDto addCartItem(UUID cartId, AddCartItemDto addCartItemDto) {
        Cart cart = getCartElseThrow(cartId);
        Product product = productRepository.findById(addCartItemDto.getProductId()).orElseThrow(() -> new ProductNotFoundException("Product not found"));
        CartItem cartItem = cart.addCartItem(product);
        cartRepository.save(cart);
        return cartItemMapper.toDto(cartItem);
    }

    private Cart getCartElseThrow(UUID cartId) {
        return cartRepository.findByCartWithItems(cartId).orElseThrow(() -> new CartNotFoundException("Cart not found"));
    }

    public CartDto getCart(UUID cartId) {
        Cart cart = getCartElseThrow(cartId);
        return cartMapper.toDto(cart);
    }

    public CartItemDto updateProduct(UUID cartId, Long productId, UpdateCartItemDto updateCartItemDto) {
        Cart cart = getCartElseThrow(cartId);
        CartItem cartItem = cart.getCartItem(productId).orElseThrow(() -> new ProductNotFoundException("Product not found"));
        cartItem.setQuantity(updateCartItemDto.getQuantity());
        cartRepository.save(cart);
        return cartItemMapper.toDto(cartItem);
    }

    public void deleteProduct(UUID cartId, Long productId) {
        Cart cart = getCartElseThrow(cartId);
        if (!cart.removeCartItem(productId)) {
            throw new ProductNotFoundException("Product not found");
        }
        cartRepository.save(cart);
    }

    public void clearCart(UUID cartId) {
        Cart cart = getCartElseThrow(cartId);
        cart.clear();
        cartRepository.save(cart);
    }
}
