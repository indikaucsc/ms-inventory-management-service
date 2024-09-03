-- 2. Inventory Management
CREATE TABLE products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    expiration_date DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Insert Products
INSERT INTO products (name, quantity, price, expiration_date) VALUES
('Paracetamol', 1000, 0.50, '2025-12-31'),
('Ibuprofen', 500, 1.20, '2025-06-30'),
('Aspirin', 300, 0.80, '2024-11-15'),
('Amoxicillin', 200, 2.50, '2026-01-01'),
('Cetirizine', 150, 1.00, '2025-08-20');