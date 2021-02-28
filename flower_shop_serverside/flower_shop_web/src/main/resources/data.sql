insert ignore into user_roles(id, display_name, is_displayed, name)
values (1, 'Клиент', true, 'ROLE_CLIENT'),
       (2, 'Флорист', false, 'ROLE_FLORIST'),
       (3, 'Админ', false, 'ROLE_ADMIN');



/************/
/* categories */
INSERT IGNORE INTO categories(id, name, parent_id)
VALUES (1, 'Цветы', NULL),
       (2, 'Шары', NULL),
       (3, 'Подарки', NULL),
    /* цветы */
       (4, 'Готовые букеты', 1),
    /* Готовые вложенные букеты*/
       (5, 'с розами', 4),
       (6, 'с тюльпанами', 4),
       (7, 'с гвоздиками', 4),
    /**/
       (8, 'Цветы поштучно', 1),
    /* Цветы поштучно вложенные*/
       (9, 'Роза', 8),
       (10, 'Гвоздика', 8),
       (11, 'Тюльпан', 8),
    /**/
    /*************************/
    /* шары */
       (12, 'Прикольные', 2),
       (13, 'Гелиевые шары из латекса', 2),
       (14, 'Сеты из шаров', 2),
       (15, 'Фигуры из шаров', 2),

    /* подарки */
       (16, 'Подарочные сеты', 3);


INSERT IGNORE INTO categories_children(category_id, children_id)
VALUES
    /* цветы  */
    (1, 4),
    (1, 8),
    /* Готовые букеты*/
    (4, 5),
    (4, 6),
    (4, 7),
    /* */

    /* Цветы поштучно*/
    (8, 9),
    (8, 10),
    (8, 11),
    /* */

    /* шары */
    (2, 12),
    (2, 13),
    (2, 14),

    /* подарки */
    (2, 15),
    (3, 16);



INSERT IGNORE INTO companies(id, date_of_creation, date_of_last_update)
values (1, now(), now());


insert ignore into countries(id, country_name_ru, country_name_en)
VALUES (1, 'Австрия', 'Austria'),
       (2, 'Азербайджан', 'Azerbaijan'),
       (3, 'Армения', 'Armenia'),
       (4, 'Беларусь', 'Belarus'),
       (5, 'Бельгия', 'Belgium'),
       (6, 'Болгария', 'Bulgaria'),
       (7, 'Босния и Герцеговина', 'Bosnia and Herzegovina'),
       (8, 'Великобритания', 'UK'),
       (9, 'Венгрия', 'Hungary'),
       (10, 'Германия', 'Germany'),
       (11, 'Греция', 'Greece'),
       (12, 'Грузия', 'Georgia'),
       (13, 'Дания', 'Denmark'),
       (14, 'Индия', 'India'),
       (15, 'Иран', 'Iran'),
       (16, 'Ирландия', 'Ireland'),
       (17, 'Литва', 'Lithuania'),
       (18, 'Россия', 'Russia'),
       (19, 'Чехия', 'Czechia');