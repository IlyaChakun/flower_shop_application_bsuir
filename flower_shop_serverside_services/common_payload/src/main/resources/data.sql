/* roles */
insert ignore into user_roles(id, name, display_name, is_displayed)
values (1, 'ROLE_ADMIN', 'Администратор', 0);/*Не показывается в общей регистрации! */
insert ignore into user_roles(id, name, display_name, is_displayed)
values (2, 'ROLE_CLIENT', 'Клиент', 1);
insert ignore into user_roles(id, name, display_name, is_displayed)
values (3, 'ROLE_ANONYMOUS', 'Аноним', 0);
insert ignore into user_roles(id, name, display_name, is_displayed)
values (4, 'ROLE_FLORIST', 'Флорист', 0);


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

-- /** countries **/
insert ignore into countries(id, country_name_en, country_name_ru)
VALUES (1, 'Belarus', 'Беларусь'),
       (2, 'Russia', 'Россия');

-- /** cities **/
insert ignore into cities(id, city_name)
VALUES (1, 'Минск'),
       (2, 'Брест');

-- /** delivery_types **/
insert ignore into delivery_types(id, delivery_type_name)
VALUES (1, 'Самовывоз'),
       (2, 'Доставка курьером');

--
-- /** типы букетов**/
-- insert ignore into bouquet_types(id, bouquet_type)
-- values (1, 'Букет из роз');