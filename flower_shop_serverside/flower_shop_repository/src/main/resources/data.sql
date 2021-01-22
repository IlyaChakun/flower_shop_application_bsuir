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
/************/

-- insert ignore into users(id, date_of_creation, date_of_last_update, email, is_mail_confirmed, name, password,
--                          phone_number, provider)
-- VALUES (0, now(), now(), 'admin@mail.ru', true, 'Store admin',
--         '$2a$10$s3JeXzkxv2D5BCS5VqMPi.5ZpXYN60ICvH0.yPRXgbPy/oIp3r.AS', '375295555555', 'local');#pass 11111
--
-- insert ignore into shop_admins(id)
-- values (0);
-- insert ignore into companies(id, date_of_creation, date_of_last_update, bank_address, bank_code, bank_name, postal_code,
--                              checking_account, payer_account_number, address, city, email, first_phone_number,
--                              second_phone_number, description, licence_number, name, shop_admin_id)
-- values (0, now(), now(), '', '', '', '', '', '', '', '', '', '', '', '', '', '', 0);
--
-- update shop_admins
-- set company_id = 0
-- where id = 0;
--
-- insert ignore into users_roles(user_id, role_id)
-- VALUES (0, 1);
--
-- /************/
-- /* Типы цветов */
-- insert ignore into flower_types(id, flower_type)
-- VALUES (1, 'Розы');
-- insert ignore into flower_types(id, flower_type)
-- VALUES (2, 'Кустовые розы');
-- insert ignore into flower_types(id, flower_type)
-- VALUES (3, 'Хризантемы');
-- insert ignore into flower_types(id, flower_type)
-- VALUES (4, 'Гвоздики');
-- insert ignore into flower_types(id, flower_type)
-- VALUES (5, 'Тюльпаны');
-- insert ignore into flower_types(id, flower_type)
-- VALUES (6, 'Лилии');
-- insert ignore into flower_types(id, flower_type)
-- VALUES (7, 'Альстромерии');
-- /************/
--
-- # //TODO  в базу хардкодом пишем все цвета, все страны, сорта
--
-- insert ignore into colors(id, color_name)
-- VALUES (1, 'Красный');
-- insert ignore into colors(id, color_name)
-- VALUES (2, 'Кремовый');
-- insert ignore into colors(id, color_name)
-- VALUES (3, 'Желтый');
-- insert ignore into colors(id, color_name)
-- VALUES (4, 'Оранжевый');
-- insert ignore into colors(id, color_name)
-- VALUES (5, 'Белый');
-- insert ignore into colors(id, color_name)
-- VALUES (6, 'Розовый');
-- insert ignore into colors(id, color_name)
-- VALUES (7, 'Фиолетовый');
--
--
-- insert ignore into sorts(id, sort_name_en, sort_name_ru)
-- VALUES (1, 'Avalanche', 'Аваланш');
-- insert ignore into sorts(id, sort_name_en, sort_name_ru)
-- VALUES (2, 'Avalanche Peach', 'Аваланш Пич');
-- insert ignore into sorts(id, sort_name_en, sort_name_ru)
-- VALUES (3, 'Red Naomi', 'Красная Наоми');
--
--


-- /** countries **/
insert ignore into countries(id, country_name_en, country_name_ru)
VALUES (1, 'Belarus', 'Беларусь'),
       (2, 'Russia', 'Россия');

-- /** cities **/
insert ignore into cities(id, city_name)
VALUES (1, 'Минск'),
       (2, 'Брест');

--
-- /** типы букетов**/
-- insert ignore into bouquet_types(id, bouquet_type)
-- values (1, 'Букет из роз');