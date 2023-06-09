DROP TABLE IF EXISTS DOMICILIOS;
CREATE TABLE DOMICILIOS (ID INT AUTO_INCREMENT PRIMARY KEY,
CALLE VARCHAR(100),
NUMERO VARCHAR(20),
LOCALIDAD VARCHAR(20),
PROVINCIA VARCHAR(20));
DROP TABLE IF EXISTS PACIENTES;
CREATE TABLE PACIENTES (ID INT AUTO_INCREMENT PRIMARY KEY,
APELLIDO VARCHAR(100),
NOMBRE VARCHAR(100),
DOCUMENTO VARCHAR(100),
FECHA_INGRESO DATE,
DOMICILIO_ID INT,
EMAIL VARCHAR(100));
DROP TABLE IF EXISTS ODONTOLOGOS;
CREATE TABLE ODONTOLOGOS (ID INT AUTO_INCREMENT PRIMARY KEY,
APELLIDO VARCHAR(100),
NOMBRE VARCHAR(100),
MATRICULA VARCHAR(100));

INSERT INTO DOMICILIOS (CALLE, NUMERO, LOCALIDAD, PROVINCIA) VALUES ('Av Santo Domigo', '02','Valle del Cauca', 'Jamundi');
INSERT INTO PACIENTES (APELLIDO, NOMBRE, DOCUMENTO, FECHA_INGRESO, DOMICILIO_ID, EMAIL) VALUES (
'Zapata', 'Yohana', '1130681593', '2023-03-13', 1, 'yohaskj22@gmail.com');
INSERT INTO ODONTOLOGOS (APELLIDO, NOMBRE, MATRICULA) VALUES ('Ortiz',';Mirian','48545');