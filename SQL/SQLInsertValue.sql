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
(203, 2, 'Good condition', 1),
(204, 2, 'Good condition', 1),
(205, 3, 'Under maintenance', 1),
(206, 4, 'Good condition', 2),
(207, 4, 'Good condition', 2),
(208, 4, 'Good condition', 2),
(209, 4, 'Good condition', 2),
(301, 4, 'Good condition', 2),
(302, 4, 'Good condition', 3),
(303, 2, 'Good condition', 3),
(304, 2, 'Good condition', 3),
(305, 3, 'Under maintenance', 3),
(306, 4, 'Good condition', 3),
(307, 4, 'Good condition', 4),
(308, 4, 'Good condition', 4),
(309, 4, 'Good condition', 4),
(401, 4, 'Good condition', 4),
(402, 4, 'Good condition', 4),
(403, 2, 'Good condition', 5),
(404, 2, 'Good condition', 5),
(405, 3, 'Under maintenance', 5),
(406, 4, 'Good condition', 5),
(407, 4, 'Good condition', 5),
(408, 4, 'Good condition', 6),
(409, 4, 'Good condition', 6),
(501, 4, 'Good condition', 6),
(502, 4, 'Good condition', 6),
(503, 2, 'Good condition', 6),
(504, 2, 'Good condition', 7),
(505, 3, 'Under maintenance', 7),
(506, 4, 'Good condition', 7),
(507, 4, 'Good condition', 7),
(508, 4, 'Good condition', 7),
(509, 4, 'Good condition', 8),
(601, 4, 'Good condition', 8),
(602, 4, 'Good condition', 8),
(603, 2, 'Good condition', 8),
(604, 2, 'Good condition', 8),
(605, 3, 'Under maintenance', 9),
(606, 4, 'Good condition', 9),
(607, 4, 'Good condition', 9),
(608, 4, 'Good condition', 9),
(609, 4, 'Good condition', 9);




INSERT INTO member( name, gender, birth, national_id, email, phone_number, credit_card, contact_address, password, registration_date,member_status,nationality,total_bonus_points,login_time) VALUES
( 'Ernest Barnes', '男', '1990/7/1', 'G63184808', 'Ernest.Barnes31@outlook.com', '669-792-1661', '************4322', '高雄市旗山區中寮二路14號', 'scknkscn290u94', '2014/10/3', 'normal', 'Taiwan', 400.6, '2014/10/30'),
( 'Rebecca Parker', '女', '1995/9/5', 'K231674514', 'Rebecca_Parker@comcast.net', '652-885-2745', '************3734', '苗栗縣苗栗市金龍街10號', 'gheuijf982038', '2015/6/11', 'normal', 'Taiwan', 423.7, '2015/6/12'),
( 'Laura Murray', '女', '1996/3/15', 'H253410978', 'Laura_M@gmail.com', '364-656-8427', '************5677', '桃園市桃園區大華五街18號', 'wefwf46', '2015/6/12', 'normal', 'Taiwan', 345.7, '2015/6/15'),
( 'Linda Hines', '女', '1997/7/11', 'G277633276', 'LHines@verizon.com', '713-226-5883', '************5498', '宜蘭縣冬山鄉東七路33號', 'wrew3524!', '2015/6/12', 'normal', 'Taiwan', 498.8, '2015/6/20'),
( 'James Taylor', '男', '1992/2/20', 'D84126912', 'James.Taylor82@yahoo.com', '715-635-8882', '************1234', '台中市北屯區松竹路二段89號', 'dhsjdfsj29', '2016/4/5', 'normal', 'Taiwan', 312.5, '2016/4/10'),
( 'Susan King', '女', '1993/11/25', 'L302451389', 'Susan_King@icloud.com', '684-712-9631', '************8572', '新竹市東區中正路156號', 'kdfj84dfj', '2016/8/9', 'normal', 'Taiwan', 278.4, '2016/8/15'),
( 'Charles White', '男', '1994/8/30', 'E541278410', 'Charles.White@aol.com', '798-651-3341', '************5493', '台北市中山區松江路98號', 'dkjshf87', '2016/12/22', 'normal', 'Taiwan', 450.9, '2016/12/30'),
( 'Nancy Harris', '女', '1996/5/17', 'F128374590', 'Nancy.Harris@gmail.com', '725-614-7765', '************7854', '台南市南區府前路二段89號', 'nfie78fj', '2017/1/18', 'normal', 'Taiwan', 399.3, '2017/1/20'),
( 'Robert Lee', '男', '1998/1/4', 'B768345102', 'Robert.Lee@outlook.com', '789-512-3365', '************9654', '嘉義市西區民生南路45號', 'jdsf93fj', '2017/3/5', 'normal', 'Taiwan', 356.8, '2017/3/10'),
( 'Jennifer Clark', '女', '1991/10/9', 'C287541903', 'Jennifer.Clark@icloud.com', '631-854-7895', '************1235', '花蓮縣花蓮市中美路56號', 'dshfsj48', '2017/5/14', 'normal', 'Taiwan', 412.7, '2017/5/20'),
( 'Michael Johnson', '男', '1992/12/13', 'K561237849', 'Michael.Johnson@comcast.net', '712-845-9632', '************6547', '高雄市左營區文康路88號', 'kdjfhw83', '2017/8/8', 'normal', 'Taiwan', 489.6, '2017/8/15'),
( 'Emily Wilson', '女', '1993/6/6', 'L395641287', 'Emily.Wilson@gmail.com', '684-752-4963', '************3215', '新北市新莊區中正路168號', 'nfsd89f', '2017/11/11', 'normal', 'Taiwan', 379.4, '2017/11/15');


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

