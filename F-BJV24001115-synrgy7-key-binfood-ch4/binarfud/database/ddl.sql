-- Buat database baru
CREATE DATABASE key_db;

-- Buat tabel merchant
CREATE TABLE merchant (
    merchant_id UUID PRIMARY KEY,
    merchant_name VARCHAR(100) NOT NULL,
    merchant_location VARCHAR(255),
    open BOOLEAN
);

-- Buat tabel user
CREATE TABLE users (
    user_id UUID PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    email_address VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

-- Buat tabel product
CREATE TABLE product (
    product_id UUID PRIMARY KEY,
    product_name VARCHAR(100) NOT NULL,
    price DOUBLE PRECISION,
    merchant_id UUID,
    CONSTRAINT fk_merchant FOREIGN KEY (merchant_id) REFERENCES merchant (merchant_id)
);

-- Buat tabel orders
CREATE TABLE orders (
    order_id UUID PRIMARY KEY,
    destination_address VARCHAR(255),
    order_time DATE,
    complete BOOLEAN,
    user_id UUID,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (user_id)
);

-- Buat tabel order_detail
CREATE TABLE order_detail (
    order_detail_id UUID PRIMARY KEY,
    quantity INTEGER,
    total_price DOUBLE PRECISION,
    product_id UUID,
    order_id UUID,
    CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES product (product_id),
    CONSTRAINT fk_order FOREIGN KEY (order_id) REFERENCES orders (order_id)
);