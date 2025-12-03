INSERT INTO categories (name)
VALUES ('Fruits'),
       ('Vegetables'),
       ('Dairy'),
       ('Bakery'),
       ('Beverages');

INSERT INTO products (name, price, description, category_id)
VALUES ('Bananas (1kg)', 180.00, 'Fresh yellow bananas rich in potassium, perfect for snacks and smoothies.', 1),
       ('Red Apples (1kg)', 320.00, 'Crisp and juicy red apples sourced from premium farms.', 1),
       ('Tomatoes (1kg)', 150.00, 'Farm-fresh ripe tomatoes ideal for cooking and salads.', 2),
       ('Potatoes (2kg)', 220.00, 'Clean, premium-quality potatoes suitable for frying and curries.', 2),
       ('Milk 1 Liter – Olpers', 250.00, 'Ultra-heat-treated (UHT) full cream milk with rich taste.', 3),
       ('Yogurt Cup 400g – Nestlé', 190.00, 'Smooth and creamy yogurt, perfect for daily meals.', 3),
       ('Whole Wheat Bread – Dawn', 160.00, 'Soft whole-wheat bread with no artificial preservatives.', 4),
       ('Chocolate Donuts – Box of 4', 350.00, 'Freshly baked donuts topped with rich chocolate glaze.', 4),
       ('Mineral Water 1.5L – Nestlé', 120.00, 'Pure and refreshing drinking water for daily hydration.', 5),
       ('Coca-Cola 1.25L', 180.00, 'Classic carbonated soft drink with the original Coke taste.', 5);