INSERT INTO comment (comment_text, situation_type, member_Id, type_instance, score) VALUES 
('Great service!', 'Positive', 1, 'Service', 5.0),
('Room was clean and tidy.', 'Positive', 2, 'Room', 4.5),
('Excellent location.', 'Positive', 3, 'Location', 4.0),
('Good value for money.', 'Positive', 4, 'Value', 4.5),
('Friendly staff.', 'Positive', 11, 'Service', 4.8),
('The bed was very comfortable.', 'Positive', 12, 'Room', 4.7),
('Convenient location near attractions.', 'Positive', 1, 'Location', 4.9),
('Affordable prices.', 'Positive', 2, 'Value', 4.6),
('環境大氣舒適，人員服務親切有禮，床還是席夢思的，早餐也好吃。住宿體驗比五星級飯店都棒，以後還會再入住。', 'Positive', 1, 'Room', 5.0),
('服務很棒的奢華飯店，是幫父母訂的，他住得很開心，有機會我們也會再去。', 'Positive', 2, 'Room', 4.8),
('商務包廂，隱密性高又幽靜，是私人聚會或商務餐敘的好地方。', 'Positive', 3, 'Room', 4.7),
('環境大氣舒適，人員服務親切有禮，床還是席夢思的，早餐也好吃。住宿體驗比五星級飯店都棒，以後還會再入住。', 'Positive', 4, 'Room', 4.9),
('服務很棒的奢華飯店，是幫父母訂的，他住得很開心，有機會我們也會再去。', 'Positive', 5, 'Room', 4.6),
('商務包廂，隱密性高又幽靜，是私人聚會或商務餐敘的好地方。', 'Positive', 6, 'Room', 4.8),
('環境大氣舒適，人員服務親切有禮，床還是席夢思的，早餐也好吃。住宿體驗比五星級飯店都棒，以後還會再入住。', 'Positive', 7, 'Room', 4.7),
('服務很棒的奢華飯店，是幫父母訂的，他住得很開心，有機會我們也會再去。', 'Positive', 8, 'Room', 4.9),
('商務包廂，隱密性高又幽靜，是私人聚會或商務餐敘的好地方。', 'Positive', 9, 'Room', 4.8),
('環境大氣舒適，人員服務親切有禮，床還是席夢思的，早餐也好吃。住宿體驗比五星級飯店都棒，以後還會再入住。', 'Positive', 10, 'Room', 4.9);


INSERT INTO Minibar (minibar_item, minibar_price, minibar_make, minibar_expire)
VALUES
('whisky', 800, '2024-05-09', 12),
('soda drink', 120, '2024-04-01', 225),
('coffee', 120, '2024-05-09', 52),
('instant noodles', 80, '2024-06-19', 152),
('mineral water', 80, '2024-07-29', 3122),
('cola', 120, '2024-07-29', 222),
('ice cream', 200, '2024-07-29', 120),
('coffee', 80, '2024-07-29', 92),
('miketea', 30, '2024-07-29', 37),
('hotdog', 60, '2024-07-29', 86),
('snack', 30, '2024-07-29', 410),
('hand pulled noodle', 250, '2024-07-29', 42);

INSERT INTO additionalCharges (minibar_id, housing_management_id, quantity, amount)
VALUES 
(1, 1, 2, 30.00),
(2, 2, 1, 15.00),
(3, 3, 3, 45.00),
(4, 4, 1, 20.00),
(5, 5, 2, 40.00);


