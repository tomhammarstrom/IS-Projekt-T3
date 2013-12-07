create table student(civic varchar(20) not null, name varchar(20), constraint s_pk PRIMARY KEY(civic))
create table course(courseId varchar(20) not null, name varchar(20), constraint c_pk PRIMARY KEY(courseId))

create table studies_active(civic varchar(20) not null, courseId varchar(20) not null, grade varchar(5), 
constraint st_pk PRIMARY KEY(civic,courseId), constraint st_fk1 FOREIGN KEY(civic) references student(civic),
constraint st_fk2 FOREIGN KEY(courseId) references course(courseId))

create table studies_inactive(civic varchar(20) not null, courseId varchar(20) not null, grade varchar(5), 
constraint st_pk PRIMARY KEY(civic,courseId), constraint st_fk1 FOREIGN KEY(civic) references student(civic),
constraint st_fk2 FOREIGN KEY(courseId) references course(courseId))

insert into student values('111', 'AAAName')
insert into student values('222', 'BBBName')
insert into student values('333', 'CCCName')

insert into course values('KursKodA01', 'KursNameAAA')
insert into course values('KursKodB02', 'KursNameBBB')
insert into course values('KursKodC03', 'KursNameCCC')

insert into studies_active values('111', 'KursKodA01','A')
insert into studies_active values('222', 'KursKodA01', null)
