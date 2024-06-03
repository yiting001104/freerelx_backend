use Hotel;
 drop table if exists alert;
 drop table  if exists order_details;
 drop table if exists cart ;
 drop table  if exists productphoto;
 drop table  if exists product;
 drop table  if exists orders;
 drop table  if exists supplier;

 drop table if exists AdditionalCharges;
 drop table if exists CheckOutInspection;
 drop table if exists HousingManagement;
 drop table if exists orderRoomDetail;
 drop table if exists  comment;
 drop table if exists transactionTable;
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
	last_five_account_number varchar(255),
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
		  references Hotel.dbo.creditCardDiscount (bank_id) ON DELETE SET NULL,

	constraint FK_Refunde foreign key (refund_id)
		  references Hotel.dbo.refundType (refundType_id) ON DELETE SET NULL,
);
----------------------------------------------------------------------------------
--其他

create table employee(
	employee_id int identity,
	name nvarchar(255) not null,
	position nvarchar(255) not null,
	premission nvarchar(255) not null,
	password varchar(255) not null,
	emoloyee_status nvarchar(255) default '在職',
	email varchar(255) not null,
	constraint PK_EmployeeId primary key (employee_id ),
);

create table income(
	income_id int identity,
	amount decimal(20,6) not null,
	create_date datetime2(6) default getdate(),
	income_type nvarchar(255) not null,
	item_name nvarchar(255),

	constraint PK_IncomeId primary key (income_id)
);

