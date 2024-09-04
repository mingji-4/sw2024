create table blog (
id int primary key auto_increment,
title varchar2(100),
content varchar2(1000)
);

insert into blog(title, content) values('test', 'test test');
insert into blog(title, content) values('test2', 'test2 test');

select * from blog