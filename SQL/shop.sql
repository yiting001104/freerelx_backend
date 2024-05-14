CREATE TABLE supplier (
    product_supplier_id INT IDENTITY NOT NULL,
    product_manufacturer_address VARCHAR(255),
    product_manufacturer_contact_name VARCHAR(255),
    product_manufacturer_contact_email VARCHAR(255),
    product_manufacturer VARCHAR(255),
    product_manufacturer_contact_phone VARCHAR(255),
    PRIMARY KEY (product_supplier_id)
);

CREATE TABLE category (
    category_id INT IDENTITY NOT NULL,
    category_name VARCHAR(255),
    PRIMARY KEY (category_id)
);


CREATE TABLE orders (
    id INT IDENTITY NOT NULL,
    member_id INT,
    PRIMARY KEY (id),
    FOREIGN KEY (member_id) REFERENCES member(member_id)
);

CREATE TABLE product (
    product_id INT IDENTITY NOT NULL,
    product_expected_arrival_day INT,
    product_description VARCHAR(255),
    product_name VARCHAR(255),
    product_price INT,
    product_stock INT,
    product_category_id INT,
    product_supplier_id INT,
    PRIMARY KEY (product_id),
    FOREIGN KEY (product_category_id) REFERENCES category(category_id),
    FOREIGN KEY (product_supplier_id) REFERENCES supplier(product_supplier_id)
);

CREATE TABLE productphoto (
    id INT IDENTITY NOT NULL,
    photoFile VARBINARY(MAX),
    photoName VARCHAR(255),
    product_id INT,
    PRIMARY KEY (id),
    FOREIGN KEY (product_id) REFERENCES product(product_id)
);

CREATE TABLE order_details (
    detail_id INT IDENTITY NOT NULL,
    addedTime DATETIME2(6),
    orderstatus VARCHAR(255),
    productmultiplequantity INT,
    quantity INT,
    order_id INT,
    product_id INT,
    PRIMARY KEY (detail_id),
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (product_id) REFERENCES product(product_id)
);

CREATE TABLE cart (
    quantity INT,
    member_member_id INT NOT NULL,
    id_product_id INT NOT NULL,
    PRIMARY KEY (id_product_id, member_member_id),
    FOREIGN KEY (id_product_id) REFERENCES product(product_id),
    FOREIGN KEY (member_member_id) REFERENCES member(member_id)
);
