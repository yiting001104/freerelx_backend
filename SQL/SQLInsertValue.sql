use Hotel;

INSERT INTO roomState (state)
VALUES
('待入住'),
('已入住'),
('已退房(未清潔)'),
('準備完成(已清潔)');



INSERT INTO roomType ( chinese, english, japanese) VALUES
( N'標準套房', 'Standard Guestroom', N'スタンダードフロアー'),
( N'商務套房', 'Executive Guestroom', N'エグぜクティブフロア'),
( N'尊榮套房', 'Deluxe Suites', N'デラックススイート');


INSERT INTO roomLevel ( chinese, english, japanese) VALUES
( N'精緻', 'Superior', N'スーペリアルーム'),
( N'豪華', 'Deluxe', N'デラックスルーム'),
( N'家庭', 'Family', N'プレミアムファミリールーム'),
( N'貴賓', 'VIP', N'VIPスイート'),
( N'皇家', 'Royal', N'ロイヤルスイート'),
( N'總統', 'Presidential', N'レジデンシャル スイート');


INSERT INTO RoomInformation (room_type_id, room_level_id, bed_type, max_occupancy, room_price, room_photo, room_depiction) VALUES
(1, 1, '標準客房', 2, 7000, 'http://bosscode.monoame.com/20170323_vue_comp/img/room%20(1).jpg', 'Cozy room with a view of the city skyline.'),
(1, 2, '精緻客房', 2, 7800, 'https://www.grand-hilai.com/room/upload/room_list_pic/twL_room_22F02_nuswf33jvc.jpg', 'Spacious room with modern amenities.'),
(1, 3, '豪華客房', 2, 8600, 'http://bosscode.monoame.com/20170323_vue_comp/img/room%20(2).jpg', 'Luxurious suite with panoramic ocean views.'),
(1, 4, '豪華家庭房', 2, 8200, 'https://www.grand-hilai.com/room-detail/upload/room_b/twL_room_22F02_gkv2it2qcn.jpg', 'Cozy room with a view of the city skyline.'),
(2, 1, '標準商務房', 2, 8200,'http://bosscode.monoame.com/20170323_vue_comp/img/room (5).jpg', 'Spacious room with modern amenities.'),
(2, 3, '尊榮商務房', 2, 9600, 'https://www.grand-hilai.com/room-detail/upload/room_b/twL_room_23B24_piuj5kh9pi.jpg', 'Luxurious suite with panoramic ocean views.'),
(3, 1, '大使套房', 2, 12800, 'http://bosscode.monoame.com/20170323_vue_comp/img/room (6).jpg', 'Cozy room with a view of the city skyline.'),
(3, 2, '皇家套房', 2, 22800, 'https://www.grand-hilai.com/room-detail/upload/room_b/twL_room_23A31_fcxih8b8ja.jpg', 'Spacious room with modern amenities.'),
(3, 3, '總統套房', 2, 30000, 'https://s.yimg.com/ny/api/res/1.2/pk2B1_Nn2Opf2CgIuBO6Ag--/YXBwaWQ9aGlnaGxhbmRlcjt3PTk2MDtoPTU0MDtjZj13ZWJw/https://media.zenfs.com/en/nownews.com/2c734ac1f9d0bdfc0d27e98bfda65e51', 'Elegant room with a comfortable queen-sized bed.');

INSERT INTO roomManagement (room_number, room_state_id, repair_status, room_information_id) VALUES
(201, 4, 'Good condition', 1),
(202, 4, 'Good condition', 1),
(203, 4, 'Good condition', 1),
(204, 4, 'Good condition', 1),
(205, 4, 'Good condition', 1),
(206, 4, 'Good condition', 2),
(207, 4, 'Good condition', 2),
(208, 4, 'Good condition', 2),
(209, 4, 'Good condition', 2),
(301, 4, 'Good condition', 2),
(302, 4, 'Good condition', 3),
(303, 4, 'Good condition', 3),
(304, 4, 'Good condition', 3),
(305, 4, 'Good condition', 3),
(306, 4, 'Good condition', 3),
(307, 4, 'Good condition', 4),
(308, 4, 'Good condition', 4),
(309, 4, 'Good condition', 4),
(401, 4, 'Good condition', 4),
(402, 4, 'Good condition', 4),
(403, 4, 'Good condition', 5),
(404, 4, 'Good condition', 5),
(405, 4, 'Good condition', 5),
(406, 4, 'Good condition', 5),
(407, 4, 'Good condition', 5),
(408, 4, 'Good condition', 6),
(409, 4, 'Good condition', 6),
(501, 4, 'Good condition', 6),
(502, 4, 'Good condition', 6),
(503, 4, 'Good condition', 6),
(504, 4, 'Good condition', 7),
(505, 4, 'Good condition', 7),
(506, 4, 'Good condition', 7),
(507, 4, 'Good condition', 7),
(508, 4, 'Good condition', 7),
(509, 4, 'Good condition', 8),
(601, 4, 'Good condition', 8),
(602, 4, 'Good condition', 8),
(603, 4, 'Good condition', 8),
(604, 4, 'Good condition', 8),
(605, 4, 'Good condition', 9),
(606, 4, 'Good condition', 9),
(607, 4, 'Good condition', 9),
(608, 4, 'Good condition', 9),
(609, 4, 'Good condition', 9);


