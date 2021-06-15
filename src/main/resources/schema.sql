CREATE SEQUENCE IF NOT EXISTS ID_PHONE_SEQ START WITH 1 INCREMENT BY 1 MINVALUE 1;
CREATE TABLE IF NOT EXISTS USERS (
id_user VARCHAR2(400 BYTE) PRIMARY KEY NOT NULL,
name VARCHAR2(400 BYTE),
email VARCHAR2(400 BYTE),
password VARCHAR2(400 BYTE),
create_at DATETIME,
modify_at DATETIME,
last_login DATETIME,
is_active BIT,
token VARCHAR2(400 BYTE));

CREATE TABLE IF NOT EXISTS phones (
id BIGINT PRIMARY KEY NOT NULL,
number_phone VARCHAR2(400 BYTE),
city_code VARCHAR2(400 BYTE),
country_code VARCHAR2(400 BYTE),
id_user VARCHAR2(400 BYTE),
 FOREIGN KEY (id_user) REFERENCES USERS(id_user));





--INSERT INTO users(id_user,name,email,password,create_at,modify_at,last_login,is_active,token) VALUES(uuid(),'juan rodriguez','juan@rodriguez.org','hunter2',SYSDATE,SYSDATE,SYSDATE,false,'token');

--INSERT INTO phones(id,number_phone,city_code,country_code) VALUES(1,'1234567','1','57');
