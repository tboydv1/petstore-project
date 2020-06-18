CREATE USER IF NOT EXISTS `petuser`@`localhost` IDENTIFIED BY 'pet_user1';
GRANT ALL PRIVILEGES ON petstore_db.* to `petuser`@`localhost`;
FLUSH PRIVILEGES;

CREATE  DATABASE IF NOT EXISTS petstore_db;