INSERT INTO Minibar (minibar_item, minibar_price, minibar_make, minibar_photo, minibar_expire)
VALUES
('whisky', 800, '2024-05-09', (SELECT BulkColumn FROM OPENROWSET(BULK 'C:\images\whisky.jpg', SINGLE_BLOB) AS minibar_photo), 12),
('soda drink', 120, '2024-04-01', (SELECT BulkColumn FROM OPENROWSET(BULK 'C:\images\soda_drink.jpg', SINGLE_BLOB) AS minibar_photo), 225),
('coffee', 120, '2024-05-09', (SELECT BulkColumn FROM OPENROWSET(BULK 'C:\images\coffee.jpg', SINGLE_BLOB) AS minibar_photo), 52),
('instant noodles', 80, '2024-06-19', (SELECT BulkColumn FROM OPENROWSET(BULK 'C:\images\instant_noodles.jpg', SINGLE_BLOB) AS minibar_photo), 152),
('mineral water', 80, '2024-07-29', (SELECT BulkColumn FROM OPENROWSET(BULK 'C:\images\mineral_water.jpg', SINGLE_BLOB) AS minibar_photo), 3122),
('cola', 120, '2024-07-29', (SELECT BulkColumn FROM OPENROWSET(BULK 'C:\images\cola.jpg', SINGLE_BLOB) AS minibar_photo), 222),
('ice cream', 200, '2024-07-29', (SELECT BulkColumn FROM OPENROWSET(BULK 'C:\images\ice_cream.jpg', SINGLE_BLOB) AS minibar_photo), 120),
('coffee', 80, '2024-07-29', (SELECT BulkColumn FROM OPENROWSET(BULK 'C:\images\coffee.jpg', SINGLE_BLOB) AS minibar_photo), 92),
('miketea', 30, '2024-07-29', (SELECT BulkColumn FROM OPENROWSET(BULK 'C:\images\mike_tea.jpg', SINGLE_BLOB) AS minibar_photo), 37),
('hotdog', 60, '2024-07-29', (SELECT BulkColumn FROM OPENROWSET(BULK 'C:\images\hotdog.jpg', SINGLE_BLOB) AS minibar_photo), 86),
('snack', 30, '2024-07-29', (SELECT BulkColumn FROM OPENROWSET(BULK 'C:\images\snack.jpg', SINGLE_BLOB) AS minibar_photo), 410),
('hand pulled noodle', 250, '2024-07-29', (SELECT BulkColumn FROM OPENROWSET(BULK 'C:\images\hand-pulled_noodle.jpg', SINGLE_BLOB) AS minibar_photo), 42);


INSERT INTO member( name, gender, birth, national_id, email, phone_number, credit_card, contact_address, password, registration_date,member_status,nationality,total_bonus_points,login_time) VALUES
( 'Ernest Barnes', '男', '1990/7/1', 'G63184808', 'Ernest.Barnes31@outlook.com','669-792-1661', '************4322', '高雄市旗山區中寮二路14號', 'scknkscn290u94', '2014/10/3','normal', 'Taiwan',400.6, '2014/10/30'),
( 'Rebecca Parker', '女', '1995/9/5', 'K231674514', 'Rebecca_Parker@comcast.net','652-885-2745', '************3734', '苗栗縣苗栗市金龍街10號', 'gheuijf982038', '2015/6/11','normal', 'Taiwan',423.7, '2015/6/12'),
( 'Laura Murray', '女', '1996/3/15', 'H253410978', 'Laura_M@gmail.com','364-656-8427', '************5677', '桃園市桃園區大華五街18號', 'wefwf46', '2015/6/12','normal', 'Taiwan',345.7, '2015/6/15'),
( 'Linda Hines', '女', '1997/7/11', 'G277633276', 'LHines@verizon.com','713-226-5883', '************5498', '宜蘭縣冬山鄉東七路33號', 'wrew3524!', '2015/6/12','normal', 'Taiwan',498.8,'2015/6/20');



