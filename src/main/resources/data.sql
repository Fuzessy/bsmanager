-- CORE
-- jelszó: jelszó :)
delete from application_user;
delete from account;
delete from financial_item_category;
delete from item_category;
delete from item_target;

insert into application_user (id, avatar, full_name, nick_name, password, user_name) VALUES (1,null,'Ecseri Barbara','Puncus','$2a$10$NQm265wCp67ECj/uKgiZSuD/4TWVXw7Gh6ulDLPdxfx5baeGpr.LC','barbi');
insert into application_user (id, avatar, full_name, nick_name, password, user_name) VALUES (2,null,'Füzesi Zsolt','Zsolti','$2a$10$NQm265wCp67ECj/uKgiZSuD/4TWVXw7Gh6ulDLPdxfx5baeGpr.LC','zsolti');


-- FINANCIAL
INSERT INTO account (id, name, order_number) VALUES (1, 'OTP számla', 1);
INSERT INTO account (id, name, order_number) VALUES (2, 'Barbi kp.', 2);
INSERT INTO account (id, name, order_number) VALUES (3, 'Zsolti kp.', 3);
INSERT INTO account (id, name, order_number) VALUES (4, 'Takarék számla', 4);
INSERT INTO account (id, name, order_number) VALUES (5, 'KP megtakarítás', 5);
INSERT INTO account (id, name, order_number) VALUES (6, 'KP megtakarítás gyerekek', 6);

insert into financial_item_category (id, alive, name) VALUES (1,true,'bevásárlás');
insert into financial_item_category (id, alive, name) VALUES (2,true,'benzin');
insert into financial_item_category (id, alive, name) VALUES (3,true,'Barbi család');
insert into financial_item_category (id, alive, name) VALUES (4,true,'Zsolti család');
insert into financial_item_category (id, alive, name) VALUES (5,true,'gyógyszer');
insert into financial_item_category (id, alive, name) VALUES (6,true,'jövedelem');

--SHOPPING
insert into item_category (id, alive, name) VALUES (1,true,'pékáru');
insert into item_category (id, alive, name) VALUES (2,true,'tejtermék');
insert into item_category (id, alive, name) VALUES (3,true,'tisztítószer');
insert into item_category (id, alive, name) VALUES (4,true,'egyéb');

insert into item_target (id, name) VALUES (1,'Saját');
insert into item_target (id, name) VALUES (2,'Ica mama');
insert into item_target (id, name) VALUES (3,'Anyuci');
insert into item_target (id, name) VALUES (4,'Szandra');
insert into item_target (id, name) VALUES (5,'Pécel');
insert into item_target (id, name) VALUES (6,'Egyéb');
