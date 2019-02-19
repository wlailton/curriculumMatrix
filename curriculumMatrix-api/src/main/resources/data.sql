-- Courses
insert into COURSE(id, name) values(1,'Ciência da Computação');
insert into COURSE(id, name) values(2,'Sistemas de Informação');
-- Semesters
insert into SEMESTER(id, semester_number, year, course_id) values(1, 1, 2019, 1);
insert into SEMESTER(id, semester_number, year, course_id) values(2, 2, 2019, 1);
insert into SEMESTER(id, semester_number, year, course_id) values(3, 3, 2019, 1);
insert into SEMESTER(id, semester_number, year, course_id) values(4, 4, 2019, 1);

insert into SEMESTER(id, semester_number, year, course_id) values(5, 1, 2019, 2);
insert into SEMESTER(id, semester_number, year, course_id) values(6, 2, 2019, 2);
insert into SEMESTER(id, semester_number, year, course_id) values(7, 3, 2019, 2);
insert into SEMESTER(id, semester_number, year, course_id) values(8, 4, 2019, 2);
-- Diciplines
insert into DISCIPLINE(id, name) values(1, 'Algoritmo');
insert into DISCIPLINE(id, name) values(2, 'Banco de Dados');
insert into DISCIPLINE(id, name) values(3, 'Cáldulo 1');
insert into DISCIPLINE(id, name) values(4, 'Matemática Discreta');
insert into DISCIPLINE(id, name) values(5, 'Desafios do ciberespaço');
insert into DISCIPLINE(id, name) values(6, 'Experimentação orientada');
insert into DISCIPLINE(id, name) values(7, 'Fund. sist. omputacionais');
insert into DISCIPLINE(id, name) values(8, 'Modelagem prob. matemática');
insert into DISCIPLINE(id, name) values(9, 'Postura prof. com interpessoal');
insert into DISCIPLINE(id, name) values(10, 'Raciocínio lógico algoritmo');
insert into DISCIPLINE(id, name) values(11, 'Estrutura de dados');
insert into DISCIPLINE(id, name) values(12, 'Experimentação de protótipos');
insert into DISCIPLINE(id, name) values(13, 'Interação humano-computador');
insert into DISCIPLINE(id, name) values(14, 'Programação orient. objetos');
insert into DISCIPLINE(id, name) values(15, 'Res. problemas nat. discreta');
insert into DISCIPLINE(id, name) values(16, 'Arquitetura de computadores');
--Semester Diciplines
insert into SEMESTER_DISCIPLINE(semester_id, discipline_id) values(1, 1);
insert into SEMESTER_DISCIPLINE(semester_id, discipline_id) values(1, 2);
insert into SEMESTER_DISCIPLINE(semester_id, discipline_id) values(1, 3);
insert into SEMESTER_DISCIPLINE(semester_id, discipline_id) values(1, 4);
insert into SEMESTER_DISCIPLINE(semester_id, discipline_id) values(2, 5);
insert into SEMESTER_DISCIPLINE(semester_id, discipline_id) values(2, 6);
insert into SEMESTER_DISCIPLINE(semester_id, discipline_id) values(2, 7);
insert into SEMESTER_DISCIPLINE(semester_id, discipline_id) values(2, 8);
insert into SEMESTER_DISCIPLINE(semester_id, discipline_id) values(3, 9);
insert into SEMESTER_DISCIPLINE(semester_id, discipline_id) values(3, 10);
insert into SEMESTER_DISCIPLINE(semester_id, discipline_id) values(3, 11);
insert into SEMESTER_DISCIPLINE(semester_id, discipline_id) values(3, 12);
insert into SEMESTER_DISCIPLINE(semester_id, discipline_id) values(4, 13);
insert into SEMESTER_DISCIPLINE(semester_id, discipline_id) values(4, 14);
insert into SEMESTER_DISCIPLINE(semester_id, discipline_id) values(4, 15);
insert into SEMESTER_DISCIPLINE(semester_id, discipline_id) values(4, 16);
-- Roles
INSERT INTO roles(name) VALUES('ROLE_ADMIN');
INSERT INTO roles(name) VALUES('ROLE_COORDINATOR');
INSERT INTO roles(name) VALUES('ROLE_PROFESSOR');
INSERT INTO roles(name) VALUES('ROLE_STUDENT');
-- User test
INSERT INTO users(email, name, password, username) VALUES('usertest@xpto.com', 'User Test', '123456', 'usertest');
INSERT INTO user_roles(user_id, role_id) values(1,1);
INSERT INTO user_roles(user_id, role_id) values(1,2);
INSERT INTO user_roles(user_id, role_id) values(1,3);
INSERT INTO user_roles(user_id, role_id) values(1,4);
-- Queries
/*
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
*/

/*
SELECT * 
  FROM course c
 WHERE EXISTS (SELECT 1 
                 FROM semester s 
                WHERE s.course_id = c.id 
                  AND s.year = '2019'
                  AND EXISTS (SELECT 1
                                FROM SEMESTER_DISCIPLINE sd
                               WHERE sd.semester_id = s.id)) 
 */

