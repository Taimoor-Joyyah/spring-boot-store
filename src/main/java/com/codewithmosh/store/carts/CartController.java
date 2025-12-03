package com.codewithmosh.store.carts;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/carts")
class CartController {
    private final CartService cartService;

    @PostMapping
    public ResponseEntity<CartDto> createCart() {
        CartDto cartDto = cartService.createCart();
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").build(cartDto.getId());
        return ResponseEntity.created(uri).body(cartDto);
    }

    @PostMapping("/{cartId}/items")
    public ResponseEntity<CartItemDto> addProduct(
            @PathVariable UUID cartId,
            @RequestBody AddCartItemDto addCartItemDto) {
        CartItemDto cartItemDto = cartService.addCartItem(cartId, addCartItemDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(cartItemDto);
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<CartDto> getCart(@PathVariable UUID cartId) {
        CartDto cartDto = cartService.getCart(cartId);
        return ResponseEntity.ok(cartDto);
    }

    @PutMapping("/{cartId}/items/{productId}")
    public ResponseEntity<CartItemDto> updateProduct(
            @PathVariable UUID cartId,
            @PathVariable Long productId,
            @Valid
            @RequestBody UpdateCartItemDto updateCartItemDto
    ) {
        CartItemDto cartItemDto = cartService.updateProduct(cartId, productId, updateCartItemDto);
        return ResponseEntity.ok(cartItemDto);
    }

    @DeleteMapping("/{cartId}/items/{productId}")
    public ResponseEntity<?> deleteProduct(
            @PathVariable UUID cartId,
            @PathVariable Long productId
    ) {
        cartService.deleteProduct(cartId, productId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<?> clearCart(
            @PathVariable UUID cartId
    ) {
        cartService.clearCart(cartId);
        return ResponseEntity.noContent().build();
    }
}
