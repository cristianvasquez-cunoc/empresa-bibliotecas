use empresa_bibliotecas;

CREATE TABLE usuario (
  codigo INTEGER PRIMARY KEY not null auto_increment,
  nombre varchar(50) not null,
  username varchar(45) not null,
  password varchar(45) not null,
  rol varchar(45) not null,
  email varchar(100)
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

-- INSERT INTO user (name, username, password, rol, email) VALUES
-- ('Chris', 'chris07', '12345', 'admin', 'chris@gmail.com'),
-- ('Marck', 'marchhk', 'pass', 'final', 'marchhk@gmail.com'),
-- ('Dani', 'mdani', 'twice', 'transportista', 'mdani@gmail.com'),
-- ('veronica', 'vero', '54321', 'secretario', 'vero@gmail.com'),
-- ('karol', 'karolg', '123', 'final', 'karolg@gmail.com');

-- INSERT INTO user_final (id, suscrito, anulado) VALUES (2, 0, 1), (5, 1, 0);

-- select password from user where username = 'chris07';

-- SELECT u.name, u.username, u.password, u.rol, u.email, f.suscrito, f.anulado
-- FROM user as u
-- INNER JOIN user_final as f
-- ON u.id = f.id;

-- insert into categoria (id, name, description) values
-- ('1', 'thriller', 'El thriller es un género literario y cinematográfico que se caracteriza por mantener una alta tensión emocional y una trama llena de giros inesperados.'),
-- ('2', 'divulgacion', 'objetivo es hacer llegar un mensaje con ideas, conceptos o hechos sobre un tema específico a personas no formadas en ese tema.');

-- insert into libro (isbn, name, autor, categoria) values
-- ('123', 'breve historia del tiempo', 'Stephen Hawking', '2'),
-- ('321', 'IT', 'Stephen King', '1');

-- select * from libro;

-- SELECT l.isbn, l.name, l.autor, c.name
-- FROM libro as l
-- INNER JOIN categoria as c
-- ON l.categoria = c.id;

-- insert into biblioteca (name, direccion) values
-- ('bartolome', 'xela'),
-- ('municipal', 'antigua');

-- ('veronica', 'vero', '54321', 'secretario', 'vero@gmail.com')
-- insert into secretario (id, biblioteca) values ('4', '1');

-- SELECT * from secretario;

-- SELECT us.name, us.username, us.password, us.rol, us.email, bib.name
-- FROM secretario as sec
-- INNER JOIN user as us
-- ON sec.id = us.id
-- INNER JOIN biblioteca as bib;

-- SELECT u.name, u.username, b.name FROM secretario as s
--   INNER JOIN user as u
--     ON u.id = s.id
--   INNER JOIN biblioteca as b
--     ON s.biblioteca = b.id;


-- INSERT INTO unidades_libro (biblioteca_id, isbn, unidades) values
--   (1, 123, 10),
--   (1, 321, 7),
--   (2, 123, 5);
--  
-- update unidades_libro SET unidades = 45 where (isbn = 123 AND biblioteca_id = 1);
--  
-- SELECT b.name, l.name, ul.unidades from unidades_libro as ul
--   INNER JOIN libro as l
--     ON l.isbn = ul.isbn
--   INNER JOIN biblioteca as b
--     ON b.id = ul.biblioteca_id;