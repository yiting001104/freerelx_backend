use Hotel;
 drop table if exists cart;
 drop table if exists order_details;
 drop table if exists productphoto;
 drop table if exists orders;
 drop table if exists product;
 drop table if exists category
 drop table if exists supplier;
 drop table if exists AdditionalCharges;
 drop table if exists CheckOutInspection;
 drop table if exists HousingManagement;
 drop table if exists orderRoomDetail;
 drop table if exists  comment;
 drop table if exists transactionTable
 drop table if exists creditCardDiscount;
 drop table if exists  transactionTable;
 drop table if exists orderRoom;
 drop table if exists member;
 drop table if exists employee;
 drop table if exists income;

create table member (  
    member_id        int identity,
    name          nvarchar(255) not null,
    gender  nvarchar(255) not null,
	birth date not null,
	national_id varchar(255) not null,
	email varchar(255) not null,
	phone_number varchar(255) not null,
	credit_card varchar(255),
	contact_address nvarchar(255) not null,
	password varchar(255) not null,
	registration_date datetime2(6) not null,
	member_status varchar(255) default 'normal',
	nationality nvarchar(255) not null,
	total_bonus_points decimal(20,6) default 0.0,
	login_time datetime2(6),
	login_status nvarchar(255) default '尚未登入',
	picture varbinary(max),
	constraint PK_MemberId primary key (member_id),
	constraint UQ_phone_number unique (phone_number) ,

);  

 
create table orderRoom (  
    order_id        int identity,
	orderdate datetime2(6) not null,
    order_person_name          nvarchar(255) not null,
    gender  nvarchar(255) not null,
	birth date not null,
	national_id varchar(255) not null,
	email varchar(255) not null,
	phone_number varchar(255) not null,
	credit_card varchar(255),
	adult_pax int not null,
	child_pax int default 0,
	room_type_amount int not null,
	arrival_date datetime2(6) not null,
	checkout_date datetime2(6) not null,
	reservation_status varchar(255)  default 'Awaiting payment',
	reservation_status_date datetime2(6) not null,
	transaction_password varchar(255) not null,
	member_id        int ,
	cancellation_reason  nvarchar(255),

	base_price decimal(20,6),
	stay_person_name nvarchar(255),
	stay_person_gender nvarchar(255),
	stay_person_birth date,
	stay_person_national_id varchar(255),
	stay_person_phone varchar(255),
	stay_person_Email varchar(255),
	remark nvarchar(255),
	          constraint PK_OrderId primary key (order_id),
          constraint FK_MemberId foreign key (member_id)
		  references Hotel.dbo.member (member_id),
);  

create table creditCardDiscount (  
    bank_id        int identity,
	cooperate_bank  nvarchar(255) not null,
	discount decimal(20,6) not null,

	constraint PK_BankId primary key (bank_id ),

);  

 drop table if exists refundType;
create table refundType(
	refundType_id int identity,
	type nvarchar(255) not null,
	refund_ratio decimal(20,6)not null,

	constraint PK_RefundTypeId primary key (refundType_id ),
);

create table transactionTable(
	transaction_Id int identity,
	amount decimal(20,6) not null,
	order_id  int not null,
	last_five_account_number varchar(255) not null,
	transfer_date datetime2(6) ,
	discount_id int,
	taxIDNumber varchar(255),
	transaction_status varchar(255) default ' not completed',
	payment_method nvarchar(255),
	unsubscribe_date datetime2(6),
	refund_amount  decimal(20,6),
	refund_id  int,
	remark nvarchar(255),





	constraint PK_transactionId primary key (transaction_Id ),

	constraint FK_OrderId_Transaction foreign key (order_id)
		  references Hotel.dbo.orderRoom (order_id),

	constraint FK_Discount foreign key (discount_id)
		  references Hotel.dbo.creditCardDiscount (bank_id),

	constraint FK_Refunde foreign key (refund_id)
		  references Hotel.dbo.refundType (refundType_id),
);
----------------------------------------------------------------------------------
--其他

create table employee(
	employee_id int identity,
	name nvarchar(255) not null,
	position nvarchar(255) not null,
	premission nvarchar(255) not null,

	constraint PK_EmployeeId primary key (employee_id ),
);

create table income(
	income_id int identity,
	amount decimal(20,6) not null,
	create_date datetime2(6) not null default getdate(),
	income_type nvarchar(255) not null,

	constraint PK_IncomeId primary key (income_id)
);

create Table comment(
	comment_id int identity,
	comment_text nvarchar(255) not null,
	create_date datetime2(6) not null default getdate(),
	picture varbinary(max),
	situation_type nvarchar(255) not null,
	member_Id int not null,
	type_instance nvarchar(255) not null,
	score decimal(2,1) not null,

	constraint PK_CommentId primary key (comment_id),
	constraint FK_MemberId_Comment foreign key (member_Id)
		  references Hotel.dbo.member (member_Id),
);

----------------------------------------------------------------------
---房務相關
drop table if exists RoomManagement
drop table if exists RoomAssignment
drop table if exists RoomInformation
drop table if exists RoomLevel;
drop table if exists RoomType;
drop table if exists RoomState;
drop table if exists Minibar
 
CREATE TABLE RoomLevel (
    room_level_Id INT PRIMARY KEY IDENTITY,
    chinese NVARCHAR(50),
    english NVARCHAR(50),
    japanese NVARCHAR(50)
);

INSERT INTO RoomLevel ( chinese, english, japanese) VALUES
( N'精緻', 'Superior', N'スーペリアルーム'),
( N'豪華', 'Deluxe', N'デラックスルーム'),
( N'家庭', 'Family', N'プレミアムファミリールーム'),
( N'貴賓', 'VIP', N'VIPスイート'),
( N'皇家', 'Royal', N'ロイヤルスイート'),
( N'總統', 'Presidential', N'レジデンシャル スイート');


