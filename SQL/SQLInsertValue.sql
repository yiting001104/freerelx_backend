use Hotel;

INSERT INTO member( name, gender, birth, national_id, email, phone_number, credit_card, contact_address, password, registration_date,member_status,nationality,total_bonus_points,login_time) VALUES
( 'Ernest Barnes', 'man', '1990/7/1', 'G63184808', 'Ernest.Barnes31@outlook.com','669-792-1661', '************4322', '台灣省高雄市旗山區中寮二路14號', 'scknkscn290u94', '2014/10/3','normal', '臺灣',400.6, '2014/10/30'),
( 'Rebecca Parker', 'female', '1995/9/5', 'K231674514', 'Rebecca_Parker@comcast.net','652-885-2745', '************3734', '360 苗栗縣苗栗市金龍街10號', 'gheuijf982038', '2015/6/11','normal', '臺灣',423.7, '2015/6/12'),
( 'Laura Murray', 'female', '1996/3/15', 'H253410978', 'Laura_M@gmail.com','364-656-8427', '************5677', '330 桃園市桃園區大華五街18號', 'wefwf46', '2015/6/12','normal', '臺灣',345.7, '2015/6/15'),
( 'Linda Hines', 'female', '1997/7/11', 'G277633276', 'LHines@verizon.com','713-226-5883', '************5498', '269 宜蘭縣冬山鄉東七路33號', 'wrew3524!', '2015/6/12','normal', '臺灣',498.8,'2015/6/20');



INSERT INTO orderRoom(orderdate, order_person_name, gender, birth, national_id, email, phone_number, credit_card, transaction_password, adult_pax,child_pax,room_type_amount,arrival_date,reservation_status, reservation_status_date, member_id, cancellation_reason, checkout_date, base_price) VALUES
( '2015/4/8','Heather Hart', 'man', '1994/2/1', 'L283271972', 'Heather.H@xfinity.com','431-329-6663', '************2780', 'efo2oif31890',4,1,2, '2015/5/1', 'Check-Out', '2015/7/9', null, null, '2015/7/9',33033.0),
( '2015/6/11','Ernest Barnes', 'man', '1990/7/1', 'G63184808', 'Ernest.Barnes31@outlook.com','669-792-1661', '************4322', 'scknkscn290u94',2,1,1, '2015/7/1', 'Check-Out', '2015/7/9', 1, null, '2015/7/9', 14784.0),
( '2015/8/31','Rebecca Parker', 'female', '1995/9/5', 'K231674514', 'Rebecca_Parker@comcast.net','652-885-2745', '************3734', 'gheuijf982038',1,1,1,'2015/9/6', 'Check-Out','2015/9/9',2,null,'2015/9/9',18249.0),
( '2015/10/8','Laura Murray', 'female', '1996/3/15', 'H253410978', 'Laura_M@gmail.com','364-656-8427', '************5677', 'wefwf46',3,0,1,'2015/11/18', 'Canceled', '2015/11/1',3,'改變計畫', '2015/11/20',0),
('2015/10/14', 'Linda Hines', 'female', '1997/7/11', 'G277633276', 'LHines@verizon.com','713-226-5883', '************5498', 'wrew3524!',2,0,1,'2015/12/21', 'Canceled', '2015/12/21',4,'改變計畫', '2015/12/22',0);

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
('˙7天前', 0.5),
('當天', 0);

INSERT INTO transactionTable(amount, order_id, last_five_account_number, transfer_date, taxIDNumber, transaction_status, payment_method, unsubscribe_date, refund_amount) VALUES
(7500,1,'2780', '2015/4/10', '29932116', 'done', '匯款', null,null),
(14784,2,'4322', '2015/6/12', '91794839', 'done', '信用卡交易', null,null),
(18249,3,'3734', '2015/9/1', '56685047', 'done', '匯款', null,null),
(18249,5,'9263','2015/11/4', '2929953', 'done', '匯款', '2015/12/13', 9124.5);