create Table comment(
	comment_id int identity,
	comment_text nvarchar(255) not null,
	create_date datetime2(6) default getdate(),
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
 
CREATE TABLE roomLevel (
    room_level_id INT PRIMARY KEY IDENTITY,
    chinese NVARCHAR(50),
    english NVARCHAR(50),
    japanese NVARCHAR(50)
);

CREATE TABLE roomType (
    room_type_id INT PRIMARY KEY IDENTITY,
    chinese NVARCHAR(50),
    english NVARCHAR(50),
    japanese NVARCHAR(50)
);

CREATE TABLE roomInformation (
    room_information_id INT PRIMARY KEY IDENTITY,
    room_type_id INT,
    room_level_id INT,
    bed_type NVARCHAR(50),
    max_occupancy INT,
    room_price DECIMAL(20, 6),
    room_photo VARBINARY(max),
    room_depiction NVARCHAR(255),
	room_total int default 5,
    constraint FK_RoomInformationId FOREIGN KEY (room_type_id) REFERENCES roomType(room_type_id), 
    constraint FK_RoomLevel FOREIGN KEY (room_level_id) REFERENCES roomLevel(room_level_id) 
);

	create table orderRoomDetail(
	--orderDetial_id  INT PRIMARY KEY IDENTITY,
	room_Information_Id INT not null,
	room_amount INT not null,
	price decimal(20,6),
	order_id int not null,
	constraint FK_RoomInformation_Id foreign key (room_Information_Id)references Hotel.dbo.RoomInformation (room_Information_Id),
	constraint FK_OrderId_Detial foreign key (order_id)references Hotel.dbo.orderRoom (order_id),
	constraint PK_OrderDetialId primary key (order_id, room_Information_Id)
);

CREATE TABLE roomAssignment (
    assignment_id INT PRIMARY KEY IDENTITY,
    room_information_id INT,
    rooms_left INT,
    assignment_date DATETIME2(6),
	order_room_detail_id INT
    --constraint FK_OrderIdHMana FOREIGN KEY (order_room_detail_id) REFERENCES  orderRoomDetail(order_id, room_Information_Id),
	constraint FK_RoomInformationIdAss FOREIGN KEY (room_information_id) REFERENCES roomInformation (room_information_id)
);

CREATE TABLE roomState (
    room_state_id INT PRIMARY KEY IDENTITY,
    state NVARCHAR(255)
);

CREATE TABLE roomManagement (
    room_management_id INT PRIMARY KEY IDENTITY,
     room_number int UNIQUE,
    room_state_id INT default 4,
    repair_status NVARCHAR(255),
    room_information_id INT,
	constraint FK_RoomStateMana FOREIGN KEY (room_state_id) REFERENCES roomState(room_state_id), 
	constraint FK_RoomInformationIdMana FOREIGN KEY (room_information_id) REFERENCES roomInformation(room_information_id)
);

CREATE TABLE housingManagement (
    housing_management_id INT PRIMARY KEY IDENTITY,
    room_management_id INT,
    order_id INT,
    remarks NVARCHAR(255),
    checkInTime DATETIME,
    checkOutTime DATETIME,
    total_compensation_fee DECIMAL(20, 6),
    total_additional_fee DECIMAL(20, 6),
   constraint FK_RoomManagementHmana  FOREIGN KEY (room_management_id) REFERENCES  roomManagement (room_management_id),
   constraint FK_OrderIdHMana FOREIGN KEY (order_id) REFERENCES  Hotel.dbo.orderRoom(order_id)
);

CREATE TABLE checkOutInspection (
    id INT PRIMARY KEY IDENTITY,
    compensation NVARCHAR(255),
    compensation_fee DECIMAL(20, 6),
    compensation_photo varbinary(max),
    housing_management_id INT, 
	constraint FK_HousingManagementIdCO FOREIGN KEY (housing_management_id) REFERENCES housingManagement(housing_management_id)
);


CREATE TABLE minibar (
    minibar_id INT PRIMARY KEY IDENTITY,
	minibar_price DECIMAL(20, 6),
	minibar_make　DATETIME2(6),
	minibar_expire　INT,
    minibar_item NVARCHAR(50),
	minibar_photo varbinary(max),
);


	CREATE TABLE additionalCharges (
    minibar_id INT,
    housing_management_id INT,
    quantity INT,
    amount DECIMAL(20, 6),
	constraint Fk_minibar foreign key (minibar_id)references minibar (minibar_id),
	constraint Fk_housingmanagement foreign key (housing_management_id)references housingManagement (housing_management_id),
	constraint PK_additionalCharges primary key (minibar_id, housing_management_id)
);


----
------------------------------------------------------------
-- 購物車相關
-- 建立 alert 表格
CREATE TABLE alert(
	id INT IDENTITY(1,1) PRIMARY KEY,
	alertmessage VARCHAR(255),
	member_id INT,
    FOREIGN KEY (member_id) REFERENCES member(member_id)
)
-- 建立 orders 表格
CREATE TABLE orders (
	id INT IDENTITY(1,1) PRIMARY KEY,
    addedTime DATETIME2(6),
	arriveddTime DATETIME2(6),
    contactAddress VARCHAR(255),
    memberName VARCHAR(255),
    orderstatus VARCHAR(255),
	payment VARCHAR(255),
    phoneNumber VARCHAR(255),
	payerName VARCHAR(255),
	payerPhoneNumber VARCHAR(255),
	payerContactAddress VARCHAR(255),
	total INT,
	usebonus INT,
	addbonus INT,
	totalminususebonus INT,
    member_id INT,
    FOREIGN KEY (member_id) REFERENCES member(member_id)
);

-- 建立 supplier 表格
CREATE TABLE supplier (
    product_supplier_id INT IDENTITY(1,1) PRIMARY KEY,
    product_manufacturer_address NVARCHAR(255),
    product_manufacturer_contact_name NVARCHAR(255),
    product_manufacturer_contact_email NVARCHAR(255),
    product_manufacturer NVARCHAR(255),
    product_manufacturer_contact_phone NVARCHAR(255)
);
-- 建立 product 表格
CREATE TABLE product (
    product_id INT IDENTITY(1,1) PRIMARY KEY,
    product_expected_arrival_day INT,
    product_description NVARCHAR(255),
    product_name NVARCHAR(255),
    product_price INT,
    product_stock INT,
    product_supplier_id INT,
    FOREIGN KEY (product_supplier_id) REFERENCES supplier(product_supplier_id)
);

-- 建立 productphoto 表格
CREATE TABLE productphoto (
    id INT IDENTITY(1,1) PRIMARY KEY,
    photoFile VARBINARY(MAX),
    product_id INT,
    FOREIGN KEY (product_id) REFERENCES product(product_id)
);
-- 1. 建立 cart 表格
CREATE TABLE cart (
    checkout BIT,
    quantity INT,
    member_member_id INT NOT NULL,
    id_product_id INT NOT NULL,
    PRIMARY KEY (id_product_id, member_member_id),
    FOREIGN KEY (id_product_id) REFERENCES product(product_id),
    FOREIGN KEY (member_member_id) REFERENCES member(member_id)
);

-- 建立 order_details 表格
CREATE TABLE order_details (
    detail_id INT IDENTITY(1,1) PRIMARY KEY,
    productmultiplequantity INT,
    quantity INT,
    order_id INT,
    product_id INT,
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (product_id) REFERENCES product(product_id)
);

--------------------------------------------------------
-- 公設
drop table if exists Facility
drop table if exists FacilityOrder
create table Facility (
        FACILITY int not null,
        MAXIMUM_CAPACITY int,
        FACILITY_NAME varchar(255),
        FACILITY_TYPE varchar(255),
        primary key (FACILITY)
    );
    
 create table FacilityOrder (
        FACILITY_BOOKING_DATE date,
        FACILITY_ORDER_ID int identity not null,
        MEMBER_ID int,
        END_TIME datetime2(6),
        START_TIME datetime2(6),
        FACILITY_ID varchar(255),
        FACILITY_STATUS varchar(255),
        PHONE_NUMBER varchar(255),
        primary key (FACILITY_ORDER_ID)	
	);