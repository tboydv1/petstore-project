use petstore_db;
SET FOREIGN_KEY_CHECKS = 0;




TRUNCATE TABLE `pet`;
TRUNCATE TABLE `store`;
TRUNCATE TABLE `store_pets`;


INSERT INTO pet(id, name, color, `pet_type`) 
VALUES(1, 'bob', 'blue', 'dog'),
(2, 'rick', 'red', 'cat'),
(3, 'blow', 'blue', 'dog'),
(4, 'nancy', 'blue', 'dog');





SET FOREIGN_KEY_CHECKS = 1;