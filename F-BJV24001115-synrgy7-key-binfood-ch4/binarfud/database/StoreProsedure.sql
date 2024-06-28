-- Stored Procedure untuk Tabel merchant

-- Insert Merchant
DELIMITER //
CREATE PROCEDURE insertMerchant(
    IN p_merchant_id UUID,
    IN p_merchant_name VARCHAR(100),
    IN p_merchant_location VARCHAR(255),
    IN p_open BOOLEAN
)
BEGIN
    INSERT INTO merchant (merchant_id, merchant_name, merchant_location, open)
    VALUES (p_merchant_id, p_merchant_name, p_merchant_location, p_open);
END //
DELIMITER ;

-- Update Merchant
DELIMITER //
CREATE PROCEDURE updateMerchant(
    IN p_merchant_id UUID,
    IN p_merchant_name VARCHAR(100),
    IN p_merchant_location VARCHAR(255),
    IN p_open BOOLEAN
)
BEGIN
    UPDATE merchant
    SET merchant_name = p_merchant_name,
        merchant_location = p_merchant_location,
        open = p_open
    WHERE merchant_id = p_merchant_id;
END //
DELIMITER ;

-- Delete Merchant
DELIMITER //
CREATE PROCEDURE deleteMerchant(
    IN p_merchant_id UUID
)
BEGIN
    DELETE FROM merchant WHERE merchant_id = p_merchant_id;
END //
DELIMITER ;

-- Stored Procedure untuk Tabel users

-- Insert User
DELIMITER //
CREATE PROCEDURE insertUser(
    IN p_user_id UUID,
    IN p_username VARCHAR(255),
    IN p_email_address VARCHAR(255),
    IN p_password VARCHAR(255)
)
BEGIN
    INSERT INTO users (user_id, username, email_address, password)
    VALUES (p_user_id, p_username, p_email_address, p_password);
END //
DELIMITER ;

-- Update User
DELIMITER //
CREATE PROCEDURE updateUser(
    IN p_user_id UUID,
    IN p_username VARCHAR(255),
    IN p_email_address VARCHAR(255),
    IN p_password VARCHAR(255)
)
BEGIN
    UPDATE users
    SET username = p_username,
        email_address = p_email_address,
        password = p_password
    WHERE user_id = p_user_id;
END //
DELIMITER ;

-- Delete User
DELIMITER //
CREATE PROCEDURE deleteUser(
    IN p_user_id UUID
)
BEGIN
    DELETE FROM users WHERE user_id = p_user_id;
END //
DELIMITER ;

-- Stored Procedure untuk Tabel product

-- Insert Product
DELIMITER //
CREATE PROCEDURE insertProduct(
    IN p_product_id UUID,
    IN p_product_name VARCHAR(100),
    IN p_price DOUBLE PRECISION,
    IN p_merchant_id UUID
)
BEGIN
    INSERT INTO product (product_id, product_name, price, merchant_id)
    VALUES (p_product_id, p_product_name, p_price, p_merchant_id);
END //
DELIMITER ;

-- Update Product
DELIMITER //
CREATE PROCEDURE updateProduct(
    IN p_product_id UUID,
    IN p_product_name VARCHAR(100),
    IN p_price DOUBLE PRECISION,
    IN p_merchant_id UUID
)
BEGIN
    UPDATE product
    SET product_name = p_product_name,
        price = p_price,
        merchant_id = p_merchant_id
    WHERE product_id = p_product_id;
END //
DELIMITER ;

-- Delete Product
DELIMITER //
CREATE PROCEDURE deleteProduct(
    IN p_product_id UUID
)
BEGIN
    DELETE FROM product WHERE product_id = p_product_id;
END //
DELIMITER ;

-- Stored Procedure untuk Tabel orders

-- Insert Order
DELIMITER //
CREATE PROCEDURE insertOrder(
    IN p_order_id UUID,
    IN p_destination_address VARCHAR(255),
    IN p_order_time DATE,
    IN p_complete BOOLEAN,
    IN p_user_id UUID
)
BEGIN
    INSERT INTO orders (order_id, destination_address, order_time, complete, user_id)
    VALUES (p_order_id, p_destination_address, p_order_time, p_complete, p_user_id);
END //
DELIMITER ;

-- Update Order
DELIMITER //
CREATE PROCEDURE updateOrder(
    IN p_order_id UUID,
    IN p_destination_address VARCHAR(255),
    IN p_order_time DATE,
    IN p_complete BOOLEAN,
    IN p_user_id UUID
)
BEGIN
    UPDATE orders
    SET destination_address = p_destination_address,
        order_time = p_order_time,
        complete = p_complete,
        user_id = p_user_id
    WHERE order_id = p_order_id;
END //
DELIMITER ;

-- Delete Order
DELIMITER //
CREATE PROCEDURE deleteOrder(
    IN p_order_id UUID
)
BEGIN
    DELETE FROM orders WHERE order_id = p_order_id;
END //
DELIMITER ;

-- Stored Procedure untuk Tabel order_detail

-- Insert Order Detail
DELIMITER //
CREATE PROCEDURE insertOrderDetail(
    IN p_order_detail_id UUID,
    IN p_quantity INTEGER,
    IN p_total_price DOUBLE PRECISION,
    IN p_product_id UUID,
    IN p_order_id UUID
)
BEGIN
    INSERT INTO order_detail (order_detail_id, quantity, total_price, product_id, order_id)
    VALUES (p_order_detail_id, p_quantity, p_total_price, p_product_id, p_order_id);
END //
DELIMITER ;

-- Update Order Detail
DELIMITER //
CREATE PROCEDURE updateOrderDetail(
    IN p_order_detail_id UUID,
    IN p_quantity INTEGER,
    IN p_total_price DOUBLE PRECISION,
    IN p_product_id UUID,
    IN p_order_id UUID
)
BEGIN
    UPDATE order_detail
    SET quantity = p_quantity,
        total_price = p_total_price,
        product_id = p_product_id,
        order_id = p_order_id
    WHERE order_detail_id = p_order_detail_id;
END //
DELIMITER ;

-- Delete Order Detail
DELIMITER //
CREATE PROCEDURE deleteOrderDetail(
    IN p_order_detail_id UUID
)
BEGIN
    DELETE FROM order_detail WHERE order_detail_id = p_order_detail_id;
END //
DELIMITER ;