------測試用
INSERT INTO orderRoom(orderdate, order_person_name, gender, birth, national_id, email, phone_number, credit_card, transaction_password, adult_pax,child_pax,room_type_amount,arrival_date,reservation_status, reservation_status_date, member_id, cancellation_reason, checkout_date, base_price) VALUES
( '2015/4/8','Heather Hart', '男', '1994/2/1', 'L283271972', 'Heather.H@xfinity.com','431-329-6663', '************2780', 'efo2oif31890',4,1,2, '2015/5/1', 'Check-Out', '2015/7/9', null, null, '2015/7/9',33033.0),
( '2015/6/11','Ernest Barnes', '男', '1990/7/1', 'G63184808', 'Ernest.Barnes31@outlook.com','669-792-1661', '************4322', 'scknkscn290u94',2,1,1, '2015/7/1', 'Check-Out', '2015/7/9', 1, null, '2015/7/9', 14784.0),
( '2015/8/31','Rebecca Parker', '女', '1995/9/5', 'K231674514', 'Rebecca_Parker@comcast.net','652-885-2745', '************3734', 'gheuijf982038',1,1,1,'2015/9/6', 'Check-Out','2015/9/9',2,null,'2015/9/9',18249.0),
( '2015/10/8','Laura Murray', '女', '1996/3/15', 'H253410978', 'Laura_M@gmail.com','364-656-8427', '************5677', 'wefwf46',3,0,1,'2015/11/18', 'Canceled', '2015/11/1',3,'改變計畫', '2015/11/20',0),
('2015/10/14', 'Linda Hines', '女', '1997/7/11', 'G277633276', 'LHines@verizon.com','713-226-5883', '************5498', 'wrew3524!',2,0,1,'2015/12/21', 'Canceled', '2015/12/21',4,'改變計畫', '2015/12/22',0),
('2016/1/5', 'Steven Johnson', '男', '1988/9/20', 'J491067351', 'StevenJ@example.com', '512-555-1234', '************1234', 'secret123', 2, 1, 1, '2016/2/1', 'Check-Out', '2016/2/5', 5, null, '2016/2/5', 15000.0),
('2016/3/15', 'Emily Davis', '女', '1993/5/10', 'M781039245', 'EmilyD@example.com', '713-555-9876', '************5678', 'secure5678', 3, 2, 2, '2016/4/1', 'Check-Out', '2016/4/5', 6, null, '2016/4/5', 20000.0),
('2016/5/25', 'James Wilson', '男', '1985/2/28', 'K632015987', 'JamesW@example.com', '281-555-5555', '************9012', 'password9012', 2, 1, 1, '2016/6/1', 'Check-Out', '2016/6/5', 7, null, '2016/6/5', 18000.0),
('2016/7/10', 'Sarah Thompson', '女', '1990/12/15', 'N572034812', 'SarahT@example.com', '832-555-1111', '************3456', 'secure3456', 2, 1, 1, '2016/8/1', 'Check-Out', '2016/8/5', 8, null, '2016/8/5', 16000.0),
('2016/9/20', 'Michael Martinez', '男', '1982/7/7', 'L490312576', 'MichaelM@example.com', '214-555-7777', '************7890', 'secret7890', 1, 1, 1, '2016/10/1', 'Check-Out', '2016/10/5', 9, null, '2016/10/5', 10000.0),
('2016/11/8', 'Jennifer Hernandez', '女', '1979/10/12', 'P291087432', 'JenniferH@example.com', '713-555-3333', '************2345', 'password2345', 2, 1, 1, '2016/12/1', 'Check-Out', '2016/12/5', 10, null, '2016/12/5', 18000.0),
('2017/1/15', 'David Brown', '男', '1991/4/25', 'M864019753', 'DavidB@example.com', '713-555-4444', '************6789', 'secure6789', 3, 2, 2, '2017/2/1', 'Check-Out', '2017/2/5', 11, null, '2017/2/5', 22000.0),
('2017/3/25', 'Jessica Taylor', '女', '1986/8/5', 'N410287635', 'JessicaT@example.com', '832-555-2222', '************0123', 'secret0123', 1, 1, 1, '2017/4/1', 'Check-Out', '2017/4/5', 12, null, '2017/4/5', 12000.0),
('2017/5/10', 'Christopher Clark', '男', '1980/11/30', 'K123098765', 'ChristopherC@example.com', '214-555-8888', '************4567', 'password4567', 2, 1, 1, '2017/6/1', 'Check-Out', '2017/6/5', null, null, '2017/6/5', 16000.0),
('2017/7/20', 'Amanda Rodriguez', '女', '1984/6/17', 'P987654321', 'AmandaR@example.com', '713-555-5555', '************7891', 'secure7891', 2, 1, 1, '2017/8/1', 'Check-Out', '2017/8/5', null, null, '2017/8/5', 14000.0);


INSERT INTO housingManagement (room_management_id, order_id, remarks, checkInTime, checkOutTime, total_compensation_fee, total_additional_fee)
VALUES
(1, 1, NULL, '2024-06-11', NULL, 0, 0),
(2, 2, '需要加毛毯', '2024-06-11', NULL, 0, 0),
(3, 3, '需要嬰兒床', '2024-06-11', NULL, 0, 0),
(4, 4, NULL, '2024-06-11', NULL, 0, 0),
(5, 5, '需要加毛毯', '2024-06-11', NULL, 0, 0),
(6, 6, '需要嬰兒床', '2024-06-11', NULL, 0, 0),
(7, 7, NULL, '2024-06-11', NULL, 0, 0),
(8, 8, '需要加毛毯', '2024-06-11', NULL, 0, 0),
(9, 9, '需要嬰兒床', '2024-06-11', NULL, 0, 0),
(10, 10, NULL, '2024-06-11', NULL, 0, 0),
(11, 11, '需要加毛毯', '2024-06-11', NULL, 0, 0),
(12, 12, '需要嬰兒床', '2024-06-11', NULL, 0, 0),
(13, 13, NULL, '2024-06-11', NULL, 0, 0),
(14, 14, '需要加毛毯', '2024-06-11', NULL, 0, 0),
(15, 15, '需要嬰兒床', '2024-06-11', NULL, 0, 0);