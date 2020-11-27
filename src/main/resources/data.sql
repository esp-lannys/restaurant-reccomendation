SET GLOBAL FOREIGN_KEY_CHECKS=0;

INSERT INTO location (id, district) values ('1','Distric 1');
INSERT INTO location (id, district) values ('2','Distric 2');
INSERT INTO location (id, district) values ('3','Distric 3');
INSERT INTO location (id, district) values ('4','Distric 4');
INSERT INTO location (id, district) values ('5','Distric 5');
INSERT INTO location (id, district) values ('6','Distric 6');
INSERT INTO location (id, district) values ('7','Distric 7');
INSERT INTO location (id, district) values ('8','Distric 8');
INSERT INTO location (id, district) values ('9','Distric 9');
INSERT INTO location (id, district) values ('10','Distric 10');
INSERT INTO location (id, district) values ('11','Distric 11');
INSERT INTO location (id, district) values ('12','Distric 12');
INSERT INTO location (id, district) values ('13','Binh Thanh District');
INSERT INTO location (id, district) values ('14','Go Vap District');
INSERT INTO location (id, district) values ('15','Thu Duc Distric');
INSERT INTO location (id, district) values ('16','Phu Nhuan District');
INSERT INTO location (id, district) values ('17','Tan Binh District');
INSERT INTO location (id, district) values ('18','Tan Phu District');
INSERT INTO location (id, district) values ('19','Binh Tan District');

INSERT INTO users (id, first_name, last_name, phone, email, username, password) VALUES ('3','Luan','Bui Hoang','0123456789','luan@test.com','luan','$2a$10$3WQPws9o2zN4E1XAs1nKc.vWKUTtV/uRgvOAJ6foqrHcs/rqmWjLi');
INSERT INTO users (id, first_name, last_name, phone, email, username, password) VALUES ('4','Quang','Vo Ho Nhat Quang','0123456789','quang@test.com','quang','$2a$10$PX0HS/Lbb2M9Kw/yRhaYuOgRXKbA4KGARvdFZHRsAp2.nX6Fzm9je');
INSERT INTO users (id, first_name, last_name, phone, email, username, password) VALUES ('5','Trien','Le Khue','0123456789','trien@test.com','trien','$2a$10$kpUWFMHUOEuwTYX9gZMX1Ow0kEb7doAHJKt3akPEwZeBpuO0eGzRW');
INSERT INTO users (id, first_name, last_name, phone, email, username, password) VALUES ('6','USER_TEST_1','','','user1@test.com','user1','$2a$10$l8AUEdN0kyAdiuRuUbVT7OkC7WXDBIR.ClAPSH5CjYrnm4da5DfxC');
INSERT INTO users (id, first_name, last_name, phone, email, username, password) VALUES ('7','USER_TEST_2','','','user2@test.com','user2','$2a$10$QssIrlqAkbXohRrWBcWPMOZwSIxWZTwX8ztc5vyN/NUmBfLEmwE.y');


INSERT INTO user_role (user_id, role_id) VALUES ('3','2');
INSERT INTO user_role (user_id, role_id) VALUES ('4','2');
INSERT INTO user_role (user_id, role_id) VALUES ('5','2');
INSERT INTO user_role (user_id, role_id) VALUES ('6','1');
INSERT INTO user_role (user_id, role_id) VALUES ('7','1');
