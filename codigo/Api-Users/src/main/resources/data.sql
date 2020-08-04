insert into usuario values('aa74a730-6393-43d3-9653-e742f462aebc', 'algo@com', true, 'ade' , 'pass', '2012-09-17 18:47:52.69', '2012-09-17 18:47:52.69');
INSERT INTO PHONES VALUES ((select max(id)+1 from phones), 1, 54, 123, 'aa74a730-6393-43d3-9653-e742f462aebc');

insert into usuario values('bb74a730-6393-43d3-9653-e742f462aebc', 'mimail@algo.com', true, 'xx' , 'pass', '2012-09-17 18:47:52.69', '2012-09-17 18:47:52.69');
INSERT INTO PHONES VALUES ((select max(id)+1 from phones), 11, 54, 222, 'bb74a730-6393-43d3-9653-e742f462aebc');


insert into usuario values('cc74a730-6393-43d3-9653-e742f462aebc', 'domail@algo.com', false, 'zz' , 'pass', '2012-09-17 18:47:52.69', '2012-09-17 18:47:52.69');
INSERT INTO PHONES VALUES ((select max(id)+1 from phones), 11, 54, 333, 'cc74a730-6393-43d3-9653-e742f462aebc');

/* Creamos algunos usuarios con sus roles */
INSERT INTO users (id,username, password, enabled) VALUES (1, 'ademia','$2a$10$YH0j6zwIgMmGat1.XndptuzAbpBkMyFWSA9AS9RXfVo7.6kpYaUOq',1);
INSERT INTO users (id,username, password, enabled) VALUES (2, 'admin','$2a$10$YH0j6zwIgMmGat1.XndptuzAbpBkMyFWSA9AS9RXfVo7.6kpYaUOq',1);

INSERT INTO authorities (id, authority) VALUES (1,'ROLE_USER');
INSERT INTO authorities (id, authority) VALUES (2,'ROLE_ADMIN');

insert into role_users (user_id, role_id) values (1,1);
insert into role_users (user_id, role_id) values (2,2);

commit;



