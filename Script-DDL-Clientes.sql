CREATE TABLE T_PESSOA (
    ID BIGINT NOT NULL AUTO_INCREMENT,
    NOME VARCHAR(100) NOT NULL,
    CPF VARCHAR(11) NOT NULL,
    UNIQUE KEY (CPF),
    PRIMARY KEY (ID)
);

CREATE TABLE T_CLIENTE (
    ID BIGINT NOT NULL AUTO_INCREMENT,
    PESSOA_ID BIGINT NOT NULL,
    SENHA VARCHAR(10) NOT NULL,
    PRIMARY KEY (ID),
    UNIQUE KEY (PESSOA_ID),
    FOREIGN KEY (PESSOA_ID) REFERENCES T_PESSOA (ID)
);

CREATE TABLE T_CONTATO (
    ID BIGINT NOT NULL AUTO_INCREMENT,
    PESSOA_ID BIGINT NOT NULL,
    CLIENTE_ID BIGINT NOT NULL,
    PRIMARY KEY (ID),
    UNIQUE KEY (PESSOA_ID, CLIENTE_ID),
    FOREIGN KEY (ID) REFERENCES T_PESSOA (ID),
    FOREIGN KEY (CLIENTE_ID) REFERENCES T_PESSOA (ID)
);

CREATE TABLE T_TELEFONE (
    ID BIGINT NOT NULL AUTO_INCREMENT,
    TIPO VARCHAR(20) NOT NULL,
    NUMERO VARCHAR(15) NOT NULL,
    PESSOA_ID BIGINT NOT NULL,
    PRIMARY KEY (ID),
    FOREIGN KEY (PESSOA_ID) REFERENCES T_PESSOA (ID)
);

CREATE TABLE T_ENDERECO (
    ID BIGINT NOT NULL AUTO_INCREMENT,
    PESSOA_ID BIGINT NOT NULL,
    LOGRADOURO VARCHAR(200) NOT NULL,
    NUMERO VARCHAR(8) NOT NULL,
    BAIRRO VARCHAR(100) NOT NULL,
    CEP VARCHAR(8) NOT NULL,
    CIDADE VARCHAR(50) NOT NULL,
    ESTADO VARCHAR(20) NOT NULL,
    PRIMARY KEY (ID),
    FOREIGN KEY (PESSOA_ID) REFERENCES T_PESSOA (ID)
);