INSERT INTO orderRoom(orderdate, order_person_name, gender, birth, national_id, email, phone_number, credit_card, transaction_password, adult_pax,child_pax,room_type_amount,arrival_date,reservation_status, reservation_status_date, member_id, cancellation_reason, checkout_date, base_price) VALUES
( '2015/4/8','Heather Hart', '男', '1994/2/1', 'L283271972', 'Heather.H@xfinity.com','431-329-6663', '************2780', 'efo2oif31890',4,1,2, '2015/5/1', 'Check-Out', '2015/7/9', null, null, '2015/7/9',33033.0),
( '2015/6/11','Ernest Barnes', '男', '1990/7/1', 'G63184808', 'Ernest.Barnes31@outlook.com','669-792-1661', '************4322', 'scknkscn290u94',2,1,1, '2015/7/1', 'Check-Out', '2015/7/9', 1, null, '2015/7/9', 14784.0),
( '2015/8/31','Rebecca Parker', '女', '1995/9/5', 'K231674514', 'Rebecca_Parker@comcast.net','652-885-2745', '************3734', 'gheuijf982038',1,1,1,'2015/9/6', 'Check-Out','2015/9/9',2,null,'2015/9/9',18249.0),
( '2015/10/8','Laura Murray', '女', '1996/3/15', 'H253410978', 'Laura_M@gmail.com','364-656-8427', '************5677', 'wefwf46',3,0,1,'2015/11/18', 'Canceled', '2015/11/1',3,'改變計畫', '2015/11/20',0),
('2015/10/14', 'Linda Hines', '女', '1997/7/11', 'G277633276', 'LHines@verizon.com','713-226-5883', '************5498', 'wrew3524!',2,0,1,'2015/12/21', 'Canceled', '2015/12/21',4,'改變計畫', '2015/12/22',0);

INSERT INTO orderRoomDetail (room_amount, price, room_Information_Id, order_id) VALUES
(1,15800, 3,1),
(1, 12800,1,1),
(1,12800,1,2),
(4,15800,1,3);

INSERT INTO creditCardDiscount(cooperate_bank, discount) VALUES
('A-Bank', 0.95),
('B-Bank', 0.9),
('C-Bank', 0.9),
('D-Bank', 0.88);

INSERT INTO refundType(type, refund_ratio) values
('21天前', 1),
('14天前', 0.8),
('7天前', 0.5),
('小於7天', 0);

INSERT INTO transactionTable(amount, order_id, last_five_account_number, transfer_date, taxIDNumber, transaction_status, payment_method, unsubscribe_date, refund_amount) VALUES
(7500,1,'2780', '2015/4/10', '29932116', 'done', '匯款', null,null),
(14784,2,'4322', '2015/6/12', '91794839', 'done', '信用卡交易', null,null),
(18249,3,'3734', '2015/9/1', '56685047', 'done', '匯款', null,null),
(18249,5,'9263','2015/11/4', '2929953', 'done', '匯款', '2015/12/13', 9124.5);

INSERT INTO [Hotel].[dbo].[FacilityOrder] ( [MEMBER_ID], [PHONE_NUMBER], [FACILITY_ID], [FACILITY_STATUS], [FACILITY_BOOKING_DATE], [START_TIME], [END_TIME]) VALUES (1,'669-792-1661','1','B', '2015-05-01', '2015-05-01 18:00:00',  '2015-05-01 19:00:00');
INSERT INTO [Hotel].[dbo].[FacilityOrder] ( [MEMBER_ID], [PHONE_NUMBER], [FACILITY_ID], [FACILITY_STATUS], [FACILITY_BOOKING_DATE], [START_TIME], [END_TIME]) VALUES (2,'652-885-2745','2','B', '2015-07-01', '2015-07-01 17:00:00',  '2015-07-01 18:00:00');
INSERT INTO [Hotel].[dbo].[FacilityOrder] ( [MEMBER_ID], [PHONE_NUMBER], [FACILITY_ID], [FACILITY_STATUS], [FACILITY_BOOKING_DATE], [START_TIME], [END_TIME]) VALUES (3,'364-656-8427','2','C', '2015-09-06', '2015-09-06 17:00:00',  '2015-09-06 18:00:00');
INSERT INTO [Hotel].[dbo].[FacilityOrder] ( [MEMBER_ID], [PHONE_NUMBER], [FACILITY_ID], [FACILITY_STATUS], [FACILITY_BOOKING_DATE], [START_TIME], [END_TIME]) VALUES (4,'713-226-5883','1','C', '2015-11-18', '2015-11-18 19:00:00',  '2015-11-18 20:00:00');
INSERT INTO [Hotel].[dbo].[FacilityOrder] ( [MEMBER_ID], [PHONE_NUMBER], [FACILITY_ID], [FACILITY_STATUS], [FACILITY_BOOKING_DATE], [START_TIME], [END_TIME]) VALUES (5,'190-271-6743','7','B', '2015-12-21', '2015-12-21 19:00:00',  '2015-12-21 20:00:00');

