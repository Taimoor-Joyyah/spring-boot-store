CREATE TABLE carts
(
    id           BINARY(16) PRIMARY KEY DEFAULT (UUID_TO_BIN(UUID())) NOT NULL,
    date_created DATE                   DEFAULT (DATE(NOW()))         NOT NULL
);

CREATE TABLE cart_items
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    cart_id    BINARY(16) NOT NULL,
    product_id BIGINT     NOT NULL,
    quantity   INT        NOT NULL,

    CONSTRAINT uk_cart_product UNIQUE (cart_id, product_id),
    CONSTRAINT fk_cart FOREIGN KEY (cart_id) REFERENCES carts (id) ON DELETE CASCADE,
    CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE
);
