/* Populate tables */
INSERT INTO login (idTrabajador, contraseña, email) VALUES('1', '$2a$10$Abw2fx8bb97LSM4SOB1D4.qU6PQWJy9jCOubDpcy6fLBkI0sNtXCi', 'admin@australsac.com');
INSERT INTO login (idTrabajador, contraseña, email) VALUES('2', '$2a$10$Abw2fx8bb97LSM4SOB1D4.qU6PQWJy9jCOubDpcy6fLBkI0sNtXCi', 'secretaria1@australsac.com');
INSERT INTO login (idTrabajador, contraseña, email) VALUES('3', '$2a$10$Abw2fx8bb97LSM4SOB1D4.qU6PQWJy9jCOubDpcy6fLBkI0sNtXCi', 'secretaria2@australsac.com');
INSERT INTO login (idTrabajador, contraseña, email) VALUES('4', '$2a$10$Abw2fx8bb97LSM4SOB1D4.qU6PQWJy9jCOubDpcy6fLBkI0sNtXCi', 'secretaria3@australsac.com');
INSERT INTO login (idTrabajador, contraseña, email) VALUES('5', '$2a$10$Abw2fx8bb97LSM4SOB1D4.qU6PQWJy9jCOubDpcy6fLBkI0sNtXCi', 'T1@australsac.com');
INSERT INTO login (idTrabajador, contraseña, email) VALUES('6', '$2a$10$Abw2fx8bb97LSM4SOB1D4.qU6PQWJy9jCOubDpcy6fLBkI0sNtXCi', 'T2@australsac.com');
INSERT INTO login (idTrabajador, contraseña, email) VALUES('7', '$2a$10$Abw2fx8bb97LSM4SOB1D4.qU6PQWJy9jCOubDpcy6fLBkI0sNtXCi', 'T3@australsac.com');
INSERT INTO login (idTrabajador, contraseña, email) VALUES('8', '$2a$10$Abw2fx8bb97LSM4SOB1D4.qU6PQWJy9jCOubDpcy6fLBkI0sNtXCi', 'T4@australsac.com');
INSERT INTO login (idTrabajador, contraseña, email) VALUES('9', '$2a$10$Abw2fx8bb97LSM4SOB1D4.qU6PQWJy9jCOubDpcy6fLBkI0sNtXCi', 'T5@australsac.com');
INSERT INTO login (idTrabajador, contraseña, email) VALUES('10', '$2a$10$Abw2fx8bb97LSM4SOB1D4.qU6PQWJy9jCOubDpcy6fLBkI0sNtXCi', 'T6@australsac.com');
INSERT INTO login (idTrabajador, contraseña, email) VALUES('11', '$2a$10$Abw2fx8bb97LSM4SOB1D4.qU6PQWJy9jCOubDpcy6fLBkI0sNtXCi', 'T7@australsac.com');
INSERT INTO login (idTrabajador, contraseña, email) VALUES('12', '$2a$10$Abw2fx8bb97LSM4SOB1D4.qU6PQWJy9jCOubDpcy6fLBkI0sNtXCi', 'T8@australsac.com');
INSERT INTO login (idTrabajador, contraseña, email) VALUES('13', '$2a$10$Abw2fx8bb97LSM4SOB1D4.qU6PQWJy9jCOubDpcy6fLBkI0sNtXCi', 'T9@australsac.com');
INSERT INTO login (idTrabajador, contraseña, email) VALUES('14', '$2a$10$Abw2fx8bb97LSM4SOB1D4.qU6PQWJy9jCOubDpcy6fLBkI0sNtXCi', 'T10@australsac.com');

INSERT INTO rol (idRol, Descripcion, Nombre) VALUES('1','ADMIN','ADMIN');
INSERT INTO rol (idRol, Descripcion, Nombre) VALUES('2','SECRE','SECRE');
INSERT INTO rol (idRol, Descripcion, Nombre) VALUES('3','USER','USER');

INSERT INTO rol_login (idRol,idTrabajador) VALUES('1','1');
INSERT INTO rol_login (idRol,idTrabajador) VALUES('2','2');
INSERT INTO rol_login (idRol,idTrabajador) VALUES('2','3');
INSERT INTO rol_login (idRol,idTrabajador) VALUES('2','4');
INSERT INTO rol_login (idRol,idTrabajador) VALUES('3','5');
INSERT INTO rol_login (idRol,idTrabajador) VALUES('3','6');
INSERT INTO rol_login (idRol,idTrabajador) VALUES('3','7');
INSERT INTO rol_login (idRol,idTrabajador) VALUES('3','8');
INSERT INTO rol_login (idRol,idTrabajador) VALUES('3','9');
INSERT INTO rol_login (idRol,idTrabajador) VALUES('3','10');
INSERT INTO rol_login (idRol,idTrabajador) VALUES('3','11');
INSERT INTO rol_login (idRol,idTrabajador) VALUES('3','12');
INSERT INTO rol_login (idRol,idTrabajador) VALUES('3','13');
INSERT INTO rol_login (idRol,idTrabajador) VALUES('3','14');
