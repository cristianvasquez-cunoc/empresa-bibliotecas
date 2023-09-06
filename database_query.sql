use empresa_bibliotecas;

SHOW VARIABLES WHERE VARIABLE_NAME IN('hostname','port');

SELECT * FROM usuario;

CREATE TABLE rol (
	codigo integer primary key not null,
    valor varchar(50) not null
);

CREATE TABLE usuario (
  codigo INTEGER PRIMARY KEY not null auto_increment,
  nombre varchar(50) not null,
  username varchar(45) not null,
  password varchar(45) not null,
  rol integer not null,
  email varchar(100),
  FOREIGN KEY (rol) REFERENCES rol(codigo)
);

CREATE TABLE usuario_final (
  codigo INTEGER PRIMARY KEY NOT NULL,
  suscrito TINYINT not null,
  anulado TINYINT not null,
  saldo float,
  FOREIGN KEY (codigo) REFERENCES usuario(codigo)
);

create table biblioteca (
  codigo INTEGER PRIMARY KEY not null auto_increment,
  nombre varchar(45) not null,
  direccion varchar(90) not null
);

create table usuario_secretaria (
  codigo INTEGER PRIMARY key not null,
  biblioteca INTEGER not null,
  FOREIGN key (codigo) REFERENCES usuario(codigo),
  FOREIGN key (biblioteca) REFERENCES biblioteca(codigo)
);

create table categoria (
  codigo INTEGER PRIMARY KEY not null,
  name varchar(45) not null,
  description varchar(360) not null
);

create table libro (
  isbn INTEGER PRIMARY KEY not null,
  nombre varchar(90) not null,
  autor varchar(45) not null,
  categoria INTEGER not null,
  costo float not null,
  FOREIGN KEY (categoria) REFERENCES categoria(codigo)
);


create table unidades_libro (
  id INTEGER PRIMARY key not null auto_increment,
  biblioteca INTEGER not null,
  isbn integer not null,
  unidades integer,
  FOREIGN KEY (biblioteca) REFERENCES biblioteca(codigo),
  FOREIGN KEY (isbn) REFERENCES libro(isbn)
);

CREATE TABLE administracion (
	id integer primary key not null,
    dias_suspension integer not null,
    multa float not null,
    costo_domicilio float not null,
    costo_suscripcion float not null,
    descuento_domicilio_premium float not null,
	limite_dias integer not null,
    limite_libros integer not null
);

INSERT INTO administracion 
(id, dias_suspension, multa, costo_domicilio, costo_suscripcion, descuento_domicilio_premium, limite_dias, limite_libros) values
(1, 15, 50.0, 40.0, 30.0, 0.4, 8, 5);

select * from administracion;

INSERT INTO rol (codigo, valor) values
(1, "usuario final"),
(2, "usuario admin"),
(3, "usuario de secretaria"),
(4, "usuario transportista");

INSERT INTO usuario (codigo, nombre, username, password, rol, email) VALUES
(1,'Chris', 'chris07', '12345', 1, 'chris@gmail.com'),
(2,'Marck', 'marchhk', 'pass', 2, 'marchhk@gmail.com'),
(3,'Dani', 'mdani', 'twice', 4, 'mdani@gmail.com'),
(4,'veronica', 'vero', '54321', 3, 'vero@gmail.com'),
(5,'karol', 'karolg', '123', 2, 'karolg@gmail.com');

INSERT INTO usuario (nombre, username, password, rol, email) VALUES
('Pedro', 'final', '12345', 1, 'pedro@gmail.com');

-- INSERT INTO categoria (codigo, name, description)
-- VALUES
--   (1, 'Ficción', 'Libros de ficción, novelas, y literatura imaginativa.'),
--   (2, 'Misterio', 'Libros de misterio y suspenso.'),
--   (3, 'Historia', 'Libros relacionados con eventos históricos.'),
--   (4, 'Autoayuda', 'Libros de autoayuda y desarrollo personal.'),
--   (5, 'Cocina', 'Libros de cocina y recetas.');

SELECT l.isbn, l.nombre, l.autor, l.costo, c.name as categoria
FROM libro as l
inner join categoria as c 
on l.categoria = c.codigo;
  
  INSERT INTO libro (isbn, nombre, autor, categoria, costo)
VALUES
  (1, 'El Gran Gatsby', 'F. Scott Fitzgerald', 1, 19.99),
  (2, 'Sherlock Holmes: Aventuras', 'Arthur Conan Doyle', 2, 14.50),
  (3, 'Los Pilares de la Tierra', 'Ken Follett', 3, 17.75),
  (4, 'Piense y Hágase Rico', 'Napoleon Hill', 4, 12.95),
  (5, 'Recetas de la Abuela', 'María González', 5, 9.99),
  (6, '1984', 'George Orwell', 1, 16.99),
  (7, 'El Código enigma', 'Simon Singh', 2, 13.25),
  (8, 'Sapiens: De animales a dioses', 'Yuval Noah Harari', 3, 15.50),
  (9, 'El Poder del Hábito', 'Charles Duhigg', 4, 11.75),
  (10, 'Cocina Italiana', 'Antonio Rossi', 5, 10.49),
  (11, 'Don Quijote de la Mancha', 'Miguel de Cervantes', 1, 18.99),
  (12, 'El código Da Vinci', 'Dan Brown', 2, 12.50),
  (13, 'Breve historia del tiempo', 'Stephen Hawking', 3, 14.75),
  (14, 'El poder del ahora', 'Eckhart Tolle', 4, 11.25),
  (15, 'La cocina al minuto', 'Nitza Villapol', 5, 8.99);
  
  
