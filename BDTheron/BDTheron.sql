CREATE DATABASE BDTheron;

USE BDTheron;


CREATE TABLE Productos
(PNombre VARCHAR(30), 
Cantidad INT,
Precio DOUBLE,
No_Orden INT,
Imagen LONGBLOB,
CONSTRAINT pd_pk PRIMARY KEY (PNombre));

CREATE TABLE Combo
(CNombre VARCHAR(25),
Descuento DOUBLE,
Precio DOUBLE,
Imagen LONGBLOB,
CONSTRAINT cbo_pk PRIMARY KEY (CNombre));

CREATE TABLE Detalle_Combo
(CNombre VARCHAR(25),
PNombre VARCHAR(30),
CONSTRAINT dt_cbo_cbo_fk FOREIGN KEY (CNombre) REFERENCES Combo(CNombre),
CONSTRAINT dt_cbo_pd_fk FOREIGN KEY (PNombre) REFERENCES Productos(PNombre));

CREATE TABLE Factura
(Factura_id INT AUTO_INCREMENT,
Fecha DATE,
Total DOUBLE,
CONSTRAINT fct_pk PRIMARY KEY (Factura_id));

CREATE TABLE Detalle_Factura
(Factura_id INT,
PNombre VARCHAR(30),
CONSTRAINT dt_fct_fct_fk FOREIGN KEY (Factura_id) REFERENCES Factura(Factura_id),
CONSTRAINT dt_fct_pd_fk FOREIGN KEY (PNombre) REFERENCES Productos(PNombre));

CREATE TABLE Usuario
(Usuario VARCHAR(10),
Constrasena VARCHAR(10),
Tipo_Usuario VARCHAR(10),
Nombre VARCHAR(25),
Telefono VARCHAR(13),
Fecha_Registro DATE,
CONSTRAINT usr_pk PRIMARY KEY (Usuario));

