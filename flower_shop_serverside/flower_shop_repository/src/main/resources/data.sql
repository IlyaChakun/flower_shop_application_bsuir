# /* roles */
# insert ignore into roles(id, name, display_name, is_displayed)
# values (1, 'ROLE_DRIVER', 'Водитель', 0);/*Не показывается в общей регистрации! */
# insert ignore into roles(id, name, display_name, is_displayed)
# values (2, 'ROLE_CLIENT', 'Клиент', 1);
# insert ignore into roles(id, name, display_name, is_displayed)
# values (3, 'ROLE_CARRIER', 'Перевозчик', 1);
# insert ignore into roles(id, name, display_name, is_displayed)
# values (4, 'ROLE_STOCK_OWNER', 'Владелец склада', 1);
# /************/
#
#
/************/
/* Типы цветов */
insert ignore into flower_types(id, date_of_creation, date_of_last_update, flower_type)
VALUES (1, now(), now(), 'Розы');
insert ignore into flower_types(id, date_of_creation, date_of_last_update, flower_type)
VALUES (2, now(), now(), 'Кустовые розы');
insert ignore into flower_types(id, date_of_creation, date_of_last_update, flower_type)
VALUES (3, now(), now(), 'Хризантемы');
insert ignore into flower_types(id, date_of_creation, date_of_last_update, flower_type)
VALUES (2, now(), now(), 'Гвоздики');
insert ignore into flower_types(id, date_of_creation, date_of_last_update, flower_type)
VALUES (2, now(), now(), 'Тюльпаны');
insert ignore into flower_types(id, date_of_creation, date_of_last_update, flower_type)
VALUES (2, now(), now(), 'Лилии');
insert ignore into flower_types(id, date_of_creation, date_of_last_update, flower_type)
VALUES (2, now(), now(), 'Альстромерии');
/************/