CREATE TABLE RoomType (
    room_type_Id INT PRIMARY KEY IDENTITY,
    chinese NVARCHAR(50),
    english NVARCHAR(50),
    japanese NVARCHAR(50)
);


INSERT INTO RoomType ( chinese, english, japanese) VALUES
( N'標準套房', 'Standard Guestroom', N'スタンダードフロアー'),
( N'商務套房', 'Executive Guestroom', N'エグぜクティブフロア'),
( N'尊榮套房', 'Deluxe Suites', N'デラックススイート');


CREATE TABLE RoomInformation (
    room_Information_Id INT PRIMARY KEY,
    room_type INT,
    room_level INT,
    bed_type NVARCHAR(50),
    max_occupancy INT,
    room_price DECIMAL(20, 6),
    room_picture VARBINARY(max),
    room_depiction NVARCHAR(255),
    FOREIGN KEY (room_type) REFERENCES RoomType(room_type_Id), 
    FOREIGN KEY (room_level) REFERENCES RoomLevel(room_level_Id) 
);

INSERT INTO RoomInformation ( room_Information_Id, room_type, room_level, bed_type, max_occupancy, room_price, room_picture, room_depiction) VALUES
(1, 1, 1, 'Twin Bed', 2, 12800, NULL, NULL),
(2, 1, 1, 'Queen Bed', 2, 12800, NULL, NULL),
(3, 1, 2, 'Twin Bed', 2, 15800, NULL, NULL),
(4, 1, 2, 'Queen Bed', 2, 15800, NULL, NULL),
(5, 1, 3, 'Two Double Bed', 4, 22800, NULL, NULL),
(6, 2, 1, 'Queen Bed', 2, 20800, NULL, NULL),
(7, 2, 2, 'Queen Bed', 2, 24800, NULL, NULL),
(8, 3, 2, 'Queen Bed', 2, 30000, NULL, NULL),
(9, 3, 4, 'Queen Bed', 2, 42000, NULL, NULL),
(10, 3, 5, 'Queen Bed', 2, 150000, NULL, NULL),
(11, 3, 6, 'Queen Bed', 2, 300000, NULL, NULL);


CREATE TABLE RoomAssignment (
    assignment_id INT PRIMARY KEY IDENTITY,
    roomInformation_Id INT,
    rooms_left INT,
    assignment_date DATE,
    FOREIGN KEY (roomInformation_Id) REFERENCES roomInformation ( room_Information_Id)
);

CREATE TABLE RoomState (
    room_state INT PRIMARY KEY,
    state NVARCHAR(255)
);

INSERT INTO RoomState (room_state, state)
VALUES
(1, '入住'),
(2, '尚未入住'),
(3, '已退房(未清潔)'),
(4, '準備完成(已清潔)');

CREATE TABLE RoomManagement (
    room_management_id INT PRIMARY KEY IDENTITY,
    room_id INT  UNIQUE,
    room_state INT,
    repair_status NVARCHAR(255),
    roomInformationId INT,
    FOREIGN KEY (room_state) REFERENCES RoomState(room_state), 
    FOREIGN KEY (roomInformationId) REFERENCES RoomInformation(room_Information_Id)
);



CREATE TABLE HousingManagement (
    housing_management_id INT PRIMARY KEY IDENTITY,
    room_id INT,
    member_Id INT,
    orderID INT,
    Remarks NVARCHAR(255),
    checkInTime DATETIME,
    checkOutTime DATETIME,
    total_additional_fee DECIMAL(20, 6),
    total_compensation_fee DECIMAL(20, 6),
    FOREIGN KEY (room_id) REFERENCES  RoomManagement (room_id),
    FOREIGN KEY (member_Id) REFERENCES Member(member_Id),
    FOREIGN KEY (orderID) REFERENCES orderRoom(order_id)
);

CREATE TABLE CheckOutInspection (
    id INT PRIMARY KEY IDENTITY,
    compensation NVARCHAR(255),
    compensation_fee DECIMAL(20, 6),
    compensation_photo varbinary(max),
    housing_managementId INT FOREIGN KEY REFERENCES HousingManagement(housing_management_id)
);


CREATE TABLE Minibar (
    minibar_id INT PRIMARY KEY IDENTITY,
    item NVARCHAR(50),
    price DECIMAL(10, 2)
);

INSERT INTO Minibar (item, price) VALUES
('whisky', 800),
('vodka', 800),
('wine', 800),
('soda drink', 120),
('sparkling water', 120),
('coffee', 120),
('instant noodles', 80),
('mineral water', 80);

CREATE TABLE AdditionalCharges (
    id INT PRIMARY KEY IDENTITY,
    item_id INT FOREIGN KEY REFERENCES minibar(minibar_id),
    quantity INT,
    amount DECIMAL(10, 2),
    housing_managementId INT FOREIGN KEY REFERENCES HousingManagement(housing_management_id)
);
----
create table orderRoomDetail(
	--orderDetial_id INT  IDENTITY,
	room_Information_Id INT not null,
	room_amount INT not null,
	price decimal(20,6),
	order_id int not null,

		

	constraint FK_RoomInformation_Id foreign key (room_Information_Id)
		  references Hotel.dbo.RoomInformation (room_Information_Id),

		  	constraint FK_OrderId_Detial foreign key (order_id)
		  references Hotel.dbo.orderRoom (order_id),

	constraint PK_OrderDetialId primary key (order_id, room_Information_Id),
);

------------------------------------------------------------
-- 購物車相關
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