CREATE TABLE TransactionTypes
( id integer
, description varchar(100)
);

ALTER TABLE TransactionTypes
 ADD CONSTRAINT TRAN_TYPE_PKEY PRIMARY KEY
  ( id
  )
;


INSERT INTO TransactionTypes VALUES(1,'Normal Wash');
INSERT INTO TransactionTypes VALUES(2,'Full Wash');
INSERT INTO TransactionTypes VALUES(3,'Premium Wash');
INSERT INTO TransactionTypes VALUES(4,'Wash and Go');
INSERT INTO TransactionTypes VALUES(5,'Full Wash + Polish');
INSERT INTO TransactionTypes VALUES(6,'Normal Wash + Polish');
INSERT INTO TransactionTypes VALUES(7,'Premium Wash + Polish');
INSERT INTO TransactionTypes VALUES(8,'Wash and Go + Dry');
INSERT INTO TransactionTypes VALUES(9,'Full Valet');
INSERT INTO TransactionTypes VALUES(10,'Full Valet + Polish');

