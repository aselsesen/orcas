
-- creates the user named 'orca_admin'.
CREATE USER 'orca_admin'@'localhost' IDENTIFIED BY 'Orca_123';

--  gives all the privileges to the new user on the newly created database.
GRANT CREATE, SELECT, INSERT, DELETE, DROP, ALTER, CREATE TEMPORARY TABLES, EXECUTE, UPDATE, LOCK TABLES ON `orca`.* TO 'orca_admin'@'localhost';

/* About user 'orca_admin':
 * this user has no global privileges but has some rights on 'orca' database
 * such as:
 *     Create, Select, Insert, Delete, Drop, Alter, Create Temporary Table, Execute, Update, Lock Tables
*/

-- creates the user named 'orca_admin'.
CREATE USER 'orca_user'@'localhost' IDENTIFIED BY 'Orca_456';

--  gives all the privileges to the new user on the newly created database.
GRANT SELECT, INSERT, DELETE, UPDATE, EXECUTE ON `orca`.* TO 'orca_user'@'localhost';

/* About user 'orca_user':
 * this user has no global privileges but has some rights on 'orca' database
 * such as:
 *     Select, Insert, Delete, Update, Execute
*/