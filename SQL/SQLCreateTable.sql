  drop table if exists  comment;
 drop table if exists creditCardDiscount;
 drop table if exists  transactionTable;
 drop table if exists orderRoom;
 drop table if exists member;
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
	reservation_status varchar(255) not null,
	reservation_status_date datetime2(6) not null,
	transaction_password varchar(255) not null,
	member_id        int ,
	cancellation_reason  nvarchar(255),
	order_status  varchar(255) default ' not completed',
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

--------------------------------------------------------------------------------------
--¨ä¥L

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
	score decimal(2,1) not null,

	constraint PK_CommentId primary key (comment_id),
	constraint FK_MemberId_Comment foreign key (member_Id)
		  references Hotel.dbo.member (member_Id),
);