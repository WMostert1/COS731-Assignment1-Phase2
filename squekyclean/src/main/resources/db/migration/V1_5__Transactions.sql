CREATE TABLE Transactions
( id serial,
  transactionTypeId integer,
  carId integer,
  description varchar(100),
  tmstamp timestamp

);

ALTER TABLE Transactions
 ADD CONSTRAINT TRANS_PKEY PRIMARY KEY
  ( id
  )
;


ALTER TABLE Transactions
 ADD CONSTRAINT CAR_FK FOREIGN KEY
  (
  carId
  ) REFERENCES Cars MATCH FULL
;