INSERT INTO biblioteca (nombre, direccion)
VALUES
  ('Biblioteca 1', 'Guatemala'),
  ('Biblioteca 2', 'Sacatepéquez'),
  ('Biblioteca 3', 'Quetzaltenango'),
  ('Biblioteca 4', 'Huehuetenango'),
  ('Biblioteca 5', 'Izabal'),
  ('Biblioteca 6', 'Chimaltenango');
  

SELECT b.nombre, b.direccion, ub.unidades FROM biblioteca as b INNER JOIN unidades_libro as ub ON (b.codigo = ub.biblioteca) INNER JOIN libro as l ON (l.isbn = ub.isbn) WHERE l.isbn = 1;

SELECT * FROM empresa_bibliotecas.unidades_libro;

UPDATE unidades_libro
SET unidades = 10
WHERE id = 1;

update unidades_libro set unidades = 10 where id = 41;
-- id 41 unidades = 14

select * from categoria;

insert into categoria (name, description) values ('Romance', 'Libros de historias de amor y relaciones.');

select u.codigo, u.nombre, u.username, u.email, b.codigo as codigo_biblioteca, b.nombre as biblioteca, b.direccion from usuario as u inner join usuario_secretaria as us on u.codigo = us.codigo inner join biblioteca as b on b.codigo = us.biblioteca;

update usuario set nombre = "Tyler", username = "tyler", password = "12345", email = "tyler@gmail.com" where codigo = 13;
update usuario set nombre = "Tyler", username = "tyler",email = "tyler@gmail.com" where codigo = 13;

update usuario_secretaria  set biblioteca = 6 where  codigo = 11;

insert into usuario_secretaria (codigo, biblioteca) values (11, 3), (12,3);

select * from usuario_secretaria ;

-- agregar usuario final y la solicitud de revocacion de suspension
insert into usuario (nombre, username, password, email, rol, activo) values ('Julian', 'julian', '12345', 'julian@gmail.com', 1, true);
insert into usuario_final (codigo, suscrito, suspendido, saldo, fecha_suspension) values (27, false, true, 500, '2023-09-05 15:29:37');
insert into revocacion_suspension (aprovada, codigo_usuario, fecha_suspension) values (false, 6, '2023-09-05 15:29:37'), (false, 25, '2023-09-03 15:29:37'),(false, 26, '2023-09-0 15:29:37');

select * from revocacion_suspension;
select * from usuario_final;

update revocacion_suspension set fecha_suspension = '2023-09-01 14:30:00' where id = 4;

select rs.id, rs.aprovada, rs.codigo_usuario, rs.fecha_suspension, rs.fecha_aprovacion, uf.suspendido, u.nombre, u.username from revocacion_suspension as rs inner join usuario_final as uf on rs.codigo_usuario = uf.codigo inner join usuario as u on uf.codigo = u.codigo  order by rs.aprovada ; 

update revocacion_suspension set fecha_aprovacion = CURRENT_TIMESTAMP, aprovada = true where id = 4;
update usuario_final set suspendido = false, fecha_suspension = null where codigo = 25;


SELECT * FROM empresa_bibliotecas.unidades_libro;


select l.isbn, l.nombre, l.autor, c.name as categoria, ul.biblioteca, ul.unidades from libro as l inner join unidades_libro as ul inner join categoria as c on c.codigo = l.categoria where ul.biblioteca = 1 and ul.unidades > 0 and l.isbn = ul.isbn;

CREATE TABLE prestamo (
	codigo INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    biblioteca INT NOT NULL,
    recepcionista INT NOT NULL,
    multa DOUBLE NOT NULL,
    codigo_usuario INT NOT NULL,
    isbn INT NOT NULL,
    pendiente BOOLEAN NOT NULL,
    FOREIGN KEY (biblioteca) references biblioteca(codigo),
    FOREIGN KEY (recepcionista) references usuario_secretaria(codigo),
    FOREIGN KEY (codigo_usuario) references usuario_final(codigo)
);

ALTER TABLE prestamo add column fecha_prestamo timestamp not null default CURRENT_TIMESTAMP;
ALTER TABLE prestamo add column dias_prestamo int not null;

ALTER TABLE prestamo add column fecha_devolucion TIMESTAMP DEFAULT (CURRENT_TIMESTAMP + INTERVAL dias_prestamo DAY);


















