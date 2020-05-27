--
-- USERS
--
delete from authorities;
delete from users where username in ('admin', 'guest');


-- INSERT INTO users ( username, password, enabled ) VALUES ( 'admin', 'admmin', 1 );
-- INSERT INTO users ( username, password, enabled ) VALUES ( 'guest', 'guest', 1 );
--
-- USERS-SHA
--
INSERT INTO users ( username, password, enabled ) VALUES ( 'admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 1 );
INSERT INTO users ( username, password, enabled ) VALUES ( 'guest', '84983c60f7daadc1cb8698621f802c0d9f9a3c3c295c810748fb048115c186ec', 1 );
--
-- USERS-SHA
--
-- INSERT INTO users ( username, password, enabled ) VALUES ( 'admin', SHA2('admmin', 256), 1 );
-- INSERT INTO users ( username, password, enabled ) VALUES ( 'guest', SHA2('guest', 256), 1 );
--
-- AUTHORITY
--
INSERT INTO authorities ( username, authority ) VALUES ( 'admin', 'ADMIN' );
INSERT INTO authorities ( username, authority ) VALUES ( 'admin', 'USER' );
INSERT INTO authorities ( username, authority ) VALUES ( 'guest', 'USER' );