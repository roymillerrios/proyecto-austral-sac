
INSERT INTO trabajadores (password, email) VALUES('$2a$10$Abw2fx8bb97LSM4SOB1D4.qU6PQWJy9jCOubDpcy6fLBkI0sNtXCi', 'admin@australsac.com');

INSERT INTO roles (descripcion) VALUES('ADMIN');

INSERT INTO trabajador_rol (trabajador_id,rol_id) VALUES(1,1);

