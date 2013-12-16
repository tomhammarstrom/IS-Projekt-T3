create table student (
pnr varchar (20) primary key,
name varchar (50),
adr varchar (50))

create table course (
id varchar (20) primary key,
name varchar (50),
point int,
descr varchar (100))

create table studies (
pnr varchar (20),
id varchar (20),
constraint _studies_pk primary key (pnr, id),
constraint studies_student_fk foreign key (pnr) references student(pnr) on delete cascade,
constraint studies_course_fk foreign key (id) references course(id) on delete cascade)

create table studied (
pnr varchar (20),
id varchar (20),
grade varchar (1), constraint __studies_pk primary key (pnr, id),
constraint studied_student_fk foreign key (pnr) references student(pnr) on delete cascade,
constraint studied_course_fk foreign key (id) references course(id) on delete cascade)
