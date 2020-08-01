insert into users values('aa74a730-6393-43d3-9653-e742f462aebc', 'algo@com', true, 'ade' , 'pass', '2012-09-17 18:47:52.69', '2012-09-17 18:47:52.69');
INSERT INTO PHONES VALUES ((select max(id)+1 from phones), 1, 54, 123, 'aa74a730-6393-43d3-9653-e742f462aebc');

insert into users values('bb74a730-6393-43d3-9653-e742f462aebc', 'mimail@algo.com', true, 'xx' , 'pass', '2012-09-17 18:47:52.69', '2012-09-17 18:47:52.69');
INSERT INTO PHONES VALUES ((select max(id)+1 from phones), 11, 54, 222, 'bb74a730-6393-43d3-9653-e742f462aebc');


insert into users values('cc74a730-6393-43d3-9653-e742f462aebc', 'domail@algo.com', false, 'zz' , 'pass', '2012-09-17 18:47:52.69', '2012-09-17 18:47:52.69');
INSERT INTO PHONES VALUES ((select max(id)+1 from phones), 11, 54, 333, 'cc74a730-6393-43d3-9653-e742f462aebc');