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
