insert into COURSE values(1,'Sistemas');

insert into SEMESTER values(1, 1, 2018, 1);
insert into SEMESTER values(2, 2, 2018, 1);
insert into SEMESTER values(3, 3, 2018, 1);
insert into SEMESTER values(4, 4, 2018, 1);

insert into SEMESTER values(5, 1, 2019, 1);
insert into SEMESTER values(6, 2, 2019, 1);
insert into SEMESTER values(7, 3, 2019, 1);
insert into SEMESTER values(8, 4, 2019, 1);

insert into DISCIPLINE values(1, 'Algoritmo');

insert into SEMESTER_DISCIPLINE values(1, 1);




SELECT c.name
     , s.semester_number
     , s.year
     , d.name 
  FROM COURSE c
     , SEMESTER s
     , SEMESTER_DISCIPLINE sd
     , DISCIPLINE d
 WHERE c.id = s.course_id
   AND sd.semester_id = s.id
   AND sd.discipline_id = d.id;
