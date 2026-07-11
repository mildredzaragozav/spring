-- schema.sql
CREATE TABLE IF NOT EXISTS orders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_id VARCHAR(255),
    quantity INT,
    status VARCHAR(255)
);