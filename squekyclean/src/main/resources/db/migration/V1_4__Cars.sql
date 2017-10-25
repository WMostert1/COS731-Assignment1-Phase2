CREATE TABLE Cars
( id serial,
  userId integer NULL,
 registrationNumber varchar(60)
);

ALTER TABLE Cars
 ADD CONSTRAINT CARS_PKEY PRIMARY KEY
  ( id
  )
;


ALTER TABLE Cars
 ADD CONSTRAINT CARS_UKEY_REG UNIQUE
  ( registrationNumber
  )
;