USE mysql;

-- DATABASE
DROP DATABASE IF EXISTS db_dogs_test_api_rest_java_servlet;
CREATE DATABASE db_dogs_test_api_rest_java_servlet;

USE db_dogs_test_api_rest_java_servlet;

-- TABLE
DROP TABLE IF EXISTS size_dog;
CREATE TABLE size_dog(
    id INT AUTO_INCREMENT,
    _size_dog VARCHAR(7),
    PRIMARY KEY (id)
);
DROP TABLE IF EXISTS race_dog;
CREATE TABLE race_dog(
    id INT AUTO_INCREMENT,
    _race_dog VARCHAR(20),
    PRIMARY KEY (id)
);
DROP TABLE IF EXISTS dog;
CREATE TABLE dog(
    id INT AUTO_INCREMENT,
    _name VARCHAR(50) NOT NULL,
    weight DECIMAL(4,2) NOT NULL,
    id_race INT NOT NULL,
    id_size INT NOT NULL,
    owner VARCHAR(100),
    description TEXT, 
    carried BIT DEFAULT 0, 
    PRIMARY KEY (id),
    FOREIGN KEY (id_race) REFERENCES race_dog(id),
    FOREIGN KEY (id_size) REFERENCES size_dog(id)
);


-- INSERTS
INSERT INTO size_dog(_size_dog) VALUES 
    ("Pequeño"), ("Mediano"), ("Grande");

INSERT INTO race_dog(_race_dog) VALUES 
    ("Salchicha"), ("Bulldog inglés"), ("Normal"), 
    ("Pitbull - Perro asesino"), ("San Bernardo");

INSERT INTO dog(_name, weight, id_race, id_size, owner, description) VALUES
    ("Nerón", 48, 2, 2, "Manuel Rivera", "Es un perro gordito que le gusta comer y dormir mucho."),
    ("Cesar", 20.5, 5, 1, "Maycol Soto" , "Es un cachorrito equisde."), 
    ("Perro 2", 30.85, 4, 2, "Joseph Joestar" , "No sé... Un perro asesino de esos."), 
    ("Jake", 38.2, 2, 2, "Finn The Human" , "Es un perro parlante y mutante que se comió la gomu gomu no mi :u.");



-- STORED PROCEDURES (PROCEDIMIENTOS ALMACENADOS)
DROP PROCEDURE IF EXISTS sp_get_dogs;
DELIMITER //
CREATE PROCEDURE sp_get_dogs(
    IN __get_all BIT
) 
BEGIN
    SELECT  
	d.id AS 'ID', 
	d._name AS 'NAME', 
        d.weight AS 'WEIGHT',
        d.owner AS 'OWNER',
        d.description AS 'DESCRIPTION',
        rad.id AS 'ID_RACE_DOG',
        rad._race_dog AS 'RACE_DOG',
        sid.id AS 'ID_SIZE_DOG',
        sid._size_dog AS 'SIZE_DOG',
        d.carried AS 'CARRIED'
    FROM dog d
    INNER JOIN race_dog rad ON d.id_race = rad.id 
    INNER JOIN size_dog sid ON d.id_size = sid.id
    WHERE __get_all = 1 OR (__get_all = 0 AND d.carried = 0)
    ORDER BY id;
END
// 


DROP PROCEDURE IF EXISTS sp_find_dog;
DELIMITER //
CREATE PROCEDURE sp_find_dog(
    IN __id INT
) 
BEGIN
    SELECT  
	d.id AS 'ID', 
	d._name AS 'NAME', 
        d.weight AS 'WEIGHT',
        d.owner AS 'OWNER',
        d.description AS 'DESCRIPTION',
        rad.id AS 'ID_RACE_DOG',
        rad._race_dog AS 'RACE_DOG',
        sid.id AS 'ID_SIZE_DOG',
        sid._size_dog AS 'SIZE_DOG',
        d.carried AS 'CARRIED'
    FROM dog d
    INNER JOIN race_dog rad ON d.id_race = rad.id 
    INNER JOIN size_dog sid ON d.id_size = sid.id
    WHERE d.id = __id AND d.carried = 0
    ORDER BY id;
END
// 

DROP PROCEDURE IF EXISTS sp_add_dog;
DELIMITER //
CREATE PROCEDURE sp_add_dog(
    IN __name VARCHAR(50),
    IN __weight DECIMAL(4,2),
    IN __id_race INT,
    IN __id_size INT,
    IN __owner VARCHAR(100),
    IN __description TEXT
) 
BEGIN
    INSERT INTO dog(_name, weight, id_race, id_size, owner, description) VALUES
        (__name, __weight, __id_race, __id_size, __owner, __description);
    SELECT 'SUCCESS' AS 'RES';
END
// 

DROP PROCEDURE IF EXISTS sp_update_dog;
DELIMITER //
CREATE PROCEDURE sp_update_dog(
    IN __id INT, 
    IN __name VARCHAR(50),
    IN __weight DECIMAL(4,2),
    IN __id_race INT,
    IN __id_size INT,
    IN __owner VARCHAR(100),
    IN __description TEXT
) 
BEGIN
    UPDATE dog 
    SET 
        _name = CASE WHEN __name IS NOT NULL THEN __name ELSE _name END, 
        weight = CASE WHEN __weight IS NOT NULL THEN __weight ELSE weight END,
        id_race = CASE WHEN __id_race IS NOT NULL THEN __id_race ELSE id_race END,
        id_size = CASE WHEN __id_size IS NOT NULL THEN __id_size ELSE id_size END,
        owner = CASE WHEN __owner IS NOT NULL THEN __owner ELSE owner END,
        description = CASE WHEN __description IS NOT NULL THEN __description ELSE description END
    WHERE id = __id;
    SELECT 'SUCCESS' AS 'RES';
END
// 

DROP PROCEDURE IF EXISTS sp_carry_dog;
DELIMITER //
CREATE PROCEDURE sp_carry_dog(
    IN __id INT
) 
BEGIN
	DECLARE var_id INT;
	SET var_id = (SELECT id FROM dog WHERE id = __id AND carried = 0);
    	IF var_id IS NOT NULL THEN
		UPDATE dog SET carried = 1 WHERE id = __id;
    	END IF;
    	SELECT IF (var_id IS NOT NULL, 'SUCCESS', 'ERROR') AS 'RES';
END
// 

/*
How would be in prepared queries?

CALL sp_get_dogs(?);
CALL sp_get_dog(?);
CALL sp_add_dog(?, ?, ?, ?, ?, ?);
CALL sp_update_dog(?, ?, ?, ?, ?, ?, ?);
CALL sp_carry_dog(?);

-- TRYING OUT
CALL sp_get_dogs(1);
CALL sp_add_dog("MAYCOL", 10.3, 1, 1, "LA TÍA DE MAYCOL", "Es un perro muy perro que se llama como el sobrino de la doña.");
CALL sp_update_dog(NULL, NULL, NULL, NULL, NULL, "GAAAAAAAAA...");
CALL sp_get_dogs(1);
*/
