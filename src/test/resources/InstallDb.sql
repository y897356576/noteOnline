/*
常用字段类型:
VARCHAR(50)
INTEGER
BIGINT
DATE DEFAULT CURRENT_DATE
*/

/*建立表USER*/
CREATE MEMORY TABLE USER (
  ID   VARCHAR(50) NOT NULL PRIMARY KEY,
  USERNAME VARCHAR(100) DEFAULT '' NOT NULL,
  PASSWORD VARCHAR(50) DEFAULT '' NOT NULL,
  STATUS INTEGER DEFAULT 1 NOT NULL,
  REGISTERTIME DATE,
  REMARK VARCHAR(100) DEFAULT ''
);


SET SCHEMA PUBLIC;


--插入USER
INSERT INTO USER(ID, USERNAME, PASSWORD, STATUS, REGISTERTIME, REMARK )
  VALUES('1', '魅影', null, 1, CURRENT_DATE, '' );


