-- +----------------------------------------------------------------------+
--  Jo�o Marcos Torres Lins Marques
--  Joaomarcos1@outlook.com
--
--  Controle de receitas e despesas.
-- +----------------------------------------------------------------------+

-- +----TRUNCATE AND DROP---------+
DROP TABLE MOVIMENTACAO
DROP TABLE CONTA
DROP TABLE ENDERECO
DROP TABLE PESSOA


DROP SEQUENCE SEQ_IDPESSOA;
DROP SEQUENCE SEQ_CODEND;
DROP SEQUENCE SEQ_CODMOV;

-- +----CRIA��O DAS TABELAS---------+
CREATE TABLE PESSOA(
  DTCADASTRO DATE NOT NULL,
  ID INT NOT NULL UNIQUE,
  NOME VARCHAR(255),
  RAZAOSOCIAL VARCHAR(255),
  TIPO CHAR(1),
  CGC_CPF VARCHAR(50),
  RG VARCHAR(20),
  IE VARCHAR(20),
  CONSTRAINT PK_PESSOA PRIMARY KEY (ID)
);


CREATE TABLE CONTA(
  SALDO FLOAT DEFAULT 0,
  LIMITE FLOAT DEFAULT 0,
  NUMEROCONTA INT NOT NULL,
  AGENCIA INT NOT NULL,
  IDPESSOA INT,
  CONSTRAINT PK_CONTA PRIMARY KEY (NUMEROCONTA,AGENCIA),
  CONSTRAINT FK_ID_PESSOA FOREIGN KEY (IDPESSOA) REFERENCES PESSOA(ID)
);


CREATE TABLE MOVIMENTACAO(
  DHMOV DATE,
  CODMOV INT NOT NULL,
  IDPESSOA INT NOT NULL,
  NUMEROCONTA INT NOT NULL,
  AGENCIA INT NOT NULL,
  TIPO CHAR NOT NULL,
  VALOR DECIMAL(5,2) NOT NULL,
  CONSTRAINT PK_MOVIMENTACAO PRIMARY KEY (CODMOV)
);


CREATE TABLE ENDERECO(
  CODEND INT NOT NULL,
  RUA VARCHAR(100) NOT NULL,
  NUMERO VARCHAR(20) NOT NULL,
  COMPLEMENTO VARCHAR(100),
  BAIRRO VARCHAR(50),
  CIDADE VARCHAR(50),
  UF VARCHAR(2),
  CEP VARCHAR(20),
  IDPESSOA INT NOT NULL,
  CONSTRAINT PK_ENDERECO PRIMARY KEY (CODEND),
  CONSTRAINT FK_ENDERECO_IDPESSOA FOREIGN KEY (IDPESSOA) REFERENCES PESSOA(ID)
);

-- +----CRIA��O DAS SEQUENCES---------+
CREATE SEQUENCE SEQ_IDPESSOA START WITH 1;
CREATE SEQUENCE SEQ_CODEND START WITH 1;
CREATE SEQUENCE SEQ_CODMOV START WITH 1;

-- +----OBSERVA��ES IMPORTANTES---------+
COMMENT ON COLUMN MOVIMENTACAO.IDPESSOA IS 'FK TABLE PESSOA';

-- +----TRIGGER: AUTOINCREMENT DA COLUNA ID DA TABELA PESSOA---------+
CREATE OR REPLACE TRIGGER UPD_TBLPESSOA_ID
  before insert on PESSOA
  for each row
begin
  if :NEW.ID is null then
    select SEQ_IDPESSOA.nextval into :NEW.ID from dual;
  end if;
end;

-- +----TRIGGER: IMPEDIR A EXCLUS�O DE UMA CONTA SE HOUVER MOVIMENTA��O VINCULADA A ELA---------+
CREATE OR REPLACE TRIGGER DEL_CONTA
  before delete on CONTA
  for each ROW
DECLARE v_count INTEGER;
BEGIN
  SELECT Count(*)
    INTO v_count
  FROM MOVIMENTACAO
  WHERE NUMEROCONTA = :OLD.NUMEROCONTA AND AGENCIA = :OLD.AGENCIA;

  IF v_count >0 THEN
    Raise_Application_Error(-20999,'Opera��o n�o permitida. Esta conta possui movimenta��o.');
  END IF;

end;

-- +----TRIGGER: AUTOINCREMENT DA COLUNA CODMOV DA TABELA MOVIMENTACAO---------+
CREATE OR REPLACE TRIGGER UPD_TBLMOVIMENTACAO_CODMOV
  before insert on MOVIMENTACAO
  for each row
begin
  if :NEW.CODMOV is null then
    select SEQ_CODMOV.nextval into :NEW.CODMOV from dual;
  end if;
end;

-- +----TRIGGER: ATUALIZA A COLUNA SALDO DA TABELA CONTA QUANDO EXISTIR MOVIMENTACAO---------+
CREATE OR REPLACE TRIGGER INS_MOVIMENTACAO
  before insert on MOVIMENTACAO
  for each row
BEGIN
   IF :NEW.TIPO ='C' THEN
    UPDATE CONTA SET SALDO =+ :NEW.VALOR
    WHERE IDPESSOA = :NEW.IDPESSOA
    AND NUMEROCONTA = :NEW.NUMEROCONTA
    AND AGENCIA = :NEW.AGENCIA;
   ELSE
    UPDATE CONTA SET SALDO =- :NEW.VALOR
    WHERE IDPESSOA = :NEW.IDPESSOA
    AND NUMEROCONTA = :NEW.NUMEROCONTA
    AND AGENCIA = :NEW.AGENCIA;
  END IF;
end;

-- +----TRIGGER: AUTOINCREMENT DA COLUNA CODEND DA TABELA ENDERECO---------+
CREATE OR REPLACE TRIGGER INS_ENDERECO_CODEND
  before insert on ENDERECO
  for each row
begin
  if :NEW.CODEND is null then
    select SEQ_CODEND.nextval into :NEW.CODEND from dual;
  end if;
end;