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