CREATE TABLE RoomLevel (
    room_level_Id INT PRIMARY KEY IDENTITY,
    chinese NVARCHAR(50),
    english NVARCHAR(50),
    japanese NVARCHAR(50)
);

INSERT INTO RoomLevel ( chinese, english, japanese) VALUES
( N'精緻', 'Superior', 'スーペリアルーム'),
( N'豪華', 'Deluxe', 'デラックスルーム'),
( N'家庭', 'Family', 'プレミアムファミリールーム'),
( N'貴賓', 'VIP', 'VIPスイート'),
( N'皇家', 'Royal', 'ロイヤルスイート'),
( N'總統', 'Presidential', 'レジデンシャル スイート');


CREATE TABLE RoomType (
    room_type_Id INT PRIMARY KEY IDENTITY,
    chinese NVARCHAR(50),
    english NVARCHAR(50),
    japanese NVARCHAR(50)
);


INSERT INTO RoomType ( chinese, english, japanese) VALUES
( N'標準套房', 'Standard Guestroom', 'スタンダードフロアー'),
( N'商務套房', 'Executive Guestroom', 'エグぜクティブフロア'),
( N'尊榮套房', 'Deluxe Suites', 'デラックススイート');


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
    --FOREIGN KEY (member_Id) REFERENCES Members(member_Id),
    --FOREIGN KEY (orderID) REFERENCES Orders(orderID)
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


