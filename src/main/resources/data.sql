INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_MODERATOR');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');

 INSERT INTO users (id, email , password, username, first_acess) VALUES (1, "user@user.com", "$2a$10$DUkVRxFFiR7L2OpwwpxHyOCC.7v/iFLqp66BBqy3AC0lHrVa9lnQK", "user", false )
 -- login -> user e senha -> role_User!

--  INSERT INTO user_roles (user_id, role_id) VALUES (1, 1)