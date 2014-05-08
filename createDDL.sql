CREATE TABLE BEM (cd_bem_id INTEGER AUTO_INCREMENT NOT NULL, descricao_bem VARCHAR(255) NOT NULL, cd_dispositivo_id INTEGER, PRIMARY KEY (cd_bem_id))
CREATE TABLE DISPOSITIVO (cd_dispositivo_id INTEGER AUTO_INCREMENT NOT NULL, DS_COORDENADAS VARCHAR(255), DS_DISPOSITIVO VARCHAR(255), DT_ULTIMA_CONSULTA DATETIME, DT_ULTIMA_ONLINE DATETIME, FIRMWARE VARCHAR(255), MARCA VARCHAR(255), ONLINE TINYINT(1) default 0, PRIMARY KEY (cd_dispositivo_id))
ALTER TABLE BEM ADD CONSTRAINT FK_BEM_cd_dispositivo_id FOREIGN KEY (cd_dispositivo_id) REFERENCES DISPOSITIVO (cd_dispositivo_id)