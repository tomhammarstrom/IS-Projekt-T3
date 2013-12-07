create table student(
civic varchar(20) not null, 
name varchar(20), 
constraint student_pk PRIMARY KEY(civic)
)

create table course(
id varchar(20) not null, 
name varchar(20), 
contents varchar(100),
points int not null,
constraint course_pk PRIMARY KEY(id)
)

create table studies_active(
civic varchar(20) not null, 
courseId varchar(20) not null, 
constraint active_pk PRIMARY KEY(civic,courseId),
constraint active_fk1 FOREIGN KEY(civic) references student(civic) on delete cascade,
constraint active_fk2 FOREIGN KEY(courseId) references course(id) on delete cascade
)

create table studies_inactive(
civic varchar(20) not null, 
courseId varchar(20) not null, 
grade char, 
constraint inactive_pk PRIMARY KEY(civic,courseId), 
constraint inactive_fk1 FOREIGN KEY(civic) references student(civic) on delete cascade,
constraint inactive_fk2 FOREIGN KEY(courseId) references course(id) on delete cascade
)


insert into student values('111', 'AAAName')
insert into student values('222', 'BBBName')
insert into student values('333', 'CCCName')

insert into course values('KursKodA01', 'KursNameAAA', 'Rolig kurs', 10)
insert into course values('KursKodB02', 'KursNameBBB', 'Trakig kurs', 20)
insert into course values('KursKodC03', 'KursNameCCC', 'Ambivalent kurs', 30)

insert into studies_active values('111', 'KursKodA01')
insert into studies_active values('222', 'KursKodB02')

insert into studies_inactive values('333', 'KursKodC03', 'A')
insert into studies_inactive values('222', 'KursKodC03', 'D')