INSERT INTO supplier (product_manufacturer_address,product_manufacturer,product_manufacturer_contact_email,product_manufacturer_contact_phone,product_manufacturer_contact_name)VALUES
('台北市大安區','專賣蛋糕廠商','cake@gmail.com','0973647111','蛋糕達人'),
('台北市信義區','專賣枕頭廠商','pillow@gmail.com','0973647222','枕頭達人')



INSERT INTO housingManagement (room_management_id, order_id, remarks, checkInTime, checkOutTime, total_compensation_fee, total_additional_fee)
VALUES 
(1, 1, 'No remarks', '2024-06-05 14:00:00', '2024-06-10 12:00:00', 0.00, 0.00),
(2, 2, 'No remarks', '2024-06-06 14:00:00', '2024-06-11 12:00:00', 0.00, 0.00),
(3, 3, 'No remarks', '2024-06-07 14:00:00', '2024-06-12 12:00:00', 0.00, 0.00),
(4, 4, 'No remarks', '2024-06-08 14:00:00', '2024-06-13 12:00:00', 0.00, 0.00),
(5, 5, 'No remarks', '2024-06-09 14:00:00', '2024-06-14 12:00:00', 0.00, 0.00);


INSERT INTO checkOutInspection (compensation, compensation_fee, compensation_photo, housing_management_id)
VALUES 
('Broken lamp', 50.00, 0x123456789ABCDEF, 1),
('Scratched table', 30.00, 0x23456789ABCDEF1, 2),
('Stained carpet', 40.00, 0x3456789ABCDEF12, 3);


INSERT INTO additionalCharges (minibar_id, housing_management_id, quantity, amount)
VALUES 
(1, 1, 2, 30.00),
(2, 2, 1, 15.00),
(3, 3, 3, 45.00),
(4, 4, 1, 20.00),
(5, 5, 2, 40.00);

INSERT INTO product (product_expected_arrival_day,product_description,product_name,product_price,product_stock,product_supplier_id)VALUES
(1,'據傳當時帝王使用材質','帝王枕頭',1000,200,2),
(1,'保證好睡','暢銷枕頭',700,200,2),
(1,'夏季必備','夏季枕頭',500,200,2),
(1,'夏季必備','冬季枕頭',450,200,2),
(1,'雲霧般的舒適感，純白透氣羽絲絨一入，締造五星級睡眠享受的極致奢華。','五星級純白透氣羽絲絨１入',1400,200,2),
(1,'奢華品質融合，五星級有白有棕透氣羽絲絨各一入，打造無與倫比的睡眠體驗。','有白有棕透氣羽絲絨各１入',1000,200,2),
(1,'絢爛記憶：帶您進入夢幻般的睡眠之旅。','記憶枕頭１入',1600,200,2),
(1,'適合作為派對、聚會或下午茶的點心','草莓杯子蛋糕',300,200,1),
(1,'外酥內軟，沾滿香濃巧克力醬，是美味的甜點','巧克力甜甜圈',300,200,1),
(1,'適合作為派對、聚會或下午茶的點心','巧克力杯子蛋糕',300,200,1),
(1,'濃郁巧克力餅乾與香滑奶油夾心，組成的美味蛋糕，口感豐富，令人愛不釋手。','Oreo Cake',300,200,1)

INSERT INTO employee (name, position, premission, password, emoloyee_status, email) VALUES
('Jennifer Curry', 'Housekeeping', '查看管理訂單', '$2a$10$sPLEJa42x1v7XLgMmJpYt.m0vTKhHljgrcMnp8u0fmF.qgiN8HS6G	', '在職', 'JenniferCurry@hotmail.com'),
('Test1', 'GeneralManger', '沒有限制', '$2a$10$HrNRCY66ihcVJ/RDZWgMReawPSXKLIHNOYv2tra9cReHzpOYtLhAC', '在職', 'Test1@protonmail.com')
