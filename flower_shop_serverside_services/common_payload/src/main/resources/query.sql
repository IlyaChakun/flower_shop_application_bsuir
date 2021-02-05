-- /* create table */
-- CREATE TABLE category
-- (
--     category_id INT AUTO_INCREMENT PRIMARY KEY,
--     name        VARCHAR(20) NOT NULL,
--     parent      INT DEFAULT NULL
-- );
--
-- INSERT INTO categories(id, name, parent_id)
-- VALUES (1, 'ELECTRONICS', NULL),
--        (2, 'TELEVISIONS', 1),
--        (3, 'TUBE', 2),
--        (4, 'LCD', 2),
--        (5, 'PLASMA', 2),
--        (6, 'PORTABLE ELECTRONICS', 1),
--        (7, 'MP3 PLAYERS', 6),
--        (8, 'FLASH', 7),
--        (9, 'CD PLAYERS', 6),
--        (10, '2 WAY RADIOS', 6);
--
-- SELECT *
-- FROM categories
-- ORDER BY id;
--
--
-- /* get full tree */
--
-- SELECT t1.name AS lev1, t2.name as lev2, t3.name as lev3, t4.name as lev4
-- FROM categories AS t1
--          LEFT JOIN categories AS t2 ON t2.parent_id = t1.id
--          LEFT JOIN categories AS t3 ON t3.parent_id = t2.id
--          LEFT JOIN categories AS t4 ON t4.parent_id = t3.id
-- WHERE t1.name = 'Цветы';
--
-- /*Retrieving a Single Path*/
--
--
-- SELECT t1.name AS lev1, t2.name as lev2, t3.name as lev3, t4.name as lev4
-- FROM categories AS t1
--          LEFT JOIN categories AS t2 ON t2.parent_id = t1.id
--          LEFT JOIN categories AS t3 ON t3.parent_id = t2.id
--          LEFT JOIN categories AS t4 ON t4.parent_id = t3.id
-- WHERE t1.name = 'ELECTRONICS'
--   AND t4.name = 'FLASH';
--
--
-- /* get single packet*/
-- select *
-- from categories as c
-- where c.parent_id = 2;