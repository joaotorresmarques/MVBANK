## CRUD JDBC IREPORT DAO JAVA

 **SOBRE:**
<p align="center">

<img src="https://github.com/joaotorresmarques/MVBANK/blob/main/src/main/java/assets/printdatela.jpg" width="50%" height="50%"/>

</p>

 
O projeto consiste na criação de um sistema de controle de receita e despesa em Java, utilizando JDBC comunicando-se com o banco ORACLE, com saída de relatorio no IREPORT.

**CRUD:** (Create, Read, Update e Delete) são as quatro operações básicas utilizadas em bases de dados relacionais fornecidas aos utilizadores do sistema. Outros acrônimos podem ser usados para definir as mesmas operações 

**JDBC (Java Database Connectivity):** é um conjunto de classes e interfaces (API) escritas em Java que fazem o envio de instruções SQL para qualquer banco de dados relacional, como: MySQL, PostgreSQL, SQL Server, Oracle, DB2 IBM dentre outros.

**DAO:** é um padrão para aplicações que utilizam persistência de dados, onde tem a separação das regras de negócio das regras de acesso a banco de dados, implementada com linguagens de programação orientadas a objetos.

<p align="center">

<img src="https://github.com/joaotorresmarques/MVBANK/blob/main/src/main/java/assets/DAO.jpg" width="50%" height="50%"/>

</p>

**OBSERVAÇÕES:**

>Utilizar a clase de teste(src/teste/java) para validar o CRUD

>Na pasta lib, tem as bibliotecas para utilização do Ireport 5.6 e o JDBC do ORACLE(Você não consegue baixar as dependencias via maven da oracle, por politicas se segurança)

>Na clase main do projeto encontra-se a massa de dados necessaria para testes.

>A classe responsavel de geração do ireport é Relatorio, basta descomentar no main para gerar :)

>Na pasta Assets encontra-se o .sql responsavel para geração dos objetos de banco.

**ESTRUTURA SQL:**

```SQL
-- +----------------------------------------------------------------------+
--  João Marcos Torres Lins Marques
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

-- +----CRIAÇÃO DAS TABELAS---------+
CREATE TABLE PESSOA(
  DTCADASTRO DATE NOT NULL,
  ID INT NOT NULL,
  NOME VARCHAR(255),
  RAZAOSOCIAL VARCHAR(255),
  TIPO CHAR(1),
  CGC_CPF VARCHAR(50),
  RG VARCHAR(20),
  IE VARCHAR(20),
  CONSTRAINT PKk_PESSOA PRIMARY KEY (ID)
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

-- +----CRIAÇÃO DAS SEQUENCES---------+
CREATE SEQUENCE SEQ_IDPESSOA START WITH 1;
CREATE SEQUENCE SEQ_CODEND START WITH 1;
CREATE SEQUENCE SEQ_CODMOV START WITH 1;

-- +----OBSERVAÇÕES IMPORTANTES---------+
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

-- +----TRIGGER: IMPEDIR A EXCLUSÃO DE UMA CONTA SE HOUVER MOVIMENTAÇÃO VINCULADA A ELA---------+
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
    Raise_Application_Error(-20999,'Operação não permitida. Esta conta possui movimentação.');
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

```


**GERAÇÃO IREPORT**
<img src= "https://github.com/joaotorresmarques/MVBANK/blob/main/src/main/java/assets/ireport.jpg" 
     align="left" width="100%" height="100%">
 
 

